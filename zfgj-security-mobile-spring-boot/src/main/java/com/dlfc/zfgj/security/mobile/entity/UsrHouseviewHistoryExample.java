package com.dlfc.zfgj.security.mobile.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsrHouseviewHistoryExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public UsrHouseviewHistoryExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("ID is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ID is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("ID =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("ID <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("ID >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("ID >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("ID <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("ID <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("ID like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("ID not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("ID in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("ID not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("ID between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("ID not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andPindexIsNull() {
			addCriterion("PINDEX is null");
			return (Criteria) this;
		}

		public Criteria andPindexIsNotNull() {
			addCriterion("PINDEX is not null");
			return (Criteria) this;
		}

		public Criteria andPindexEqualTo(Integer value) {
			addCriterion("PINDEX =", value, "pindex");
			return (Criteria) this;
		}

		public Criteria andPindexNotEqualTo(Integer value) {
			addCriterion("PINDEX <>", value, "pindex");
			return (Criteria) this;
		}

		public Criteria andPindexGreaterThan(Integer value) {
			addCriterion("PINDEX >", value, "pindex");
			return (Criteria) this;
		}

		public Criteria andPindexGreaterThanOrEqualTo(Integer value) {
			addCriterion("PINDEX >=", value, "pindex");
			return (Criteria) this;
		}

		public Criteria andPindexLessThan(Integer value) {
			addCriterion("PINDEX <", value, "pindex");
			return (Criteria) this;
		}

		public Criteria andPindexLessThanOrEqualTo(Integer value) {
			addCriterion("PINDEX <=", value, "pindex");
			return (Criteria) this;
		}

		public Criteria andPindexIn(List<Integer> values) {
			addCriterion("PINDEX in", values, "pindex");
			return (Criteria) this;
		}

		public Criteria andPindexNotIn(List<Integer> values) {
			addCriterion("PINDEX not in", values, "pindex");
			return (Criteria) this;
		}

		public Criteria andPindexBetween(Integer value1, Integer value2) {
			addCriterion("PINDEX between", value1, value2, "pindex");
			return (Criteria) this;
		}

		public Criteria andPindexNotBetween(Integer value1, Integer value2) {
			addCriterion("PINDEX not between", value1, value2, "pindex");
			return (Criteria) this;
		}

		public Criteria andUidIsNull() {
			addCriterion("UID is null");
			return (Criteria) this;
		}

		public Criteria andUidIsNotNull() {
			addCriterion("UID is not null");
			return (Criteria) this;
		}

		public Criteria andUidEqualTo(String value) {
			addCriterion("UID =", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotEqualTo(String value) {
			addCriterion("UID <>", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidGreaterThan(String value) {
			addCriterion("UID >", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidGreaterThanOrEqualTo(String value) {
			addCriterion("UID >=", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLessThan(String value) {
			addCriterion("UID <", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLessThanOrEqualTo(String value) {
			addCriterion("UID <=", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLike(String value) {
			addCriterion("UID like", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotLike(String value) {
			addCriterion("UID not like", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidIn(List<String> values) {
			addCriterion("UID in", values, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotIn(List<String> values) {
			addCriterion("UID not in", values, "uid");
			return (Criteria) this;
		}

		public Criteria andUidBetween(String value1, String value2) {
			addCriterion("UID between", value1, value2, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotBetween(String value1, String value2) {
			addCriterion("UID not between", value1, value2, "uid");
			return (Criteria) this;
		}

		public Criteria andHidIsNull() {
			addCriterion("HID is null");
			return (Criteria) this;
		}

		public Criteria andHidIsNotNull() {
			addCriterion("HID is not null");
			return (Criteria) this;
		}

		public Criteria andHidEqualTo(String value) {
			addCriterion("HID =", value, "hid");
			return (Criteria) this;
		}

		public Criteria andHidNotEqualTo(String value) {
			addCriterion("HID <>", value, "hid");
			return (Criteria) this;
		}

		public Criteria andHidGreaterThan(String value) {
			addCriterion("HID >", value, "hid");
			return (Criteria) this;
		}

		public Criteria andHidGreaterThanOrEqualTo(String value) {
			addCriterion("HID >=", value, "hid");
			return (Criteria) this;
		}

		public Criteria andHidLessThan(String value) {
			addCriterion("HID <", value, "hid");
			return (Criteria) this;
		}

		public Criteria andHidLessThanOrEqualTo(String value) {
			addCriterion("HID <=", value, "hid");
			return (Criteria) this;
		}

		public Criteria andHidLike(String value) {
			addCriterion("HID like", value, "hid");
			return (Criteria) this;
		}

		public Criteria andHidNotLike(String value) {
			addCriterion("HID not like", value, "hid");
			return (Criteria) this;
		}

		public Criteria andHidIn(List<String> values) {
			addCriterion("HID in", values, "hid");
			return (Criteria) this;
		}

		public Criteria andHidNotIn(List<String> values) {
			addCriterion("HID not in", values, "hid");
			return (Criteria) this;
		}

		public Criteria andHidBetween(String value1, String value2) {
			addCriterion("HID between", value1, value2, "hid");
			return (Criteria) this;
		}

		public Criteria andHidNotBetween(String value1, String value2) {
			addCriterion("HID not between", value1, value2, "hid");
			return (Criteria) this;
		}

		public Criteria andDtimeIsNull() {
			addCriterion("DTIME is null");
			return (Criteria) this;
		}

		public Criteria andDtimeIsNotNull() {
			addCriterion("DTIME is not null");
			return (Criteria) this;
		}

		public Criteria andDtimeEqualTo(Date value) {
			addCriterion("DTIME =", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeNotEqualTo(Date value) {
			addCriterion("DTIME <>", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeGreaterThan(Date value) {
			addCriterion("DTIME >", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("DTIME >=", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeLessThan(Date value) {
			addCriterion("DTIME <", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeLessThanOrEqualTo(Date value) {
			addCriterion("DTIME <=", value, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeIn(List<Date> values) {
			addCriterion("DTIME in", values, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeNotIn(List<Date> values) {
			addCriterion("DTIME not in", values, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeBetween(Date value1, Date value2) {
			addCriterion("DTIME between", value1, value2, "dtime");
			return (Criteria) this;
		}

		public Criteria andDtimeNotBetween(Date value1, Date value2) {
			addCriterion("DTIME not between", value1, value2, "dtime");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table usr_houseview_history
	 * @mbggenerated  Fri Feb 19 20:08:45 CST 2016
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table usr_houseview_history
     *
     * @mbggenerated do_not_delete_during_merge Fri Feb 19 18:49:56 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}