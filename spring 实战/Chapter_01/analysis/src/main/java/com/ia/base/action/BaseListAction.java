package main.java.com.ia.base.action;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.Criterion;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 查询Action基类
 * 
 * 
 */
public abstract class BaseListAction extends ActionSupport {
	private int rows;// 每页多少行，对应rowNum
	private int page;// Get the requested page. By default grid sets this to 1.
	private int total;// 总页�?
	private long records;// 总记录数
	private List gridModel;// 对象列表

	private String sord = "asc"; // sorting order - asc or desc
	private String sidx = "";// 表示用于排序的列名的参数名称

	private String searchField;// Search Field
	private String searchString; // The Search String
	private String searchOper;// The Search Operation
								// ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']

	private String filters;
	private String[] notIndustrialProtocol = { "Dahua Dvr", "SNMP" };
	private String[] notIndustrialProtocolNull = { "", "" };

	public String[] getNotIndustrialProtocolNull() {
		return notIndustrialProtocolNull;
	}

	public void setNotIndustrialProtocolNull(String[] notIndustrialProtocolNull) {
		this.notIndustrialProtocolNull = notIndustrialProtocolNull;
	}

	public String[] getNotIndustrialProtocol() {
		return notIndustrialProtocol;
	}

	public void setNotIndustrialProtocol(String[] notIndustrialProtocol) {
		this.notIndustrialProtocol = notIndustrialProtocol;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		total = (int) Math.ceil((double) records / (double) rows);
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public List getGridModel() {
		return gridModel;
	}

	public void setGridModel(List gridModel) {
		this.gridModel = gridModel;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	public boolean isAsc() {
		return this.sord.equals("asc");
	}

	/**
	 * 根据前台传入fileters生成复杂查询条件的方�?
	 * 
	 * @param filters
	 *            前台查询条件json�?
	 * 
	 * @return 生成的查询条件criteria
	 */
	public List<Criterion> generateSearchCriteriaFromFilters(String filters) {
		List<Criterion> criteria = new ArrayList<Criterion>();
		JSONObject jsonObject = JSONObject.fromObject(filters);
		// String str = new
		// String("{\"groupOp\":\"AND\",\"rules\":[{\"field\":\"adRegionCity\",\"op\":\"eq\",\"data\":\"沈阳市\"}]}");
		// try {
		// jsonObject = JSONObject.fromObject(filters);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// filters =
		// {"rules":[{"field":"drainName","op":"bw","data":"鞍子�?,"datatype":"String"}]}
		JSONArray rules = jsonObject.getJSONArray("rules");

		for (Object obj : rules) {
			JSONObject rule = (JSONObject) obj;

			String field = rule.getString("field");
			String op = rule.getString("op");
			String dataString = rule.getString("data");
			String dataType = rule.getString("datatype");
			Object data = null;
			if (dataType.equals("int")) {
				data = new Long(dataString);
			} else if (dataType.equals("bd")) {
				data = new BigDecimal(dataString);
			} else if (dataType.equals("double")) {
				data = new Double(dataString);
			} else if (dataType.equals("date")) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				try {
					data = sdf.parse(dataString + " 00:00:00");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				data = new String(dataString);
			}

			Criterion criterion = RestrictionUtil.constructCriterion(field,
					data, op);

			if (criterion != null) {
				criteria.add(criterion);
			}
		}

		return criteria;
	}

	/**
	 * 根据前台传入fileters生成复杂查询条件的方�?
	 * 
	 * @param filters
	 *            前台查询条件json�?
	 * 
	 * @return 生成的查询条件List<Object[]>
	 */
	public List<Object[]> generateSearchFromFilters(String filters) {
		List<Object[]> searchf = new ArrayList<Object[]>();

		JSONObject jsonObject = JSONObject.fromObject(filters);
		// String str = new
		// String("{\"groupOp\":\"AND\",\"rules\":[{\"field\":\"adRegionCity\",\"op\":\"eq\",\"data\":\"沈阳市\"}]}");
		// try {
		// jsonObject = JSONObject.fromObject(filters);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// filters =
		// {"rules":[{"field":"drainName","op":"bw","data":"鞍子�?,"datatype":"String"}]}
		JSONArray rules = jsonObject.getJSONArray("rules");

		for (Object obj : rules) {
			JSONObject rule = (JSONObject) obj;

			String field = rule.getString("field");
			String op = rule.getString("op");
			String dataString = rule.getString("data");
			String dataType = rule.getString("datatype");
			Object data = null;
			if (dataType.equals("int")) {
				data = new Long(dataString);
			} else if (dataType.equals("bd")) {
				data = new BigDecimal(dataString);
			} else if (dataType.equals("double")) {
				data = new Double(dataString);
			} else if (dataType.equals("date")) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				try {
					data = sdf.parse(dataString + " 00:00:00");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				data = new String(dataString);
			}
			Object[] s;
			s = new Object[3];
			s[0] = field;
			s[1] = data;
			s[2] = op;

			if (s != null) {
				searchf.add(s);
			}
		}

		return searchf;
	}

	/**
	 * 根据前台传入fileters生成更新Vo对象的方�?
	 * 
	 * @param vObject
	 *            已查询出的要更新的Vo对象 filters 前台查询条件json�?
	 * 
	 * @return 修改过更新字段的Vo对象
	 */

	public <M extends java.io.Serializable> M getVoFromFilters(M vObject,
			String filters) {
		M updateObject = vObject;
		JSONObject jsonObject = null;
		boolean setValue = false;// 是否给该字段赋�?，默认不赋�?
		try {
			jsonObject = JSONObject.fromObject(filters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// JSONObject jsonObject = JSONObject.fromObject(filters);
		JSONArray updateData = jsonObject.getJSONArray("updateData");
		for (Object obj : updateData) {
			JSONObject rule = (JSONObject) obj;

			String field = rule.getString("field");
			String dataString = rule.getString("data");
			String dataType = rule.getString("datatype");
			Object data = null;
			Field fieldObj;
			try {
				fieldObj = updateObject.getClass().getDeclaredField(field);
				if (dataType.equals("int")) {
					if ((dataString != null) && (!dataString.equals(""))) {
						data = new Long(dataString);
						setValue = true;
					}
				} else if (dataType.equals("bd")) {
					if ((dataString != null) && (!dataString.equals(""))) {
						data = new BigDecimal(dataString);
						setValue = true;
					}
				} else if (dataType.equals("double")) {
					if ((dataString != null) && (!dataString.equals(""))) {
						data = new Double(dataString);
						setValue = true;
					}
				} else if (dataType.equals("date")) {
					if ((dataString != null) && (!dataString.equals(""))) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						try {
							data = sdf.parse(dataString);
							setValue = true;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					data = new String(dataString);
					setValue = true;
				}

				try {
					if (setValue) {// 存在值时,才给字段赋�?
						fieldObj.setAccessible(true);
						fieldObj.set(updateObject, data);
					}
					setValue = false;// 重新赋�?判断
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return updateObject;
	}

	/**
	 * 根据前台传入fileter获取要更新的Vo对象ID
	 * 
	 * @param filters
	 *            前台更新操作的json�?
	 * 
	 * @return 要更新Vo对象的Id
	 */

	public Long getIdFromFilters(String filters) {

		JSONObject jsonObject = JSONObject.fromObject(filters);
		String strId = jsonObject.getString("id");
		Long id = new Long(strId);
		return id;
	}
}
