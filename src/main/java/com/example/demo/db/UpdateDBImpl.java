package com.example.demo.db;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author LiQiong
 */
public class UpdateDBImpl implements IUpdateDB {
    private SQLiteDatabase sqlDb;

    public SQLiteDatabase getSqlDb() {
        return sqlDb;
    }

    public void setSqlDb(SQLiteDatabase sqlDb) {
        this.sqlDb = sqlDb;
    }

    public UpdateDBImpl(SQLiteDatabase sqlDb) {
        this.sqlDb = sqlDb;
    }

    @Override
    public <T> int update(T t, String whereClause, String[] whereArgs) {
        try {

            DatabaseUtil databaseUtil = new DatabaseUtil();
            ContentValues values = databaseUtil.valuesGet(t);
            // the number of rows affected
            int affected = sqlDb.update(t.getClass().getSimpleName(), values, whereClause, whereArgs);

            databaseUtil = null;
            return affected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }
}