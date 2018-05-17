package main.java.com.ia.visualization.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ia.base.dao.BaseDao;
import com.ia.base.service.impl.BaseServiceImpl;
import com.ia.visualization.dao.ThreatFeatureDao;
import com.ia.visualization.service.ThreatFeatureService;
import com.ia.visualization.vo.ThreatFeature;

//import com.ia.base.dao.impl.BaseDaoImpl;
@Service(value = "threatFeatureService")
public class ThreatFeatureServiceImpl extends
		BaseServiceImpl<ThreatFeature, Integer> implements ThreatFeatureService {

	@Resource(name = "threatFeatureDao")
	private ThreatFeatureDao threatFeatureDao;

	@Override
	public BaseDao<ThreatFeature, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return threatFeatureDao;
	}

	@Override
	public Map<Object, Object> clusterGraph() {
		// TODO Auto-generated method stub
		List<Object> returnList = new ArrayList<Object>();
		List<Object[]> spList = threatFeatureDao.clusterGraph();
		Map<String, List<Object>> groupM = new HashMap<String, List<Object>>();
		for (Object[] objArray : spList) {
			Object[] objG = new Object[5];
			objG[0] = objArray[0];
			objG[1] = objArray[1];
			objG[2] = objArray[4];
			objG[3] = objArray[2];
			objG[4] = objArray[3];
			String level = objArray[3].toString();
			// for (String keyLevel : groupM.keySet()) {
			List<Object> listValue = new ArrayList<Object>();
			if (groupM.containsKey(level)) {
				listValue = groupM.get(level);
				listValue.add(objG);
				groupM.put(level, listValue);
			} else {
				listValue.add(objG);
				groupM.put(level, listValue);
			}

			// }
		}
		List<Object> listOut = new ArrayList<Object>();

		for (String keyLevel : groupM.keySet()) {
			listOut.add(groupM.get(keyLevel));
		}
		Map<Object, Object> returnM = new HashMap<Object, Object>();

		returnM.put("data", listOut);
		return returnM;

		// return returnList;
	}

	@Override
	public Map<Object, Object> threadTable(String ipParam) {
		// TODO Auto-generated method stub
		Map<Object, Object> returnM = new HashMap<Object, Object>();
		List<Object> returnList = new ArrayList<Object>();
		List<Object[]> columnList = threatFeatureDao.threadTablecolumn(ipParam);
		for (Object[] objArray : columnList) {
			Map<String, String> itemM = new HashMap<String, String>();
			itemM.put("ip", objArray[0].toString());
			// Object[] columnTab = new Object[5];
			String ip = objArray[0].toString();
			// columnTab[0] = objArray[0];
			if (objArray[1].toString().equals("0")) {
				itemM.put("threat_type", "Zmap");
				// columnTab[1] = "Zmap";
			} else if (objArray[1].toString().equals("1")) {
				itemM.put("threat_type", "低速DOS/Fuzz");
				// columnTab[1] = "低速DOS/Fuzz";
			} else if (objArray[1].toString().equals("2")) {
				itemM.put("threat_type", "DOS");
				// columnTab[1] = "DOS";
			} else if (objArray[1].toString().equals("3")) {
				itemM.put("threat_type", "低速扫描");
				// columnTab[1] = "低速扫描";
			} else if (objArray[1].toString().equals("4")) {
				itemM.put("threat_type", "低交互探测");
				// columnTab[1] = "低交互探测";
			}
			itemM.put("threat_level", objArray[2].toString());
			// columnTab[2] = objArray[2];
			itemM.put("time_start", objArray[3].toString());
			itemM.put("id", objArray[4].toString());
			// columnTab[3] = objArray[3];
			Integer countIp = Integer.parseInt(threatFeatureDao
					.threadTablecolumnHistory(ip).get(0).toString());
			if (countIp == 1)
				itemM.put("history", "不存在");
			else
				itemM.put("history", "存在");
			// columnTab[4] = countIp;
			returnList.add(itemM);
		}

		returnM.put("data", returnList);
		return returnM;
	}

	@Override
	public Map<Object, Object> findByIp(String ip) {
		// TODO Auto-generated method stub
		List<Object> returnList = new ArrayList<Object>();
		List<Object[]> columnList = threatFeatureDao.findByIp(ip);
		for (Object[] objArray : columnList) {
			Map<String, String> itemM = new HashMap<String, String>();
			if (objArray[0].toString().equals("0")) {
				itemM.put("threat_type", "Zmap");
				// columnTab[1] = "Zmap";
			} else if (objArray[0].toString().equals("1")) {
				itemM.put("threat_type", "低速DOS/Fuzz");
				// columnTab[1] = "低速DOS/Fuzz";
			} else if (objArray[0].toString().equals("2")) {
				itemM.put("threat_type", "DOS");
				// columnTab[1] = "DOS";
			} else if (objArray[0].toString().equals("3")) {
				itemM.put("threat_type", "低速扫描");
				// columnTab[1] = "低速扫描";
			} else if (objArray[0].toString().equals("4")) {
				itemM.put("threat_type", "低交互探测");
				// columnTab[1] = "低交互探测";
			}
			itemM.put("time_start", objArray[1].toString());
			// columnTab[1] = objArray[1];
			returnList.add(itemM);
		}
		Map<Object, Object> returnM = new HashMap<Object, Object>();

		returnM.put("data", returnList);
		return returnM;
	}

	@Override
	public Map<Object, Object> graphPoint() {
		// TODO Auto-generated method stub
		List<Object> returnList = new ArrayList<Object>();
		List<Object[]> columnList = threatFeatureDao.graphPoint();
		Map<Object, Object> returnM = new HashMap<Object, Object>();
		// Object[] tempG = new Object[4];
		for (Object[] objArray : columnList) {
			Object[] columnTab = new Object[4];

			columnTab[0] = objArray[0];
			columnTab[1] = objArray[1];
			String[] strip = objArray[2].toString().split(",");
			columnTab[2] = strip;
			String[] strid = objArray[3].toString().split(",");
			Integer[] integerid = new Integer[strid.length];
			for (int i = 0; i < strid.length; i++) {
				integerid[i] = Integer.parseInt(strid[i]);
			}
			columnTab[3] = integerid;
			returnList.add(columnTab);
		}
		returnM.put("data", returnList);
		return returnM;

	}

}
