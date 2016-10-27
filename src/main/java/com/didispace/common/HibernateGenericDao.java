package com.didispace.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 通用dao的实现
 * @param <T>
 */
@Repository
public class HibernateGenericDao<T> implements IGenericDao<T> {

	private Session session;

	@Autowired
	private SessionFactory sessionFactory;


	public Session getSession() {
		session = sessionFactory.getCurrentSession();
		return session;
	}

	public void close() {
		if (session != null && session.isOpen()) {
			session.close();
		}
		session = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) getSession().get(c, id);
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void merge(T entity) {
		getSession().merge(entity);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}



	@Override
	public List<T> findAll(Class<T> c) {
		List<T> list = getSession().createCriteria(c).list();
		return list;
	}

	@Override
	public List<T> find(String hsql) {
		List<T> list = getSession().createQuery(hsql).list();
		return list;
	}

	@Override
	public List<T> find(String hsql, Object... values) {
		Query query = getSession().createQuery(hsql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		List<T> list = query.list();
		return list;
	}

	/**
	 * 
	 * @Title: find
	 * @author YangMing
	 * @param hsql
	 * @param maps
	 * @return
	 * @throws
	 */
	public List<T> find(String hsql, Map<String, Object> maps) {
		Query query = getSession().createQuery(hsql);
		if (maps != null && !maps.isEmpty()) {
			for (Map.Entry<String, Object> entry : maps.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		List<T> list = query.list();
		return list;
	}

	/**
	 *
	 * @Title: find
	 * @author YangMing
	 * @param hsql
	 * @param maps
	 * @return
	 * @throws
	 */
	public List<T> findParamByMap(String hsql, Map<String, Object> maps) {
		Query query = getSession().createQuery(hsql);
		if (maps != null && !maps.isEmpty()) {
			for (Map.Entry<String, Object> entry : maps.entrySet()) {
				if ( entry.getValue() instanceof Collection) {
					query.setParameterList(entry.getKey(), (Collection)entry.getValue());
				}else if(entry.getValue() instanceof Object[]){
					query.setParameterList(entry.getKey(), (Object[])entry.getValue());
				}else{
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		List<T> list = query.list();
		return list;
	}

	/**
	 * 
	 * @Title: find
	 * @author YangMing
	 * @param hsql
	 * @param maps
	 * @return
	 * @throws
	 */
	public List<T> find(String hsql, Map<String, Object> maps, int firstResult, int maxResults) {
		Query query = getSession().createQuery(hsql);
		if (maps != null && !maps.isEmpty()) {
			for (Map.Entry<String, Object> entry : maps.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List<T> list = query.list();
		return list;
	}

	public List<T> find(int num, String hsql) {
		Query query = getSession().createQuery(hsql);
		query.setMaxResults(num);
		return query.list();
	}
	
	public List<T> find(int num, String hsql,Object... objects) {
		Query query = getSession().createQuery(hsql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		query.setMaxResults(num);
		return query.list();
	}

	@Override
	public List<T> findByProperty(Class<T> c, String name, Object value) {
		DetachedCriteria dc = DetachedCriteria.forClass(c);
		dc.add(Restrictions.eq(name, value));
		return findByCriteria(dc);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria criteria) {
		@SuppressWarnings("unchecked")
		List<T> list = criteria.getExecutableCriteria(getSession()).list();
		return list;
	}
	
	@Override
	public <V> List<V> findByCriteria(Class<V> v, DetachedCriteria dc) {
		Criteria criteria = dc.getExecutableCriteria(session);
		//criteria.setResultTransformer(new AliasToBeanResultTransformer(v.getClass()));
		CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
		
		Projection projection = criteriaImpl.getProjection();
		criteria.setProjection(projection);
		
		///ResultTransformer resultTransformer=Transformers.aliasToBean(v);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

	
		@SuppressWarnings("unchecked")
		List<V> list = criteria.list();
		return list;
	}
	
	@Override
	public <V> List<V> findByCriteria(Class<V> v,DetachedCriteria dc, int firstResult, int maxResults) {
		
		Session session = getSession();
		Criteria criteria = dc.getExecutableCriteria(session);
		criteria.setResultTransformer(new AliasToBeanResultTransformer(v.getClass()));
		CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
		
		Projection projection = criteriaImpl.getProjection();
		criteria.setProjection(projection);
		
		ResultTransformer resultTransformer=Transformers.aliasToBean(v);
		criteria.setResultTransformer(resultTransformer);
		
		@SuppressWarnings("unchecked")
		List<V> list =criteria.setFirstResult(firstResult).setMaxResults(maxResults).list();
		return list;
	}
	
	

	@Override
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) {
		@SuppressWarnings("unchecked")
		List<T> list = criteria.getExecutableCriteria(getSession()).setFirstResult(firstResult).setMaxResults(maxResults).list();

		return list;
	}


	@Override
	public Object query(String hql) {
		return getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long getRowCount(String hsql) {
		Query query = getSession().createQuery(hsql);
		List count = query.list();
		if (count == null || count.isEmpty()) {
			return 0L;
		}
		Long c = (Long) count.get(0);
		return c;
	}

	@Override
	public Long getRowCount(String hsql, Object... values) {
		Query queryObject = getSession().createQuery(hsql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return (Long) queryObject.uniqueResult();
	}

	@Override
	public Long getRowCount(DetachedCriteria criteria) {
		Session session = getSession();
		Criteria cri = criteria.getExecutableCriteria(session);
		Object obj = cri.setProjection(Projections.rowCount()).uniqueResult();
		return (Long) obj;
	}

	@Override
	public int execute(String hql) {
		Query query = getSession().createQuery(hql);
		int num = query.executeUpdate();
		return num;
	}

	@Override
	public int execute(String hql, Object... values) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate();
	}
	
	public int executeUpdateParamByMap(String hql, Map<String, Object> values) {
		Query query = getSession().createQuery(hql);
		for (Map.Entry<String, Object> entry : values.entrySet()) {
			if ( entry.getValue() instanceof Collection) {
				query.setParameterList(entry.getKey(), (Collection)entry.getValue());
			}else if(entry.getValue() instanceof Object[]){
				query.setParameterList(entry.getKey(), (Object[])entry.getValue());
			}else{
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.executeUpdate();
	}

	/**
	 * 例如:delete User where id = :id map.put("id", "123");
	 * 
	 * @param hql
	 * @param map
	 * @return
	 */
	public int execute(String hql, Map<String, Object> map) {
		Query query = getSession().createQuery(hql);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.executeUpdate();
	}

	@Override
	public Object getObject(Class c, Serializable id) {
		return getSession().get(c, id);
	}

	@Override
	public void saveObject(Object obj) {
		getSession().save(obj);
	}

	@Override
	public void updateObject(Object obj) {
		getSession().update(obj);
	}

	@Override
	public void deleteObject(Object obj) {
		getSession().delete(obj);
	}

	@Override
	public List findObjects(String hsql) {
		return getSession().createQuery(hsql).list();
	}

	@Override
	public List findObjects(String hsql, Object... values) {
		Query query = getSession().createQuery(hsql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	public void evict(Object obj){
		getSession().evict(obj);
	}

	public void clear(){
		getSession().clear();
	}


}
