package com.dlfc.zfgj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgtCusCommentExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public AgtCusCommentExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
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

		public Criteria andCustomerIdIsNull() {
			addCriterion("CUSTOMER_ID is null");
			return (Criteria) this;
		}

		public Criteria andCustomerIdIsNotNull() {
			addCriterion("CUSTOMER_ID is not null");
			return (Criteria) this;
		}

		public Criteria andCustomerIdEqualTo(String value) {
			addCriterion("CUSTOMER_ID =", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdNotEqualTo(String value) {
			addCriterion("CUSTOMER_ID <>", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdGreaterThan(String value) {
			addCriterion("CUSTOMER_ID >", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdGreaterThanOrEqualTo(String value) {
			addCriterion("CUSTOMER_ID >=", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdLessThan(String value) {
			addCriterion("CUSTOMER_ID <", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdLessThanOrEqualTo(String value) {
			addCriterion("CUSTOMER_ID <=", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdLike(String value) {
			addCriterion("CUSTOMER_ID like", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdNotLike(String value) {
			addCriterion("CUSTOMER_ID not like", value, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdIn(List<String> values) {
			addCriterion("CUSTOMER_ID in", values, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdNotIn(List<String> values) {
			addCriterion("CUSTOMER_ID not in", values, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdBetween(String value1, String value2) {
			addCriterion("CUSTOMER_ID between", value1, value2, "customerId");
			return (Criteria) this;
		}

		public Criteria andCustomerIdNotBetween(String value1, String value2) {
			addCriterion("CUSTOMER_ID not between", value1, value2,
					"customerId");
			return (Criteria) this;
		}

		public Criteria andEventIsNull() {
			addCriterion("EVENT is null");
			return (Criteria) this;
		}

		public Criteria andEventIsNotNull() {
			addCriterion("EVENT is not null");
			return (Criteria) this;
		}

		public Criteria andEventEqualTo(Integer value) {
			addCriterion("EVENT =", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventNotEqualTo(Integer value) {
			addCriterion("EVENT <>", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventGreaterThan(Integer value) {
			addCriterion("EVENT >", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventGreaterThanOrEqualTo(Integer value) {
			addCriterion("EVENT >=", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventLessThan(Integer value) {
			addCriterion("EVENT <", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventLessThanOrEqualTo(Integer value) {
			addCriterion("EVENT <=", value, "event");
			return (Criteria) this;
		}

		public Criteria andEventIn(List<Integer> values) {
			addCriterion("EVENT in", values, "event");
			return (Criteria) this;
		}

		public Criteria andEventNotIn(List<Integer> values) {
			addCriterion("EVENT not in", values, "event");
			return (Criteria) this;
		}

		public Criteria andEventBetween(Integer value1, Integer value2) {
			addCriterion("EVENT between", value1, value2, "event");
			return (Criteria) this;
		}

		public Criteria andEventNotBetween(Integer value1, Integer value2) {
			addCriterion("EVENT not between", value1, value2, "event");
			return (Criteria) this;
		}

		public Criteria andHouseNoIsNull() {
			addCriterion("HOUSE_NO is null");
			return (Criteria) this;
		}

		public Criteria andHouseNoIsNotNull() {
			addCriterion("HOUSE_NO is not null");
			return (Criteria) this;
		}

		public Criteria andHouseNoEqualTo(String value) {
			addCriterion("HOUSE_NO =", value, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoNotEqualTo(String value) {
			addCriterion("HOUSE_NO <>", value, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoGreaterThan(String value) {
			addCriterion("HOUSE_NO >", value, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoGreaterThanOrEqualTo(String value) {
			addCriterion("HOUSE_NO >=", value, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoLessThan(String value) {
			addCriterion("HOUSE_NO <", value, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoLessThanOrEqualTo(String value) {
			addCriterion("HOUSE_NO <=", value, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoLike(String value) {
			addCriterion("HOUSE_NO like", value, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoNotLike(String value) {
			addCriterion("HOUSE_NO not like", value, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoIn(List<String> values) {
			addCriterion("HOUSE_NO in", values, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoNotIn(List<String> values) {
			addCriterion("HOUSE_NO not in", values, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoBetween(String value1, String value2) {
			addCriterion("HOUSE_NO between", value1, value2, "houseNo");
			return (Criteria) this;
		}

		public Criteria andHouseNoNotBetween(String value1, String value2) {
			addCriterion("HOUSE_NO not between", value1, value2, "houseNo");
			return (Criteria) this;
		}

		public Criteria andEventTimeIsNull() {
			addCriterion("EVENT_TIME is null");
			return (Criteria) this;
		}

		public Criteria andEventTimeIsNotNull() {
			addCriterion("EVENT_TIME is not null");
			return (Criteria) this;
		}

		public Criteria andEventTimeEqualTo(Date value) {
			addCriterion("EVENT_TIME =", value, "eventTime");
			return (Criteria) this;
		}

		public Criteria andEventTimeNotEqualTo(Date value) {
			addCriterion("EVENT_TIME <>", value, "eventTime");
			return (Criteria) this;
		}

		public Criteria andEventTimeGreaterThan(Date value) {
			addCriterion("EVENT_TIME >", value, "eventTime");
			return (Criteria) this;
		}

		public Criteria andEventTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("EVENT_TIME >=", value, "eventTime");
			return (Criteria) this;
		}

		public Criteria andEventTimeLessThan(Date value) {
			addCriterion("EVENT_TIME <", value, "eventTime");
			return (Criteria) this;
		}

		public Criteria andEventTimeLessThanOrEqualTo(Date value) {
			addCriterion("EVENT_TIME <=", value, "eventTime");
			return (Criteria) this;
		}

		public Criteria andEventTimeIn(List<Date> values) {
			addCriterion("EVENT_TIME in", values, "eventTime");
			return (Criteria) this;
		}

		public Criteria andEventTimeNotIn(List<Date> values) {
			addCriterion("EVENT_TIME not in", values, "eventTime");
			return (Criteria) this;
		}

		public Criteria andEventTimeBetween(Date value1, Date value2) {
			addCriterion("EVENT_TIME between", value1, value2, "eventTime");
			return (Criteria) this;
		}

		public Criteria andEventTimeNotBetween(Date value1, Date value2) {
			addCriterion("EVENT_TIME not between", value1, value2, "eventTime");
			return (Criteria) this;
		}

		public Criteria andCommentsIsNull() {
			addCriterion("COMMENTS is null");
			return (Criteria) this;
		}

		public Criteria andCommentsIsNotNull() {
			addCriterion("COMMENTS is not null");
			return (Criteria) this;
		}

		public Criteria andCommentsEqualTo(String value) {
			addCriterion("COMMENTS =", value, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsNotEqualTo(String value) {
			addCriterion("COMMENTS <>", value, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsGreaterThan(String value) {
			addCriterion("COMMENTS >", value, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsGreaterThanOrEqualTo(String value) {
			addCriterion("COMMENTS >=", value, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsLessThan(String value) {
			addCriterion("COMMENTS <", value, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsLessThanOrEqualTo(String value) {
			addCriterion("COMMENTS <=", value, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsLike(String value) {
			addCriterion("COMMENTS like", value, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsNotLike(String value) {
			addCriterion("COMMENTS not like", value, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsIn(List<String> values) {
			addCriterion("COMMENTS in", values, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsNotIn(List<String> values) {
			addCriterion("COMMENTS not in", values, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsBetween(String value1, String value2) {
			addCriterion("COMMENTS between", value1, value2, "comments");
			return (Criteria) this;
		}

		public Criteria andCommentsNotBetween(String value1, String value2) {
			addCriterion("COMMENTS not between", value1, value2, "comments");
			return (Criteria) this;
		}

		public Criteria andCreateUserIsNull() {
			addCriterion("CREATE_USER is null");
			return (Criteria) this;
		}

		public Criteria andCreateUserIsNotNull() {
			addCriterion("CREATE_USER is not null");
			return (Criteria) this;
		}

		public Criteria andCreateUserEqualTo(String value) {
			addCriterion("CREATE_USER =", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserNotEqualTo(String value) {
			addCriterion("CREATE_USER <>", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserGreaterThan(String value) {
			addCriterion("CREATE_USER >", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
			addCriterion("CREATE_USER >=", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserLessThan(String value) {
			addCriterion("CREATE_USER <", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserLessThanOrEqualTo(String value) {
			addCriterion("CREATE_USER <=", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserLike(String value) {
			addCriterion("CREATE_USER like", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserNotLike(String value) {
			addCriterion("CREATE_USER not like", value, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserIn(List<String> values) {
			addCriterion("CREATE_USER in", values, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserNotIn(List<String> values) {
			addCriterion("CREATE_USER not in", values, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserBetween(String value1, String value2) {
			addCriterion("CREATE_USER between", value1, value2, "createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserNotBetween(String value1, String value2) {
			addCriterion("CREATE_USER not between", value1, value2,
					"createUser");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityIsNull() {
			addCriterion("CREATE_USER_IDENTITY is null");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityIsNotNull() {
			addCriterion("CREATE_USER_IDENTITY is not null");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityEqualTo(Short value) {
			addCriterion("CREATE_USER_IDENTITY =", value, "createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityNotEqualTo(Short value) {
			addCriterion("CREATE_USER_IDENTITY <>", value, "createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityGreaterThan(Short value) {
			addCriterion("CREATE_USER_IDENTITY >", value, "createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityGreaterThanOrEqualTo(Short value) {
			addCriterion("CREATE_USER_IDENTITY >=", value, "createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityLessThan(Short value) {
			addCriterion("CREATE_USER_IDENTITY <", value, "createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityLessThanOrEqualTo(Short value) {
			addCriterion("CREATE_USER_IDENTITY <=", value, "createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityIn(List<Short> values) {
			addCriterion("CREATE_USER_IDENTITY in", values,
					"createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityNotIn(List<Short> values) {
			addCriterion("CREATE_USER_IDENTITY not in", values,
					"createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityBetween(Short value1, Short value2) {
			addCriterion("CREATE_USER_IDENTITY between", value1, value2,
					"createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateUserIdentityNotBetween(Short value1,
				Short value2) {
			addCriterion("CREATE_USER_IDENTITY not between", value1, value2,
					"createUserIdentity");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("CREATE_TIME is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("CREATE_TIME is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(Date value) {
			addCriterion("CREATE_TIME =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(Date value) {
			addCriterion("CREATE_TIME <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(Date value) {
			addCriterion("CREATE_TIME >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("CREATE_TIME >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(Date value) {
			addCriterion("CREATE_TIME <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
			addCriterion("CREATE_TIME <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<Date> values) {
			addCriterion("CREATE_TIME in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<Date> values) {
			addCriterion("CREATE_TIME not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(Date value1, Date value2) {
			addCriterion("CREATE_TIME between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
			addCriterion("CREATE_TIME not between", value1, value2,
					"createTime");
			return (Criteria) this;
		}

		public Criteria andModifyUserIsNull() {
			addCriterion("MODIFY_USER is null");
			return (Criteria) this;
		}

		public Criteria andModifyUserIsNotNull() {
			addCriterion("MODIFY_USER is not null");
			return (Criteria) this;
		}

		public Criteria andModifyUserEqualTo(String value) {
			addCriterion("MODIFY_USER =", value, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserNotEqualTo(String value) {
			addCriterion("MODIFY_USER <>", value, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserGreaterThan(String value) {
			addCriterion("MODIFY_USER >", value, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserGreaterThanOrEqualTo(String value) {
			addCriterion("MODIFY_USER >=", value, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserLessThan(String value) {
			addCriterion("MODIFY_USER <", value, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserLessThanOrEqualTo(String value) {
			addCriterion("MODIFY_USER <=", value, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserLike(String value) {
			addCriterion("MODIFY_USER like", value, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserNotLike(String value) {
			addCriterion("MODIFY_USER not like", value, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserIn(List<String> values) {
			addCriterion("MODIFY_USER in", values, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserNotIn(List<String> values) {
			addCriterion("MODIFY_USER not in", values, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserBetween(String value1, String value2) {
			addCriterion("MODIFY_USER between", value1, value2, "modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserNotBetween(String value1, String value2) {
			addCriterion("MODIFY_USER not between", value1, value2,
					"modifyUser");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityIsNull() {
			addCriterion("MODIFY_USER_IDENTITY is null");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityIsNotNull() {
			addCriterion("MODIFY_USER_IDENTITY is not null");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityEqualTo(Short value) {
			addCriterion("MODIFY_USER_IDENTITY =", value, "modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityNotEqualTo(Short value) {
			addCriterion("MODIFY_USER_IDENTITY <>", value, "modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityGreaterThan(Short value) {
			addCriterion("MODIFY_USER_IDENTITY >", value, "modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityGreaterThanOrEqualTo(Short value) {
			addCriterion("MODIFY_USER_IDENTITY >=", value, "modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityLessThan(Short value) {
			addCriterion("MODIFY_USER_IDENTITY <", value, "modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityLessThanOrEqualTo(Short value) {
			addCriterion("MODIFY_USER_IDENTITY <=", value, "modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityIn(List<Short> values) {
			addCriterion("MODIFY_USER_IDENTITY in", values,
					"modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityNotIn(List<Short> values) {
			addCriterion("MODIFY_USER_IDENTITY not in", values,
					"modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityBetween(Short value1, Short value2) {
			addCriterion("MODIFY_USER_IDENTITY between", value1, value2,
					"modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyUserIdentityNotBetween(Short value1,
				Short value2) {
			addCriterion("MODIFY_USER_IDENTITY not between", value1, value2,
					"modifyUserIdentity");
			return (Criteria) this;
		}

		public Criteria andModifyTimeIsNull() {
			addCriterion("MODIFY_TIME is null");
			return (Criteria) this;
		}

		public Criteria andModifyTimeIsNotNull() {
			addCriterion("MODIFY_TIME is not null");
			return (Criteria) this;
		}

		public Criteria andModifyTimeEqualTo(Date value) {
			addCriterion("MODIFY_TIME =", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeNotEqualTo(Date value) {
			addCriterion("MODIFY_TIME <>", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeGreaterThan(Date value) {
			addCriterion("MODIFY_TIME >", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("MODIFY_TIME >=", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeLessThan(Date value) {
			addCriterion("MODIFY_TIME <", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
			addCriterion("MODIFY_TIME <=", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeIn(List<Date> values) {
			addCriterion("MODIFY_TIME in", values, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeNotIn(List<Date> values) {
			addCriterion("MODIFY_TIME not in", values, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeBetween(Date value1, Date value2) {
			addCriterion("MODIFY_TIME between", value1, value2, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
			addCriterion("MODIFY_TIME not between", value1, value2,
					"modifyTime");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgIsNull() {
			addCriterion("DELETE_FLG is null");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgIsNotNull() {
			addCriterion("DELETE_FLG is not null");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgEqualTo(Short value) {
			addCriterion("DELETE_FLG =", value, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgNotEqualTo(Short value) {
			addCriterion("DELETE_FLG <>", value, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgGreaterThan(Short value) {
			addCriterion("DELETE_FLG >", value, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgGreaterThanOrEqualTo(Short value) {
			addCriterion("DELETE_FLG >=", value, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgLessThan(Short value) {
			addCriterion("DELETE_FLG <", value, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgLessThanOrEqualTo(Short value) {
			addCriterion("DELETE_FLG <=", value, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgIn(List<Short> values) {
			addCriterion("DELETE_FLG in", values, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgNotIn(List<Short> values) {
			addCriterion("DELETE_FLG not in", values, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgBetween(Short value1, Short value2) {
			addCriterion("DELETE_FLG between", value1, value2, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andDeleteFlgNotBetween(Short value1, Short value2) {
			addCriterion("DELETE_FLG not between", value1, value2, "deleteFlg");
			return (Criteria) this;
		}

		public Criteria andVersionIsNull() {
			addCriterion("VERSION is null");
			return (Criteria) this;
		}

		public Criteria andVersionIsNotNull() {
			addCriterion("VERSION is not null");
			return (Criteria) this;
		}

		public Criteria andVersionEqualTo(Integer value) {
			addCriterion("VERSION =", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotEqualTo(Integer value) {
			addCriterion("VERSION <>", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionGreaterThan(Integer value) {
			addCriterion("VERSION >", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
			addCriterion("VERSION >=", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLessThan(Integer value) {
			addCriterion("VERSION <", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLessThanOrEqualTo(Integer value) {
			addCriterion("VERSION <=", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionIn(List<Integer> values) {
			addCriterion("VERSION in", values, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotIn(List<Integer> values) {
			addCriterion("VERSION not in", values, "version");
			return (Criteria) this;
		}

		public Criteria andVersionBetween(Integer value1, Integer value2) {
			addCriterion("VERSION between", value1, value2, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotBetween(Integer value1, Integer value2) {
			addCriterion("VERSION not between", value1, value2, "version");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table agt_cus_comment
	 * @mbggenerated  Wed Nov 18 16:55:17 CST 2015
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
     * This class corresponds to the database table agt_cus_comment
     *
     * @mbggenerated do_not_delete_during_merge Wed Nov 18 11:46:28 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}