package main.java.com.ia.visualization.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ia.base.action.BaseListAction;
import com.ia.visualization.service.ThreatFeatureService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = BaseListAction.SUCCESS, type = "json"),
		@Result(name = BaseListAction.ERROR, type = "json") })
public class ThreatFeatureAction extends BaseListAction {

	@Resource(name = "threatFeatureService")
	private ThreatFeatureService threatFeatureService;
	private Map<Object, Object> returnMap = new HashMap<Object, Object>();
	private List<Object> returnList = new ArrayList<Object>();// 存储返回的数据
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public ThreatFeatureService getThreatFeatureService() {
		return threatFeatureService;
	}

	public void setThreatFeatureService(
			ThreatFeatureService threatFeatureService) {
		this.threatFeatureService = threatFeatureService;
	}

	public List<Object> getReturnList() {
		return returnList;
	}

	public void setReturnList(List<Object> returnList) {
		this.returnList = returnList;
	}

	public Map<Object, Object> getReturnMap() {
		return returnMap;
	}

	public void setReturnMap(Map<Object, Object> returnMap) {
		this.returnMap = returnMap;
	}

	@Action(value = "getClusterGraph", results = { @Result(name = SUCCESS, type = "json", params = {
			"root", "returnMap" }) })
	public String getClusterGraph() {
		String countryName = "All";
		returnMap = threatFeatureService.clusterGraph();

		return SUCCESS;
	}

	@Action(value = "threadTable", results = { @Result(name = SUCCESS, type = "json", params = {
			"root", "returnMap" }) })
	public String threadTable() {
		returnMap = threatFeatureService.threadTable(ip);

		return SUCCESS;
	}

	@Action(value = "findByIp", results = { @Result(name = SUCCESS, type = "json", params = {
			"root", "returnMap" }) })
	public String findTableInfoByIp() {
		returnMap = threatFeatureService.findByIp(ip);

		return SUCCESS;
	}

	@Action(value = "graphPoint", results = { @Result(name = SUCCESS, type = "json", params = {
			"root", "returnMap" }) })
	public String graphPoint() {
		returnMap = threatFeatureService.graphPoint();

		return SUCCESS;
	}
}
