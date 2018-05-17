package main.java.com.ia.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ia.base.dao.BaseDao;

/**
 * 通过Hibernate方式实现BaseDao接口，项目中其它DAO组件均为此抽象类的子类�?
 * 
 * @param <M>
 *            实体Entity
 * @param <PK>
 *            主键
 */
@Repository(value = "baseDao")
public abstract class BaseDaoImpl<M extends Serializable, PK extends Serializable>
		implements BaseDao<M, PK> {
	// Hibernate操作会话工厂�?
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private final Class<M> voClass;
	// private String entityFlag = "";// 实体标志
	private String entityName;// 实体名称

	public String getEntityName() {
		return this.voClass.getName();
	}

	// public String getEntityName() {
	// if(null == entityFlag){
	// return this.voClass.getSimpleName();
	// }else{
	// return entityFlag + this.voClass.getSimpleName();
	// }
	// }
	//
	// public String getEntityFlag() {
	// return entityFlag;
	// }
	//
	// public void setEntityFlag(String entityFlag) {
	// this.entityFlag = entityFlag;
	// }

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		voClass = (Class<M>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 获得当前会话
	 * 
	 * @return 当前会话
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 将业务对象进行持久化操作，即保存到数据库中�?
	 * 
	 * @param model
	 *            �?��持久化的业务对象
	 * @return 持久化后生成的数据库记录主键
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PK save(M model) {
		return (PK) getSession().save(getEntityName(), model);
	}

	/**
	 * 更新�?��数据库记�?
	 * 
	 * @param model
	 *            �?��更新的业务对�?
	 */
	@Override
	public void update(M model) {
		getSession().update(getEntityName(), model);
	}

	/**
	 * 如果对象不存在，则插入一条新记录，如果对象已存在，则更新该记录�?
	 * 
	 * @param model
	 *            �?��持久化的业务对象
	 */
	@Override
	public void saveOrUpdate(M model) {
		getSession().saveOrUpdate(getEntityName(), model);

	}

	/**
	 * 根据对象ID删除数据库中�?��记录
	 * 
	 * @param id
	 *            对象ID
	 */
	@Override
	public void delete(PK id) {
		List<M> modelList = findBy("id", id);
		if (modelList.size() == 1) {
			getSession().delete(getEntityName(), modelList.get(0));
		}
	}

	/**
	 * 删除业务对象对应的数据库记录
	 * 
	 * @param model
	 *            欲删除的业务对象
	 */
	@Override
	public void deleteObject(M model) {
		getSession().delete(getEntityName(), model);
	}

	/**
	 * 根据给定对象ID从数据库中加载一个持久化对象�?
	 * 
	 * @param id
	 *            对象ID
	 * @return 与对象ID对应的持久化对象，当数据库中不存在与对象ID对应的记录时返回null�?
	 */
	@SuppressWarnings("unchecked")
	@Override
	public M get(PK id) {
		return (M) getSession().get(getEntityName(), id);
	}

	/**
	 * 查询�?��对象
	 * 
	 * @return �?��对象列表
	 */
	@Override
	public List<M> findAll() {
		return this.find("from " + getEntityName());
	}

	/**
	 * 查询�?��对象，带分页参数
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @return �?��对象列表
	 */
	@SuppressWarnings("unchecked")
	public List<M> findAll(int from, int rows) {
		Query query = getSession().createQuery("from " + getEntityName());
		query.setMaxResults(rows);
		query.setFirstResult(from);
		return query.list();
	}

	/**
	 * 查询�?��对象，带排序参数
	 * 
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @return �?��对象列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findAll(String orderBy, boolean isAsc) {
		Criteria criteria = this.getSession().createCriteria(getEntityName());
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria.list();
	}

	/**
	 * 查询�?��对象，带分页参数和排序参�?
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @return �?��对象列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findAll(int from, int rows, String orderBy, boolean isAsc) {
		Criteria criteria = this.getSession().createCriteria(getEntityName());
		criteria.setMaxResults(rows);
		criteria.setFirstResult(from);
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria.list();
	}

	@Override
	public long countAll() {
		return this.countByCriteria();
		// return this.aggregate("select count(*) from " + getEntityName());
	}

	@Override
	public boolean exist(PK id) {
		return get(id) != null;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void flush() {
		this.getSession().flush();
	}

	@Override
	public void clear() {
		this.getSession().clear();
	}

	@SuppressWarnings("unchecked")
	protected <T> T aggregate(final String hql, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		return (T) query.uniqueResult();

	}

	protected void setParameters(Query query, Object[] paramlist) {
		if (paramlist != null) {
			for (int i = 0; i < paramlist.length; i++) {
				if (paramlist[i] instanceof Date) {
					query.setTimestamp(i, (Date) paramlist[i]);
				} else {
					query.setParameter(i, paramlist[i]);
				}
			}
		}
	}

	public <T> List<T> find(final String hql, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		List<T> results = query.list();
		return results;
	}

	/**
	 * 取得Entity的Criteria.
	 * 
	 * @param criterions
	 *            查询条件，可变的Restrictions条件列表
	 * @return Entity的Criteria
	 */
	@Override
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 取得Entity的Criteria.
	 * 
	 * @param criterions
	 *            查询条件列表
	 * @return Entity的Criteria
	 */
	public Criteria createCriteria(List<Criterion> criterions) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 取得Entity的Criteria,带分页参�?
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param criterions
	 *            查询条件
	 * @return Entity的Criteria
	 */
	@Override
	public Criteria createCriteria(int from, int rows, Criterion... criterions) {
		Criteria criteria = createCriteria(criterions);
		criteria.setMaxResults(rows);
		criteria.setFirstResult(from);
		return criteria;
	}

	/**
	 * 取得Entity的Criteria,带排序参�?
	 * 
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @param criterions
	 *            查询条件
	 * @return Entity的Criteria
	 */
	public Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion... criterions) {
		Criteria criteria = createCriteria(criterions);
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));
		return criteria;
	}

	/**
	 * 取得Entity的Criteria,带分页条件和排序参数.
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @param criterions
	 *            查询条件
	 * @return Entity的Criteria
	 */
	public Criteria createCriteria(int from, int rows, String orderBy,
			boolean isAsc, Criterion... criterions) {
		Criteria criteria = createCriteria(orderBy, isAsc, criterions);
		criteria.setMaxResults(rows);
		criteria.setFirstResult(from);
		return criteria;
	}

	// added by lishuo at 2013-12-12
	/**
	 * 取得Entity的Criteria,带分页条件和排序参数.
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @param criterions
	 *            查询条件
	 * @return Entity的Criteria
	 */
	public Criteria createCriteria(int from, int rows, String orderBy,
			boolean isAsc, List<Criterion> criterionList) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		for (Criterion c : criterionList) {
			criteria.add(c);
		}
		criteria.setMaxResults(rows);
		criteria.setFirstResult(from);

		if (null != orderBy && !orderBy.isEmpty()) {
			String[] orderByArray = orderBy.split(",");
			for (int i = 0; i < orderByArray.length; i++) {
				if (isAsc)
					// criteria.addOrder(Order.asc(orderBy));
					criteria.addOrder(Order.asc(orderByArray[i]));
				else
					// criteria.addOrder(Order.desc(orderBy));
					criteria.addOrder(Order.desc(orderByArray[i]));
			}
		}
		return criteria;
	}

	public Criteria createUnionCriteria(int from, int rows, String orderBy,
			boolean isAsc, boolean isSubAsc, List<Criterion> criterionList,
			String subObject, List<Criterion> subCriterionList) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		for (Criterion c : criterionList) {
			criteria.add(c);
		}
		criteria.setMaxResults(rows);
		criteria.setFirstResult(from);

		if (!isSubAsc) {
			if (null != orderBy && !orderBy.isEmpty()) {
				if (isAsc)
					criteria.addOrder(Order.asc(orderBy));
				else
					criteria.addOrder(Order.desc(orderBy));
			}
		}

		criteria.createCriteria(subObject, subObject);
		// criteria.createCriteria(subObject);

		for (Criterion ct : subCriterionList) {
			criteria.add(ct);
		}
		if (isSubAsc) {
			if (null != orderBy && !orderBy.isEmpty()) {
				if (isAsc)
					criteria.addOrder(Order.asc(orderBy));
				else
					criteria.addOrder(Order.desc(orderBy));
			}
		}

		return criteria;
	}

	/**
	 * 根据属�?名和属�?值查询对�?
	 * 
	 * @param propertyName
	 *            属�?�?
	 * @param value
	 *            属�?�?
	 * @return 符合条件的对象列�?
	 */
	@SuppressWarnings("unchecked")
	public List<M> findBy(String propertyName, Object value) {
		Criteria Criteria = createCriteria(Restrictions.eq(propertyName, value));
		return Criteria.list();
	}

	/**
	 * 根据属�?名和属�?值查询对�?带分页参�?
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param propertyName
	 *            属�?�?
	 * @param value
	 *            属�?�?
	 * @return 符合条件的对象列�?
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findBy(int from, int rows, String propertyName, Object value) {
		Criteria criteria = this.getSession().createCriteria(getEntityName());
		criteria.add(Restrictions.eq(propertyName, value));
		criteria.setMaxResults(rows);
		criteria.setFirstResult(from);
		return criteria.list();
	}

	/**
	 * 根据属�?名和属�?值统计记录�?�?
	 * 
	 * @param popertyName
	 *            属�?�?
	 * @param value
	 *            属�?�?
	 * @return 符合条件的记录�?�?
	 */
	public long countBy(String propertyName, Object value) {
		return this.countByCriteria(Restrictions.eq(propertyName, value));
	}

	/**
	 * 根据属�?名和属�?值查询对�?带排序参数�?
	 * 
	 * @param propertyName
	 *            属�?�?
	 * @param value
	 *            属�?�?
	 * @param orderBy
	 *            排序参数
	 * @param isAsc
	 *            是否升序
	 * @return 符合条件的对象列�?
	 */
	@SuppressWarnings("unchecked")
	public List<M> findBy(String propertyName, Object value, String orderBy,
			boolean isAsc) {
		return createCriteria(orderBy, isAsc,
				Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据属�?名和属�?值查询对�?带分页参数和排序参数�?
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param propertyName
	 *            属�?�?
	 * @param value
	 *            属�?�?
	 * @param orderBy
	 *            排序参数
	 * @param isAsc
	 *            是否升序
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findBy(int from, int rows, String propertyName,
			Object value, String orderBy, boolean isAsc) {
		Criteria criteria = this.getSession().createCriteria(getEntityName());
		criteria.add(Restrictions.eq(propertyName, value));
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));
		criteria.setMaxResults(rows);
		criteria.setFirstResult(from);
		return criteria.list();
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<M> findByCriteria(DetachedCriteria detachedCriteria) {
	// return detachedCriteria.getExecutableCriteria(this.getSession()).list();
	// }

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<M> findByCriteria(int from, int rows,
	// DetachedCriteria detachedCriteria) {
	// Criteria criteria = detachedCriteria.getExecutableCriteria(this
	// .getSession());
	// criteria.setMaxResults(rows);
	// criteria.setFirstResult(from);
	// return criteria.list();
	// }

	/**
	 * 根据条件列表查询对象
	 * 
	 * @param criterionList
	 *            条件列表
	 * @return 符合条件的对象列�?
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findByCriterionList(List<Criterion> criterionList) {
		return createCriteria(criterionList).list();
	}

	// added by lishuo at 2013-12-12
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 根据条件列表查询对象
	 * 
	 * @param criterionList
	 *            条件列表 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @return 符合条件的对象列�?
	 */
	public List<M> findByCriterionList(int from, int rows, String orderBy,
			boolean isAsc, List<Criterion> criterionList) {
		return createCriteria(from, rows, orderBy, isAsc, criterionList).list();
	}

	/**
	 * 根据条件列表统计记录总数
	 * 
	 * @param criterionList
	 *            条件列表
	 * @return 符合条件的记录�?�?
	 */
	@Override
	public long countByCriterionList(List<Criterion> criterionList) {
		return this.countByCriteria(criterionList);
	}

	/**
	 * 根据查询条件统计总数
	 * 
	 * @param criterions
	 *            查询条件
	 * @return 符合条件的记录�?�?
	 */
	public long countByCriteria(Criterion... criterions) {
		Criteria criteria = createCriteria(criterions);
		return (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
	}

	/**
	 * 根据查询条件统计总数
	 * 
	 * @param criterions
	 *            查询条件列表
	 * @return 符合条件的记录�?�?
	 */
	public long countByCriteria(List<Criterion> criterions) {
		Criteria criteria = createCriteria(criterions);
		return (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
	}

	/**
	 * 根据条件查询对象
	 * 
	 * @param criterions
	 *            查询条件
	 * @return 符合条件的对象列�?
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findByCriteria(Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 根据条件查询对象，带分页参数
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param criterions
	 *            查询条件
	 * @return 符合条件的对象列�?
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findByCriteria(int from, int rows, Criterion... criterions) {
		return this.createCriteria(from, rows, criterions).list();
	}

	/**
	 * 根据条件查询对象，带分页参数和排序字�?
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @param criterions
	 *            查询条件
	 * @return 符合条件的对象列�?
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findByCriteria(int from, int rows, String orderBy,
			boolean isAsc, Criterion... criterions) {
		return this.createCriteria(from, rows, orderBy, isAsc, criterions)
				.list();
	}

	/**
	 * 根据条件Map查询对象，Map为key-value对集合，key为属性名，value为属性�?，等值判�?
	 * 
	 * @param map
	 *            条件Map
	 * @return 符合条件的对象列�?
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findByMap(Map map) {
		return this.createCriteria(Restrictions.allEq(map)).list();
	}

	/**
	 * 根据条件Map查询对象，带分页参数，Map为key-value对集合，key为属性名，value为属性�?，等值判�?
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @param map
	 *            条件Map
	 * @return 符合条件的对象列�?
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<M> findByMap(int from, int rows, Map map) {
		return this.createCriteria(from, rows, Restrictions.allEq(map)).list();
	}

	/**
	 * 根据条件Map统计总数，Map为key-value对集合，key为属性名，value为属性�?，等值判�?
	 * 
	 * @param map
	 *            条件Map
	 * @return 符合条件的记录�?�?
	 */
	@Override
	public long countByMap(Map map) {
		return this.countByCriteria(Restrictions.allEq(map));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findNativeSqlQuery(String sql) {
		return this.getSession().createSQLQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> unionQueryByCriterionList(int pageNum, int rows,
			String orderBy, boolean isAsc, boolean isSubAsc,
			List<Criterion> criterions, String subObject,
			List<Criterion> subCriterions) {
		return this.createUnionCriteria(pageNum, pageNum, orderBy, isAsc,
				isSubAsc, criterions, subObject, subCriterions).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long unionCountByCriteria(List<Criterion> criterions,
			String subObject, List<Criterion> subCriterions) {
		Criteria criteria = getSession().createCriteria(getEntityName());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		criteria.createCriteria(subObject);
		for (Criterion ct : subCriterions) {
			criteria.add(ct);
		}
		return (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
	}

	/**
	 * 根据条件多表查询，带分页参数和排序字�?
	 * 
	 * @param pageNum
	 *            页数，从�?页开�?
	 * @param rows
	 *            每页记录条数
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @param queryVo
	 *            查询条件对象
	 * @param criterions
	 *            复杂查询条件
	 * @param subQueryVo
	 *            关联的子表的属�?�?条件对象键�?�?
	 * @return 符合条件的对象列�?
	 */
	@Override
	public List<M> unionQueryVoList(int pageNum, int rows, String orderBy,
			boolean isAsc, M queryVo, List<Criterion> criterions,
			Map<String, List> subQueryVo) {
		// String hql =
		// "from CProprojectInfo as proprojectInfo where proprojectInfo.grantContractInfo.totalGrantArea=200";
		// //String sql =
		// "select * from Ct_Proproject_Info a , Ct_Grant_Contract_Info b where a.proproject_id=b.proproject_id and b.grant_contractinfo_id=1";
		// Query query = getSession().createQuery(hql);
		// //Query sq = getSession().createSQLQuery(sql);
		// query.list();
		// return query.list();
		// Map<Long,M> tempMap = new HashMap<Long,M>();
		// Long numFlag = 0l;
		// for(Object o: queryVo){
		// Criteria cri = getSession().createCriteria(o.getClass())
		// .add(Example.create(o));
		// .createAlias(relatedKey, relatedKey)
		// .add(Example.create(queryCondition));
		// List lsResult = cri.list();
		// Method method;
		// StringBuffer sb = new StringBuffer();
		// sb.append("get");
		// sb.append(relatedKey.substring(0, 1).toUpperCase());
		// sb.append(relatedKey.substring(1));
		// try{
		// method = o.getClass().getMethod(sb.toString());
		// }catch (Exception e) {
		// method = null;
		// }finally{
		//
		// }
		// for(Object p : lsResult){
		// M m = null;
		// try {
		// m = (M) method.invoke(p);
		// } catch (IllegalArgumentException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// if(null != tempMap){
		// if(!tempMap.containsValue(m)){
		// tempMap.put(numFlag, m);
		// numFlag++;
		// }
		// }
		// }
		// }
		//
		//
		// List<M> returnList = null;
		// Iterator iter = tempMap.entrySet().iterator();
		// while (iter.hasNext()) {
		// Map.Entry entry = (Map.Entry) iter.next();
		// Object val = entry.getValue();
		// returnList.add((M)val);
		// }
		// return returnList;

		/*********************************** 测试成功 ****************************/
		// Criteria cri = getSession().createCriteria(CProprojectInfo.class);
		// cri.createCriteria("grantContractInfos", "grantContractInfos");
		// cri.add(Restrictions.eq("grantContractInfos.totalGrantArea", new
		// Double(200)));
		// cri.list();
		// return cri.list();
		// CProprojectInfo p= new CProprojectInfo();
		// p.setAdRegion("沈阳市大东区");
		// CGrantContractInfo g = new CGrantContractInfo();
		// g.setTotalGrantArea(new Double(200));
		//
		// Criteria cri = getSession().createCriteria(CProprojectInfo.class)
		// .add(Example.create(p))
		// .createCriteria("grantContractInfos", "grantContractInfos")
		// .add(Example.create(g));
		// return cri.list();

		// Criteria cri = getSession().createCriteria(CGrantContractInfo.class);
		// cri.add(Restrictions.eq("totalGrantArea", new Double(100)));
		// cri.createAlias("proProjectInfo", "proProjectInfo");
		// cri.add(Restrictions.eq("proProjectInfo.proprojectName","测试1"));
		// List<CGrantContractInfo> ls = cri.list();
		// List<CProprojectInfo> ls2 = new ArrayList<CProprojectInfo>();
		// for(CGrantContractInfo g:ls){
		// ls2.add(g.getProProjectInfo());
		// }
		// return ls2;

		Criteria criteria = getSession().createCriteria(getEntityName());
		if (null != queryVo) {
			criteria.add(Example.create(queryVo));// 添加查询条件对象
		}

		if (isAsc)// 排序
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));

		criteria.setMaxResults(rows);// 分页
		criteria.setFirstResult(0 + rows * (pageNum - 1));

		if (null != criterions) {// 添加复杂查询条件
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}

		if (null != subQueryVo) {
			Iterator iter = subQueryVo.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				List val = (List) entry.getValue();// 取出关联子表名称

				Serializable queryObject = (Serializable) val.get(0);// 取出关联子表查询条件对象

				List<Criterion> subCriterions = null;
				if (val.size() > 1) {
					subCriterions = (List<Criterion>) val.get(1);// 取出关联子表复杂查询条件
				}

				criteria.createCriteria(key, key).add(
						Example.create(queryObject));

				if (null != subCriterions) {
					for (Criterion c : subCriterions) {
						criteria.add(c);
					}
				}

			}
		}

		return criteria.list();
	}

	@Override
	public Integer getScanRounds() {
		// TODO Auto-generated method stub
		String sql = "SELECT MAX(rounds) FROM protocol_progress";
		List<Object[]> stList = new ArrayList<Object[]>();
		stList = findNativeSqlQuery(sql);
		Object objArray = stList.get(0);
		System.out.println(objArray.toString());
		Integer myInt = Integer.parseInt(objArray.toString());
		return myInt;
	}
}
