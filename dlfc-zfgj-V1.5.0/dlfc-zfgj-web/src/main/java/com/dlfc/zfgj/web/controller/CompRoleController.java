/**
 * @name: CompRoleController.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 角色管理
 *
 * @version: 1.0
 * @date : 2015年11月23日 
 * @author: Alex.Ge 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年11月23日       Alex.Ge        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.web.BaseController;

import com.dlfc.admin.modules.sys.entity.Menu;
import com.dlfc.admin.modules.sys.entity.Office;
import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.service.OfficeService;
import com.dlfc.admin.modules.sys.service.SystemService;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.PRScope;
import com.dlfc.zfgj.entity.SysPermissionScope;
import com.dlfc.zfgj.entity.SysPermissionScopeExample;
import com.dlfc.zfgj.entity.SysRole;
import com.dlfc.zfgj.entity.SysRoleExample;
import com.dlfc.zfgj.entity.SysRoleExample.Criteria;
import com.dlfc.zfgj.mapper.SysPermissionScopeMapper;
import com.dlfc.zfgj.mapper.SysRoleMapper;
import com.dlfc.zfgj.service.SysMenuService;
import com.dlfc.zfgj.service.SysRoleService;

/**
 * @name: CompRoleController
 * @description: 角色管理
 * @version 1.0
 * @author Alex.Ge
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/hr/role")
public class CompRoleController extends BaseController {
	/** 角色页面根目录*/
	private static final String JSP_DIR = "dlfc/hr/";
	/** 系统service*/
	@Autowired
	private SystemService systemService;
	/** 部门信息service*/
	@Autowired
	private OfficeService officeService;
	/** 角色信息service*/
	@Autowired
	private SysRoleService sysRoleService;
	/** 角色信息mapper*/
	@Autowired
	private SysRoleMapper sysRoleMapper;
	/** 权限信息mapper*/
	@Autowired
	private SysPermissionScopeMapper sysPermissionScopeMapper;

	/**
	 * 获取角色
	 * @param id 角色ID
	 * @return Role 角色对象
	 */
	@ModelAttribute("role")
	public Role get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			// 获取角色对象
			return systemService.getRole(id);
		} else {
			return new Role();
		}
	}

	/**
	 * 获取角色列表
	 * @param user 用户信息对象
	 * @param role 角色信息对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:role:view")
	@RequestMapping(value = { "list", "" })
	public String list(User user, Role role, Model model, HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Page<Role> page=new Page<Role>(request, response); // 设置分页参数
		 * role.setPage(page); // 执行分页查询
		 * page.setList(sysRoleMapper.findMgrList(role));
		 */
		Office office = new Office();
		office.setId(user.getCurrentUser().getOffice().getId());
		role.setOffice(office);
		model.addAttribute("roleList", sysRoleMapper.findMgrList(role));
		return JSP_DIR + "roleList";
	}

	/**
	 * 获取角色信息
	 * @param role 角色信息对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:role:view")
	@RequestMapping(value = "form")
	public String form(Role role, Model model) {
		//判断部门是否为空，如果空设置为当前登录用户的部门
		if (role.getOffice() == null) {
			role.setOffice(UserUtils.getUser().getOffice());
		}
		//名称
		String name = (String) getRequestParameter("name");
		//英文名称
		String enname = (String) getRequestParameter("enname");
		//校验name
		if (StringUtils.isNoneBlank(name)) {
			role.setName(name);
		}
		//校验enname
		if (StringUtils.isNoneBlank(enname)) {
			role.setEnname(enname);
		}
		model.addAttribute("role", role);
		model.addAttribute("prScope", sysRoleService.prScope(role.getId()));
		//经纪人权限一级菜单
		model.addAttribute("compRoles", SysMenuService.getRoleChildren("经纪人首页", "emp"));
		//经纪人权限所有菜单
		model.addAttribute("menuList", SysMenuService.getRoleAllChildren("经纪人首页", "like"));
		model.addAttribute("officeList", officeService.findAll());
		String detail = (String) getRequestParameter("detail");
		model.addAttribute("detail", detail);
		return JSP_DIR + "roleForm";
	}

	/**
	 * 获取管理员角色信息
	 * @param role 角色信息对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:role:view")
	@RequestMapping(value = "mgrForm")
	public String mgrForm(Role role, Model model) {
		//判断部门是否为空，如果空设置为当前登录用户的部门
		if (role.getOffice() == null) {
			role.setOffice(UserUtils.getUser().getOffice());
		}
		//名称
		String name = (String) getRequestParameter("name");
		//英文名称
		String enname = (String) getRequestParameter("enname");
		//校验name
		if (StringUtils.isNoneBlank(name)) {
			role.setName(name);
		}
		//校验enname
		if (StringUtils.isNoneBlank(enname)) {
			role.setEnname(enname);
		}
		model.addAttribute("role", role);
		//获取管理员权限一级菜单
		model.addAttribute("compRoles", SysMenuService.getRoleChildren("管理员首页", "manager"));
		// model.addAttribute("menuList", systemService.findAllMenu());
		//获取管理员权限所有菜单
		model.addAttribute("menuList", SysMenuService.getRoleAllChildren("管理员首页", "like"));
		model.addAttribute("officeList", officeService.findAll());
		model.addAttribute("name", name);
		model.addAttribute("enname", enname);
		String detail = (String) getRequestParameter("detail");
		model.addAttribute("detail", detail);
		return JSP_DIR + "roleMgrForm";
	}

	/**
	 * 保存角色权限
	 * @param role 角色信息对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param prScope 角色权限
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:role:edit")
	@RequestMapping(value = "save")
	public String save(Role role, Model model, PRScope prScope, RedirectAttributes redirectAttributes) {
		//校验参数
		if (!beanValidator(model, role)) {
			return form(role, model);
		}
		//校验姓名是否存在
		if (!"true".equals(checkName(role.getOldName(), role.getName()))) {
			addMessage(model, "保存角色'" + role.getName() + "'失败, 角色名已存在");
			return form(role, model);
		}
		//校验英文名是否存在
		if (!"true".equals(checkEnname(role.getOldEnname(), role.getEnname()))) {
			addMessage(model, "保存角色'" + role.getName() + "'失败, 英文名已存在");
			return form(role, model);
		}
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");//设置日期格式
		role.setCreateDate(new Date());
		systemService.saveRole(role);
		if (prScope != null) {
			//删除角色对应所有权限
			SysPermissionScope record = new SysPermissionScope();
			record.setDeleteFlg(new Short("1"));
			SysPermissionScopeExample example = new SysPermissionScopeExample();
			com.dlfc.zfgj.entity.SysPermissionScopeExample.Criteria criteria = example.createCriteria();
			criteria.andRoleIdEqualTo(role.getId());
			// 更新权限
			sysPermissionScopeMapper.updateByExampleSelective(record, example);
			//保存角色权限
			if (prScope.getPrScope() != null) {
				for (Menu m : prScope.getPrScope()) {
					if (m.getPermission() != null && m.getEmpScope() != null) {
						SysPermissionScope sps = new SysPermissionScope();
						sps.preInsert();
						sps.setRoleId(role.getId());
						sps.setPermission(m.getPermission());
						sps.setScope(m.getEmpScope());
						sysPermissionScopeMapper.insertSelective(sps);
					}
				}
			}
		}
		addMessage(redirectAttributes, "保存角色'" + role.getName() + "'成功");
		return "redirect:list?repage";
	}

	/**
	 * 删除角色
	 * @param role 角色信息对象
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:role:edit")
	@RequestMapping(value = "delete")
	public String delete(Role role, RedirectAttributes redirectAttributes) {
		// 删除角色
		systemService.deleteRole(role);
		addMessage(redirectAttributes, "删除角色成功");
		return "redirect:list?repage";
	}

	/**
	 * 开启/禁用角色
	 * @param role 角色信息对象
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:role:edit")
	@RequestMapping(value = "useing")
	public String startUsing(Role role, RedirectAttributes redirectAttributes) {
		String usable = "1";
		//当usable=1是启用，usable=0是禁用
		if (role != null && "1".equals(role.getUseable())) {
			usable = "0";
		}
		SysRole record = new SysRole();
		record.setUseable(usable);
		SysRoleExample example = new SysRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(role.getId());
		// 更新角色信息
		sysRoleMapper.updateByExampleSelective(record, example);

		if ("0".equals(usable)) {
			addMessage(redirectAttributes, "禁用角色成功");
		}
		if ("1".equals(usable)) {
			addMessage(redirectAttributes, "启用角色成功");
		}
		return "redirect:list?repage";
	}

	/**
	 * 验证角色名是否有效
	 * @param oldName 原来的名字
	 * @param name 新名字
	 * @return "true":名字可用，"false":名字不可用
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String name) {
		// 姓名非空并且新输入的名字和原名字相同
		if (name != null && name.equals(oldName)) {
			return "true";
		} else if (name != null && systemService.getRoleByName(name) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证角色英文名是否有效
	 * @param oldName 原来的英文名
	 * @param name 新英文名
	 * @return "true":名字可用，"false":名字不可用
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkEnname")
	public String checkEnname(String oldEnname, String enname) {
		// 姓名非空并且新输入的名字和原名字相同
		if (enname != null && enname.equals(oldEnname)) {
			return "true";
		} else if (enname != null && systemService.getRoleByEnname(enname) == null) {
			return "true";
		}
		return "false";
	}
}
