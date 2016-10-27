package com.hys.common;

import java.util.List;

/**
 * 基本的service 
 * @author hys
 *
 * @param <T>
 */
public interface BaseService<T>{
	public void save(T t);
	public T update(T t);
	public T saveFectch(T t);
	public List<T> list(T t);
	public void del(T t);
}
