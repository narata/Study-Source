package main.java.com.ia.base.action;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * 查询条件处理工具�?
 * 
 */
public class RestrictionUtil {
	/**
	 * 构�?单个查询条件
	 * 
	 * @param searchField
	 *            查询字段�?
	 * @param searchValue
	 *            查询字段�?
	 * @param searchOper
	 *            查询操作�?
	 * @return 单个查询条件
	 */
	public static Criterion constructCriterion(String searchField,
			Object searchValue, String searchOper) {
		switch (SearchOper.getSearchOper(searchOper)) {
		case eq:// 相等 equal
			return Restrictions.eq(searchField, searchValue);
		case ne:// 不等 not equal
			return Restrictions.ne(searchField, searchValue);
		case lt:// 小于 less
			return Restrictions.lt(searchField, searchValue);
		case le:// 小于等于 less or equal
			return Restrictions.le(searchField, searchValue);
		case gt:// 大于 greater
			return Restrictions.gt(searchField, searchValue);
		case ge: // 大于等于 greater or equal
			return Restrictions.ge(searchField, searchValue);
		case bw:// �?���?begins with
			return Restrictions.like(searchField, searchValue + "%");
		case bn:// 不开始于 does not begin with
			return Restrictions.not(Restrictions.like(searchField, searchValue
					+ "%"));
		case in:// 属于 is in，多值之间用英文逗号�?”隔�?
			return Restrictions.in(searchField,
					((String) searchValue).split(","));
		case ni:// 不属�?is not in
			return Restrictions.not(Restrictions.in(searchField,
					((String) searchValue).split(",")));
		case ew: // 结束�?ends with
			return Restrictions.like(searchField, "%" + searchValue);
		case en:// 不结束于 does not end with
			return Restrictions.not(Restrictions.like(searchField, "%"
					+ searchValue));
		case cn:// 包含 contains
			return Restrictions.like(searchField, "%" + searchValue + "%");
		case nc:// 不包�?does not contain
			return Restrictions.not(Restrictions.like(searchField, "%"
					+ searchValue + "%"));
		default:
			return null;
		}
	}
}
