/**
* @name: AgtController.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 经纪人审核、离职、增删改查等操作

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dlfc.admin.common.exception.AppRuntimeException;
import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.security.shiro.session.SessionDAO;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.GetPinyin;
import com.dlfc.admin.common.utils.MD5;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtCertLink;
import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.AgtEmpInfoList;
import com.dlfc.zfgj.entity.AgtUsrCompLogInfo;
import com.dlfc.zfgj.entity.AgtUsrCompLogInfoExample;
import com.dlfc.zfgj.entity.SelectEntity;
import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.entity.SysParam;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.SysRole;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.service.AgtCertLinkService;
import com.dlfc.zfgj.service.AgtCompInfoService;
import com.dlfc.zfgj.service.AgtEmpInfoService;
import com.dlfc.zfgj.service.AgtService;
import com.dlfc.zfgj.service.MessageService;
import com.dlfc.zfgj.service.SMSService;
import com.dlfc.zfgj.service.SysInfoAttService;
import com.dlfc.zfgj.service.SysParamService;
import com.dlfc.zfgj.service.SysPersonService;
import com.dlfc.zfgj.service.SysRoleService;
import com.dlfc.zfgj.service.UsrUserService;
import com.housecenter.dlfc.modules.sign.service.UsrSignInfoService;
import com.housecenter.dlfc.modules.sign.service.UsrSignResInfoService;
import com.housecenter.dlfc.zfgj.common.enums.AgtOptEventEnum;
import com.housecenter.dlfc.zfgj.common.enums.InfoAttFileTypeEnum;
import com.housecenter.dlfc.zfgj.common.persistence.Page;
import com.housecenter.dlfc.zfgj.common.utils.Const;
import com.housecenter.dlfc.zfgj.common.utils.QRCodeUtils;

import sun.misc.BASE64Decoder;

/**
 * @name: AgtController
 * @description: 经纪人审核、离职、增删改查等操作
 * @version 1.0
 * @author daiym
 *
 */
@SuppressWarnings("restriction")
@Controller
@RequestMapping(value = "${adminPath}/agt")
public class AgtController extends BaseController {
	/** 经纪人信息service */
	@Autowired
	private AgtService agtService;
	/** 信息service */
	@Autowired
	private MessageService messageService;
	/** 用户信息service */
	@Autowired
	private UsrUserService usrUserService;
	/** 附件信息service */
	@Autowired
	private SysInfoAttService sysInfoAttService;
	/** 经纪公司信息Service */
	@Autowired
	private AgtCompInfoService agtCompInfoService;
	/** 角色信息service */
	@Autowired
	private SysRoleService sysRoleService;
	/** 经纪人资格认证service */
	@Autowired
	private AgtCertLinkService agtCertLinkService;
	/** 经纪人信息service */
	@Autowired
	private AgtEmpInfoService agtEmpInfoService;
	/** 短信service */
	@Autowired
	private SMSService smsService;
	/** SessionDAO */
	@Autowired
	private SessionDAO sessionDAO;
	/** 人员信息service */
	@Autowired
	private SysPersonService sysPersonService;
	/** 经纪人签到service */
	@Autowired
	private UsrSignInfoService usrSignInfoService;
	/** 经纪人签到结果service */
	@Autowired
	private UsrSignResInfoService usrSignResInfoService;
	/** 系统参数service*/
	@Autowired
	private SysParamService sysParamService;

	/**
	 * 获取经纪人临时表对象
	 * 
	 * @param id
	 *            经纪人临时表对象ID
	 * @return AgtEmpInfoList 经纪人临时表对象
	 */
	@ModelAttribute
	public AgtEmpInfoList get(@RequestParam(required = false) String id) {
		return new AgtEmpInfoList();
	}

	/**
	 * 经纪人列表展示
	 * 
	 * @param empInfo
	 *            经纪人对象列表
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequestMapping(value = { "list" })
	public String list(AgtEmpInfoList empInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 经纪人状态
		String status = (String) getRequestParameter("status");
		// 公司ID
		String companyId = agtService.selectCompIdByOfficeId(UserUtils.getUser().getCompany().getId());
		empInfo.setCompanyId(companyId);
		empInfo.setStatus(status);
		// 经纪人列表分页对象
		BasePage<AgtEmpInfoList> page = agtService.find(new Page<AgtEmpInfoList>(request, response), empInfo);
		model.addAttribute("page", page);
		model.addAttribute("role", "0");
		model.addAttribute("status", status);
		return "dlfc/agent/agentlist";
	}

	/**
	 * 在职经纪人列表
	 * 
	 * @return 请求地址
	 */
	@RequestMapping(value = { "onlinelist" })
	public String agtOnlineList() {
		return "redirect:" + adminPath + "/agt/list?status=0";
	}

