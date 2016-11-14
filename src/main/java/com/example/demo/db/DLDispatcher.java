package com.chazuo.college.enterprise.download;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LiQiong on 2016/10/27.
 */

public final class DLDispatcher {
    private int corePoolSize;
    private ExecutorService executorService;
    private List<DLCall> calls;

    public DLDispatcher() {
        calls = new ArrayList<>();
    }

    public DLDispatcher build() {
        if (executorService == null)
            executorService = Executors.newFixedThreadPool(getCorePoolSize());
        return this;
    }

    public DLDispatcher enqueue(DLCall download) {
        executorService.execute(download);
        calls.add(download);
        return this;
    }

    public DLDispatcher setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public DLDispatcher shutdown() {
        if (executorService != null) {
            for (DLCall call : calls) {
                Log.e("liqiongqiong",call.name+"---"+call.toString());
                call.isStop = true;
            }
            Log.e("liqiong", "shutdownNow!!!!!!!!!!!!!!");
//            executorService.shutdownNow();

        } else {
            Log.e("liqiong", "executorService.isTerminated()-->>" + executorService.isTerminated());
            Log.e("liqiong", "executorService.isShutdown()-->>" + executorService.isShutdown());
        }
        return this;
    }

    public int getCorePoolSize() {
        if (corePoolSize == 0)
            ++corePoolSize;
        return corePoolSize;
    }
}
