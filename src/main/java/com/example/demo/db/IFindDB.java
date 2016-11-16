package com.chazuo.czlib.db;


import java.util.List;

/**
 * 
 * @author LiQiong
 *
 */
public interface IFindDB {
	public <T> List<T> findAll(Class<T> t);

	public <T> List<T> find(Class<T> t, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);

	public <T> List<T> find(Class<T> t, String selection, String[] selectionArgs);
}
