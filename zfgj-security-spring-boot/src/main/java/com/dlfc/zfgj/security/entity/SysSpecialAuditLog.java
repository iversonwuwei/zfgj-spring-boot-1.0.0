package com.dlfc.zfgj.security.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.util.Date;

public class SysSpecialAuditLog extends MyDataEntity<SysSpecialAuditLog> implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.PINDEX
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private Integer pindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.KEY_WORD
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private String keyWord;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.AUDIT_ID
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private String auditId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.TYPE
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private Integer type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.FROM_NAME
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private String fromName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.FROM_DATETIME
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private Date fromDatetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.IN_DATETIME
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private Date inDatetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.STATUS
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private Integer status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.REJECT_TYPE
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private Integer rejectType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_special_audit_log.REJECT_REASON
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private String rejectReason;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table sys_special_audit_log
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.PINDEX
	 * @return  the value of sys_special_audit_log.PINDEX
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public Integer getPindex() {
		return pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.PINDEX
	 * @param pindex  the value for sys_special_audit_log.PINDEX
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.KEY_WORD
	 * @return  the value of sys_special_audit_log.KEY_WORD
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.KEY_WORD
	 * @param keyWord  the value for sys_special_audit_log.KEY_WORD
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord == null ? null : keyWord.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.AUDIT_ID
	 * @return  the value of sys_special_audit_log.AUDIT_ID
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public String getAuditId() {
		return auditId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.AUDIT_ID
	 * @param auditId  the value for sys_special_audit_log.AUDIT_ID
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setAuditId(String auditId) {
		this.auditId = auditId == null ? null : auditId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.TYPE
	 * @return  the value of sys_special_audit_log.TYPE
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.TYPE
	 * @param type  the value for sys_special_audit_log.TYPE
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.FROM_NAME
	 * @return  the value of sys_special_audit_log.FROM_NAME
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public String getFromName() {
		return fromName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.FROM_NAME
	 * @param fromName  the value for sys_special_audit_log.FROM_NAME
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setFromName(String fromName) {
		this.fromName = fromName == null ? null : fromName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.FROM_DATETIME
	 * @return  the value of sys_special_audit_log.FROM_DATETIME
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public Date getFromDatetime() {
		return fromDatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.FROM_DATETIME
	 * @param fromDatetime  the value for sys_special_audit_log.FROM_DATETIME
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setFromDatetime(Date fromDatetime) {
		this.fromDatetime = fromDatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.IN_DATETIME
	 * @return  the value of sys_special_audit_log.IN_DATETIME
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public Date getInDatetime() {
		return inDatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.IN_DATETIME
	 * @param inDatetime  the value for sys_special_audit_log.IN_DATETIME
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setInDatetime(Date inDatetime) {
		this.inDatetime = inDatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.STATUS
	 * @return  the value of sys_special_audit_log.STATUS
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.STATUS
	 * @param status  the value for sys_special_audit_log.STATUS
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.REJECT_TYPE
	 * @return  the value of sys_special_audit_log.REJECT_TYPE
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public Integer getRejectType() {
		return rejectType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.REJECT_TYPE
	 * @param rejectType  the value for sys_special_audit_log.REJECT_TYPE
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setRejectType(Integer rejectType) {
		this.rejectType = rejectType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_special_audit_log.REJECT_REASON
	 * @return  the value of sys_special_audit_log.REJECT_REASON
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public String getRejectReason() {
		return rejectReason;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_special_audit_log.REJECT_REASON
	 * @param rejectReason  the value for sys_special_audit_log.REJECT_REASON
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason == null ? null : rejectReason.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_special_audit_log
	 * @mbggenerated  Mon Dec 21 11:22:53 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", pindex=").append(pindex);
		sb.append(", keyWord=").append(keyWord);
		sb.append(", auditId=").append(auditId);
		sb.append(", type=").append(type);
		sb.append(", fromName=").append(fromName);
		sb.append(", fromDatetime=").append(fromDatetime);
		sb.append(", inDatetime=").append(inDatetime);
		sb.append(", status=").append(status);
		sb.append(", rejectType=").append(rejectType);
		sb.append(", rejectReason=").append(rejectReason);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
	
	public String getAuditTypeName() {
		return auditTypeName;
	}

	public void setAuditTypeName(String auditTypeName) {
		this.auditTypeName = auditTypeName;
	}

	private String auditTypeName;
}