package com.chazuo.college.enterprise.download;

/**
 * Created by LiQiong on 2016/10/31.
 */

public class DBTask {
    private String name;
    private String netUrl;
    private int length;
    //type :
    // 0->fail,
    // 1->success,
    // 2->downloading,
    // 3->未开始,
    // 4->pause
    private int type;

    public DBTask() {

    }

    public DBTask(String name, String netUrl, int type, int length) {
        this.name = name;
        this.netUrl = netUrl;
        this.type = type;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "DBTask{" +
                "name='" + name + '\'' +
                ", netUrl='" + netUrl + '\'' +
                ", length=" + length +
                ", type=" + type +
                '}';
    }
}
