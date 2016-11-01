package com.example.demo.download;

/**
 * Created by LiQiong on 2016/10/31.
 */

public class DBTask {
    private String name;
    private String netUrl;
    //type :
    // 0->fail,
    // 1->success,
    // 2->downloading,
    // 3->未开始,
    // 4->pause
    private int type;

    public DBTask() {

    }

    public DBTask(String name, String netUrl, int type) {
        this.name = name;
        this.netUrl = netUrl;
        this.type = type;
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

    @Override
    public String toString() {
        return "DBTask{" +
                "name='" + name + '\'' +
                ", netUrl='" + netUrl + '\'' +
                ", type=" + type +
                '}';
    }
}