	/**
	 * 离职经纪人列表
	 * 
	 * @return 请求地址
	 */
	@RequestMapping(value = { "offlinelist" })
	public String agtOfflineList() {
		return "redirect:" + adminPath + "/agt/list?status=1";
	}

	/**
	 * 经纪人列表展示
	 * 
	 * @param empInfo
	 *            经纪人对象列表
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequestMapping(value = { "ajaxlist" })
	public String ajaxList(AgtEmpInfoList empInfo, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 角色
		String role = (String) getRequestParameter("role");
		// 经纪人状态
		String status = (String) getRequestParameter("status");
		// 插入经纪人角色（1：经理 2：经纪人）
		if ("1".equals(role)) {
			empInfo.setRoleCode(PropertyUtils.getSysVal("role.manager.id"));
		} else if ("2".equals(role)) {
			empInfo.setRoleCode(PropertyUtils.getSysVal("role.agent.id"));
		} else {
			role = "0";
		}
		// 姓名、电话
		String selectValue = (String) getRequestParameter("sv");
		// 部门ID
		String officeId = (String) getRequestParameter("oId");
		// 姓名、电话不为空，插入姓名或者电话信息
		if (!StringUtils.isEmpty(selectValue)) {
			empInfo.setSelectValue(selectValue);
		}
		// 部门ID不为空，插入部门ID
		if (!StringUtils.isEmpty(officeId)) {
			empInfo.setParentId(officeId);
		}
		// 公司ID
		String companyId = agtService.selectCompIdByOfficeId(UserUtils.getUser().getCompany().getId());
		empInfo.setCompanyId(companyId);
		empInfo.setStatus(status);
		// 经纪人列表分页对象
		BasePage<AgtEmpInfoList> page = agtService.find(new Page<AgtEmpInfoList>(request, response), empInfo);

		model.addAttribute("page", page);
		model.addAttribute("role", role);
		model.addAttribute("status", status);

		return "dlfc/agent/agentlistcontent";
	}

	/**
	 * 设置离职
	 * 
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败提示信息
	 */
	@RequestMapping(value = { "quit" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult doQuit() {
		// 经纪人ID
		String eId = (String) getRequestParameter("id");
		AgtEmpInfo empInfo = new AgtEmpInfo();
		empInfo.setId(eId);
		empInfo.setStatus(Const.AGT_STATUS_QUIT);
		empInfo.setStatusTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		// 操作状态
		int res = agtService.updateAgtEmpInfo(empInfo, AgtOptEventEnum.QUIT_ENUM);
		// 返回1：操作成功
		if (res == 1) {
			return ajaxSuccess();
		} else {
			return ajaxFail("更新失败，数据可能已经被修改。");
		}

	}

	/**
	 * 设置删除
	 * 
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败提示信息
	 */
	@RequestMapping(value = { "delete" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult doDelete() {
		AgtEmpInfo empInfo = new AgtEmpInfo();
		empInfo.setId((String) getRequestParameter("id"));
		empInfo.setDeleteFlg(Const.DEL_DELETE);
		// 操作状态
		int res = agtService.updateAgtEmpInfo(empInfo, AgtOptEventEnum.DELETE_ENUM);
		// 返回1：操作成功
		if (res == 1) {
			return ajaxSuccess();
		} else {
			return ajaxFail("更新失败，数据可能已经被修改。");
		}

	}

	/**
	 * 经纪人详情展示
	 * 
	 * @return 请求地址
	 */
	@RequestMapping(value = { "detail" })
	public String detail() {
		// 经纪人ID
		String id = (String) getRequestParameter("id");
		// 编辑判断
		String edit = (String) getRequestParameter("edit");
		// 经纪人对象
		AgtEmpInfo empInfo = agtService.getAgtEmpInfo(id);
		// 经纪人认证对象
		AgtCertLink acl = agtCertLinkService.findByUid(empInfo.getUserId());
		// 经纪人认证对象非空
		if (acl != null) {
			List<SysInfoAtt> sia = sysInfoAttService.selectListByLidTypeDesc(acl.getId(),
					InfoAttFileTypeEnum.EMP_CERT_CARD_ENUM.getValue());
			// 从业资格证图片
			getRequest().setAttribute("sia", sia);
		}
		SysInfoAtt sysInfoAtt = new SysInfoAtt();
		// 图片对象
		sysInfoAtt = sysInfoAttService.selectByLidAndType(empInfo.getUsrUser().getId(),
				InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue());
		String time = DateUtils.getDate("HH:mm");
		getRequest().setAttribute("accessKey", MD5.GetMD5Code("dlfc" + time));
		// 头像信息
		getRequest().setAttribute("avatarInfo", sysInfoAtt);
		setRequestAttribute("empInfo", empInfo);
		setRequestAttribute("edit", edit);
		setRequestAttribute("id", id);

		List<SysRole> roleList = sysRoleService.findEmpRole();

		setRequestAttribute("roleList", roleList);

		return "dlfc/agent/agentdetail";
	}

	/**
	 * 经纪人详情展示
	 * 
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败提示信息
	 */
	@RequestMapping(value = { "edit" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult edit() {
		// 经纪人ID
		String id = (String) getRequestParameter("id");
		// 用户ID
		String userId = (String) getRequestParameter("userId");
		// 角色ID
		String roleId = (String) getRequestParameter("rId");
		// 部门ID
		String deptId = (String) getRequestParameter("oId");
		// 说明
		String comment = (String) getRequestParameter("comment");

		AgtEmpInfo empInfo = new AgtEmpInfo();
		empInfo.setId(id);
		empInfo.setUserId(userId);
		// 部门ID不为空，插入部门ID
		if (StringUtils.isNotBlank(deptId)) {
			empInfo.setOfficeId(deptId);
		}
		// 角色ID不为空，插入角色ID
		if (StringUtils.isNoneBlank(roleId)) {
			empInfo.setRoleCode(roleId);
		}
		// 说明不为空，插入说明
		if (StringUtils.isNoneBlank(comment)) {
			empInfo.setComment(comment);
		}
		// 操作状态
		int res = agtService.updateAgtEmpInfo(empInfo, AgtOptEventEnum.EDIT_ENUM);
		// 返回1：操作成功
		if (res == 1) {
			return ajaxSuccess();
		} else {
			return ajaxFail("更新失败，数据可能已经被修改。");
		}

	}

	/**
	 * 发送信息
	 */
	@RequestMapping(value = { "sm" })
	public void sendMessage() {
		String content = PropertyUtils.getMessage("msg.house.aduit.pass.content", "您好！");
		messageService.sendMessage("9bccbd2f58a941b583ba04927cf80679", content);
		messageService.sendMessage("sender", "senderName", "receiver", "title", content, (short) 1);
		messageService.sendMessageMass("sender", "senderName", "title", "content", (short) 0);
	}

	/**
	 * 经纪人审核
	 * 
	 * @param id
	 *            被审核人ID
	 * @return 请求地址
	 */
	@RequiresPermissions("hr:emp:verify")
	@RequestMapping(value = { "verifydetail" })
	public String verifydetail(String id) {
		// 被审核人ID
		String ids = (String) getRequestParameter("ids");
		// 操作状态（0：登记，1：拒绝）
		String re = (String) getRequestParameter("re");
		AgtUsrCompLogInfoExample example = new AgtUsrCompLogInfoExample();
		AgtUsrCompLogInfoExample.Criteria c = example.createCriteria();
		c.andIdEqualTo(ids);
		// 用户公司对象列表
		List<AgtUsrCompLogInfo> aei = agtService.getAgtUsrCompLogInfo(example);
		// 用户公司对象列表存在，将对象放到request中
		if (aei != null && aei.size() != 0) {
			setRequestAttribute("aei", aei.get(0));
		}
		// 经纪人图片对象列表
		List<SysInfoAtt> sia = sysInfoAttService.selectListByLidTypeDesc(aei.get(0).getAgtCertLink().getId(),
				InfoAttFileTypeEnum.EMP_CERT_CARD_ENUM.getValue());
		SysInfoAtt sysInfoAtt = new SysInfoAtt();
		sysInfoAtt = sysInfoAttService.selectByLidAndType(aei.get(0).getUsrUser().getId(),
				InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue());
		String time = DateUtils.getDate("HH:mm");
		getRequest().setAttribute("accessKey", MD5.GetMD5Code("dlfc" + time));
		// 头像信息
		getRequest().setAttribute("avatarInfo", sysInfoAtt);
		// 从业资格证图片
		getRequest().setAttribute("sia", sia);
		List<SelectEntity> roleList = new ArrayList<SelectEntity>();
		SelectEntity role = new SelectEntity();

		role.setId(PropertyUtils.getSysVal("role.agent.id"));
		role.setName("经纪人");
		roleList.add(role);
		role = new SelectEntity();
		role.setId(PropertyUtils.getSysVal("role.manager.id"));
		role.setName("经理");
		roleList.add(role);
		setRequestAttribute("roleList", roleList);
		setRequestAttribute("re", re);
		return "dlfc/agent/agentVerifyDetail";
	}

	/**
	 * 经纪人审核列表
	 * 
	 * @param agtUsrCompLogInfo
	 *            经纪公司日志对象
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return String 网页地址
	 */
	@RequestMapping(value = { "agentlistVerify" })
	public String agentlistVerify(AgtUsrCompLogInfo agtUsrCompLogInfo, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取搜索框值
		String input = (String) getRequestParameter("input");
		// 搜索框值非空，插入搜索框
		if (null != input && "" != input) {
			// 赋值给agtUsrCompLogInfo
			agtUsrCompLogInfo.setPid(input);
		}
		// 查询status状态为0
		agtUsrCompLogInfo.setStatus(0);
		agtUsrCompLogInfo.setCompanyId(UserUtils.getUser().getCompany().getId());
		// 条件查询
		BasePage<AgtUsrCompLogInfo> page = agtService.find(new Page<AgtUsrCompLogInfo>(request, response),
				agtUsrCompLogInfo);
		// 搜索框值非空，插入返回值
		if (null != input && "" != input) {
			model.addAttribute("input", input);
		}
		model.addAttribute("page", page);
		String time = DateUtils.getDate("HH:mm");
		getRequest().setAttribute("accessKey", MD5.GetMD5Code("dlfc" + time));
		return "dlfc/agent/agentlistVerify";
	}

	/**
	 * 经纪人审核登记
	 * 
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败
	 */
	@RequestMapping(value = { "vedit" }, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult vedit() {
		// 被审核人ID
		String id = (String) getRequestParameter("id");
		// 错误提示信息
		String errorInfo = "";
		User user = UserUtils.getUser();
		// 判断公司是否被锁定
		if (agtEmpInfoService.isCompLockedBySysUser(user.getId())) {
			return ajaxFail("公司已被锁定，无法审核人员");
		}
		AgtUsrCompLogInfoExample example = new AgtUsrCompLogInfoExample();
		AgtUsrCompLogInfoExample.Criteria c = example.createCriteria();
		c.andIdEqualTo(id);
		// 用户公司对象列表
		List<AgtUsrCompLogInfo> aeiOld = agtService.getAgtUsrCompLogInfo(example);
		// 审核判断（re = 0 代表审核，re != 0 代表拒绝）
		if ("0".equals(getRequestParameter("re"))) {
			String roleId = (String) getRequestParameter("rId");
			String deptId = (String) getRequestParameter("oId");
			// 部门ID空校验
			if (StringUtils.isBlank(deptId)) {
				errorInfo = "请选择部门";
			}
			// 角色ID空校验
			if (StringUtils.isBlank(roleId)) {
				errorInfo = "请选择角色";
			}
			// 错误提示信息非空，返回错误信息
			if (StringUtils.isNotBlank(errorInfo)) {
				return ajaxFail(errorInfo);
			}
			// 用户公司对象为空，返回失败信息
			if (aeiOld == null || aeiOld.size() == 0) {
				return ajaxFail("更新失败，数据可能已经被修改。");
			} else {
				// 判断用户是否是经纪人
				if (agtService.isInCom(aeiOld.get(0).getUserId())) {
					return ajaxFail("此用户已经成为经纪人，请重新加载数据");
				}
				AgtEmpInfo aei = new AgtEmpInfo();
				aei.setUserId(aeiOld.get(0).getUserId());
				aei.setPid(aeiOld.get(0).getPid());
				aei.setCompanyId(aeiOld.get(0).getAgtCompInfo().getId());
				String isId = agtService.getIsEmp(aei);
				aei.setOfficeId(deptId);
				aei.setRoleCode(roleId);
				UsrUser u = usrUserService.findById(aeiOld.get(0).getUserId());
				aei.setPhone(u.getMobile());
				// 获取个人信息
				SysPerson person = sysPersonService.get(aeiOld.get(0).getPid());
				if(person != null){
					// 设置全拼
					aei.setSort(GetPinyin.getPingYin(person.getName()));
				}
				AgtUsrCompLogInfo aucli = new AgtUsrCompLogInfo();
				aucli.setId(id);
				aucli.preUpdate();
				// 操作状态
				int res = agtService.InserAgtEmpInfo(aei, aucli, isId);
				// res:1操作成功
				if (res == 1) {
					// 踢出用户
					kickOutSession(aeiOld.get(0).getUsrUser());
					return ajaxSuccess();
				} else {
					return ajaxFail("更新失败，数据可能已经被修改。");
				}
			}

		} else { // 拒绝
			// 用户公司对象为空，返回失败信息
			if (aeiOld == null || aeiOld.size() == 0) {
				return ajaxFail("更新失败，数据可能已经被修改。");
			}
			String reF = (String) getRequestParameter("reF");
			// 拒绝原因空校验
			if (!StringUtils.isNotBlank(reF)) {
				errorInfo = "请填写拒绝原因";
			} else if (reF.length() > 300) { // 拒绝原因长度校验
				errorInfo = "拒绝原因不能超过300个字符";
			}

			// 错误信息非空，返回错误提示信息
			if (StringUtils.isNotBlank(errorInfo)) {
				return ajaxFail(errorInfo);
			}
			AgtEmpInfo aei = new AgtEmpInfo();
			aei.setUserId(aeiOld.get(0).getUserId());
			aei.setCompanyId(aeiOld.get(0).getAgtCompInfo().getId());

			AgtUsrCompLogInfo aucli = new AgtUsrCompLogInfo();
			aucli.setId(id);
			aucli.setStatus(2);
			aucli.setRejectReason(reF);
			aucli.preUpdate();
			// 操作状态
			int res = agtService.updateAgtUser(aucli, aei);
			// res:1 操作成功
			if (res == 1) {
				return ajaxSuccess();
			} else {
				return ajaxFail("更新失败，数据可能已经被修改。");
			}
		}

	}

	/**
	 * 登录判断入口
	 * 
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String loginIndex(Model model) {
		User userInfo = UserUtils.getUser();
		// 0为在职，1为不在职 ，2为加入公司审核中，3为拒绝
		String flag = "0";
		// 在职，取公司信息
		AgtEmpInfo comAll = agtService.getComAll(userInfo.getId());
		if (agtService.isInCom(userInfo.getId())) {
			int actHouse = agtService.actHouse(comAll.getId());
			int conHouse = agtService.conHouse(comAll.getId());
			int wHouse = agtService.wHouse(comAll.getId());
			int unAuHouse = agtService.unAuHouse(comAll.getId());
			int cus = agtService.cus(comAll.getId());
			int contract = agtService.contract(comAll);
			int eContract = agtService.eContract(comAll);
			SysInfoAtt compLogo = sysInfoAttService.selectById(comAll.getAgtCompInfo().getImg1Id());

			// 1、经纪人首页“抢房源”数据统计为推送至其账户的所有委托总数；
			int entCount = agtService.selectEntrustCount(comAll.getAgtCompInfo().getId());
			// 2、“委托房源”数据统计为经纪人抢单成功的委托总数。
			int exaCount = agtService.selectExamineCount(userInfo.getId());
			model.addAttribute("entCount", entCount);
			model.addAttribute("exaCount", exaCount);
			model.addAttribute("actHouse", actHouse);
			model.addAttribute("conHouse", conHouse);
			model.addAttribute("wHouse", wHouse);
			model.addAttribute("unAuHouse", unAuHouse);
			model.addAttribute("cus", cus);
			model.addAttribute("contract", contract);
			model.addAttribute("eContract", eContract);
			model.addAttribute("compLogo", compLogo);
			model.addAttribute("cert", 1);
			model.addAttribute("phone", agtService.getPhone(userInfo.getId()));
			
			//经纪人签到
			boolean signFlag = false;
			//是否可以领取奖金
			boolean allowFlag = false;
			//是否添加了支付宝号
			boolean aliFlag = false;
			//累计签到天数
			int days = 0;
			//累计奖金
			int total = 0;
			
			//获取是否可以签到开关
			signFlag = usrSignInfoService.isSign();
			
			SysParam sysParam = new SysParam();
			sysParam.setScope("sign_prize");
			sysParam.setSkey("on-off");
			//获取是否可以领取奖金开关
			allowFlag = sysParamService.isOn(sysParam);
			
			//允许领奖时判断是否添加支付宝账户
			if(allowFlag){
				aliFlag = usrSignResInfoService.aliFlag();
			}
			
			//获取签到天数
			days = usrSignInfoService.getDays();
			
			//计算奖金
			if(days > 6 && days < 14){
				total = 3;
			}else if(days > 13 && days < 21){
				total = 9;
			}else if(days > 20 && days < 28){
				total = 18;
			}else if (days > 27){
				total = 30;
			}
			
			//组装页面返回值
			model.addAttribute("signFlag", signFlag);
			model.addAttribute("allowFlag", allowFlag);
			model.addAttribute("days", days);
			model.addAttribute("total", total);
			model.addAttribute("empName", UserUtils.getUser().getName());
			model.addAttribute("aliFlag", aliFlag);
			
			
			
		} else {
			// 没提交过从业资格证
			if (!usrUserService.isEmpCerted(userInfo)) {
				return "dlfc/agent/zfgjToZhzx";
			} else {
				String cert = usrUserService.isEmpCerting(userInfo);
				// 审核中(日后可能会有业务修改暂留各种if)
				if ("0".equals(cert)) {
					model.addAttribute("cert", cert);
					return "dlfc/agent/zfgjToZhzx";
				} else if ("2".equals(cert)) {// 拒绝(日后可能会有业务修改暂留各种if)
					model.addAttribute("cert", cert);
					return "dlfc/agent/zfgjToZhzx";
				} else {// 成功
					// 不在职，取所有公司列表
					model.addAttribute("cert", cert);
					List<AgtUsrCompLogInfo> logList = agtService.findAgtUsrCompLog(userInfo.getId());
					// 用户公司对象非空，审核状态=0（审核中）
					if (logList != null && logList.size() != 0 && logList.get(0).getStatus() == 0) {
						AgtCompInfo aci = agtCompInfoService.getCompInfoById(logList.get(0).getCompanyId());
						model.addAttribute("AgtUsrCompLogInfoId", logList.get(0).getId());
						model.addAttribute("compName", aci.getFullName());
						flag = "2";
					} else {
						List<AgtCompInfo> list = agtService.findList();
						StringBuffer sb = new StringBuffer();
						String sbEnd = "";
						if (list != null && list.size() != 0) {
							sb.append("[");
							for (int i = 0; i < list.size(); i++) {
								sb.append("'" + list.get(i).getFullName() + "|" + list.get(i).getsSpelling() + "|"
										+ list.get(i).getaSpelling() + "',");
							}
						}
						// 公司信息字符串非空，组装返回的字符串
						if (!StringUtils.isBlank(sb)) {
							sbEnd = sb.substring(0, sb.length() - 1) + "]";
						}
						flag = "1";
						model.addAttribute("comInfo", sbEnd);
						if (logList != null && logList.size() != 0 && logList.get(0).getStatus() == 2) {
							flag = "3";
							model.addAttribute("reR", logList.get(0).getRejectReason());
						}
					}
				}
			}
		}
		model.addAttribute("comAll", comAll);
		SysInfoAtt sysInfoAtt = new SysInfoAtt();
		//经纪人头像
		sysInfoAtt = sysInfoAttService.selectByLidAndType(userInfo.getId(), InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue());
		SysInfoAtt sysErWeiMa = new SysInfoAtt();
		if(comAll != null){
			//经纪人二维码
			sysErWeiMa = sysInfoAttService.selectByLidAndType(comAll.getId(), InfoAttFileTypeEnum.EMP_ERWEIMA_ENUM.getValue());
		}
		
		String time = DateUtils.getDate("HH:mm");
		getRequest().setAttribute("accessKey", MD5.GetMD5Code("dlfc" + time));
		model.addAttribute("avatarInfo", sysInfoAtt);
		model.addAttribute("sysErWeiMa",sysErWeiMa);
		model.addAttribute("flag", flag);
		model.addAttribute("name", userInfo.getName());
		return "dlfc/agent/agentHomePage";
	}

	/**
	 * 加入公司提交
	 * 
	 * @param citySelect
	 *            公司名称
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败
	 */
	@RequestMapping(value = "/jcs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult comSubmit(String citySelect, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String errorInfo = "";
		// 是否选择公司空校验
		if (StringUtils.isBlank(citySelect)) {
			errorInfo = "请选择要加入的公司";
		}
		List<AgtCompInfo> list = agtService.isCom(citySelect);
		// 公司信息空校验
		if (!(list != null && list.size() != 0)) {
			errorInfo = "请正确选择公司";
		}
		// 提示信息非空，返回错误提示信息
		if (StringUtils.isNotBlank(errorInfo)) {
			return ajaxFail(errorInfo);
		}
		User userInfo = UserUtils.getUser();
		// 经纪人在职状态，返回错误提示信息
		if (agtService.isInCom(userInfo.getId())) {
			return ajaxFail("员工处于在职状态无法加入公司");
		}
		agtService.insertCom(list.get(0).getId(), list.get(0).getOfficeId());
		return ajaxSuccess("加入提交");

	}

	/**
	 * 绑定手机
	 * 
	 * @param phone
	 *            电话号码
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败
	 */
	@RequestMapping(value = "/vc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult getVcode(String phone, HttpServletRequest request) {
		// 手机号空检验
		if (StringUtils.isBlank(phone)) {
			return ajaxFail("请输入新手机号");
		}
		// 操作状态
		String res = smsService.sendCapcha(phone, "Z0011", "7", request);
		// 操作状态不为空，获取验证码成功
		if (StringUtils.isNotBlank(res)) {
			return ajaxSuccess("获取验证码成功");
		} else {
			return ajaxFail("获取验证码失败");
		}
	}

	/**
	 * 修改手机号提交
	 * 
	 * @param phone
	 *            手机号
	 * @param vCode
	 *            验证码
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败
	 */
	@RequestMapping(value = "/cm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult changeMobile(String phone, String vCode, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String errorInfo = "";
		// 手机号空校验
		if (StringUtils.isBlank(phone)) {
			errorInfo = "请输入新手机号";
		}
		// 验证码空校验
		if (StringUtils.isBlank(vCode)) {
			errorInfo = "请输入验证码";
		}
		// 错误提示信息非空，返回错误提示信息
		if (StringUtils.isNotBlank(errorInfo)) {
			return ajaxFail(errorInfo);
		}
		// 查询验证码不为空
		if (smsService.selectVerCodeCapcha(phone, vCode) != null) {
			AgtEmpInfo aei = new AgtEmpInfo();
			aei.setUserId(UserUtils.getUser().getId());
			aei.setPhone(phone);
			// 更新手机信息
			agtService.updatePhone(aei);
			return ajaxSuccess("手机修改成功");
		} else {
			return ajaxFail("手机修改失败");
		}
	}

	/**
	 * 经纪人上传二维码
	 * 
	 * @param file 文件对象
	 * @param request MultipartFile
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @param eid 经纪人id
	 * @return 返回 1二维码上传成功，0二维码上传失败，map二维码路径
	 */
	@RequestMapping(value = "/erwei", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult erWerMa(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,String eid){
		//图片上传路径
		String destDir = PropertyUtils.getSysVal("upload.file.real.directory");
		String destPer = PropertyUtils.getSysVal("upload.file.real.per.directory");
		//图片大小
		long allowSize = Long.valueOf(PropertyUtils.getSysVal("upload.file.max.size"));
		//图片格式
		String allowed = PropertyUtils.getSysVal("upload.file.ext.allowed");
		String fileName;
		String filePath = null;
		
		String fileRealName = file.getOriginalFilename();
		//判断图片名是否为空
		if(fileRealName == null){
			throw new AppRuntimeException("请上传图片");
		}
		//得到后缀名
		String suffix = fileRealName.substring(
				file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
		// 判断后缀名
		if(!allowed.contains(suffix)){
			throw new AppRuntimeException("请上传允许格式的文件");
		}
		
		// 判断文件大小
		if (file.getSize() > allowSize) {
			throw new AppRuntimeException("您上传的文件大小已经超出范围");
		}
		
		try {
			//检查图片是否是二维码
			QRCodeUtils QRCode = new QRCodeUtils();
			String tu = QRCode.decoderQRCode(file.getInputStream());
			
			if(StringUtils.isEmpty(tu)){
				return ajaxFail("请上传二维码");
			}
			
			//判断是否为微信二维码
			if(!"http://weixin.qq.com/".equals(tu.substring(0,21))){
				return ajaxFail("请上传微信二维码");
			}
			
			String date = DateUtils.getSysDateStr("yyyy-MM-dd");
			//创建一个文件并赋值地址
			File destFile = new File(destDir + destPer+ date);
			//判断文件是否存在
			if (!destFile.exists()) {
				//不存在则创建目录
				destFile.mkdirs();
			}
			//生成 一个文件名
			fileName = UuidUtils.get32UUID() + suffix;
			File f = new File(destFile.getAbsoluteFile() + "/" + fileName);
			//上传图片
			file.transferTo(f);
			filePath = destPer + date + "/" + fileName;
			
			//生产二维码(图片信息，图片路径，图片格式)
			QRCode.encoderQRCode(tu,destDir+filePath,"png");
			
			//保存或更新图片
			SysInfoAtt sia = new SysInfoAtt();
			sia.setFileType(InfoAttFileTypeEnum.EMP_ERWEIMA_ENUM.getValue());
			sia.setFilePath(filePath);
			sia.setLid(eid);
			sysInfoAttService.updateErWei(sia);
			
		} catch (IllegalStateException e) {
			logger.error("AgController erWerMa IllegalStateException",e);
			throw new AppRuntimeException("上传二维码失败");
		} catch (IOException e) {
			logger.error("AgController erWerMa IOException",e);
			throw new AppRuntimeException("上传二维码失败");
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("filepath", filePath);
		return ajaxSuccess("上传成功", map);
	}
	
	/**
	 * 头像提交
	 * 
	 * @param avatarSrc
	 *            头像地址
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败
	 */
	@RequestMapping(value = "/avas", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult avatarSubmit(String avatarSrc, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String errorInfo = "";
		// 图片空校验
		if (StringUtils.isBlank(avatarSrc)) {
			errorInfo = "请上传图片";
		}
		// 错误信息非空，返回错误信息
		if (StringUtils.isNotBlank(errorInfo)) {
			return ajaxFail(errorInfo);
		}
		String[] src = avatarSrc.split(",");

		String srcEnd = "";
		// 图片数组非空并且长度大于等于2
		if (src != null && src.length >= 2) {
			srcEnd = src[1];
		} else {
			return ajaxFail("上传图片失败");
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(srcEnd);
			for (int i = 0; i < b.length; ++i) {
				// 调整异常数据
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			String realDir = PropertyUtils.getSysVal("upload.file.real.directory");
			String date = DateUtils.getSysDateStr("yyyy-MM-dd");
			String fileName = UuidUtils.get32UUID() + ".png";
			String perDir = PropertyUtils.getSysVal("upload.file.real.per.directory");
			// 生成png图片
			String imgFilePath = realDir + perDir + date + "/" + fileName;
			// 新生成的图片
			File destFile = new File(realDir + perDir + date);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			SysInfoAtt sia = new SysInfoAtt();
			sia.setFileType(InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue());
			sia.setFilePath(perDir + date + "/" + fileName);
			sysInfoAttService.updateAvarar(sia);
		} catch (Exception e) {
			logger.error("MyCenterController avatarSubmit", e);
			return ajaxFail("上传头像失败");
		}
		return ajaxSuccess("上传头像成功");
	}

	/**
	 * 取消申请
	 * 
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param id
	 *            申请人Id
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败
	 */
	@RequestMapping(value = "/rejc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult reJoinComp(HttpServletRequest request, String id, HttpServletResponse response, ModelMap model) {
		// 取消申请加入公司
		agtService.reJoinComp(id);
		return ajaxSuccess("取消申请");

	}

	/**
	 * 查找与离职人员同一公司经纪人列表
	 * 
	 * @param empInfo
	 *            经纪人对象
	 * @param eid
	 *            将要移交的经纪人ID
	 * @param type
	 *            类型(0:房源，1:客源)
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public String transferList(AgtEmpInfoList empInfo, String eid, Integer type, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		empInfo.setStatus("0");
		// 经纪人列表分页对象
		BasePage<AgtEmpInfoList> page = agtService.findTransfer(new Page<AgtEmpInfoList>(request, response), empInfo);

		model.addAttribute("page", page);
		model.addAttribute("oldEid", eid);
		model.addAttribute("type", type);
		model.addAttribute("companyId", empInfo.getCompanyId());
		return "dlfc/agent/agenTransferList";
	}

	/**
	 * 移交离职经纪人房源或者客源
	 * 
	 * @param eid
	 *            将要移交的经纪人Id
	 * @param oldEid
	 *            离职经纪人Id
	 * @param type
	 *            类型(0:房源，1:客源)
	 * @return ajaxSuccess:操作成功，ajaxFail:操作失败
	 */
	@RequestMapping(value = "/tc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult transferConfirm(String eid, String oldEid, Integer type) {
		// 离职经纪人ID和移交经纪人ID不为空的时候更新信息
		if (StringUtils.isNotBlank(eid) && StringUtils.isNotBlank(oldEid)) {
			agtService.updateHouseOrCus(eid, oldEid, type);
			return ajaxSuccess();
		} else {
			return ajaxFail();
		}
	}

	/**
	 * 踢出用户
	 * 
	 * @param UsrUser
	 *            被踢出的用户
	 * @author yuanjiwei 2016/08/09
	 */
	public void kickOutSession(UsrUser user) {
		// 强制踢出用户，此处只在指定会话中设置SESSION_FORCE_LOGOUT_KEY属性，之后通过KickoutSessionFilter判断并进行强制退出。
		Collection<Session> sessions = sessionDAO.getActiveSessions();// 获取所有session
		for (Session session : sessions) {
			// 获取登录名
			String loginName = (String) session.getAttribute("userid");
			// 查找到被审核人登录名对应的session
			if (StringUtils.isNotBlank(loginName) && user != null && StringUtils.isNotBlank(user.getUsername())
					&& user.getUsername().equals(loginName)) {
				session.setAttribute("SESSION_FORCE_LOGOUT_KEY", Boolean.TRUE);
			}
		}

	}
	
	
	/**
	 * 经纪人签到
	 * 
	 * @return ajaxSuccess:签到成功，ajaxFail:签到失败
	 */
	@RequestMapping(value = "/sign", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult sign() {
		//是否可签
		if(usrSignInfoService.isSign()){
			//签到
			if(usrSignInfoService.sign()){
				return ajaxSuccess("签到成功！");
			}
		}
		return ajaxFail("今天已经签到！");
	}
	
	/**
	 * 保存支付宝帐号
	 * 
	 * @return ajaxSuccess:保存成功，ajaxFail:保存失败
	 */
	@RequestMapping(value = "/savealino", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult savealino(String payAccount) {
		try {
			//校验支付宝号长度
			if(payAccount.length() > 50){
				return ajaxFail("支付宝帐号长度超长！");
			}
			//保存支付宝号
			usrSignResInfoService.save(payAccount);
			return ajaxSuccess("支付宝添加成功！");
		} catch (Exception e) {
			return ajaxFail("支付宝添加失败！");
		}
	}

}
