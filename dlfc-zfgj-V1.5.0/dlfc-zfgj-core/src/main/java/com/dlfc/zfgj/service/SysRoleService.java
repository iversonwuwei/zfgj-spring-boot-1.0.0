/**
 * @name: SysRoleService.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 
 *
 * @version: 1.0
 * @date : 2015年11月27日 
 * @author: Alex.Ge 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年11月27日       Alex.Ge        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.SysPermissionScope;
import com.dlfc.zfgj.entity.SysPermissionScopeExample;
import com.dlfc.zfgj.entity.SysPermissionScopeExample.Criteria;
import com.dlfc.zfgj.entity.SysRole;
import com.dlfc.zfgj.mapper.SysPermissionScopeMapper;
import com.dlfc.zfgj.mapper.SysRoleMapper;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: SysRoleService
 * @description: 角色Service
 * @version 1.0
 * @author Alex.Ge
 *
 */
@Service
public class SysRoleService {
	/**角色持久层对象*/
	@Autowired
	private SysRoleMapper sysRoleMapper;
	/**权限持久层对象*/
	@Autowired
	private SysPermissionScopeMapper sysPermissionScopeMapper;

	/**
	 * 获取角色权限列表
	 * 
	 * @param roleId 角色ID
	 * @return 权限对象列表
	 */
	public List<SysPermissionScope> prScope(String roleId) {
		List<SysPermissionScope> l = new ArrayList<SysPermissionScope>();
		if (roleId == null) {
			return l;
		} else {
			SysPermissionScopeExample e = new SysPermissionScopeExample();
			Criteria c = e.createCriteria();
			c.andRoleIdEqualTo(roleId);
			c.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
			l = sysPermissionScopeMapper.selectByExample(e);
		}
		return l;

	}
	
	/**
	 * 判断判断是否拥有权限
	 * 
	 * @param patten 选中
	 * @param list 权限列表
	 * @param key 权限
	 * @param value 权限范围
	 * @return 无权限:"",有权限:patten
	 */
	public static String yesOrNo(String patten,List<SysPermissionScope> list,String key,String value){
		for(SysPermissionScope s:list){
			if(key.equals(s.getPermission())&&value.equals(s.getScope())){
				return patten;
			}
		}
		return "";
		
	}
	
	/**
	 * 获取管理角色
	 * 
	 * @return 角色对象列表
	 */
	public List<SysRole> findRole(){
		User user = UserUtils.getUser();
		SysRole role = new SysRole();
		role.setOfficeId(user.getOffice().getId());
		return sysRoleMapper.findRole(role);
	}
	
	/**
	 * 获取经纪人角色
	 * 
	 * @return 角色对象列表
	 */
	public List<SysRole> findEmpRole(){
		User user = UserUtils.getUser();
		SysRole role = new SysRole();
		role.setOfficeId(user.getOffice().getId());
		return sysRoleMapper.findEmpRole(role);
	}
}
