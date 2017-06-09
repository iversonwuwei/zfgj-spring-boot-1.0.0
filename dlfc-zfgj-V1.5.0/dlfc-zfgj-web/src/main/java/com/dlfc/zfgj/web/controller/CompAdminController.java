/**
* @name: CompAdminController.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 用户操作、用户是否启用等
*
* @version: 1.0
* @date : 2015年11月12日 
* @author: Alex.Ge 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月12日       Alex.Ge        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import java.util.List;

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

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.submission.annotation.AvoidDuplicateSubmission;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.modules.sys.entity.Office;
import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.service.SystemService;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.SysRole;
import com.dlfc.zfgj.service.CompService;
import com.dlfc.zfgj.service.SysRoleService;
import com.dlfc.zfgj.service.SysUserService;
import com.google.common.collect.Lists;
import com.housecenter.dlfc.zfgj.common.persistence.Page;

/**
 * @name: CompAdminController
 * @description: 用户操作、用户是否启用等
 * @version 1.0
 * @author Alex.Ge
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/user")
public class CompAdminController extends BaseController{
	/** 用户页面根目录*/
	private static final String JSP_DIR = "dlfc/hr/";
	/**系统service*/
	@Autowired
	private SystemService systemService;
	/**用户信息service*/
	@Autowired
	private SysUserService sysUserService;
	/**公司信息service*/
	@Autowired
	private CompService compService;
	/**角色信息service*/
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 获取用户
	 * @param id 用户ID
	 * @return User 用户对象
	 */
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			// 获取用户对象
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}
	
	/**
	 * 获取用户列表
	 * @param user 用户对象
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求对象
	 */
	@RequiresPermissions("hr:user:view")
	@RequestMapping(value = {"list", ""})
	public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		BasePage<User> page = systemService.findUser(new Page<User>(request, response), user);
		//sys_user表中的compantId 对应的是agt_comp_info里的office_id
		String companyId = user.getCurrentUser().getCompany().getId();
		String superId = "";
		//获取超级管理员adminId
		if(null != companyId && !"".equals(companyId)){
			AgtCompInfo agtCompInfo = compService.findByOffId(companyId);
			if(null != agtCompInfo){
				superId = agtCompInfo.getAdminId();
			}
		}
		model.addAttribute("loginId", user.getCurrentUser().getId());
		model.addAttribute("superId", superId);
		model.addAttribute("page", page);
		return JSP_DIR+"userList";
	}
	
	/**
	 * 获取用户详情
	 * @param user 用户对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:user:view")
	@RequestMapping(value = "form")
	@AvoidDuplicateSubmission(needSaveToken = true)
	public String form(User user, Model model) {
		String cz = user.getCz();
		//添加公司
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(UserUtils.getUser().getCompany());
		}
		//添加部门
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("user", user);
		//筛选role-type 为"comp-role"
		List<SysRole> roleList = sysRoleService.findRole();
		if(null != roleList){
			for(int i =0;i<roleList.size();i++){
				//移除角色
				if(!"comp-role".endsWith(roleList.get(i).getRoleType())){
					roleList.remove(i);
				}
			}
		}

		String detail = (String) getRequestParameter("detail");
		model.addAttribute("detail", detail);
		model.addAttribute("allRoles", roleList);
		model.addAttribute("cz", cz);
		return JSP_DIR+"userForm";
	}
	
	/**
	 * 保存用户
	 * @param user 用户对象
	 * @param request 接收客户端向服务器发出请求
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:user:edit")
	@RequestMapping(value = "save")
	@AvoidDuplicateSubmission(needRemoveToken = true)
	public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		}
		//参数验证
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		//校验登录名
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		//角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (SysRole r : sysRoleService.findRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(new Role(r.getId()));
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		return "redirect:" + "list?repage";
	}
	
	/**
	 * 删除用户
	 * @param user 用户对象
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:user:edit")
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		//判断删除的用户是否是当前登录的用户
		if (UserUtils.getUser().getId().equals(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		//判断是否是超级管理员
		}else if (User.isAdmin(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		}else{
			systemService.deleteUser(user);
			addMessage(redirectAttributes, "删除用户成功");
		}
		return "redirect:"  + "list?repage";
	}
	
	/**
	 * 验证登录名是否有效
	 * @param oldLoginName 
	 * @param loginName
	 * @return "true":用户名存在，"false":用户名不存在
	 */
	@ResponseBody
	@RequiresPermissions("hr:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}
	
	/** 
	 * 设置是否启用用户
	 * @param user 用户对象
	 * @param loginFlag 是否启用 0:禁用，1:启用
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:user:edit")
	@RequestMapping(value = "loginFlag")
	public String loginFlag (User user, String loginFlag,RedirectAttributes redirectAttributes) {		
		// 更新用户信息
		sysUserService.updateLoginFlag(user.getId(),loginFlag);
		//禁用
		if("0".equals(loginFlag)){
			addMessage(redirectAttributes, "禁用成功");
		}
		//启用
		if("1".equals(loginFlag)){
			addMessage(redirectAttributes, "启用成功");
		}
		return "redirect:list?repage";
	}

}
