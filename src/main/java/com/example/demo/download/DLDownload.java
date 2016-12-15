package com.chazuo.college.enterprise.download;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * Created by LiQiong on 2016/10/28.
 */

public class DLDownload extends DLCall {
    private DLTask task;
    private DLClient client;
    private DBClient dbClient;

    public DLDownload(DLClient client, DLTask task) {
        super(task.getBuilder().getName() + UUID.randomUUID());
        this.client = client;
        this.task = task;
        this.dbClient = DLQueue.getInstance().getDbClient();
    }

    @Override
    protected void execute() {
        try {
            Log.e("liqiong", "name-->>" + task.getBuilder().getName() + "--startPoint--" +
                    task.getBuilder().getStartPoint() + "--endPoint--" + task.getBuilder().getLength());
            Log.e("liqiong", task.getBuilder().getTaskType().toString());
            if (task.getBuilder().getTaskType() == DLTaskType.SUCCESS)
                return;
            InputStream is = client.bodyInputStream(task.getBuilder().getNetUrl(),
                    task.getBuilder().getStartPoint(), task.getBuilder().getLength());
            if (is == null) {
                task.getBuilder().setTaskType(DLTaskType.FAIL);
                return;
            }

            FileOutputStream fos = new FileOutputStream(task.getBuilder().getLocalUrl() + "/" + task.getBuilder().getName());
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                task.getBuilder().setCurrentLength(task.getBuilder().getCurrentLength() + len);
                //
                if (isStop)
                    break;
            }
            Log.e("liqiongqiong", "task-length->" + task.getBuilder().getLength() + "---task-CurrentLength->" + task.getBuilder().getCurrentLength()
                    + "---file.length->" + fos);
            is.close();
            buffer.clone();
            fos.close();

            //如果字节数相等，说明下载完成
            if (task.getBuilder().getLength() == task.getBuilder().getCurrentLength()) {
                task.getBuilder().setTaskType(DLTaskType.SUCCESS);
            }
        } catch (IOException e) {
            e.printStackTrace();
            task.getBuilder().setTaskType(DLTaskType.FAIL);
        }
    }
}