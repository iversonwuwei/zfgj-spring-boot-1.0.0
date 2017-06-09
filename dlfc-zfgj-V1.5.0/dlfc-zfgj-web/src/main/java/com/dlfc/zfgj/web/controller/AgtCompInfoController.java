/**
* @name: AgtCompInfoController.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 经纪公司信息查询、经纪公司logo修改的等操作
*
* @version: 1.0
* @date : 2016年2月29日 
* @author: yuanjiwei
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*/
package com.dlfc.zfgj.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.CacheUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.service.SystemService;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.SysUser;
import com.dlfc.zfgj.service.AgtCompInfoService;
import com.dlfc.zfgj.service.SysUserService;
import com.dlfc.zfgj.service.UsrUserService;

/**
 * @name: AgtCompInfoController
 * @description: 经纪公司信息查询、经纪公司logo修改的等操作
 * @version 1.0
 * @author yuanjiwei
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/comp")
public class AgtCompInfoController extends BaseController {
	/** 经纪公司信息service */
	@Autowired
	private AgtCompInfoService agtCompInfoService;
	/** 修改登陆service */
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 获取经纪公司信息
	 * 
	 * @param id
	 *            经纪公司ID
	 * @return AgtCompInfo 公司对象
	 */
	public AgtCompInfo get(@RequestParam(required = false) String id) {
		// ID不为空从数据库查询对象，ID为空新建对象
		if (StringUtils.isNotBlank(id)) {
			return agtCompInfoService.getById(id);
		} else {
			return new AgtCompInfo();
		}
	}

	/**
	 * 经纪公司信息获取
	 * 
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequestMapping(value = { "select" })
	public String select(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		String id = user.getCompany().getId();
		// 获取公司详情
		AgtCompInfo agtCompInfo = agtCompInfoService.getById(id);
		// 获取公司发布房源总数
		int houseTotal = agtCompInfoService.getHouseTotal(id);
		// 获取公司在职经纪人总数
		int agentTotal = agtCompInfoService.getAgentTotal(id);
		// 获取公司签订合同总数
		int contractTotal = agtCompInfoService.getContractTotal(id);
		request.setAttribute("agtCompInfo", agtCompInfo);
		request.setAttribute("houseTotal", houseTotal);
		request.setAttribute("agentTotal", agentTotal);
		request.setAttribute("contractTotal", contractTotal);
		return "dlfc/agent/compSelect";
	}

	/**
	 * 更新佣金金额
	 * 
	 * @param agtCompInfo
	 *            经纪公司信息对象
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequestMapping(value = { "update" })
	public String update(AgtCompInfo agtCompInfo, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		agtCompInfoService.update(agtCompInfo);
		addMessage(redirectAttributes, "佣金设置成功！");
		return "redirect:" + adminPath + "/comp/select";
	}

	/**
	 * 验证密码
	 * 
	 * @param agtCompInfo
	 *            经纪公司对象
	 * @return ajaxSuccess(0:密码验证成功，1:密码验证失败)
	 */
	@RequestMapping(value = { "validatePassword", "" }, method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult validate(AgtCompInfo agtCompInfo) {
		// 新密码
		String password = (String) getRequestParameter("password");
		// 原密码
		String myPassword = UserUtils.getUser().getPassword();
		// 返回值
		String str = "";
		// 验证密码
		Boolean flag = SystemService.validatePassword(password, myPassword);
		// 0：密码验证成功，
		if (flag) {
			str = "0";
		} else {
			str = "1";
		}
		return ajaxSuccess(str);
	}

	/**
	 * 更改密码
	 * 
	 * @param newpwd
	 *            新密码
	 * @param password
	 *            原密码
	 * @param newpwd1
	 *            新密码重复
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return ajaxSuccess:修改密码成功，ajaxFail:返回失败提示信息
	 */
	@RequestMapping(value = { "changePassword" })
	@ResponseBody
	public AjaxResult changePassword(String newpwd, String password, String newpwd1, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// 错误提示信息
		String errorInfo = "";
		// 原密码空校验
		if (StringUtils.isBlank(password)) {
			errorInfo = "请输入当前密码";
		} else if (StringUtils.isBlank(newpwd)) {// 新密码空校验
			errorInfo = "请输入新密码";
		} else if (StringUtils.isBlank(newpwd1)) {// 第二次输入的新密码空校验
			errorInfo = "请确认新密码";
		} else if (password.equals(newpwd)) { // 新密码与老密码相同验证
			errorInfo = "新密码不能与当前密码相同";
		} else if (!newpwd.equals(newpwd1)) { // 两次输入的新密码一致性验证
			errorInfo = "两次输入的新密码不一致";
		} else if (newpwd.length() < 8) { // 新密码最小长度校验
			errorInfo = "请输入最少8位新密码";
		} else if (newpwd.length() > 18) { // 新密码最大长度校验
			errorInfo = "请输入最长18位新密码";
		}
		// 如果错误提示信息不为空，返回失败
		if (StringUtils.isNotBlank(errorInfo)) {
			return ajaxFail(errorInfo);
		}
		// 用户ID
		String id = UserUtils.getUser().getId();
		SysUser user = new SysUser();
		user.setId(id);
		user.setPassword(SystemService.entryptPassword(newpwd));
		// 更新用户密码
		sysUserService.update(user);
		User userInfo = UserUtils.getUser();
		userInfo.setPassword(user.getPassword());
		CacheUtils.put(UsrUserService.USER_CACHE, UsrUserService.USER_CACHE_ID_ + userInfo.getId(), userInfo);
		return ajaxSuccess("修改密码成功！");
	}

	/**
	 * 修改logo
	 * 
	 * @param agtCompInfo
	 *            经纪公司对象
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequestMapping(value = { "saveLogo" })
	public String saveLogo(AgtCompInfo agtCompInfo, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// logo地址
		String logo = (String) getRequestParameter("logo");
		try {
			agtCompInfoService.saveLogo(agtCompInfo, logo);
		} catch (ApplicationException e) {
			addError(redirectAttributes, e.getMessage());
		}
		addMessage(redirectAttributes, "修改logo成功！");
		return "redirect:" + adminPath + "/comp/select";
	}

	/**
	 * 公司上传电子章成功
	 * 
	 * @param agtCompInfo
	 *            经纪公司对象
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param redirectAttributes
	 *            对象重定向传参
	 * @return 请求地址
	 */
	@RequestMapping(value = { "saveChapter" })
	public String saveChapter(AgtCompInfo agtCompInfo, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// 电子章地址
		String chapter = (String) getRequestParameter("chapter");
		try {
			agtCompInfoService.saveChapter(agtCompInfo, chapter);
		} catch (ApplicationException e) {
			addError(redirectAttributes, e.getMessage());
		}
		addMessage(redirectAttributes, "上传电子章成功！");
		return "redirect:" + adminPath + "/comp/select";
	}

	/**
	 * 下载承诺书
	 * 
	 * @param id
	 *            公司ID
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @return 请求地址
	 */
	@RequestMapping(value = { "downPleBk" })
	public String downPledgeBook(String id, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes) {
		// ID不为空下载承诺书
		if (StringUtils.isNotEmpty(id)) {
			try {
				agtCompInfoService.downPleBk(id, request, response);
			} catch (ApplicationException e) {
				addError(redirectAttributes, e.getMessage());
			}
		}
		return "redirect:" + adminPath + "/comp/select";
	}
}
