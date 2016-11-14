package com.chazuo.college.enterprise.download;

import android.os.Environment;

import com.chazuo.czlib.module.impl.CZKernel;

/**
 * Created by LiQiong on 2016/10/26.
 */

public class DLTask {
    private Builder builder;

    public DLTask(Builder builder) {
        this.builder = builder;
    }

    public Builder getBuilder() {
        return builder;
    }

    public static class Builder {
        private String name;
        private String netUrl;
        private String localUrl;
        private DLTaskType taskType = DLTaskType.WAIT;
        private long length;
        private long currentLength;
        private DLDispatcher dispatcher;
        private Object tag;

        public Builder() {
            if (Environment.getExternalStorageDirectory() != null) {
                //默认的下载目录
                this.localUrl = CZKernel.getInstance().getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath();
            }
        }

        public Builder netUrl(String netUrl) {
            this.netUrl = netUrl;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder localUrl(String localUrl) {
            this.localUrl = localUrl;
            return this;
        }

        public DLTask build() {
            return new DLTask(this);
        }

        public String getName() {
            return name;
        }

        public String getNetUrl() {
            return netUrl;
        }

        public String getLocalUrl() {
            return localUrl;
        }

        public long getLength() {
            return length;
        }

        public synchronized long getCurrentLength() {
            return currentLength;
        }

        public void setLength(long length) {
            this.length = length;
        }

        public synchronized void setCurrentLength(long currentLength) {
            this.currentLength = currentLength;
        }

        public DLTaskType getTaskType() {
            return taskType;
        }

        public void setTaskType(DLTaskType taskType) {
            this.taskType = taskType;
        }

        public DLDispatcher getDispatcher() {
            return dispatcher;
        }

        public void setDispatcher(DLDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }

    }
}
