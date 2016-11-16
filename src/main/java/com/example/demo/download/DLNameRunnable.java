package com.chazuo.college.enterprise.download;

/**
 * Created by LiQiong on 2016/10/26.
 */

public abstract class DLNameRunnable implements Runnable {
    protected final String name;

    public DLNameRunnable(String name) {
        this.name = name;
    }

    @Override
    public final void run() {
        Thread.currentThread().setName(this.name);
        execute();
    }

    protected abstract void execute();
}
