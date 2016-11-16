package com.chazuo.college.enterprise.download;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Created by LiQiong on 2016/10/28.
 */

public class DLDownload extends DLCall {
    private DLTask task;
    private long startPoint;
    private long endPoint;
    private DLClient client;
    private DBTaskPoint dbTaskPoint;
    private DBClient dbClient;

    public DLDownload(DLClient client, DLTask task, DBTaskPoint dbTaskPoint, long startPoint, long endPoint) {
        super(task.getBuilder().getName());

        this.client = client;
        this.task = task;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.dbTaskPoint = dbTaskPoint;

        this.dbClient = DLQueue.getInstance().getDbClient();
    }

    public DLDownload(String name) {
        super(name);
    }

    @Override
    protected void execute() {
        try {
            Log.e("liqiong", "name-->>" + task.getBuilder().getName() + "--startPoint--" + startPoint + "--endPoint--" + endPoint);
            Log.e("liqiong",task.getBuilder().getTaskType().toString());
            if(task.getBuilder().getTaskType()==DLTaskType.SUCCESS)
                return;
            InputStream is = client.bodyInputStream(task.getBuilder().getNetUrl(), startPoint, endPoint);
            if (is == null) {
                task.getBuilder().setTaskType(DLTaskType.FAIL);
                return;
            }
            RandomAccessFile file = new RandomAccessFile(task.getBuilder().getLocalUrl() + "/" + task.getBuilder().getName(), "rwd");
            file.seek(startPoint);
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {

                file.write(buffer, 0, len);
                task.getBuilder().setCurrentLength(task.getBuilder().getCurrentLength() + len);
                startPoint += len;
                //
                dbTaskPoint.setStartPoint(startPoint);
                dbClient.taskPointUpdate(dbTaskPoint);
                if (isStop)
                    break;
            }
            Log.e("liqiong","isStop----------------------------------------->"+isStop);
            file.close();
            is.close();
            buffer.clone();
            //如果字节数相等，说明下载完成
            if (task.getBuilder().getLength() == task.getBuilder().getCurrentLength()) {
                task.getBuilder().setTaskType(DLTaskType.SUCCESS);
                String[] whereArgs = new String[]{task.getBuilder().getNetUrl(), task.getBuilder().getName() + "%"};
                dbClient.taskPointDelete("netUrl=? and name like ?", whereArgs);

                DBTask dbTask = new DBTask();
                dbTask.setName(task.getBuilder().getName());
                dbTask.setNetUrl(task.getBuilder().getNetUrl());
                dbTask.setType(DLTaskType.SUCCESS.ordinal());
                dbClient.taskUpdate(dbTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
            task.getBuilder().setTaskType(DLTaskType.FAIL);
        }
    }
}