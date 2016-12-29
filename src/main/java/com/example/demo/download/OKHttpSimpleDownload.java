package com.chazuo.college.enterprise.download.ex;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chazuo.czlib.module.impl.CZController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.protocol.HTTP;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiQiong on 2016/12/25 14:43
 */

public class OKHttpSimpleDownload implements HttpSimpleDownload {
    private final static String TAG = OKHttpSimpleDownload.class.getSimpleName();
    private String url;
    private String localUrl;
    private OnCallBack onCallBack;

    public static HttpSimpleDownload Builder() {
        return new OKHttpSimpleDownload();
    }

    @Override
    public HttpSimpleDownload build() {

        if (TextUtils.isEmpty(url)) {
            new NullPointerException("net url is empty.");
            return this;
        }

        if (TextUtils.isEmpty(localUrl)) {
            localUrl = PDFDownloadAdapter.builder().defaultLocalUrl(url);
        }

        if (new File(localUrl).exists()) {
            Log.e(TAG, "this file exists!");
            if (onCallBack != null)
                onCallBack.onSuccess(localUrl);
            return this;
        }

        Request request = new Request.Builder().url(url).build();
        CZController.netHelp.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    byte[] buf = new byte[1024];
                    InputStream is = response.body().byteStream();
                    File file = new File(localUrl);
                    FileOutputStream fos = new FileOutputStream(file);
                    int len = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    if (onCallBack != null)
                        onCallBack.onSuccess(localUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return this;
    }

    @Override
    public HttpSimpleDownload url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public HttpSimpleDownload localUrl(String url) {
        this.localUrl = url;
        return this;
    }

    @Override
    public HttpSimpleDownload OnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
        return this;
    }
}
