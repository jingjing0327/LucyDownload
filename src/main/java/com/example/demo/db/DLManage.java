package com.chazuo.college.enterprise.download;

import android.util.Log;

import com.chazuo.czlib.module.impl.CZController;

import java.util.List;
import java.util.UUID;

/**
 * Created by LiQiong on 2016/10/26.
 */

public final class DLManage {
    protected int threadCount;
    protected DLTask task;
    private DLDispatcher dispatcher;
    private DLClient client;
    private DBClient dbClient;

    private List<DBTaskPoint> dbTaskPoints;

    public DLManage(DLTask task) {
        this.task = task;
        dispatcher = new DLDispatcher();
        dbClient = DLQueue.getInstance().getDbClient();
    }

    /**
     *
     */
    public void execute() {
        //查询当前任务有没有断点
        String[] selectionArgs = new String[]{task.getBuilder().getNetUrl(), task.getBuilder().getName() + "%"};
        dbTaskPoints=dbClient.taskPointFind("netUrl=? and name like ?", selectionArgs);
        //初始化线程池下载器
        dispatcher.setCorePoolSize(getThreadCount()).build();
        task.getBuilder().setDispatcher(dispatcher);

        if (dbTaskPoints != null && dbTaskPoints.size() != 0) {
            task.getBuilder().setCurrentLength(0);
            for (DBTaskPoint dbTaskPoint : dbTaskPoints) {
                //需要计算一下上次下载的进度
                task.getBuilder().setCurrentLength(task.getBuilder().getCurrentLength() + (dbTaskPoint.getStartPoint() - dbTaskPoint.getFirstStartPoint()));
                task.getBuilder().setLength(dbTaskPoint.getLength());
                if (dbTaskPoint.getStartPoint() < dbTaskPoint.getEndPoint())
                    dispatcher.enqueue(new DLDownload(client, task, dbTaskPoint, dbTaskPoint.getStartPoint(), dbTaskPoint.getEndPoint()));
            }
        } else {
            //
            new DLTaskInfo(client, task).execute(new DLTaskInfo.NetCallBack() {
                @Override
                public void onSuccess() {
                    calcDCall();
                }

                @Override
                public void onFail() {
                    task.getBuilder().setTaskType(DLTaskType.FAIL);
                    CZController.uiHelp.toast("下载失败！");
                }
            });
        }
    }

    /**
     *
     */
    private void calcDCall() {
        Log.e("liqiong",this.task.getBuilder().getName()+"calc____Call________________calc____Call________________calc____Call________________calc____Call________________");
        this.task.getBuilder().setCurrentLength(0);
        long length = this.task.getBuilder().getLength();
        long average = length / getThreadCount();

        for (int i = 0; i < getThreadCount(); i++) {
            long startPoint = i * average;
            long endPoint = (i + 1) * average;
            if (i == (getThreadCount() - 1)) endPoint = length;
            if (startPoint != 0) ++startPoint;

            DBTaskPoint dbTaskPoint = new DBTaskPoint();
            dbTaskPoint.setName(task.getBuilder().getName() + UUID.randomUUID().toString());
            dbTaskPoint.setNetUrl(task.getBuilder().getNetUrl());
            dbTaskPoint.setLength(length);
            dbTaskPoint.setStartPoint(startPoint);
            dbTaskPoint.setEndPoint(endPoint);
            dbTaskPoint.setFirstStartPoint(startPoint);

            dbClient.taskPointSave(dbTaskPoint);

            dispatcher.enqueue(new DLDownload(client, task, dbTaskPoint, startPoint, endPoint));
        }
    }

    public int getThreadCount() {
        if (threadCount == 0)
            threadCount = 1;
        return threadCount;
    }

    public DLManage setThreadCount(int threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    public DLManage client(DLClient client) {
        this.client = client;
        return this;
    }
}
