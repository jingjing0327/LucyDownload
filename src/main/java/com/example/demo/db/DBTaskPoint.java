package com.chazuo.college.enterprise.download;

/**
 * Created by LiQiong on 2016/10/28.
 */

public class DBTaskPoint {
    private String name;
    private String netUrl;
    private long length;
    private long firstStartPoint;
    private long startPoint;
    private long endPoint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(long startPoint) {
        this.startPoint = startPoint;
    }

    public long getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(long endPoint) {
        this.endPoint = endPoint;
    }

    public long getFirstStartPoint() {
        return firstStartPoint;
    }

    public void setFirstStartPoint(long firstStartPoint) {
        this.firstStartPoint = firstStartPoint;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    @Override
    public String toString() {
        return "DBTaskPoint{" +
                "endPoint=" + endPoint +
                ", startPoint=" + startPoint +
                ", firstStartPoint=" + firstStartPoint +
                ", length=" + length +
                ", netUrl='" + netUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
