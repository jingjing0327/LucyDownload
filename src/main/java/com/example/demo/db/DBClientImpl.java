package com.chazuo.college.enterprise.download;

import com.chazuo.czlib.module.impl.CZController;

import java.util.List;

/**
 * Created by LiQiong on 2016/10/31.
 */

public class DBClientImpl extends DBClient {

    @Override
    public void taskSaveNewOnly(DBTask task) {
        List<DBTask> findTask = taskFind("name=? and netUrl=?", new String[]{task.getName(), task.getNetUrl()});
        if (findTask.size()==0||findTask == null)
            CZController.dbHelp.save(task);
    }

    @Override
    public void taskUpdate(DBTask task) {
        String[] whereArgs = {task.getName(), task.getNetUrl()};
        CZController.dbHelp.update(task, "name=? and netUrl=?", whereArgs);
    }

    @Override
    public void taskDelete(DBTask task) {
        String where="name=? and netUrl=? ";
        String[]whereArgs={task.getName(),task.getNetUrl()};
        CZController.dbHelp.delete(DBTask.class,where,whereArgs);
    }

    @Override
    public List<DBTask> taskFind(String where, String[] whereArgs) {
        List<DBTask> findTask =  CZController.dbHelp.find(DBTask.class, where, whereArgs);
        return findTask;
    }

    @Override
    public List<DBTaskPoint> taskPointFind(String where, String[] whereArgs) {
        List<DBTaskPoint> findTaskPoint =  CZController.dbHelp.find(DBTaskPoint.class, where, whereArgs);
        return findTaskPoint;
    }



    @Override
    public void taskPointSave(DBTaskPoint point) {
        CZController.dbHelp.save(point);
    }

    @Override
    public void taskPointUpdate(DBTaskPoint point) {
        String[] whereArgs = {point.getName()};
        CZController.dbHelp.update(point, "name=?", whereArgs);
    }

    @Override
    public void taskPointDelete(String where, String[] whereArgs) {
        CZController.dbHelp.delete(DBTaskPoint.class, where, whereArgs);
    }
}
