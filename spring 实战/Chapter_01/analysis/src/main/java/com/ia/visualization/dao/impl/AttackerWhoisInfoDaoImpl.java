package main.java.com.ia.visualization.dao.impl;

import org.springframework.stereotype.Repository;

import com.ia.base.dao.BaseDao;
import com.ia.base.dao.impl.BaseDaoImpl;
import com.ia.visualization.dao.AttackerWhoisInfoDao;
import com.ia.visualization.vo.AttackerGeoipInfo;
import com.ia.visualization.vo.AttackerWhoisInfo;

//import com.ia.base.dao.impl.BaseDaoImpl;
@Repository(value = "attackerWhoisInfoDao")
public class AttackerWhoisInfoDaoImpl extends BaseDaoImpl<AttackerWhoisInfo, Integer> implements AttackerWhoisInfoDao{

}
