package com.chazuo.college.enterprise.download;

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
    public abstract void taskUpdate(String name, String netUrl, int type);
}
