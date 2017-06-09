package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import java.io.Serializable;
import java.util.Date;

public class AgtEmpInfo extends MyDataEntity<AgtEmpInfo> implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.PINDEX
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private Integer pindex;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.AVATAR_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private String avatarId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.COMPANY_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private String companyId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.OFFICE_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private String officeId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.ROLE_CODE
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private String roleCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.PHONE
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private String phone;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.PID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private String pid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.USER_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private String userId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.STATUS
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private Integer status;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.STATUS_TIME
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private Date statusTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.COMMENT
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private String comment;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.GRADE
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private Integer grade;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column agt_emp_info.SORT
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private String sort;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.PINDEX
	 * @return  the value of agt_emp_info.PINDEX
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public Integer getPindex() {
		return pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.PINDEX
	 * @param pindex  the value for agt_emp_info.PINDEX
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.AVATAR_ID
	 * @return  the value of agt_emp_info.AVATAR_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public String getAvatarId() {
		return avatarId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.AVATAR_ID
	 * @param avatarId  the value for agt_emp_info.AVATAR_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId == null ? null : avatarId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.COMPANY_ID
	 * @return  the value of agt_emp_info.COMPANY_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.COMPANY_ID
	 * @param companyId  the value for agt_emp_info.COMPANY_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId == null ? null : companyId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.OFFICE_ID
	 * @return  the value of agt_emp_info.OFFICE_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public String getOfficeId() {
		return officeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.OFFICE_ID
	 * @param officeId  the value for agt_emp_info.OFFICE_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId == null ? null : officeId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.ROLE_CODE
	 * @return  the value of agt_emp_info.ROLE_CODE
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.ROLE_CODE
	 * @param roleCode  the value for agt_emp_info.ROLE_CODE
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode == null ? null : roleCode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.PHONE
	 * @return  the value of agt_emp_info.PHONE
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.PHONE
	 * @param phone  the value for agt_emp_info.PHONE
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.PID
	 * @return  the value of agt_emp_info.PID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.PID
	 * @param pid  the value for agt_emp_info.PID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.USER_ID
	 * @return  the value of agt_emp_info.USER_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.USER_ID
	 * @param userId  the value for agt_emp_info.USER_ID
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.STATUS
	 * @return  the value of agt_emp_info.STATUS
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.STATUS
	 * @param status  the value for agt_emp_info.STATUS
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.STATUS_TIME
	 * @return  the value of agt_emp_info.STATUS_TIME
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public Date getStatusTime() {
		return statusTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.STATUS_TIME
	 * @param statusTime  the value for agt_emp_info.STATUS_TIME
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.COMMENT
	 * @return  the value of agt_emp_info.COMMENT
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.COMMENT
	 * @param comment  the value for agt_emp_info.COMMENT
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.GRADE
	 * @return  the value of agt_emp_info.GRADE
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.GRADE
	 * @param grade  the value for agt_emp_info.GRADE
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column agt_emp_info.SORT
	 * @return  the value of agt_emp_info.SORT
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column agt_emp_info.SORT
	 * @param sort  the value for agt_emp_info.SORT
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	public void setSort(String sort) {
		this.sort = sort == null ? null : sort.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table agt_emp_info
	 * @mbggenerated  Mon Aug 22 14:04:35 CST 2016
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", pindex=").append(pindex);
		sb.append(", avatarId=").append(avatarId);
		sb.append(", companyId=").append(companyId);
		sb.append(", officeId=").append(officeId);
		sb.append(", roleCode=").append(roleCode);
		sb.append(", phone=").append(phone);
		sb.append(", pid=").append(pid);
		sb.append(", userId=").append(userId);
		sb.append(", status=").append(status);
		sb.append(", statusTime=").append(statusTime);
		sb.append(", comment=").append(comment);
		sb.append(", grade=").append(grade);
		sb.append(", sort=").append(sort);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 用户信息
	 */
	private UsrUser usrUser;

	/**
	 * 人员信息
	 */
	private SysPerson sysPerson;
	
	/**
	 * 部门信息
	 */
	private SysOffice sysOffice;
	
	/**
	 * 角色信息
	 */
	private SysRole sysRole;
	
	/**
	 * 附件表信�?
	 */
	private SysInfoAtt sysInfoAtt;
	
	/**
	 * 认证信息
	 */
	private AgtCertLink agtCertLink;
	/**
	 * 公司信息
	 */
	private AgtCompInfo agtCompInfo;

	/**
	 * @return the sysPerson
	 */
	public SysPerson getSysPerson() {
		return sysPerson;
	}

	/**
	 * @param sysPerson the sysPerson to set
	 */
	public void setSysPerson(SysPerson sysPerson) {
		this.sysPerson = sysPerson;
	}

	/**
	 * @return the usrUser
	 */
	public UsrUser getUsrUser() {
		return usrUser;
	}

	/**
	 * @param usrUser the usrUser to set
	 */
	public void setUsrUser(UsrUser usrUser) {
		this.usrUser = usrUser;
	}

	/**
	 * @return the sysOffice
	 */
	public SysOffice getSysOffice() {
		return sysOffice;
	}

	/**
	 * @param sysOffice the sysOffice to set
	 */
	public void setSysOffice(SysOffice sysOffice) {
		this.sysOffice = sysOffice;
	}

	/**
	 * @return the sysRole
	 */
	public SysRole getSysRole() {
		return sysRole;
	}

	/**
	 * @param sysRole the sysRole to set
	 */
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	/**
	 * @return the sysInfoAtt
	 */
	public SysInfoAtt getSysInfoAtt() {
		return sysInfoAtt;
	}

	/**
	 * @param sysInfoAtt the sysInfoAtt to set
	 */
	public void setSysInfoAtt(SysInfoAtt sysInfoAtt) {
		this.sysInfoAtt = sysInfoAtt;
	}

	/**
	 * @return the agtCertLink
	 */
	public AgtCertLink getAgtCertLink() {
		return agtCertLink;
	}

	/**
	 * @param agtCertLink the agtCertLink to set
	 */
	public void setAgtCertLink(AgtCertLink agtCertLink) {
		this.agtCertLink = agtCertLink;
	}

	public AgtCompInfo getAgtCompInfo() {
		return agtCompInfo;
	}

	public void setAgtCompInfo(AgtCompInfo agtCompInfo) {
		this.agtCompInfo = agtCompInfo;
	}
	
}