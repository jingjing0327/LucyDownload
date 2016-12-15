package com.chazuo.college.enterprise.download;

import android.util.Log;

/**
 * Created by LiQiong on 2016/12/13 14:59
 */

public final class DLDispatcher {
    private final String LOG_NAME = this.getClass().getCanonicalName();
    private DLCall call;

    public void exec(DLCall call) {
        this.call = call;
        new Thread(call).start();
    }

    public void shutdown() {
        if (this.call != null) {
            this.call.isStop = true;
            Log.e(LOG_NAME, call.name + "is shutdown!");
        }
    }
}
