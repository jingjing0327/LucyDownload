package com.example.demo.db;


import java.util.List;
/**
 * 
 * @author LiQiong
 *
 */
public interface ISaveDB {
	public <T> long save(T t);

	public <T> void saveAll(List<T> listClazzs);
}
