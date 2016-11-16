package com.chazuo.college.enterprise.download;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by LiQiong on 2016/10/28.
 */

public abstract class DLClient {

    protected static final String RANGE_NAME ="Range";

    public abstract long bodyLength(String netUrl) throws IOException;
    public abstract InputStream bodyInputStream(String netUrl, long startPoint, long endPoint)throws IOException;

    protected final String rangeValue(long startPoint,long endPoint){
        String head= "bytes=" + startPoint + "-" + endPoint;
        return head;
    }
}