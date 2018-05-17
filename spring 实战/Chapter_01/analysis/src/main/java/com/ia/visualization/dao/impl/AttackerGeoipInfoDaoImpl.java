package main.java.com.ia.visualization.dao.impl;

import org.springframework.stereotype.Repository;

import com.ia.base.dao.BaseDao;
import com.ia.base.dao.impl.BaseDaoImpl;
import com.ia.visualization.dao.AttackerGeoipInfoDao;
import com.ia.visualization.vo.AttackerGeoipInfo;

//import com.ia.base.dao.impl.BaseDaoImpl;
@Repository(value = "attackerGeoipInfoDao")
public class AttackerGeoipInfoDaoImpl extends BaseDaoImpl<AttackerGeoipInfo, Integer> implements AttackerGeoipInfoDao{

}
