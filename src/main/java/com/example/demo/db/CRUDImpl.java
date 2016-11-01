package com.example.demo.db;


import java.util.List;

/**
 * 
 * @author LiQiong
 * @dateTime 2016-6-2下午3:50:03
 */
public class CRUDImpl implements ICRUD {
	DBUtils db = new DBUtils();

	@Override
	public <T> int update(T t, String whereClause, String[] whereArgs) {

		int affected = db.update(t, whereClause, whereArgs);
//		db.release();
//		db = null;
		return affected;
	}

	@Override
	public <T> int delete(Class<T> t, String whereClause, String[] whereArgs) {
//		DBUtils db = new DBUtils();
		int affected = db.delete(t, whereClause, whereArgs);
//		db.release();
//		db = null;
		return affected;
	}

	@Override
	public <T> int deleteAll(Class<T> t) {
//		DBUtils db = new DBUtils();
		int affected = db.deleteAll(t);
//		db.release();
//		db = null;
		return affected;
	}

	@Override
	public <T> long save(T t) {
//		DBUtils db = new DBUtils();
		long lastId = db.save(t);
//		db.release();
//		db = null;
		return lastId;
	}

	@Override
	public <T> void saveAll(List<T> listClazzs) {
//		DBUtils db = new DBUtils();
		db.saveAll(listClazzs);
//		db.release();
//		db = null;
	}

	@Override
	public <T> List<T> findAll(Class<T> t) {
//		DBUtils db = new DBUtils();
		List<T> mList = db.findAll(t);
//		db.release();
//		db = null;
		return mList;
	}

	@Override
	public <T> List<T> find(Class<T> t, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
//		DBUtils db = new DBUtils();
		List<T> mList = db.find(t, columns, selection, selectionArgs, groupBy, having, orderBy);
//		db.release();
//		db = null;
		return mList;
	}

	@Override
	public <T> List<T> find(Class<T> t, String selection, String[] selectionArgs) {
		return find(t, null, selection, selectionArgs, null, null, null);
	}
}
