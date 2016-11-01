package com.example.demo.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by LiQiong on 2016/10/28.
 */

public abstract class DLClient {

    protected final String rangeName="Range";

    public abstract long bodyLength(String netUrl) throws IOException;
    public abstract InputStream bodyInputStream(String netUrl, long startPoint, long endPoint)throws IOException;

    protected final String rangeValue(long startPoint,long endPoint){
        String head= "bytes=" + startPoint + "-" + endPoint;
        return head;
    }
}