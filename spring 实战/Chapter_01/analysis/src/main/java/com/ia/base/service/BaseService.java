package main.java.com.ia.base.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

public interface BaseService <M extends java.io.Serializable, PK extends java.io.Serializable> {

	PK save(M model);
	
	void update(M model);
	
	void delete(PK id);
	
	void deleteObject(M model);
	
	void saveOrUpdate(M model);
		
	M get(PK id);
	
	long countAll();
	
	long countBy(String propertyName, Object value);
	
	long countByMap(Map map);
	
	List<M> findAll();
	
	List<M> findAll(int from, int rows);
	
	List<M> findAll(String orderBy, boolean isAsc);
	
	List<M> findAll(int from, int rows, String orderBy, boolean isAsc);
	
	List<M> findBy(String propertyName, Object value);
	
	List<M> findBy(int from, int rows, String propertyName, Object value);
	
	List<M> findBy(String propertyName, Object value, String orderBy, boolean isAsc);
	
	List<M> findBy(int from, int rows, String propertyName, Object value, String orderBy, boolean isAsc);
	
	List<M> findByMap(Map map);
	
	List<M> findByMap(int from, int rows, Map map);
	
	List findNativeSqlQuery(String sql);

	long countByCriteria(Criterion... criterions);
	
	Criteria createCriteria(Criterion... criterions);
	/**
	 * ��������б��ѯ����
	 * 
	 * @param criterionList
	 *            �����б�
	 * @return ��������Ķ����б�?
	 */
    List<M> findByCriterionList(List<Criterion> criterionList);
}