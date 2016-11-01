package com.example.demo.db;


import java.util.List;

/**
 * 
 * @author LiQiong
 * @dateTime 2016-6-2涓嬪崍3:49:49
 */
public interface ICRUD {
	/**
	 * 
	 * @param t
	 * @param whereClause
	 * @param whereArgs
	 * @return 褰卞搷琛屾暟
	 */
	public <T> int update(T t, String whereClause, String[] whereArgs);

	/**
	 * 
	 * @param t
	 * @param whereClause
	 * @param whereArgs
	 * @return 褰卞搷琛屾暟
	 */
	public <T> int delete(Class<T> t, String whereClause, String[] whereArgs);

	/**
	 * 
	 * @param t
	 * @return 褰卞搷琛屾暟
	 */
	public <T> int deleteAll(Class<T> t);

	/**
	 * 
	 * @param t
	 * @return 鎻掑叆鐨勬柊id
	 */
	public <T> long save(T t);

	/**
	 * 
	 * @param listClazzs
	 */
	public <T> void saveAll(List<T> listClazzs);

	/**
	 * 
	 * @param t
	 * @return 鎵�湁鐨勬暟鎹�
	 */
	public <T> List<T> findAll(Class<T> t);

	/**
	 * 
	 * @param t
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return 鏍规嵁鏉′欢鏌ヨ鍑烘潵鐨勬暟鎹�
	 */
	public <T> List<T> find(Class<T> t, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);
	/**
	 * 
	 * @param t
	 * @param selection
	 * @param selectionArgs
	 * @return
	 */
	public <T> List<T> find(Class<T> t, String selection, String[] selectionArgs);
}
