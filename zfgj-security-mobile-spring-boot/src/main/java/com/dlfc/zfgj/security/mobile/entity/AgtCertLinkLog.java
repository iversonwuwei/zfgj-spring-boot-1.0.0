package com.dlfc.zfgj.security.mobile.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.util.Date;

public class AgtCertLinkLog extends MyDataEntity implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.PINDEX
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private Integer pindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.CERT_LINK_ID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private String certLinkId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.IS_CERT
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private Integer isCert;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.REJECT_REASON
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private String rejectReason;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.CERT_CARD_NO
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private String certCardNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.CERT_IMG_ID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private String certImgId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.PID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private String pid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.USER_ID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private String userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.OPERATOR
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private String operator;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.OPT_TIME
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private Date optTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.OPT_EVENT
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private Integer optEvent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.DCRP
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private String dcrp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.LOCK_USERID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private String lockUserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_cert_link_log.LOCK_TIME
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private Date lockTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table agt_cert_link_log
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.PINDEX
	 * @return  the value of agt_cert_link_log.PINDEX
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public Integer getPindex() {
		return pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.PINDEX
	 * @param pindex  the value for agt_cert_link_log.PINDEX
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.CERT_LINK_ID
	 * @return  the value of agt_cert_link_log.CERT_LINK_ID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public String getCertLinkId() {
		return certLinkId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.CERT_LINK_ID
	 * @param certLinkId  the value for agt_cert_link_log.CERT_LINK_ID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setCertLinkId(String certLinkId) {
		this.certLinkId = certLinkId == null ? null : certLinkId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.IS_CERT
	 * @return  the value of agt_cert_link_log.IS_CERT
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public Integer getIsCert() {
		return isCert;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.IS_CERT
	 * @param isCert  the value for agt_cert_link_log.IS_CERT
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setIsCert(Integer isCert) {
		this.isCert = isCert;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.REJECT_REASON
	 * @return  the value of agt_cert_link_log.REJECT_REASON
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public String getRejectReason() {
		return rejectReason;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.REJECT_REASON
	 * @param rejectReason  the value for agt_cert_link_log.REJECT_REASON
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason == null ? null : rejectReason.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.CERT_CARD_NO
	 * @return  the value of agt_cert_link_log.CERT_CARD_NO
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public String getCertCardNo() {
		return certCardNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.CERT_CARD_NO
	 * @param certCardNo  the value for agt_cert_link_log.CERT_CARD_NO
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setCertCardNo(String certCardNo) {
		this.certCardNo = certCardNo == null ? null : certCardNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.CERT_IMG_ID
	 * @return  the value of agt_cert_link_log.CERT_IMG_ID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public String getCertImgId() {
		return certImgId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.CERT_IMG_ID
	 * @param certImgId  the value for agt_cert_link_log.CERT_IMG_ID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setCertImgId(String certImgId) {
		this.certImgId = certImgId == null ? null : certImgId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.PID
	 * @return  the value of agt_cert_link_log.PID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.PID
	 * @param pid  the value for agt_cert_link_log.PID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.USER_ID
	 * @return  the value of agt_cert_link_log.USER_ID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.USER_ID
	 * @param userId  the value for agt_cert_link_log.USER_ID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.OPERATOR
	 * @return  the value of agt_cert_link_log.OPERATOR
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.OPERATOR
	 * @param operator  the value for agt_cert_link_log.OPERATOR
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.OPT_TIME
	 * @return  the value of agt_cert_link_log.OPT_TIME
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public Date getOptTime() {
		return optTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.OPT_TIME
	 * @param optTime  the value for agt_cert_link_log.OPT_TIME
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.OPT_EVENT
	 * @return  the value of agt_cert_link_log.OPT_EVENT
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public Integer getOptEvent() {
		return optEvent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.OPT_EVENT
	 * @param optEvent  the value for agt_cert_link_log.OPT_EVENT
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setOptEvent(Integer optEvent) {
		this.optEvent = optEvent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.DCRP
	 * @return  the value of agt_cert_link_log.DCRP
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public String getDcrp() {
		return dcrp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.DCRP
	 * @param dcrp  the value for agt_cert_link_log.DCRP
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setDcrp(String dcrp) {
		this.dcrp = dcrp == null ? null : dcrp.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.LOCK_USERID
	 * @return  the value of agt_cert_link_log.LOCK_USERID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public String getLockUserid() {
		return lockUserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.LOCK_USERID
	 * @param lockUserid  the value for agt_cert_link_log.LOCK_USERID
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setLockUserid(String lockUserid) {
		this.lockUserid = lockUserid == null ? null : lockUserid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_cert_link_log.LOCK_TIME
	 * @return  the value of agt_cert_link_log.LOCK_TIME
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public Date getLockTime() {
		return lockTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_cert_link_log.LOCK_TIME
	 * @param lockTime  the value for agt_cert_link_log.LOCK_TIME
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_cert_link_log
	 * @mbggenerated  Wed Dec 23 11:51:31 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", pindex=").append(pindex);
		sb.append(", certLinkId=").append(certLinkId);
		sb.append(", isCert=").append(isCert);
		sb.append(", rejectReason=").append(rejectReason);
		sb.append(", certCardNo=").append(certCardNo);
		sb.append(", certImgId=").append(certImgId);
		sb.append(", pid=").append(pid);
		sb.append(", userId=").append(userId);
		sb.append(", operator=").append(operator);
		sb.append(", optTime=").append(optTime);
		sb.append(", optEvent=").append(optEvent);
		sb.append(", dcrp=").append(dcrp);
		sb.append(", lockUserid=").append(lockUserid);
		sb.append(", lockTime=").append(lockTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}