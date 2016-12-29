package com.chazuo.college.enterprise.download.ex;

/**
 * Created by LiQiong on 2016/12/25 14:41
 */

public interface HttpSimpleDownload {

    HttpSimpleDownload build();

    HttpSimpleDownload url(String url);

    HttpSimpleDownload localUrl(String url);

    HttpSimpleDownload OnCallBack(OnCallBack onCallBack);

    interface OnCallBack {
        void onSuccess(String localUrl);
    }
}
