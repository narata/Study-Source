package main.java.com.ia.base.action;

/**
 * 查询操作�?
 * 
 */
public enum SearchOper {
	eq, ne, lt, le, gt, ge, bw, bn, in, ni, ew, en, cn, nc;

	public static SearchOper getSearchOper(String searchOper) {
		return valueOf(searchOper.toLowerCase());
	}
}
