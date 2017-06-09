/**
 * @name: UsrMenuService.java 
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.modules.sys.entity.Menu;
import com.dlfc.zfgj.mapper.SysMenuMapper;

/**
 * @name: UsrMenuService
 * @description: 菜单
 * @version 1.0
 * @author Alex.Ge
 *
 */
@Service("usrMenuService")
public class UsrMenuService {
	/**菜单持久层对象*/
	@Autowired
	private SysMenuMapper sysMenuMapper;

	/**
	 * 获取菜单对象列表
	 * @param menu 菜单对象
	 * @return 菜单对象列表
	 */
	public List<Menu> getMenuList(Menu menu) {
		// 获取经纪人系统菜单之后重构
		List<Menu> menuList = sysMenuMapper.findByUserId(menu);
		return menuList;
	}

}
