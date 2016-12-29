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

    public DLDownload(DLClient client, DLTask task) {
        super(task.getBuilder().getName() + UUID.randomUUID());
        this.client = client;
        this.task = task;
    }

    @Override
    protected void execute() {
        try {
            if (task.getBuilder().getTaskType() == DLTaskType.SUCCESS)
                return;
            InputStream is = client.bodyInputStream(task.getBuilder().getNetUrl(),
                    task.getBuilder().getStartPoint(), task.getBuilder().getLength());
            if (is == null) {
                task.getBuilder().setTaskType(DLTaskType.FAIL);
                return;
            }
            RandomAccessFile raf=new RandomAccessFile(task.getBuilder().getLocalUrl() + "/" + task.getBuilder().getName(),"rw");
            raf.seek(task.getBuilder().getStartPoint());
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                raf.write(buffer,0,len);
                task.getBuilder().setCurrentLength(task.getBuilder().getCurrentLength() + len);
                //
                if (isStop)
                    break;
            }
            is.close();
            buffer.clone();
            raf.close();

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