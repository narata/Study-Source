package main.java.com.ia.visualization.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ia.base.dao.impl.BaseDaoImpl;
import com.ia.visualization.dao.ThreatFeatureDao;
import com.ia.visualization.vo.ThreatFeature;

//import com.ia.base.dao.impl.BaseDaoImpl;
@Repository(value = "threatFeatureDao")
public class ThreatFeatureDaoImpl extends BaseDaoImpl<ThreatFeature, Integer>
		implements ThreatFeatureDao {

	@Override
	public List clusterGraph() {
		// TODO Auto-generated method stub
		String sql = "SELECT time_diff,comm_num,ip,threat_level,id FROM threat_feature";

		List<Object[]> spList = new ArrayList<Object[]>();
		spList = findNativeSqlQuery(sql);
		return spList;
	}

	@Override
	public List threadTablecolumn(String ip) {
		// TODO Auto-generated method stub
		String sql = null;
		if (ip == null)
			sql = "SELECT ip,threat_type,threat_level,time_start,id FROM threat_feature ORDER BY time_start DESC";
		else
			sql = "SELECT ip,threat_type,threat_level,time_start,id FROM threat_feature where ip='"
					+ ip + "' ORDER BY time_start DESC";

		List<Object[]> spList = new ArrayList<Object[]>();
		spList = findNativeSqlQuery(sql);
		return spList;
	}

	@Override
	public List threadTablecolumnHistory(String ip) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM threat_feature WHERE ip='" + ip
				+ "'";

		List<Object[]> spList = new ArrayList<Object[]>();
		spList = findNativeSqlQuery(sql);
		return spList;
	}

	@Override
	public List findByIp(String ip) {
		// TODO Auto-generated method stub
		String sql = "SELECT threat_type,time_start FROM threat_feature WHERE ip='"
				+ ip + "'";

		List<Object[]> spList = new ArrayList<Object[]>();
		spList = findNativeSqlQuery(sql);
		return spList;
	}

	@Override
	public List graphPoint() {
		// TODO Auto-generated method stub
		// String sql =
		// "SELECT t1.ip,t2.latitude,t2.longitude FROM threat_feature t1,attacker_geoip_info t2 WHERE t1.ip=t2.source_ip";
		String sql = "SELECT t3.latitude,t3.longitude,GROUP_CONCAT(t3.ip),GROUP_CONCAT(t3.id) "
				+ "FROM (SELECT t2.latitude,t2.longitude,t1.ip,t1.id FROM threat_feature t1 LEFT JOIN  (SELECT DISTINCT latitude,longitude,source_ip FROM attacker_geoip_info) t2 ON t1.ip = t2.source_ip ORDER BY t1.id) t3"
				+ " WHERE t3.latitude<>\"\" GROUP BY t3.latitude,t3.longitude";
		List<Object[]> spList = new ArrayList<Object[]>();
		spList = findNativeSqlQuery(sql);
		return spList;
	}
}
