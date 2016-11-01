package com.example.demo.download;

/**
 * Created by LiQiong on 2016/10/26.
 */

public abstract class DLCall extends DLNameRunnable {
    protected boolean isStop=false;
    public DLCall(String name) {
        super(name);
    }
}