package com.chazuo.college.enterprise.download;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by LiQiong on 2016/10/28.
 */

public class DLTaskInfo {
    private DLClient client;
    private DLTask task;

    public DLTaskInfo(DLClient client, DLTask task) {
        this.client = client;
        this.task = task;
    }

    public void execute(final NetCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long length = client.bodyLength(task.getBuilder().getNetUrl());
                    if(length>0){
                        task.getBuilder().setLength(length);
                        RandomAccessFile file = new RandomAccessFile(task.getBuilder().getLocalUrl() + "/" + task.getBuilder().getName(), "rwd");
                        file.setLength(length);
                        file.close();
                        callBack.onSuccess();
                    }else
                        callBack.onFail();
                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.onFail();
                }
            }
        }).start();
    }

    interface NetCallBack {
        void onSuccess();

        void onFail();
    }
}
