/**
 * @name: CompOfficeController.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 部门增删改查等操作
 *
 * @version: 1.0
 * @date : 2015年11月19日 
 * @author: Alex.Ge 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年11月19日       Alex.Ge        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.web.controller;

import com.dlfc.admin.common.config.Global;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.entity.Office;
import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.service.OfficeService;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtEmpInfoList;
import com.dlfc.zfgj.entity.SysAreaAreas;
import com.dlfc.zfgj.entity.SysOffice;
import com.dlfc.zfgj.service.AgtEmpInfoService;
import com.dlfc.zfgj.service.SysAreaAreasService;
import com.dlfc.zfgj.service.SysOfficeAreaLinkService;
import com.dlfc.zfgj.service.SysOfficeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @name: CompOfficeController
 * @description: 部门增删改查等操作
 * @version 1.0
 * @author Alex.Ge
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/office")
public class CompOfficeController extends BaseController {
	/** 部门页面根目录 */
	private static final String JSP_DIR = "dlfc/hr/";
	/** 部门信息service */
	@Autowired
	private OfficeService officeService;
	/** 部门信息service */
	@Autowired
	private SysOfficeService sysOfficeService;
	/** 部门商圈service */
	@Autowired
	private SysOfficeAreaLinkService sysOfficeAreaLinkService;
	/** 经纪人信心service */
	@Autowired
	private AgtEmpInfoService agtEmpInfoService;
	@Autowired
    private SysAreaAreasService sysAreaAreasService;

	/**
	 * 获取部门
	 * @param id 部门ID
	 * @return Office 部门对象
	 */
	@ModelAttribute("office")
	public Office get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			// 获取部门对象
			return officeService.get(id);
		} else {
			return new Office();
		}
	}

	/**
	 * 获取部门默认地址 
	 * @param office 部门对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:office:view")
	@RequestMapping(value = { "" })
	public String index(Office office, Model model) {
		// model.addAttribute("list", officeService.findAll());
		return JSP_DIR + "officeIndex";
	}

	/**
	 * 获取部门列表
	 * @param office 部门信息对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:office:view")
	@RequestMapping(value = { "list" })
	public String list(Office office, Model model) {
		office.setParentIds(office.getParentIds()+"%");
		model.addAttribute("list", officeService.findList(office));
		return JSP_DIR + "officeList";
	}

	/**
	 * 获取部门详情
	 * @param office 部门信息对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:office:view")
	@RequestMapping(value = "form")
	public String form(Office office, Model model) {
	    List<SysAreaAreas> areass = null;
		String refresh = getRequest().getParameter("refresh");
		User user = UserUtils.getUser();
		// 设置parent属性
		if (office.getParent() == null || office.getParent().getId() == null) {
			office.setParent(user.getOffice());
		}
		office.setParent(officeService.get(office.getParent().getId()));
		if (office.getArea() == null) {
			office.setArea(user.getOffice().getArea());
		}
		// 自动获取排序号
		if (StringUtils.isBlank(office.getId()) && office.getParent() != null) {
			int size = 0;
			List<Office> list = officeService.findAll();
			for (int i = 0; i < list.size(); i++) {
				Office e = list.get(i);
				if (e.getParent() != null && e.getParent().getId() != null
						&& e.getParent().getId().equals(office.getParent().getId())) {
					size++;
				}
			}
			office.setCode(office.getParent().getCode()
					+ StringUtils.leftPad(String.valueOf(size > 0 ? size + 1 : 1), 3, "0"));
		}

		//获取office对应的所有区域（business）
		model.addAttribute("officeAreaLinkList", sysOfficeAreaLinkService.find(office.getId()));
		model.addAttribute("office", office);
		model.addAttribute("areass", sysAreaAreasService.getAreaNames());


		if (StringUtils.isNotBlank(refresh)) {
			//1代表正确的提示信息样式
			model.addAttribute("refresh", "1");
		} else {
			//2代表错误的提示信息样式
			model.addAttribute("refresh", "2");
		}
		return JSP_DIR + "officeForm";
	}

	/**
	 * 保存部门
	 * @param request 接收客户端向服务器发出请求
	 * @param office 部门信息对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:office:edit")
	@RequestMapping(value = "save")
	@Transactional(readOnly = false)
	public String save(HttpServletRequest request, Office office, Model model, RedirectAttributes redirectAttributes) {
		//获取前台传递过来的参数
		String oldName = request.getParameter("oldName");
		String name = request.getParameter("name");
		String parentId = request.getParameter("parentId");
		String parentParentId = request.getParameter("parentParentId");
		String parentIds = request.getParameter("parentIds");
		String officeId = request.getParameter("id");
		String code = request.getParameter("code");

		//校验用户名
		if (checkName(oldName, name, parentId, parentParentId, parentIds, officeId).equals("false")) {
			addMessage(redirectAttributes, "机构名称存在!");
			return "redirect:form?id=" + office.getId();
		}
		//参数校验
		if (!beanValidator(model, office)) {
			return form(office, model);
		}
		User user = UserUtils.getUser();
		//判断公司是否被锁定
		if (agtEmpInfoService.isCompLockedBySysUser(user.getId())) {
			addMessage(redirectAttributes, "公司已被锁定，无法修改组织机构!");
			return "redirect:form?id=" + office.getId();
		}

		officeService.save(office);
		SysOffice sysOffice = sysOfficeService.findByPrimaryKey(office.getId());
		sysOffice.setCode(code);

		if (sysOffice != null){
			sysOfficeService.save(sysOffice);
		}

		// 保存区域（area） add by yuanjiwei
		// 1.从前台获取商圈的字符串（以逗号分隔）
		String areaStr = (String) getRequestParameter("area");
		// 2.切商圈字符串存放到字符串数组里面
		if (null != areaStr && "" != areaStr) {
			// 3.删除部门对应的所有商圈信息
			sysOfficeAreaLinkService.delete(office.getId());
			// 4.插入区域对应信息（1对多关系）当返回字符串为“zero”时代表没有选中任何区域
			if (!"zero".equalsIgnoreCase(areaStr)) {
				String[] area = areaStr.split(",");
				int i = area.length;
				while (i > 0) {
					// 循环插入部门与商圈对应关系
					sysOfficeAreaLinkService.insert(office.getId(), area[i - 1]);
					i--;
				}
			}
		}

		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		return "redirect:form?id=" + office.getId() + "&refresh=1";
	}

	/**
	 * 删除部门
	 * @param office 部门对象
	 * @param redirectAttributes 对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:office:edit")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		//判断公司是否被锁定
		if (agtEmpInfoService.isCompLockedBySysUser(user.getId())) {
			addMessage(redirectAttributes, "公司已被锁定，无法修改组织机构");
			return "redirect:form?id=" + office.getId() + "&parentIds=" + office.getParentIds();
		}
		officeService.delete(office);
		addMessage(redirectAttributes, "删除机构成功");
		return "redirect:" + JSP_DIR + "list?id=" + office.getParentId() + "&parentIds=" + office.getParentIds();
	}

	/**
	 * 删除部门
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象 
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败
	 */
	@RequiresPermissions("hr:office:edit")
	@RequestMapping(value = "deleteAjax", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUser();
		//判断公司是否被锁定
		if (agtEmpInfoService.isCompLockedBySysUser(user.getId())) {
			return ajaxFail("公司已被锁定，无法修改组织机构");
		}
		Office office = new Office();
		String id = (String) request.getParameter("id");
		office.setId(id);

		try {
			officeService.delete(office);
		} catch (Exception e) {
			logger.error("CompOfficeController delete", e);
			return ajaxFail("操作失败");
		}
		return ajaxSuccess("操作成功");
	}

	/**
	 * 拖拽后保存部门
	 * @param office 部门
	 * @param model 数据绑定接口,实现类为ExtendedModelMap 
	 * @param redirectAttributes 对象重定向传参
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败
	 */
	@RequiresPermissions("hr:office:edit")
	@RequestMapping(value = "update", produces = "application/json;charset=UTF-8")
	@Transactional(readOnly = false)
	@ResponseBody
	public AjaxResult update(Office office, Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		//判断公司是否被锁定
		if (agtEmpInfoService.isCompLockedBySysUser(user.getId())) {
			return ajaxFail("公司已被锁定，无法修改组织机构");
		}
		String parentId = (String) getRequestParameter("parentId");
		String parentIds = (String) getRequestParameter("parentIds");
		office.setParentIds(parentIds);
		Office parent = new Office();
		parent.setId(parentId);
		office.setParent(parent);
		try {
			// 保存ztree拖拽后的parentId和parentIds
			officeService.save(office);
		} catch (Exception e) {
			logger.error("CompOfficeController update", e);
			return ajaxFail("操作失败");
		}
		return ajaxSuccess("操作成功");

	}

	/**
	 * 获取机构JSON数据
	 * @param extId 排除的ID
	 * @param type 类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param isAll 是否所有
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @return List<Map<String, Object>> MAP对列列表
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		//获取部门列表
		List<Office> list = officeService.findList(isAll);
		//循环组装tree集合
		for (int i = 0; i < list.size(); i++) {
			Office e = list.get(i);
			if ((StringUtils.isBlank(extId)
					|| (extId != null && !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") == -1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
					&& Global.YES.equals(e.getUseable())) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && "3".equals(type)) {
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}

	/**
	 * 获取公司对应经纪人信息
	 * @param role 角色
	 * @param model 数据绑定接口,实现类为ExtendedModelMap 
	 * @return 请求地址
	 */
	@RequestMapping(value = "selectEmpInComp")
	public String selectEmpInComp(Role role, Model model) {
		AgtEmpInfoList empInfo = new AgtEmpInfoList();
		empInfo.setCompanyId(UserUtils.getUser().getCompany().getId());
		empInfo.setStatus("0");
		List<AgtEmpInfoList> empList = agtEmpInfoService.selectEmpINfoByOffice(empInfo);
		model.addAttribute("role", role);
		model.addAttribute("userList", empList);
		model.addAttribute("officeList", officeService.findAll());
		return JSP_DIR + "selectEmpInComp";
	}

	/**
	 * 角色分配 -- 根据部门编号获取用户列表
	 * @param officeId 部门信息ID
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @return List<Map<String, Object>> MAP对象列表
	 */
	@ResponseBody
	@RequestMapping(value = "users")
	public List<Map<String, Object>> users(String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		AgtEmpInfoList empInfo = new AgtEmpInfoList();
		empInfo.setOfficeId(officeId);
		empInfo.setStatus("0");
		for (AgtEmpInfoList e : agtEmpInfoService.selectEmpINfoByOffice(empInfo)) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", 0);
			map.put("name", e.getName());
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 验证部门名是否有效
	 * @param oldName 部门名字
	 * @param name 部门新名字
	 * @param parentId 部门父ID
	 * @param parentParentId 部门父亲的父亲ID
	 * @param parentIds 部门父ID串
	 * @param id 部门Id
	 * @return "true":名称不重复，"false":名称重复
	 */
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String name, String parentId, String parentParentId, String parentIds,
			String id) {
		//修改的时候新名称与旧名称相同
		if (name != null && oldName != null && name.equals(oldName)) {
			return "true";
		} else if (StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(parentParentId)
				&& StringUtils.isNotBlank(parentIds)) {
			//在一级菜单（跟节点）添加修改
			if ("0".equals(parentParentId)) {
				List<SysOffice> sysOfficeList = sysOfficeService.getOfficeByParentId(id, parentId, name);
				if (sysOfficeList != null && sysOfficeList.size() > 0) {
					return "false";
				} else {
					return "true";
				}
			//在二级及以下菜单（门店）添加修改，查找门店所有子节点名字（包含门店本身名字）
			} else if (parentIds.split(",").length >= 2) {
				List<SysOffice> sysOfficeList = null;
				//在二级菜单上添加
				if (parentIds.split(",").length == 2) {
					sysOfficeList = sysOfficeService.getOfficeByParentIdLike(parentId, name);
				} else {//在三级菜单及以下菜单添加修改
					String storeId = parentIds.split(",")[2];
					sysOfficeList = sysOfficeService.getOfficeByParentIdLike(storeId, name);
				}
				if (sysOfficeList != null && sysOfficeList.size() > 0) {
					return "false";
				} else {
					return "true";
				}
			}
		}
		return "false";
	}
}
