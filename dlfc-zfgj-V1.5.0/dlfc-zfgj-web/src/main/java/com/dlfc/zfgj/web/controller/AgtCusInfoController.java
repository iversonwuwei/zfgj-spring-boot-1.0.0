/**
* @name: AgtCusInfoController.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 客户增删改查等操作
*
* @version: 1.0
* @date : 2015年11月10日 
* @author: daiym 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月10日       daiym        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.submission.annotation.AvoidDuplicateSubmission;
import com.dlfc.admin.common.submission.token.TokenHelper;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtCusComment;
import com.dlfc.zfgj.entity.AgtCusInfo;
import com.dlfc.zfgj.entity.AgtRequirementInfo;
import com.dlfc.zfgj.entity.SelectEntity;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.mapper.AgtCusInfoMapper;
import com.dlfc.zfgj.service.AgtCusInfoService;
import com.dlfc.zfgj.service.AgtEmpInfoService;
import com.dlfc.zfgj.service.SysCodeService;
import com.housecenter.dlfc.zfgj.common.persistence.Page;
import com.housecenter.dlfc.zfgj.common.utils.Const;

/**
 * @name: AgtCusInfoController
 * @description: 客户增删改查等操作
 * @version 1.0
 * @author HAN.JIAQI
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/cust")
public class AgtCusInfoController extends BaseController {
	/** 经纪人客户信息service */
	@Autowired
	private AgtCusInfoService agtCusInfoService;
	/** 系统codeservice */
	@Autowired
	private SysCodeService sysCodeService;
	/** 经纪人信息service */
	@Autowired
	private AgtEmpInfoService agtEmpInfoService;
	/** 经纪人客户信息Mapper */
	@Autowired
	private AgtCusInfoMapper agtCusDao;
	/** 客户信息对象session的key值 */
	private static final String KEY_CUSTINFO_LIST_CON_SESSION = "KEY_CUSTINFO_LIST_CON_SESSION";

	/**
	 * 客户列表
	 * 
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @return 请求地址
	 */
	@RequestMapping(value = "/source", produces = "application/json;charset=UTF-8")
	@AvoidDuplicateSubmission(needSaveToken = true)
	public String souManagment(HttpServletRequest request, HttpServletResponse response) {
		AgtCusInfo cust = null;
		// 从其他页面返回本页面时，从session里取出本页面的查询参数
		if (isRepage()) {
			cust = (AgtCusInfo) getSessionAttribute(KEY_CUSTINFO_LIST_CON_SESSION);
		}

		// code名称
		String cond = (String) getRequestParameter("cond");
		// 创建时间
		String butimesort = (String) getRequestParameter("bs");
		// 状态
		String status = (String) getRequestParameter("st");
		// 权限（statu != 1:个人，statu == 1 : 其他权限）
		String statu = (String) getRequestParameter("sa");
		// 当前页面
		String currentPage = (String) getRequestParameter("currentPage");

		BasePage<AgtCusInfo> page = new Page<AgtCusInfo>();
		// 客户信息对象为空
		if (cust == null) {
			cust = new AgtCusInfo();
			// code名称非空
			if (cond != null && !"".equals(cond)) {
				// 获取code列表
				List<SysCode> sysCode = sysCodeService.getCodeByType(cond, "cust_status");
				// code列表非空
				if (sysCode != null && sysCode.size() > 0) {
					String code = sysCode.get(0).getCode();
					// code非空
					if (code != null && code != "") {
						cust.setStatusName(code);
					}
				}
				cust.setName(cond);
			}

			// 状态非空，设置状态
			if (StringUtils.isNoneBlank(status)) {
				cust.setStatus(Short.valueOf(status));
			}

			cust.setCurrentPage(currentPage);

			String sort = "";
			// 创建时间从小到大排序
			if ("asc".equalsIgnoreCase(butimesort)) {
				sort += ",A.CREATE_TIME";
			} else if ("desc".equalsIgnoreCase(butimesort)) {// 创建时间从大到小排序
				sort += ",A.CREATE_TIME DESC";
			}
			if (!"".equals(sort)) {
				sort = sort.substring(1);
			}
			cust.setOrderBy(sort);
		}
		// 个人权限
		if (!"1".equals(statu)) {
			String eid = UserUtils.getUser().getEmpId();
			cust.setCustomerId("'" + eid + "'");
			// 查询客户信息分页对象
			page = agtCusInfoService.selectPagee(new Page<AgtCusInfo>(request, response), cust);
		} else {
			String scope = agtEmpInfoService.getEmpIdStr("emp:house:view");
			cust.setCustomerId(scope);
			// 查询客户信息分页对象
			page = agtCusInfoService.selectPagee(new Page<AgtCusInfo>(request, response), cust);
		}
		setRequestAttribute("page", page);
		setRequestAttribute("st", status);
		setRequestAttribute("bs", butimesort);
		setRequestAttribute("sa", statu);
		// 将查询参数存入session，以便当从其他页面返回时获得这些参数
		setSessionAttribute(KEY_CUSTINFO_LIST_CON_SESSION, cust);

		List<SysCode> status_list = sysCodeService.getCodeByType("cust_status");
		List<SysCode> source_list = sysCodeService.getCodeByType("cust_source");
		List<SysCode> action_list = sysCodeService.getCodeByType("cust_action");

		List<SelectEntity> roleList = new ArrayList<SelectEntity>();

		SelectEntity role = new SelectEntity();
		role.setId(PropertyUtils.getSysVal("role.agent.id"));
		role.setName("经纪人");
		roleList.add(role);
		role = new SelectEntity();
		role.setId(PropertyUtils.getSysVal("role.manager.id"));
		role.setName("经理");
		roleList.add(role);

		// 角色列表
		List<Role> myRoles = UserUtils.getUser().getRoleList();
		String enname = "";
		String roleType = "";
		// 角色列表非空
		if (myRoles != null && myRoles.size() > 0) {
			for (int i = 0; i < myRoles.size(); i++) {
				// 判断登录用户是否是经理
				if ("comp-role".equals(myRoles.get(i).getRoleType())
						|| "assignment".equals(myRoles.get(i).getRoleType())) {
					roleType = myRoles.get(i).getRoleType();
				}
				if ("manager".equals(myRoles.get(i).getEnname())) {
					enname = myRoles.get(i).getEnname();
				}
			}
		}
		setRequestAttribute("enname", enname);
		setRequestAttribute("roleType", roleType);
		setRequestAttribute("roleList", roleList);
		setRequestAttribute("status_list", status_list);
		setRequestAttribute("source_list", source_list);
		setRequestAttribute("action_list", action_list);

		String retStr = "";
		// 部门客源页面
		if ("1".equals(statu)) {
			retStr = "dlfc/cust/comsource";
		} else {// 个人客源页面
			retStr = "dlfc/cust/persource";
		}

		return retStr;

	}

	/**
	 * 客户管理删除客户
	 * 
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:cust:del")
	@RequestMapping(value = "/delc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String delCus(Model model, RedirectAttributes redirectAttributes) {
		// 客户信息ID
		String checkHid = (String) getRequestParameter("custid");
		// sa == 1:部门客源，sa != 1 个人客源
		String sa = (String) getRequestParameter("hsa");
		// st = 1 或"":全部未签，st = 2:关注，st = 4:签约
		String st = (String) getRequestParameter("st");

		String[] hisArr = checkHid.split("_");
		// 经纪人ID字符串
		String scope = agtEmpInfoService.getEmpIdStr("emp:house:view");
		for (int i = 0; i < hisArr.length; i++) {
			String id = hisArr[i];
			AgtCusInfo info = new AgtCusInfo();
			info = agtCusDao.selectByPrimaryKey(id);
			// 经纪人ID是否在经纪人字符串中
			if (scope.contains(info.getEid())) {
				agtCusInfoService.deleteCust(info);
			} else {
				addError(redirectAttributes, "选定客源不属于当前登录用户，不可删除！");
			}
		}

		return "redirect:" + adminPath + "/cust/source?sa=" + sa + "&st=" + st;
	}

	/**
	 * 客户管理添加客户需求
	 * 
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequestMapping(value = "/addre", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String addReq(Model model, RedirectAttributes redirectAttributes) {
		// 客户ID
		String id = (String) getRequestParameter("custid");
		// 需求
		String require = (String) getRequestParameter("require");
		AgtRequirementInfo info = new AgtRequirementInfo();
		info.setCustomerId(id);
		info.setRequirement(require);
		// 需求长度校验
		if (require.length() > 10) {
			addError(redirectAttributes, "请输入10字以内");
		} else {
			agtCusInfoService.insertRequire(info);
		}
		return "redirect:" + adminPath + "/cust/source?repage";
	}

	/**
	 * 客户管理删除客户需求
	 * 
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:cust:view")
	@RequestMapping(value = "/delre", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String delReq(Model model, RedirectAttributes redirectAttributes) {
		// 客户ID
		String id = (String) getRequestParameter("custid");
		// 需求
		String require = (String) getRequestParameter("require");

		AgtRequirementInfo info = new AgtRequirementInfo();
		info.setId(id);
		info.setRequirement(require);
		//删除客户需求
		agtCusInfoService.deleteRequire(info);

		return "redirect:" + adminPath + "/cust/source?repage";
	}

	/**
	 * 客户管理编辑客户
	 * 
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:cust:edit")
	@RequestMapping(value = "/editc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String editCus(RedirectAttributes redirectAttributes) {
		// 客户ID
		String id = (String) getRequestParameter("custid");
		// 姓名
		String name = (String) getRequestParameter("name");
		// 性别
		String gender = (String) getRequestParameter("edit_gender");
		// 电话
		String mobile = (String) getRequestParameter("mobile");
		// 备注
		String resource = (String) getRequestParameter("resource");

		AgtCusInfo info = new AgtCusInfo();
		info.setId(id);
		info.setName(name);
		info.setGender(Integer.parseInt(gender));
		info.setMobile(mobile);
		info.setResource(Integer.parseInt(resource));
		try {
			//info.setName("");
			// 客户信息校验
			addCheck(info);
		} catch (ApplicationException e) {
			addError(redirectAttributes, e.getMessage());
			return "redirect:" + adminPath + "/cust/source?repage";
		}
		agtCusInfoService.updateCust(info);
		addMessage(redirectAttributes, info.getName()+"保存成功");
		return "redirect:" + adminPath + "/cust/source?repage";
	}

	/**
	 * 客户管理添加客户
	 * 
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:cust:add")
	@AvoidDuplicateSubmission(needRemoveToken = true)
	@RequestMapping(value = "/addc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String addCus(RedirectAttributes redirectAttributes) {
		// 姓名
		String name = (String) getRequestParameter("name");
		// 性别
		String gender = (String) getRequestParameter("gender");
		// 手机号
		String mobile = (String) getRequestParameter("mobile");
		// 描述
		String resource = (String) getRequestParameter("resource");

		AgtCusInfo info = new AgtCusInfo();
		info.setName(name);
		info.setGender(Integer.parseInt(gender));
		info.setMobile(mobile);
		info.setResource(Integer.parseInt(resource));
		info.setStatus((short) 1);
		info.setEid(UserUtils.getUser().getEmpId());
		try {
			// 客户信息校验
			addCheck(info);
		} catch (ApplicationException e) {
			addError(redirectAttributes, e.getMessage());
			return "redirect:" + adminPath + "/cust/source?repage&st=1";
		}
		agtCusInfoService.insertCust(info);

		TokenHelper.getInstance().setToken(this.getRequest());
		return "redirect:" + adminPath + "/cust/source?repage&st=1";
	}

	/**
	 * 客户信息校验
	 * 
	 * @param info
	 *            客户信息对象
	 */
	public void addCheck(AgtCusInfo info) throws ApplicationException {
		String retStr = "";
		// 电话号码空校验
		if (StringUtils.isBlank(info.getMobile())) {
			retStr += "手机必填,";
		}
		// 手机号码格式校验
		if (!info.getMobile().matches(Const.REGULAR_MOBILE)) {
			retStr += "手机号格式不正确,";
		}
		// 用户名空校验
		if (StringUtils.isBlank(info.getName())) {
			retStr += "姓名必填,";
		}
		// 错误信息非空
		if (!"".equals(retStr)) {
			retStr = retStr.substring(0, retStr.length() - 1);
			throw new ApplicationException(retStr);
		}

		// 经纪公司是否被锁定
		boolean lockFlg = agtEmpInfoService.isCompLockedByUserId(UserUtils.getUser().getId());
		// 经纪公司被锁定
		if (lockFlg) {
			throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0016"));
		}
	}
	/**
	 * 添加备注
	 * 
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:cust:view")
	@RequestMapping(value = "/mark", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String addRemark(RedirectAttributes redirectAttributes) {
		// 客户ID
		String id = (String) getRequestParameter("custid");
		// 备注
		String comments = (String) getRequestParameter("remark");

		AgtCusComment info = new AgtCusComment();
		info.setCustomerId(id);
		info.setComments(comments);
		// 备注长度校验
		if (comments.length() > 300 || comments.length() < 1) {
			addError(redirectAttributes, "请输入1-300字！");
		} else {
			// 插入备注
			agtCusInfoService.insertRemark(info);
		}

		return "redirect:" + adminPath + "/cust/source?repage";
	}

	/**
	 * 更新备注
	 * 
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:cust:view")
	@RequestMapping(value = "/um", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String updateRemark(RedirectAttributes redirectAttributes) {
		// 客户ID
		String custid = (String) getRequestParameter("custid");
		// 客户备注表ID
		String commentid = (String) getRequestParameter("commentid");
		// 备注
		String commentRemark = (String) getRequestParameter("commentRemark");

		AgtCusComment info = new AgtCusComment();
		info.setCustomerId(custid);
		info.setId(commentid);
		info.setComments(commentRemark);
		// 备注长度校验
		if (commentRemark.length() > 300 || commentRemark.length() < 1) {
			addError(redirectAttributes, "请输入1-300字！");
		} else {
			// 更新备注
			agtCusInfoService.updateRemark(info);
		}

		return "redirect:" + adminPath + "/cust/source?repage";
	}

	/**
	 * 删除备注
	 * 
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequiresPermissions("emp:cust:view")
	@RequestMapping(value = "/dm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String deleteRemark(RedirectAttributes redirectAttributes) {
		// 客户信息ID
		String custid = (String) getRequestParameter("custid");
		// 客户备注表ID
		String commentid = (String) getRequestParameter("commentid");
		AgtCusComment info = new AgtCusComment();
		info.setCustomerId(custid);
		info.setId(commentid);
		// 删除备注
		agtCusInfoService.deleteRemark(info);

		return "redirect:" + adminPath + "/cust/source?repage";
	}
	/**
	 * 修改客户状态
	 * 
	 * @return ajaxSuccess:操作成功
	 */
	@RequiresPermissions("emp:cust:edit")
	@RequestMapping(value = { "cst", "" }, method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult changeStatus() {
		// 客户ID
		String id = (String) getRequestParameter("id");
		// 客户状态
		String status = (String) getRequestParameter("status");
		AgtCusInfo info = new AgtCusInfo();
		info.setId(id);
		info.setStatus(Short.valueOf(status));
		// 修改客户状态
		agtCusInfoService.updateStatus(info);
		return ajaxSuccess("成功");
	}
}
