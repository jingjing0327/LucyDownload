package com.example.demo.db;



import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @author LiQiong
 * 
 */
public class DeleteDBImpl implements IDeleteDB {
	private SQLiteDatabase sqlDb;

	public DeleteDBImpl(SQLiteDatabase sqlDb) {
		this.sqlDb = sqlDb;
	}

	@Override
	public <T> int delete(Class<T> t, String whereClause, String[] whereArgs) {
		try {
			int number = sqlDb.delete(t.getSimpleName(), whereClause, whereArgs);
			return number;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public <T> int deleteAll(Class<T> t) {
		try {
			int number = sqlDb.delete(t.getSimpleName(), null, null);
			return number;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}