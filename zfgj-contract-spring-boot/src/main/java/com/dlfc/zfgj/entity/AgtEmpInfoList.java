package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AgtEmpInfoList extends MyDataEntity<AgtEmpInfoList> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String status;
	private String mobile;
	private String officename;
	private String rolename;
	private String roles;
	private String companyId;
	private String officeId;
	private String selectValue;
	private String parentId;
	private String roleCode;
	private Date statustime;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the officename
	 */
	public String getOfficename() {
		return officename;
	}
	/**
	 * @param officename the officename to set
	 */
	public void setOfficename(String officename) {
		this.officename = officename;
	}
	/**
	 * @return the rolename
	 */
	public String getRolename() {
		return rolename;
	}
	/**
	 * @param rolename the rolename to set
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}
	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the officeId
	 */
	public String getOfficeId() {
		return officeId;
	}
	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	/**
	 * @return the selectValue
	 */
	public String getSelectValue() {
		return selectValue;
	}
	/**
	 * @param selectValue the selectValue to set
	 */
	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * @return the statustime
	 */
	public Date getStatustime() {
		return statustime;
	}
	/**
	 * @param statustime the statustime to set
	 */
	public void setStatustime(Date statustime) {
		this.statustime = statustime;
	}
	
	private int agtCusInfoCount;
	private int houHouseInfoCount;
	private AgtCusInfo agtCusInfo;
	private List<SysOfficeAreaLink> sysOfficeAreaLink;
	private AgtCompInfo agtCompInfo;
	
	public int getAgtCusInfoCount() {
		return agtCusInfoCount;
	}
	public void setAgtCusInfoCount(int agtCusInfoCount) {
		this.agtCusInfoCount = agtCusInfoCount;
	}
	public int getHouHouseInfoCount() {
		return houHouseInfoCount;
	}
	public void setHouHouseInfoCount(int houHouseInfoCount) {
		this.houHouseInfoCount = houHouseInfoCount;
	}
	public AgtCusInfo getAgtCusInfo() {
		return agtCusInfo;
	}
	public void setAgtCusInfo(AgtCusInfo agtCusInfo) {
		this.agtCusInfo = agtCusInfo;
	}
	public AgtCompInfo getAgtCompInfo() {
		return agtCompInfo;
	}
	public void setAgtCompInfo(AgtCompInfo agtCompInfo) {
		this.agtCompInfo = agtCompInfo;
	}
	public List<SysOfficeAreaLink> getSysOfficeAreaLink() {
		return sysOfficeAreaLink;
	}
	public void setSysOfficeAreaLink(List<SysOfficeAreaLink> sysOfficeAreaLink) {
		this.sysOfficeAreaLink = sysOfficeAreaLink;
	}
}