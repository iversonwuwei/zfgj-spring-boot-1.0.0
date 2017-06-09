/**
 * @name: SysUserService.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 
 *
 * @version: 1.0
 * @date : 2015年11月5日 
 * @author: Alex.Ge 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年11月5日       Alex.Ge        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.SysUser;
import com.dlfc.zfgj.entity.SysUserExample;
import com.dlfc.zfgj.entity.SysUserExample.Criteria;
import com.dlfc.zfgj.mapper.SysUserMapper;

/**
 * @name: SysUserService
 * @description: 用户
 * @version 1.0
 * @author Alex.Ge
 *
 */
@Service("sysUserService")
public class SysUserService {
	/**系统用户持久层对象*/
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 修改用户登录状态
	 * 
	 * @param userId 用户ID
	 * @param loginFlag 允许登录:"0"，不允许登录:"1"
	 */
	public void updateLoginFlag(String userId, String loginFlag) {
		SysUser user = new SysUser();
		user.setLoginFlag(loginFlag);
		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(userId);
		sysUserMapper.updateByExampleSelective(user, example);
	}

	/**
	 * 修改用户登录密码
	 * 
	 * @param user 用户对象
	 */
	public void update(SysUser user) {
		sysUserMapper.updateByPrimaryKeySelective(user);
	}

}
