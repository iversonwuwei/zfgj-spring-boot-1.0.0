package com.dlfc.zfgj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsrUserBankcardExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public UsrUserBankcardExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
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

		public Criteria andUserIdIsNull() {
			addCriterion("USER_ID is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("USER_ID is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(String value) {
			addCriterion("USER_ID =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(String value) {
			addCriterion("USER_ID <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(String value) {
			addCriterion("USER_ID >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(String value) {
			addCriterion("USER_ID >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(String value) {
			addCriterion("USER_ID <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(String value) {
			addCriterion("USER_ID <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLike(String value) {
			addCriterion("USER_ID like", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotLike(String value) {
			addCriterion("USER_ID not like", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<String> values) {
			addCriterion("USER_ID in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<String> values) {
			addCriterion("USER_ID not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(String value1, String value2) {
			addCriterion("USER_ID between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(String value1, String value2) {
			addCriterion("USER_ID not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andBankTypeIsNull() {
			addCriterion("bank_type is null");
			return (Criteria) this;
		}

		public Criteria andBankTypeIsNotNull() {
			addCriterion("bank_type is not null");
			return (Criteria) this;
		}

		public Criteria andBankTypeEqualTo(String value) {
			addCriterion("bank_type =", value, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeNotEqualTo(String value) {
			addCriterion("bank_type <>", value, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeGreaterThan(String value) {
			addCriterion("bank_type >", value, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeGreaterThanOrEqualTo(String value) {
			addCriterion("bank_type >=", value, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeLessThan(String value) {
			addCriterion("bank_type <", value, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeLessThanOrEqualTo(String value) {
			addCriterion("bank_type <=", value, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeLike(String value) {
			addCriterion("bank_type like", value, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeNotLike(String value) {
			addCriterion("bank_type not like", value, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeIn(List<String> values) {
			addCriterion("bank_type in", values, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeNotIn(List<String> values) {
			addCriterion("bank_type not in", values, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeBetween(String value1, String value2) {
			addCriterion("bank_type between", value1, value2, "bankType");
			return (Criteria) this;
		}

		public Criteria andBankTypeNotBetween(String value1, String value2) {
			addCriterion("bank_type not between", value1, value2, "bankType");
			return (Criteria) this;
		}

		public Criteria andBaseCodeIsNull() {
			addCriterion("BASE_CODE is null");
			return (Criteria) this;
		}

		public Criteria andBaseCodeIsNotNull() {
			addCriterion("BASE_CODE is not null");
			return (Criteria) this;
		}

		public Criteria andBaseCodeEqualTo(String value) {
			addCriterion("BASE_CODE =", value, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeNotEqualTo(String value) {
			addCriterion("BASE_CODE <>", value, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeGreaterThan(String value) {
			addCriterion("BASE_CODE >", value, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeGreaterThanOrEqualTo(String value) {
			addCriterion("BASE_CODE >=", value, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeLessThan(String value) {
			addCriterion("BASE_CODE <", value, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeLessThanOrEqualTo(String value) {
			addCriterion("BASE_CODE <=", value, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeLike(String value) {
			addCriterion("BASE_CODE like", value, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeNotLike(String value) {
			addCriterion("BASE_CODE not like", value, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeIn(List<String> values) {
			addCriterion("BASE_CODE in", values, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeNotIn(List<String> values) {
			addCriterion("BASE_CODE not in", values, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeBetween(String value1, String value2) {
			addCriterion("BASE_CODE between", value1, value2, "baseCode");
			return (Criteria) this;
		}

		public Criteria andBaseCodeNotBetween(String value1, String value2) {
			addCriterion("BASE_CODE not between", value1, value2, "baseCode");
			return (Criteria) this;
		}

		public Criteria andCardTypeIsNull() {
			addCriterion("CARD_TYPE is null");
			return (Criteria) this;
		}

		public Criteria andCardTypeIsNotNull() {
			addCriterion("CARD_TYPE is not null");
			return (Criteria) this;
		}

		public Criteria andCardTypeEqualTo(String value) {
			addCriterion("CARD_TYPE =", value, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeNotEqualTo(String value) {
			addCriterion("CARD_TYPE <>", value, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeGreaterThan(String value) {
			addCriterion("CARD_TYPE >", value, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeGreaterThanOrEqualTo(String value) {
			addCriterion("CARD_TYPE >=", value, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeLessThan(String value) {
			addCriterion("CARD_TYPE <", value, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeLessThanOrEqualTo(String value) {
			addCriterion("CARD_TYPE <=", value, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeLike(String value) {
			addCriterion("CARD_TYPE like", value, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeNotLike(String value) {
			addCriterion("CARD_TYPE not like", value, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeIn(List<String> values) {
			addCriterion("CARD_TYPE in", values, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeNotIn(List<String> values) {
			addCriterion("CARD_TYPE not in", values, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeBetween(String value1, String value2) {
			addCriterion("CARD_TYPE between", value1, value2, "cardType");
			return (Criteria) this;
		}

		public Criteria andCardTypeNotBetween(String value1, String value2) {
			addCriterion("CARD_TYPE not between", value1, value2, "cardType");
			return (Criteria) this;
		}

		public Criteria andBankNumIsNull() {
			addCriterion("BANK_NUM is null");
			return (Criteria) this;
		}

		public Criteria andBankNumIsNotNull() {
			addCriterion("BANK_NUM is not null");
			return (Criteria) this;
		}

		public Criteria andBankNumEqualTo(String value) {
			addCriterion("BANK_NUM =", value, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumNotEqualTo(String value) {
			addCriterion("BANK_NUM <>", value, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumGreaterThan(String value) {
			addCriterion("BANK_NUM >", value, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumGreaterThanOrEqualTo(String value) {
			addCriterion("BANK_NUM >=", value, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumLessThan(String value) {
			addCriterion("BANK_NUM <", value, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumLessThanOrEqualTo(String value) {
			addCriterion("BANK_NUM <=", value, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumLike(String value) {
			addCriterion("BANK_NUM like", value, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumNotLike(String value) {
			addCriterion("BANK_NUM not like", value, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumIn(List<String> values) {
			addCriterion("BANK_NUM in", values, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumNotIn(List<String> values) {
			addCriterion("BANK_NUM not in", values, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumBetween(String value1, String value2) {
			addCriterion("BANK_NUM between", value1, value2, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankNumNotBetween(String value1, String value2) {
			addCriterion("BANK_NUM not between", value1, value2, "bankNum");
			return (Criteria) this;
		}

		public Criteria andBankMobileIsNull() {
			addCriterion("BANK_MOBILE is null");
			return (Criteria) this;
		}

		public Criteria andBankMobileIsNotNull() {
			addCriterion("BANK_MOBILE is not null");
			return (Criteria) this;
		}

		public Criteria andBankMobileEqualTo(String value) {
			addCriterion("BANK_MOBILE =", value, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileNotEqualTo(String value) {
			addCriterion("BANK_MOBILE <>", value, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileGreaterThan(String value) {
			addCriterion("BANK_MOBILE >", value, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileGreaterThanOrEqualTo(String value) {
			addCriterion("BANK_MOBILE >=", value, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileLessThan(String value) {
			addCriterion("BANK_MOBILE <", value, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileLessThanOrEqualTo(String value) {
			addCriterion("BANK_MOBILE <=", value, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileLike(String value) {
			addCriterion("BANK_MOBILE like", value, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileNotLike(String value) {
			addCriterion("BANK_MOBILE not like", value, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileIn(List<String> values) {
			addCriterion("BANK_MOBILE in", values, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileNotIn(List<String> values) {
			addCriterion("BANK_MOBILE not in", values, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileBetween(String value1, String value2) {
			addCriterion("BANK_MOBILE between", value1, value2, "bankMobile");
			return (Criteria) this;
		}

		public Criteria andBankMobileNotBetween(String value1, String value2) {
			addCriterion("BANK_MOBILE not between", value1, value2,
					"bankMobile");
			return (Criteria) this;
		}

		public Criteria andIsDefaultIsNull() {
			addCriterion("IS_DEFAULT is null");
			return (Criteria) this;
		}

		public Criteria andIsDefaultIsNotNull() {
			addCriterion("IS_DEFAULT is not null");
			return (Criteria) this;
		}

		public Criteria andIsDefaultEqualTo(Short value) {
			addCriterion("IS_DEFAULT =", value, "isDefault");
			return (Criteria) this;
		}

		public Criteria andIsDefaultNotEqualTo(Short value) {
			addCriterion("IS_DEFAULT <>", value, "isDefault");
			return (Criteria) this;
		}

		public Criteria andIsDefaultGreaterThan(Short value) {
			addCriterion("IS_DEFAULT >", value, "isDefault");
			return (Criteria) this;
		}

		public Criteria andIsDefaultGreaterThanOrEqualTo(Short value) {
			addCriterion("IS_DEFAULT >=", value, "isDefault");
			return (Criteria) this;
		}

		public Criteria andIsDefaultLessThan(Short value) {
			addCriterion("IS_DEFAULT <", value, "isDefault");
			return (Criteria) this;
		}

		public Criteria andIsDefaultLessThanOrEqualTo(Short value) {
			addCriterion("IS_DEFAULT <=", value, "isDefault");
			return (Criteria) this;
		}

		public Criteria andIsDefaultIn(List<Short> values) {
			addCriterion("IS_DEFAULT in", values, "isDefault");
			return (Criteria) this;
		}

		public Criteria andIsDefaultNotIn(List<Short> values) {
			addCriterion("IS_DEFAULT not in", values, "isDefault");
			return (Criteria) this;
		}

		public Criteria andIsDefaultBetween(Short value1, Short value2) {
			addCriterion("IS_DEFAULT between", value1, value2, "isDefault");
			return (Criteria) this;
		}

		public Criteria andIsDefaultNotBetween(Short value1, Short value2) {
			addCriterion("IS_DEFAULT not between", value1, value2, "isDefault");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
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
     * This class corresponds to the database table usr_user_bankcard
     *
     * @mbggenerated do_not_delete_during_merge Mon Nov 30 21:28:27 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}