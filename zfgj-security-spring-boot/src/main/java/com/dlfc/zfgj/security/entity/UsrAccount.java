package com.dlfc.zfgj.security.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class UsrAccount extends MyDataEntity implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column usr_account.PINDEX
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	private Integer pindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column usr_account.USER_ID
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	private String userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column usr_account.BALANCE
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	private BigDecimal balance;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column usr_account.STATUS
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	private Short status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column usr_account.CAN_USE
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	private BigDecimal canUse;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column usr_account.TOTAL_DEPOSIT
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	private BigDecimal totalDeposit;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table usr_account
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column usr_account.PINDEX
	 * @return  the value of usr_account.PINDEX
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public Integer getPindex() {
		return pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column usr_account.PINDEX
	 * @param pindex  the value for usr_account.PINDEX
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column usr_account.USER_ID
	 * @return  the value of usr_account.USER_ID
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column usr_account.USER_ID
	 * @param userId  the value for usr_account.USER_ID
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column usr_account.BALANCE
	 * @return  the value of usr_account.BALANCE
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column usr_account.BALANCE
	 * @param balance  the value for usr_account.BALANCE
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column usr_account.STATUS
	 * @return  the value of usr_account.STATUS
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column usr_account.STATUS
	 * @param status  the value for usr_account.STATUS
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column usr_account.CAN_USE
	 * @return  the value of usr_account.CAN_USE
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public BigDecimal getCanUse() {
		return canUse;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column usr_account.CAN_USE
	 * @param canUse  the value for usr_account.CAN_USE
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public void setCanUse(BigDecimal canUse) {
		this.canUse = canUse;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column usr_account.TOTAL_DEPOSIT
	 * @return  the value of usr_account.TOTAL_DEPOSIT
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public BigDecimal getTotalDeposit() {
		return totalDeposit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column usr_account.TOTAL_DEPOSIT
	 * @param totalDeposit  the value for usr_account.TOTAL_DEPOSIT
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	public void setTotalDeposit(BigDecimal totalDeposit) {
		this.totalDeposit = totalDeposit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_account
	 * @mbggenerated  Thu Apr 21 11:22:39 CST 2016
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", pindex=").append(pindex);
		sb.append(", userId=").append(userId);
		sb.append(", balance=").append(balance);
		sb.append(", status=").append(status);
		sb.append(", canUse=").append(canUse);
		sb.append(", totalDeposit=").append(totalDeposit);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}