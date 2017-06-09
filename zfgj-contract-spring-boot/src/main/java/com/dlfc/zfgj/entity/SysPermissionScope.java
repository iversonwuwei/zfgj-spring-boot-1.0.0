package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;

public class SysPermissionScope extends MyDataEntity<SysPermissionScope> implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_permission_scope.ROLE_ID
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String roleId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_permission_scope.SCOPE
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String scope;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_permission_scope.PERMISSION
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private String permission;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table sys_permission_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_permission_scope.ROLE_ID
	 * @return  the value of sys_permission_scope.ROLE_ID
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_permission_scope.ROLE_ID
	 * @param roleId  the value for sys_permission_scope.ROLE_ID
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_permission_scope.SCOPE
	 * @return  the value of sys_permission_scope.SCOPE
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_permission_scope.SCOPE
	 * @param scope  the value for sys_permission_scope.SCOPE
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setScope(String scope) {
		this.scope = scope == null ? null : scope.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_permission_scope.PERMISSION
	 * @return  the value of sys_permission_scope.PERMISSION
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_permission_scope.PERMISSION
	 * @param permission  the value for sys_permission_scope.PERMISSION
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission_scope
	 * @mbggenerated  Fri Nov 27 13:30:03 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", roleId=").append(roleId);
		sb.append(", scope=").append(scope);
		sb.append(", permission=").append(permission);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}