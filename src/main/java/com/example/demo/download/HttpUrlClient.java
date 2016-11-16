package com.chazuo.college.enterprise.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LiQiong on 2016/10/28.
 */

public final class HttpUrlClient extends DLClient {

    public static HttpUrlClient create() {
        return new HttpUrlClient();
    }

    @Override
    public long bodyLength(String netUrl) throws IOException {
        URL url = new URL(netUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000);
        int length = conn.getContentLength();
        return length;
    }

    @Override
    public InputStream bodyInputStream(String netUrl, long startPoint, long endPoint) throws IOException {
        URL url = new URL(netUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000);
        conn.setRequestProperty(RANGE_NAME, rangeValue(startPoint, endPoint));
        int code = conn.getResponseCode();
        if (code == 206)
            return conn.getInputStream();
        return null;
    }
}
