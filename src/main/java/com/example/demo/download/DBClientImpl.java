package com.example.demo.download;

import com.example.demo.db.CRUDImpl;
import com.example.demo.db.ICRUD;

import java.util.List;

/**
 * Created by LiQiong on 2016/10/31.
 */

public class DBClientImpl extends DBClient {
    private ICRUD crud;
    public DBClientImpl(){
        crud=new CRUDImpl();
    }

    @Override
    public void taskSaveNewOnly(DBTask task) {
        List<DBTask> findTask = taskFind("name=? and netUrl=?", new String[]{task.getName(), task.getNetUrl()});
        if (findTask.size()==0||findTask == null)
            crud.save(task);
    }

    @Override
    public void taskUpdate(DBTask task) {
        String[] whereArgs = {task.getName(), task.getNetUrl()};
        crud.update(task, "name=? and netUrl=?", whereArgs);
    }

    @Override
    public void taskDelete(DBTask task) {
        String where="name=? and netUrl=? ";
        String[]whereArgs={task.getName(),task.getNetUrl()};
        crud.delete(DBTask.class,where,whereArgs);
    }

    @Override
    public List<DBTask> taskFind(String where, String[] whereArgs) {
        List<DBTask> findTask =  crud.find(DBTask.class, where, whereArgs);
        return findTask;
    }

    @Override
    public List<DBTaskPoint> taskPointFind(String where, String[] whereArgs) {
        List<DBTaskPoint> findTaskPoint =  crud.find(DBTaskPoint.class, where, whereArgs);
        return findTaskPoint;
    }



    @Override
    public void taskPointSave(DBTaskPoint point) {
        crud.save(point);
    }

    @Override
    public void taskPointUpdate(DBTaskPoint point) {
        String[] whereArgs = {point.getName()};
        crud.update(point, "name=?", whereArgs);
    }

    @Override
    public void taskPointDelete(String where, String[] whereArgs) {
        crud.delete(DBTaskPoint.class, where, whereArgs);
    }
}
