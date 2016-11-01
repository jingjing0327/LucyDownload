package com.example.demo.db;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * @author LiQiong
 * 
 */
public class DatabaseUtil {
	private final static String DBVarChar = "varchar(200)";
	private final static String DBInt = "integer";
	private final static String DBBoolean = "BOOLEAN";
	private final static String DBFloat = "FLOAT";
	private final static String DBLong = "INTEGER(4)";
	private final static String DBDouble = "DOUBLE";

	/**
	 * 判断是否存在某一张表
	 * 
	 * @param tableName
	 * @return true is exist,false is not exist;
	 */
	public static boolean isExistTable(SQLiteDatabase sqlDb, String tableName) {
		String sql = "SELECT COUNT(*) as RESULT FROM SQLITE_MASTER WHERE NAME = ?";
		Cursor cursor = sqlDb.rawQuery(sql, new String[] { tableName });
		int count = 0;
		if (cursor.moveToFirst())
			count = cursor.getInt(cursor.getColumnIndex("RESULT"));
		if (count >= 1)
			return true;
		return false;
	}

	/**
	 * create table sql
	 * 
	 * @param <T>
	 * @return
	 */
	public <T> String createTableSql(Class<T> clazz) {
		List<FieldDB> fieldDBs = getFieldDB(clazz);
		StringBuffer sqlsb = new StringBuffer();
		sqlsb.append("create table ");
		sqlsb.append(clazz.getSimpleName());
		sqlsb.append("(");
		for (int i = 0; i < fieldDBs.size(); i++) {
			sqlsb.append(fieldDBs.get(i).getFieldName());
			sqlsb.append(" ");
			sqlsb.append(fieldDBs.get(i).getFieldType());
			if (i + 1 != fieldDBs.size())
				sqlsb.append(",");
		}
		sqlsb.append(")");
		return sqlsb.toString();
	}

	/**
	 * 
	 * @param javaType
	 * @return
	 */
	public String javaToDBType(String javaType) {
		if ("String".equals(javaType))
			return DBVarChar;
		if ("int".equals(javaType))
			return DBInt;
		if ("Intent".equals(javaType))
			return DBInt;
		if ("boolean".equals(javaType))
			return DBBoolean;
		if ("long".equals(javaType))
			return DBLong;
		if ("float".equals(javaType))
			return DBFloat;
		if ("double".equals(javaType))
			return DBDouble;
		try {
			throw new Throwable("没有这个类型-->"+javaType);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	public <T> ContentValues valuesGet(T t) {
		ContentValues values = new ContentValues();
		List<FieldDB> fieldDBs = getFieldDB(t.getClass());
		Method[] methods = t.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (!method.getName().equals("getClass") && method.getName().startsWith("get") || method.getName().startsWith("is")) {
				for (int j = 0; j < fieldDBs.size(); j++) {
					if (method.getName().toLowerCase(Locale.getDefault()).contains(fieldDBs.get(j).getFieldName().toLowerCase(Locale.getDefault()))) {
						try {
							Object keyValue = method.invoke(t);
							if (keyValue == null)
								keyValue = "null";
							values.put(fieldDBs.get(j).getFieldName(), method.invoke(t).toString());
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return values;
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	private <T> List<FieldDB> getFieldDB(Class<T> clazz) {
		List<FieldDB> fieldDBs = new ArrayList<FieldDB>();
		FieldDB fieldDB = null;
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			//Instant Run特性导致
			if(field.isSynthetic())
				continue;
			fieldDB = new FieldDB();
			fieldDB.setFieldType(javaToDBType(field.getType().getSimpleName()));
			fieldDB.setFieldName(field.getName());
			fieldDBs.add(fieldDB);
		}
		return fieldDBs;
	}
}