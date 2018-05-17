package main.java.com.ia.base.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

/**
 * DAO基类，定义基本的CRUD(创建、读取�?更新及删�?操作�?
 * 
 */
public interface BaseDao<M extends java.io.Serializable, PK extends java.io.Serializable> {
	/**
	 * 将业务对象进行持久化操作，即保存到数据库中�?
	 * 
	 * @param model
	 *            �?��持久化的业务对象
	 * @return 持久化后生成的数据库记录主键
	 */
    PK save(M model);

	/**
	 * 如果对象不存在，则插入一条新记录，如果对象已存在，则更新该记录�?
	 * 
	 * @param model
	 *            �?��持久化的业务对象
	 */
    void saveOrUpdate(M model);

	/**
	 * 更新�?��数据库记�?
	 * 
	 * @param model
	 *            �?��更新的业务对�?
	 */
    void update(M model);

	/**
	 * 根据对象ID删除数据库中�?��记录
	 * 
	 * @param id
	 *            对象ID
	 */
    void delete(PK id);

	/**
	 * 删除业务对象对应的数据库记录
	 * 
	 * @param model
	 *            欲删除的业务对象
	 */
    void deleteObject(M model);

	/**
	 * 根据给定对象ID从数据库中加载一个持久化对象�?
	 * 
	 * @param id
	 *            对象ID
	 * @return 与对象ID对应的持久化对象，当数据库中不存在与对象ID对应的记录时返回null�?
	 */
    M get(PK id);

	/**
	 * 查询�?��对象
	 * 
	 * @return �?��对象列表
	 */
    List<M> findAll();

	/**
	 * 查询�?��对象，带分页参数
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * @return �?��对象列表
	 */
    List<M> findAll(int from, int rows);

	/**
	 * 查询�?��对象，带排序参数
	 * 
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @return �?��对象列表
	 */
    List<M> findAll(String orderBy, boolean isAsc);

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
    List<M> findAll(int from, int rows, String orderBy, boolean isAsc);

	/**
	 * 统计�?��对象的数量�?
	 * 
	 * @return 对象集合数量
	 */
    long countAll();

	/**
	 * 将会话中缓存的内容删�?
	 */
    void flush();

	/**
	 * 将会话缓存的内容与数据库同步
	 */
    void clear();

	/**
	 * 判断对象ID是否存在
	 * 
	 * @param id
	 *            对象ID
	 * @return 存在返回true，不存在返回false�?
	 */
    boolean exist(PK id);

	/**
	 * 设置数据来源实体标识
	 * 
	 * @param entityFlag
	 *            实体标识
	 */
	// public void setEntityFlag(String entityFlag);

	/**
	 * 取得Entity的Criteria.
	 * 
	 * @param criterions
	 *            查询条件，可变的Restrictions条件列表
	 * @return Entity的Criteria
	 */
    Criteria createCriteria(Criterion... criterions);

	/**
	 * 取得Entity的Criteria.
	 * 
	 * @param criterions
	 *            查询条件列表
	 * @return Entity的Criteria
	 */
    Criteria createCriteria(List<Criterion> criterions);

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
    Criteria createCriteria(int from, int rows, Criterion... criterions);

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
    Criteria createCriteria(String orderBy, boolean isAsc,
                            Criterion... criterions);

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
    Criteria createCriteria(int from, int rows, String orderBy,
                            boolean isAsc, Criterion... criterions);

	/**
	 * 根据属�?名和属�?值查询对�?
	 * 
	 * @param propertyName
	 *            属�?�?
	 * @param value
	 *            属�?�?
	 * @return 符合条件的对象列�?
	 */
    List<M> findBy(String propertyName, Object value);

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
    List<M> findBy(int from, int rows, String propertyName, Object value);

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
    List<M> findBy(String propertyName, Object value, String orderBy,
                   boolean isAsc);

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
    List<M> findBy(int from, int rows, String propertyName,
                   Object value, String orderBy, boolean isAsc);

	/**
	 * 根据属�?名和属�?值统计记录�?�?
	 * 
	 * @param popertyName
	 *            属�?�?
	 * @param value
	 *            属�?�?
	 * @return 符合条件的记录�?�?
	 */
    long countBy(String propertyName, Object value);

	// added by lishuo at 2013-12-12
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
    List<M> findByCriterionList(int from, int rows, String orderBy,
                                boolean isAsc, List<Criterion> criterionList);

	/**
	 * 根据条件列表查询对象，带分页参数
	 * 
	 * @param from
	 *            起始记录位置
	 * @param rows
	 *            记录条数
	 * 
	 * @param criterionList
	 *            条件列表
	 * @return 符合条件的对象列�?
	 */
    List<M> findByCriterionList(List<Criterion> criterionList);

	/**
	 * 根据条件列表统计记录总数
	 * 
	 * @param criterionList
	 *            条件列表
	 * @return 符合条件的记录�?�?
	 */
    long countByCriterionList(List<Criterion> criterionList);

	/**
	 * 根据条件查询对象
	 * 
	 * @param criterions
	 *            查询条件
	 * @return 符合条件的对象列�?
	 */
    List<M> findByCriteria(Criterion... criterions);

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
    List<M> findByCriteria(int from, int rows, Criterion... criterions);

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
    List<M> findByCriteria(int from, int rows, String orderBy,
                           boolean isAsc, Criterion... criterions);

	/**
	 * 根据查询条件统计总数
	 * 
	 * @param criterions
	 *            查询条件
	 * @return 符合条件的记录�?�?
	 */
    long countByCriteria(Criterion... criterions);

	/**
	 * 根据查询条件统计总数
	 * 
	 * @param criterions
	 *            查询条件列表
	 * @return 符合条件的记录�?�?
	 */
    long countByCriteria(List<Criterion> criterions);

	/**
	 * 根据条件Map查询对象，Map为key-value对集合，key为属性名，value为属性�?，等值判�?
	 * 
	 * @param map
	 *            条件Map
	 * @return 符合条件的记录�?�?
	 */
    List<M> findByMap(Map map);

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
    List<M> findByMap(int from, int rows, Map map);

	/**
	 * 根据条件Map统计总数，Map为key-value对集合，key为属性名，value为属性�?，等值判�?
	 * 
	 * @param map
	 *            条件Map
	 * @return 符合条件的记录�?�?
	 */
    long countByMap(Map map);

	@SuppressWarnings("rawtypes")
    List findNativeSqlQuery(String sql);

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
	 * @param subQueryVo
	 *            关联的子表的属�?�?条件对象键�?�?
	 * @return 符合条件的对象列�?
	 */

    List<M> unionQueryVoList(int pageNum, int rows, String orderBy,
                             boolean isAsc, M queryVo, List<Criterion> criterions,
                             Map<String, List> subQueryVo);

	List<M> unionQueryByCriterionList(int pageNum, int rows,
                                      String orderBy, boolean isAsc, boolean isSubAsc,
                                      List<Criterion> criterions, String subObject,
                                      List<Criterion> subCriterions);

	long unionCountByCriteria(List<Criterion> criterions,
                              String subObject, List<Criterion> subCriterions);

	/**
	 * 获取当轮扫描轮数
	 */
    Integer getScanRounds();
}
