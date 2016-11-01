package com.example.demo.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author LiQiong
 */
public class SaveDBImpl implements ISaveDB {
    private final static String TAG = SaveDBImpl.class.getSimpleName();
    private SQLiteDatabase sqlDb;

    public SQLiteDatabase getSqlDb() {
        return sqlDb;
    }

    public void setSqlDb(SQLiteDatabase sqlDb) {
        this.sqlDb = sqlDb;
    }

    private DatabaseUtil databaseUtil = new DatabaseUtil();

    public SaveDBImpl(SQLiteDatabase sqlDb) {
        this.sqlDb = sqlDb;
    }

    @Override
    public <T> long save(T t) {
        synchronized (TAG) {
            String simpleTableName = t.getClass().getSimpleName();
            if (!DatabaseUtil.isExistTable(sqlDb, simpleTableName)) {
                String sql = databaseUtil.createTableSql(t.getClass());
                try {
                    sqlDb.execSQL(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return insertValue(simpleTableName, t);
        }
    }

    @Override
    public <T> void saveAll(List<T> listClazzs) {
        synchronized (TAG) {
            if (listClazzs.size() > 0) {
                String simpleTableName = listClazzs.get(0).getClass().getSimpleName();
                if (!DatabaseUtil.isExistTable(sqlDb, simpleTableName)) {
                    String sql = databaseUtil.createTableSql(listClazzs.get(0).getClass());
                    sqlDb.execSQL(sql);
                }
                for (int i = 0; i < listClazzs.size(); i++) {
                    insertValue(simpleTableName, listClazzs.get(i));
                }
            } else {
                Log.e(TAG, "传进来的List是空的！......save List<T> is null!");
            }
        }
    }

    /**
     * @param table
     * @param t
     */
    private <T> long insertValue(String table, T t) {
        isFieldSame(table, t);
        ContentValues values = databaseUtil.valuesGet(t);
        return sqlDb.insert(table, null, values);
    }

    /**
     * 对比字段，主要是看用户是否在增加 或�?
     * <p>
     * 减少字段，现在无法解决，因为sqlite不支持drop COLUMN
     * </p>
     *
     * @param <T>
     * @param tableName
     * @return
     */
    private <T> void isFieldSame(String tableName, T t) {
        String sql = "SELECT * FROM " + tableName + " limit 0,1";
        Cursor cursor = sqlDb.rawQuery(sql, null);
        Field[] fields = t.getClass().getDeclaredFields();
        String[] columnNames = cursor.getColumnNames();
        List<Field> diffField = getDiffField(fields, columnNames);
        for (int i = 0; i < diffField.size(); i++) {
            //Instant Run特性导致
            if (diffField.get(i).isSynthetic())
                continue;
            String sqlAlter = "ALTER TABLE " + tableName + " ADD " + diffField.get(i).getName() + " "
                    + databaseUtil.javaToDBType(diffField.get(i).getType().getSimpleName());
            sqlDb.execSQL(sqlAlter);
        }
    }

    /**
     * 获取不一样的字段，然后add COLUMN
     *
     * @param a
     * @param b
     * @return
     */
    private List<Field> getDiffField(Field[] a, String[] b) {
        List<Field> fields = new ArrayList<Field>();
        for (int i = 0; i < a.length; i++) {
            if (!isFieldsExit(a[i].getName(), b)) {
                fields.add(a[i]);
            }
        }
        return fields;
    }

    /**
     * @param param
     * @param b
     * @return
     */
    private boolean isFieldsExit(String param, String[] b) {
        for (int i = 0; i < b.length; i++) {
            if (param.equals(b[i])) {
                return true;
            }
        }
        return false;
    }
}