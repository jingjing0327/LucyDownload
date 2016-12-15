package com.chazuo.college.enterprise.download;

/**
 * Created by LiQiong on 2016/11/14.
 */

public enum DLTaskType {
    FAIL,
    SUCCESS,
    DOWNLOADING,
    WAIT,
    PAUSE;

    public static String convertTaskType(DLTaskType type) {
        if (type == null)
            return "暂停";

        String tempStatus = "";
        switch (type) {
            case FAIL:
                tempStatus = "失败";
                break;
            case SUCCESS:
                tempStatus = "成功";
                break;
            case DOWNLOADING:
                tempStatus = "下载中";
                break;
            case WAIT:
                tempStatus = "等待中";
                break;
            case PAUSE:
                tempStatus = "暂停";
                break;
        }
        return tempStatus;
    }
}

