package com.dlfc.zfgj.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CucNumPoolExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public CucNumPoolExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
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

        public Criteria andNumIsNull() {
            addCriterion("NUM is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("NUM is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("NUM =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("NUM <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("NUM >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("NUM >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("NUM <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("NUM <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("NUM like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("NUM not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("NUM in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("NUM not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("NUM between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("NUM not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andLockUidIsNull() {
            addCriterion("LOCK_UID is null");
            return (Criteria) this;
        }

        public Criteria andLockUidIsNotNull() {
            addCriterion("LOCK_UID is not null");
            return (Criteria) this;
        }

        public Criteria andLockUidEqualTo(String value) {
            addCriterion("LOCK_UID =", value, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidNotEqualTo(String value) {
            addCriterion("LOCK_UID <>", value, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidGreaterThan(String value) {
            addCriterion("LOCK_UID >", value, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidGreaterThanOrEqualTo(String value) {
            addCriterion("LOCK_UID >=", value, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidLessThan(String value) {
            addCriterion("LOCK_UID <", value, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidLessThanOrEqualTo(String value) {
            addCriterion("LOCK_UID <=", value, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidLike(String value) {
            addCriterion("LOCK_UID like", value, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidNotLike(String value) {
            addCriterion("LOCK_UID not like", value, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidIn(List<String> values) {
            addCriterion("LOCK_UID in", values, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidNotIn(List<String> values) {
            addCriterion("LOCK_UID not in", values, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidBetween(String value1, String value2) {
            addCriterion("LOCK_UID between", value1, value2, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockUidNotBetween(String value1, String value2) {
            addCriterion("LOCK_UID not between", value1, value2, "lockUid");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeIsNull() {
            addCriterion("LOCK_DATETIME is null");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeIsNotNull() {
            addCriterion("LOCK_DATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeEqualTo(Date value) {
            addCriterion("LOCK_DATETIME =", value, "lockDatetime");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeNotEqualTo(Date value) {
            addCriterion("LOCK_DATETIME <>", value, "lockDatetime");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeGreaterThan(Date value) {
            addCriterion("LOCK_DATETIME >", value, "lockDatetime");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LOCK_DATETIME >=", value, "lockDatetime");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeLessThan(Date value) {
            addCriterion("LOCK_DATETIME <", value, "lockDatetime");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("LOCK_DATETIME <=", value, "lockDatetime");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeIn(List<Date> values) {
            addCriterion("LOCK_DATETIME in", values, "lockDatetime");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeNotIn(List<Date> values) {
            addCriterion("LOCK_DATETIME not in", values, "lockDatetime");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeBetween(Date value1, Date value2) {
            addCriterion("LOCK_DATETIME between", value1, value2, "lockDatetime");
            return (Criteria) this;
        }

        public Criteria andLockDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("LOCK_DATETIME not between", value1, value2, "lockDatetime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cuc_num_pool
     *
     * @mbggenerated do_not_delete_during_merge Thu Sep 29 11:05:49 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
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

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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
}