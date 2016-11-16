package com.chazuo.czlib.db;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 
 * @author LiQiong
 * 
 */
public class FindDBImpl implements IFindDB {
	private SQLiteDatabase sqlDb;

	public SQLiteDatabase getSqlDb() {
		return sqlDb;
	}

	public void setSqlDb(SQLiteDatabase sqlDb) {
		this.sqlDb = sqlDb;
	}

	public FindDBImpl(SQLiteDatabase sqlDb) {
		this.sqlDb = sqlDb;
	}

	@Override
	public <T> List<T> findAll(Class<T> t) {
		if (!DatabaseUtil.isExistTable(sqlDb, t.getSimpleName()))
			return new ArrayList<T>();
		return find(t, null, null, null, null, null, null);
	}

	@Override
	public <T> List<T> find(Class<T> t, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
		if (!DatabaseUtil.isExistTable(sqlDb, t.getSimpleName()))
			return new ArrayList<T>();
		List<T> tList = new ArrayList<T>();
		try {
			Field[] field = t.getDeclaredFields();
			Cursor cursor = sqlDb.query(t.getSimpleName(), columns, selection, selectionArgs, groupBy, having, orderBy);
			while (cursor.moveToNext()) {
				T instance = t.newInstance();
				for (int i = 0; i < field.length; i++) {
					if (field[i].isSynthetic())
						continue;
					invokeValue(field[i], cursor, t, instance);
				}
				tList.add(instance);
			}
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tList;
	}

	@Override
	public <T> List<T> find(Class<T> t, String selection, String[] selectionArgs) {
		return find(t,null,selection,selectionArgs,null,null,null);
	}

	private <T> void invokeValue(Field field, Cursor cursor, Class<T> t, T instance) {
		try {

			Class<?> clazz = field.getType();
			String setName = field.getName();
			String setNameUpperCase = setName.replaceFirst(setName.substring(0, 1), setName.substring(0, 1).toUpperCase(Locale.getDefault()));
			if (setNameUpperCase.contains("Is")) {
				setNameUpperCase = setNameUpperCase.replace("Is", "");
			}
			if(setNameUpperCase.equals("SerialVersionUID"))
				return;
			Method method = t.getMethod("set" + setNameUpperCase, clazz);
			String classSimpleName = clazz.getSimpleName();
			if (classSimpleName.equals("String")) {
				method.invoke(instance, cursor.getString(cursor.getColumnIndex(setName)));
			} else if (classSimpleName.equals("float")) {
				method.invoke(instance, cursor.getFloat(cursor.getColumnIndex(setName)));
			} else if (classSimpleName.equals("boolean")) {
				method.invoke(instance, cursor.getInt(cursor.getColumnIndex(setName)) > 0);
			} else if (classSimpleName.equals("int")) {
				method.invoke(instance, cursor.getInt(cursor.getColumnIndex(setName)));
			} else if (classSimpleName.equals("long")) {
				method.invoke(instance, cursor.getLong(cursor.getColumnIndex(setName)));
			} else if (classSimpleName.equals("double")) {
				method.invoke(instance, cursor.getDouble(cursor.getColumnIndex(setName)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}