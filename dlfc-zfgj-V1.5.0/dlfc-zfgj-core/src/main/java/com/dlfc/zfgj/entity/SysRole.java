package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import com.dlfc.admin.modules.sys.entity.User;

import java.io.Serializable;
import java.util.Date;

public class SysRole extends MyDataEntity<SysRole> implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.office_id
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String officeId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.name
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.enname
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String enname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.role_type
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String roleType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.data_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String dataScope;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.is_sys
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String isSys;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.useable
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String useable;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.create_by
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String createBy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.create_date
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private Date createDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.update_by
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String updateBy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.update_date
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private Date updateDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.remarks
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String remarks;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.del_flag
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String delFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.office_id
	 * @return  the value of sys_role.office_id
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getOfficeId() {
		return officeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.office_id
	 * @param officeId  the value for sys_role.office_id
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId == null ? null : officeId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.name
	 * @return  the value of sys_role.name
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.name
	 * @param name  the value for sys_role.name
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.enname
	 * @return  the value of sys_role.enname
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getEnname() {
		return enname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.enname
	 * @param enname  the value for sys_role.enname
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setEnname(String enname) {
		this.enname = enname == null ? null : enname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.role_type
	 * @return  the value of sys_role.role_type
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getRoleType() {
		return roleType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.role_type
	 * @param roleType  the value for sys_role.role_type
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setRoleType(String roleType) {
		this.roleType = roleType == null ? null : roleType.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.data_scope
	 * @return  the value of sys_role.data_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getDataScope() {
		return dataScope;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.data_scope
	 * @param dataScope  the value for sys_role.data_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setDataScope(String dataScope) {
		this.dataScope = dataScope == null ? null : dataScope.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.is_sys
	 * @return  the value of sys_role.is_sys
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getIsSys() {
		return isSys;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.is_sys
	 * @param isSys  the value for sys_role.is_sys
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setIsSys(String isSys) {
		this.isSys = isSys == null ? null : isSys.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.useable
	 * @return  the value of sys_role.useable
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getUseable() {
		return useable;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.useable
	 * @param useable  the value for sys_role.useable
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setUseable(String useable) {
		this.useable = useable == null ? null : useable.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.create_by
	 * @return  the value of sys_role.create_by
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.create_by
	 * @param createBy  the value for sys_role.create_by
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.create_date
	 * @return  the value of sys_role.create_date
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.create_date
	 * @param createDate  the value for sys_role.create_date
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.update_by
	 * @return  the value of sys_role.update_by
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.update_by
	 * @param updateBy  the value for sys_role.update_by
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.update_date
	 * @return  the value of sys_role.update_date
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.update_date
	 * @param updateDate  the value for sys_role.update_date
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.remarks
	 * @return  the value of sys_role.remarks
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.remarks
	 * @param remarks  the value for sys_role.remarks
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.del_flag
	 * @return  the value of sys_role.del_flag
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.del_flag
	 * @param delFlag  the value for sys_role.del_flag
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", officeId=").append(officeId);
		sb.append(", name=").append(name);
		sb.append(", enname=").append(enname);
		sb.append(", roleType=").append(roleType);
		sb.append(", dataScope=").append(dataScope);
		sb.append(", isSys=").append(isSys);
		sb.append(", useable=").append(useable);
		sb.append(", createBy=").append(createBy);
		sb.append(", createDate=").append(createDate);
		sb.append(", updateBy=").append(updateBy);
		sb.append(", updateDate=").append(updateDate);
		sb.append(", remarks=").append(remarks);
		sb.append(", delFlag=").append(delFlag);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
	
	private User user;		// 根据用户ID查询角色列表

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}