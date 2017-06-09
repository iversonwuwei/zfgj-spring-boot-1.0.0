package com.dlfc.zfgj.security.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UsrAccountLog extends MyDataEntity implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.PINDEX
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.MAIN_ID
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private String mainId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.USER_ID
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.BALANCE
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private BigDecimal balance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.STATUS
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private Short status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.CAN_USE
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private BigDecimal canUse;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.TOTAL_DEPOSIT
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private BigDecimal totalDeposit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.OPERATOR
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private String operator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.OPT_TIME
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private Date optTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.OPT_EVENT
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private Integer optEvent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usr_account_log.DCRP
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private String dcrp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table usr_account_log
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.PINDEX
     *
     * @return the value of usr_account_log.PINDEX
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.PINDEX
     *
     * @param pindex the value for usr_account_log.PINDEX
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.MAIN_ID
     *
     * @return the value of usr_account_log.MAIN_ID
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public String getMainId() {
        return mainId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.MAIN_ID
     *
     * @param mainId the value for usr_account_log.MAIN_ID
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setMainId(String mainId) {
        this.mainId = mainId == null ? null : mainId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.USER_ID
     *
     * @return the value of usr_account_log.USER_ID
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.USER_ID
     *
     * @param userId the value for usr_account_log.USER_ID
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.BALANCE
     *
     * @return the value of usr_account_log.BALANCE
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.BALANCE
     *
     * @param balance the value for usr_account_log.BALANCE
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.STATUS
     *
     * @return the value of usr_account_log.STATUS
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.STATUS
     *
     * @param status the value for usr_account_log.STATUS
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.CAN_USE
     *
     * @return the value of usr_account_log.CAN_USE
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public BigDecimal getCanUse() {
        return canUse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.CAN_USE
     *
     * @param canUse the value for usr_account_log.CAN_USE
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setCanUse(BigDecimal canUse) {
        this.canUse = canUse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.TOTAL_DEPOSIT
     *
     * @return the value of usr_account_log.TOTAL_DEPOSIT
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public BigDecimal getTotalDeposit() {
        return totalDeposit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.TOTAL_DEPOSIT
     *
     * @param totalDeposit the value for usr_account_log.TOTAL_DEPOSIT
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setTotalDeposit(BigDecimal totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.OPERATOR
     *
     * @return the value of usr_account_log.OPERATOR
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.OPERATOR
     *
     * @param operator the value for usr_account_log.OPERATOR
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.OPT_TIME
     *
     * @return the value of usr_account_log.OPT_TIME
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public Date getOptTime() {
        return optTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.OPT_TIME
     *
     * @param optTime the value for usr_account_log.OPT_TIME
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.OPT_EVENT
     *
     * @return the value of usr_account_log.OPT_EVENT
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public Integer getOptEvent() {
        return optEvent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.OPT_EVENT
     *
     * @param optEvent the value for usr_account_log.OPT_EVENT
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setOptEvent(Integer optEvent) {
        this.optEvent = optEvent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usr_account_log.DCRP
     *
     * @return the value of usr_account_log.DCRP
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public String getDcrp() {
        return dcrp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usr_account_log.DCRP
     *
     * @param dcrp the value for usr_account_log.DCRP
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    public void setDcrp(String dcrp) {
        this.dcrp = dcrp == null ? null : dcrp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_account_log
     *
     * @mbggenerated Thu Apr 21 11:22:39 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", mainId=").append(mainId);
        sb.append(", userId=").append(userId);
        sb.append(", balance=").append(balance);
        sb.append(", status=").append(status);
        sb.append(", canUse=").append(canUse);
        sb.append(", totalDeposit=").append(totalDeposit);
        sb.append(", operator=").append(operator);
        sb.append(", optTime=").append(optTime);
        sb.append(", optEvent=").append(optEvent);
        sb.append(", dcrp=").append(dcrp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}