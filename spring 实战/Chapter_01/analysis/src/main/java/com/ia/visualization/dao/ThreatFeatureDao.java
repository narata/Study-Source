package main.java.com.ia.visualization.dao;

import java.util.List;

import com.ia.base.dao.BaseDao;
import com.ia.visualization.vo.ThreatFeature;

//import com.ia.base.dao.impl.BaseDaoImpl;

public interface ThreatFeatureDao extends BaseDao<ThreatFeature, Integer> {

	/**
	 * 聚类图
	 * 
	 * @return
	 */
    List clusterGraph();

	/**
	 * 表格
	 * 
	 * @return
	 */
    List threadTablecolumn(String ip);

	/**
	 * 表格历史行为验证
	 * 
	 * @return
	 */
    List threadTablecolumnHistory(String ip);

	/**
	 * 表格历史行为验证
	 * 
	 * @return
	 */
    List findByIp(String ip);

	/**
	 * 地图落点
	 * 
	 * @return
	 */
    List graphPoint();
}
