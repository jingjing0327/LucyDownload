package com.chazuo.college.enterprise.download;

import android.util.Log;

import com.chazuo.czlib.module.impl.CZController;

import java.io.File;
import java.util.List;

/**
 * Created by LiQiong on 2016/10/26.
 */

public final class DLManage {
    protected DLTask task;
    private DLClient client;
    private DBClient dbClient;
    private DLDispatcher dispatcher;


    public DLManage(DLTask task) {
        this.task = task;
        this.dispatcher = new DLDispatcher();
        task.getBuilder().setDispatcher(dispatcher);
        dbClient = DLQueue.getInstance().getDbClient();
    }

    /**
     *
     */
    public void execute() {
        File file = new File(task.getBuilder().getLocalUrl() + "/" + task.getBuilder().getName());
        List<DBTask> dbTasksPoint = dbClient.taskFind("name=? and netUrl=?", new String[]{task.getBuilder().getName(), task.getBuilder().getNetUrl()});

        if (file.exists() && dbTasksPoint.size() > 0 && dbTasksPoint.get(0).getLength() > 0) {
            task.getBuilder().setCurrentLength(file.length());
            task.getBuilder().setLength(dbTasksPoint.get(0).getLength());
            if(file.length()==dbTasksPoint.get(0).getLength()){
                task.getBuilder().setTaskType(DLTaskType.SUCCESS);
                return;
            }
            task.getBuilder().setStartPoint((int) file.length());
            task.getBuilder().setLength(dbTasksPoint.get(0).getLength());
            dispatcher.exec(new DLDownload(client, task));
        } else {
            //
            new DLTaskInfo(client, task).execute(new DLTaskInfo.NetCallBack() {
                @Override
                public void onSuccess() {
                    singleDCall();
                }

                @Override
                public void onFail() {
                    task.getBuilder().setTaskType(DLTaskType.FAIL);
                    CZController.uiHelp.toast("下载失败！");
                }
            });
        }
    }

    private void singleDCall() {
        dbClient.taskUpdate(new DBTask(
                task.getBuilder().getName(),
                task.getBuilder().getNetUrl(),
                DLTaskType.DOWNLOADING.ordinal(),
                (int) task.getBuilder().getLength()));
        List<DBTask> xxx = CZController.dbHelp.find(DBTask.class, "name=? and netUrl=?", new String[]{task.getBuilder().getName(), task.getBuilder().getNetUrl()});
        Log.e("liqiong", xxx.toString());
        task.getBuilder().setCurrentLength(0);
        task.getBuilder().setStartPoint(0);
        dispatcher.exec(new DLDownload(client, task));
    }

    public DLManage client(DLClient client) {
        this.client = client;
        return this;
    }
}