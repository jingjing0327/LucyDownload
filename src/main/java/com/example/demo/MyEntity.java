package com.example.demo;


/**
 * Created by LiQiong on 2016/10/20.
 */

public class MyEntity {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "MyEntity{" +
                "count=" + count +
                '}';
    }
}
