package main.java.com.ia.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;







import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Service;

import com.ia.base.dao.BaseDao;
import com.ia.base.service.BaseService;

@Service(value="baseService")
public abstract class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable>
		implements BaseService<M, PK> {
	
//	@Resource(name="baseDao")
//	protected BaseDao<M, PK> baseDao;
//	
//	public abstract void setBaseDao(BaseDao<M, PK> baseDao);
//	
	/** 
     * 使用注解,无法直接注入baseDao. �?��只有把getBaseDao放到具体的service中实�?
     * 
     * @return 
     */  
    public abstract BaseDao<M, PK> getBaseDao();  
	
	
	public PK save(M model){
		return getBaseDao().save(model);
	}
	
	public void update(M model){
		getBaseDao().update(model);
	}
	
	public void delete(PK id){
		getBaseDao().delete(id);
	}
	
	public void deleteObject(M model){
		getBaseDao().deleteObject(model);
	}
	
	public void saveOrUpdate(M model){
		getBaseDao().saveOrUpdate(model);
	}
		
	public M get(PK id){
		return getBaseDao().get(id);
	}
	
	public long countAll(){
		return getBaseDao().countAll();
	}
	
	public long countBy(String propertyName,Object value){
		return getBaseDao().countBy(propertyName, value);
	}
	
	public long countByMap(Map map){
		return getBaseDao().countByMap(map);
	}
	
	
	public List<M> findAll(){
		return getBaseDao().findAll();
	}
	
	public List<M> findAll(int from,int rows){
		return getBaseDao().findAll(from, rows);
	}
	
	public List<M> findAll(String orderBy,boolean isAsc){
		return getBaseDao().findAll(orderBy, isAsc);
	}
	
	public List<M> findAll(int from,int rows,String orderBy,boolean isAsc){
		return getBaseDao().findAll(from, rows, orderBy, isAsc);
	}
	
	public List<M> findBy(String propertyName,Object value){
		return getBaseDao().findBy(propertyName, value);
	}
	
	public List<M> findBy(int from,int rows,String propertyName,Object value){
		return getBaseDao().findBy(from, rows, propertyName, value);
	}
	
	public List<M> findBy(String propertyName,Object value,String orderBy,boolean isAsc){
		return getBaseDao().findBy(propertyName, value, orderBy, isAsc);
	}
	
	public List<M> findBy(int from,int rows,String propertyName,Object value,String orderBy,boolean isAsc){
		return getBaseDao().findBy(from, rows, propertyName, value, orderBy, isAsc);
	}
	
	public List<M> findByMap(Map map){
		return getBaseDao().findByMap(map);
	}
	
	public List<M> findByMap(int from,int rows,Map map){
		return getBaseDao().findByMap(from, rows, map);
	}
	
	public List findNativeSqlQuery(String sql){
		return getBaseDao().findNativeSqlQuery(sql);
	}

	public long countByCriteria(Criterion... criterions){
		return getBaseDao().countByCriteria(criterions);
	}
	
	public Criteria createCriteria(Criterion... criterions){
		return getBaseDao().createCriteria(criterions);
	}
	
	/**
	 * ��������б��ѯ����
	 * 
	 * @param criterionList
	 *            �����б�
	 * @return ��������Ķ����б�?
	 */
	@Override
	public List<M> findByCriterionList(List<Criterion> criterionList) {
		return getBaseDao().findByCriterionList(criterionList);
	}
}
