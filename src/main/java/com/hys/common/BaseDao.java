package com.hys.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 可以继承的直接使用的dao,封装了一些基本的共用的操作
 * @param <T>
 */
public abstract class BaseDao<T> {

    @Autowired
    protected IGenericDao<T> execute;
    
    public abstract Class<T> getEntityClass();
    
    /**
     * 得到全部数据
     * @return
     */
    public List<T> getAll(){
    	return execute.findAll(getEntityClass());
    }
    
    /**
     * 得到指定数据的数据
     * @param num
     * @return
     */
    public List<T> getList(int num, Order... orders){
    	DetachedCriteria dc=DetachedCriteria.forClass(getEntityClass());
    	for(Order oc : orders){
			dc.addOrder(oc);
		}
    	return execute.findByCriteria(dc,0,num);
    }
    
    /**
     * 计算全部记录数
     * @return
     */
    public long countAll(){
    	return execute.getRowCount("Select count(*) from "+getEntityClass().getName());
    }
    
    /**
     * 根据指定属性的的值，查询并返回所有的结果
     * @param name
     * @param value
     * @return
     */
    public List<T> findByProperty(String name,Object value){
    	return execute.findByProperty(getEntityClass(),name,value);
    }
    
    public List<T> findByProperty(String name,Object value,Order ... orders){
    	DetachedCriteria dc=DetachedCriteria.forClass(getEntityClass());
    	dc.add(Restrictions.eq(name, value));
    	for(Order oc : orders){
			dc.addOrder(oc);
		}
    	return execute.findByCriteria(dc);
    	
    }
    
    public List<T> findByCriteria(DetachedCriteria criteria){
    	return execute.findByCriteria(criteria);
    }
    
    public List<?> findByCriteria(Class<?> c, DetachedCriteria criteria){
    	return execute.findByCriteria(c,criteria);
    }
    
    public <V> List<V> findByCriteria(Class<V> v,DetachedCriteria criteria,int num){
    	return execute.findByCriteria(v, criteria, 0,num);
    }
    
    public List<T> findByCriteria(DetachedCriteria criteria,int num){
    	return execute.findByCriteria(criteria,0,num);
    }
    
    public List<T> findByProperty(String name,Object value,int num){
    	DetachedCriteria dc=DetachedCriteria.forClass(getEntityClass());
    	dc.add(Restrictions.eq(name, value));
    	return execute.findByCriteria(dc,0,num);
    }
    
    
    /**
     * 根据指定属性的的值，倒序的属性，查询并返回所有的结果
     * @param name
     * @param value
     * @return
     */
    public List<T> findByProperty(String name,Object value,int num,Order... orders){
    	DetachedCriteria dc=DetachedCriteria.forClass(getEntityClass());
    	dc.add(Restrictions.eq(name, value));
    	for(Order oc : orders){
			dc.addOrder(oc);
		}
    	if(num>0){
    	   return execute.findByCriteria(dc,0,num);
    	}else{
    	   return execute.findByCriteria(dc);
    	}
    }
    
    /**
     * 根据指定属性计算总数
     * @param name
     * @param value
     * @return
     */
    public int countByProperty(String name,Object value){
    	DetachedCriteria dc=DetachedCriteria.forClass(getEntityClass());
    	dc.add(Restrictions.eq(name, value));
    	Long logn= execute.getRowCount(dc);
    	return logn.intValue();
    }
    
    public T get(String id){
    	return execute.get(getEntityClass(), id);
    }

    public void delete(String id){
    	execute.delete(get(id));
    }
    
    public void delete(String[] ids){
    	String delHql="Delete from "+getEntityClass().getName()+" Where id in(:ids)";
    	Map<String,Object> values=new HashMap<String,Object>();
    	values.put("ids", ids);
    	execute.executeUpdateParamByMap(delHql, values);
    }
    
    public void deleteByIn(String name,Object[] values){
    	String delHql="Delete from "+getEntityClass().getName()+" Where "+name+" in(:"+name+")";
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put(name, values);
    	execute.executeUpdateParamByMap(delHql, map);
    }
    
    public void delete(T t){
    	execute.delete(t);
    }

    public void save(T t){
    	execute.save(t);
    }

    public void update(T t){
    	execute.update(t);
    }

	public void evict(T t) {
		execute.evict(t);
	}

	public void clear() {
		execute.clear();
	}



}
