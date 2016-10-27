package com.didispace.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 通过Dao接口
 */
public interface IGenericDao<T> {

	/**
	 * 通过ID提取
	 * 
	 * @param c
	 * @param id
	 * @return
	 */
	T get(Class<T> c, Serializable id);

	void save(T entity);

	void saveOrUpdate(T entity);

	void merge(T entity);

	void update(T entity);

	void delete(T entity);

	List<T> findAll(Class<T> c);// 查询全部

	List<T> find(String hsql);// 根据hql语句查询全部

	List<T> find(int num, String hsql);// 提取指定数量的集成

	List<T> find(String hsql, Object... values);// 动态参数的查询

	List<T> findParamByMap(String hsql, Map<String, Object> maps);

	List<T> findByProperty(Class<T> c, String name, Object value);

	List<T> findByCriteria(DetachedCriteria criteria);

	List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults);

	Object query(String hql);// 根据hql查询得到单个对象

	Long getRowCount(String hsql);

	Long getRowCount(String hsql, Object... values);

	Long getRowCount(DetachedCriteria criteria);

	int execute(String hql);// 执行sql语句

	int execute(String hql, Object... values);// 执行带参数的sql语句

	int executeUpdateParamByMap(String hql, Map<String, Object> values);

	Object getObject(@SuppressWarnings("rawtypes") Class c, Serializable id);

	void saveObject(Object obj);

	void updateObject(Object obj);

	void deleteObject(Object obj);

	@SuppressWarnings("rawtypes")
	List findObjects(String hsql);

	@SuppressWarnings("rawtypes")
	List findObjects(String hsql, Object... values);

	/**
	 * 动态的指定返回对应
	 * 
	 * @param c
	 * @param criteria
	 * @return
	 */
	
	<V> List<V> findByCriteria(Class<V> v, DetachedCriteria criteria);

	<V> List<V> findByCriteria(Class<V> v,DetachedCriteria criteria, int firstResult, int maxResults);
	

	public void evict(Object obj);

	public void clear();

	public Session getSession();

}
