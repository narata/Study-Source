package main.java.com.ia.visualization.service;

import java.util.Map;

import com.ia.base.service.BaseService;
import com.ia.visualization.vo.ThreatFeature;

//import com.ia.base.dao.impl.BaseDaoImpl;

public interface ThreatFeatureService extends
		BaseService<ThreatFeature, Integer> {

	/**
	 * 聚类图
	 * 
	 * @return
	 */
    Map<Object, Object> clusterGraph();

	/**
	 * 表格
	 * 
	 * @return
	 */
    Map<Object, Object> threadTable(String ip);

	/**
	 * 根据IP查找行为
	 * 
	 * @return
	 */
    Map<Object, Object> findByIp(String ip);

	/**
	 * 地图落点
	 * 
	 * @return
	 */
    Map<Object, Object> graphPoint();
}
