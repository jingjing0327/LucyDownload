package com.example.demo.db;

/**
 * 
 * @author LiQiong
 *
 */
public interface IUpdateDB {
	/**
	 * 
	 * @param t
	 * @param whereClause
	 * @param whereArgs
	 * @return 影响行数
	 */
	public <T> int update(T t, String whereClause, String[] whereArgs);
}
