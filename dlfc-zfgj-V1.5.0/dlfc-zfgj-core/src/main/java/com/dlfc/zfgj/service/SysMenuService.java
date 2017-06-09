
/**
* @name: SysMenuService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年11月25日 
* @author: Alex.Ge 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月25日       Alex.Ge        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dlfc.admin.common.utils.SpringContextHolder;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.SysMenu;
import com.dlfc.zfgj.entity.SysMenuExample;
import com.dlfc.zfgj.entity.SysMenuExample.Criteria;
import com.dlfc.zfgj.mapper.SysMenuMapper;

/**
 * @name: SysMenuService
 * @description: 系统菜单Service
 * @version 1.0
 * @author Alex.Ge
 *
 */
@Service("sysMenuService")
public class SysMenuService {
	/**系统菜单mapper*/
	private static SysMenuMapper sysMenuMapper = SpringContextHolder.getBean("sysMenuMapper");

	/**
	 * 获取一级菜单权限列表
	 * 
	 * @param name 菜单名字
	 * @param type 管理员类型："manager"
	 * @return 菜单列表
	 */
	public static List<SysMenu> getRoleChildren(String name, String type) {
		List<SysMenu> children = new ArrayList<SysMenu>();
		SysMenuExample example = new SysMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<SysMenu> list = sysMenuMapper.selectByExample(example);
		for (SysMenu menu : list) {
			SysMenuExample e = new SysMenuExample();
			Criteria c = e.createCriteria();
			// 获取管理员基础权限（一级菜单）
			if (type.equals("manager")) {
				c.andDelFlagEqualTo("0");
				c.andParentIdEqualTo(menu.getId());
				c.andIsButtonEqualTo("0");
			} else {// 获取经纪人高级权限（一级菜单）
				c.andParentIdEqualTo(menu.getId());
				c.andIsScopeEqualTo("1");
				c.andIsButtonEqualTo("1");
				c.andDelFlagEqualTo("0");
			}
			List<SysMenu> l = sysMenuMapper.selectByExample(e);
			children.addAll(l);
		}
		return children;

	}

	/**
	 * 根据根节点获取菜单树
	 * 
	 * @param name 菜单名字
	 * @param type 管理员类型："manager"
	 * @return 菜单列表
	 */
	public static List<SysMenu> getRoleAllChildren(String name, String type) {
		List<SysMenu> children = new ArrayList<SysMenu>();
		SysMenuExample example = new SysMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<SysMenu> list = sysMenuMapper.selectByExample(example);
		children.addAll(list);
		for (SysMenu menu : list) {
			SysMenuExample e = new SysMenuExample();
			Criteria c = e.createCriteria();
			if (type.equals("like")) {
				c.andParentIdsLike("%" + menu.getId() + "%");
				c.andDelFlagEqualTo("0");

			} else {
				c.andParentIdEqualTo(menu.getId());
				c.andDelFlagEqualTo("0");
			}

			List<SysMenu> l = sysMenuMapper.selectByExample(e);
			children.addAll(l);
		}
		return children;

	}

	/**
	 * 获取管理员基础权(一级菜单下所有菜单和按钮)
	 * 
	 * @param name 菜单名字
	 * @param type 类型
	 * @return 菜单列表
	 */
	public static List<SysMenu> getRoleMenu(String name, String type) {
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		SysMenuExample example = new SysMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<SysMenu> list = sysMenuMapper.selectByExample(example);
		User user = UserUtils.getUser();
		if (user.isAdmin()) {
			menuList = sysMenuMapper.getMenuList(new SysMenu());
		} else {
			SysMenu m = new SysMenu();
			m.setUserId(user.getId());
			m.setParentId(list.get(0).getId());
			menuList = sysMenuMapper.getMenuList1(m);
		}

		return menuList;

	}

	/**
	 * 获取经纪人基础权限（指定录入房源，录入客户，新建合同）
	 * 
	 * @param name 菜单名字
	 * @return 菜单列表
	 */
	public static List<SysMenu> getRole(String name) {
		List<SysMenu> roleList = new ArrayList<SysMenu>();
		SysMenuExample example = new SysMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<SysMenu> list = sysMenuMapper.selectByExample(example);
		for (SysMenu menu : list) {
			SysMenuExample e = new SysMenuExample();
			Criteria c = e.createCriteria();
			c.andParentIdsLike("%" + menu.getId() + "%");
			c.andIsScopeEqualTo("0");
			c.andIsButtonEqualTo("1");
			c.andDelFlagEqualTo("0");
			roleList = sysMenuMapper.selectByExample(e);
		}
		return roleList;

	}

	/**
	 * 获取经纪人高级权限（根据一级菜单获取下级列表）
	 * 
	 * @param id 菜单ID
	 * @param type 所有子菜单:"like"
	 * @return 菜单列表
	 */
	public static List<SysMenu> getScopeRoleChildren(String id, String type) {
		List<SysMenu> children = new ArrayList<SysMenu>();
		SysMenuExample example = new SysMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<SysMenu> list = sysMenuMapper.selectByExample(example);
		for (SysMenu menu : list) {
			SysMenuExample e = new SysMenuExample();
			Criteria c = e.createCriteria();
			//所有子菜单
			if (type.equals("like")) {
				c.andParentIdsLike("%" + menu.getId() + "%");
				c.andIsShowEqualTo("0");
				c.andIsScopeEqualTo("1");
				c.andIsButtonEqualTo("1");
				c.andDelFlagEqualTo("0");
			} else {//下级菜单
				c.andParentIdEqualTo(menu.getId());
				c.andDelFlagEqualTo("0");
			}

			List<SysMenu> l = sysMenuMapper.selectByExample(e);
			children.addAll(l);
		}
		return children;

	}

}
