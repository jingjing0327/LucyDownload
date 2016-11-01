package com.example.demo.download;

import java.util.Deque;
import java.util.List;

/**
 * Created by LiQiong on 2016/10/31.
 */

public abstract class DBClient {
    //持久化task，并且是唯一的
    public abstract void taskSaveNewOnly(DBTask task);

    //    public abstract void task
    public abstract void taskUpdate(DBTask task);

    //
    public abstract void taskDelete(DBTask task);

    //
    public abstract List<DBTask> taskFind(String where, String[] whereArgs);

    //
    public abstract void taskPointSave(DBTaskPoint point);

    //
    public abstract void taskPointUpdate(DBTaskPoint point);

    //
    public abstract List<DBTaskPoint> taskPointFind(String where, String[] whereArgs);
    //
    public abstract void taskPointDelete(String where, String[] whereArgs);

}
