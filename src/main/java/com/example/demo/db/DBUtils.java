package com.example.demo.db;

import java.util.List;

import com.chazuo.czlib.module.impl.CZKernel;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * CRUD 操作 没有关闭操作
 *
 * @author LiQiong
 */
public class DBUtils {
    private final String TAG = this.getClass().getSimpleName() + "===>>>>";
    private SQLiteDatabase sqlDb;

    /**
     * no close
     */
    public DBUtils() {
        DatabaseHelper helper = new DatabaseHelper(CZKernel.getInstance().getContext());
        sqlDb = helper.getWritableDatabase();
    }

    public <T> int update(T t, String whereClause, String[] whereArgs) {
        IUpdateDB updateImpl = new UpdateDBImpl(sqlDb);
        int affected = updateImpl.update(t, whereClause, whereArgs);
        updateImpl = null;
        return affected;
    }

    public <T> int delete(Class<T> t, String whereClause, String[] whereArgs) {
        IDeleteDB deleteImpl = new DeleteDBImpl(sqlDb);
        int number = deleteImpl.delete(t, whereClause, whereArgs);
        deleteImpl = null;
        return number;
    }

    public <T> int deleteAll(Class<T> t) {
        IDeleteDB deleteImpl = new DeleteDBImpl(sqlDb);
        int number = deleteImpl.deleteAll(t);
        deleteImpl = null;
        return number;
    }

    /**
     * 增加�?��关闭�?��
     *
     * @param t
     */
    public <T> long save(T t) {
        ISaveDB saveImpl = new SaveDBImpl(sqlDb);
        long lastId = saveImpl.save(t);
        saveImpl = null;
        return lastId;
    }

    /**
     * 保存List 列表
     *
     * @param listClazzs
     */
    public <T> void saveAll(List<T> listClazzs) {
        ISaveDB saveImpl = new SaveDBImpl(sqlDb);
        saveImpl.saveAll(listClazzs);
        saveImpl = null;
    }

    /**
     * 查询�?��
     *
     * @param t
     * @return
     */
    public <T> List<T> findAll(Class<T> t) {
        IFindDB findImpl = new FindDBImpl(sqlDb);
        List<T> tList = findImpl.findAll(t);
        return tList;
    }

    /**
     * query 和一些条�? *
     *
     * @param t
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     */
    public <T> List<T> find(Class<T> t, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        IFindDB findImpl = new FindDBImpl(sqlDb);
        List<T> tList = null;
        try {
            tList = findImpl.find(t, columns, selection, selectionArgs, groupBy, having, orderBy);
        } catch (IllegalStateException e) {
            Log.e(TAG, e.getMessage() + "---not is table!");
            e.printStackTrace();
        }
        return tList;
    }

    public <T> List<T> find(Class<T> t, String selection, String[] selectionArgs) {
        IFindDB findImpl = new FindDBImpl(sqlDb);
        List<T> tList = null;
        try {
            tList = findImpl.find(t, null, selection, selectionArgs, null, null, null);
        } catch (IllegalStateException e) {
            Log.e(TAG, e.getMessage() + "---not is table!");
            e.printStackTrace();
        }
        return tList;
    }


    /**
     * close data base
     */
    public void release() {
        sqlDb.close();
    }
}