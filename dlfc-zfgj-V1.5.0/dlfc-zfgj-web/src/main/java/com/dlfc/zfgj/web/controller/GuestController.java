/**
* @name: GuestController.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 意见反馈
*
* @version: 1.0
* @date : 2016年8月12日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月12日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.CmsGuestBook;
import com.dlfc.zfgj.service.CmsGuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * @name: GuestController
 * @description: 意见反馈
 * @version 1.0
 * @author yuanjw
 */
@Controller
@RequestMapping(value = "${adminPath}/feedback")
public class GuestController extends BaseController {
	/** 意见反馈service */
	@Autowired
	private CmsGuestBookService cmsGuestBookService;

	/**
	 * 意见反馈详情
	 * @param guestbook 意见反馈实体
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param request 接收客户端向服务器发出请求
	 * @param redirectAttributes 对象重定向传参
	 * @return String 请求地址
	 */
	@RequestMapping(value = "form")
	public String form(CmsGuestBook guestbook, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		model.addAttribute("user", user);
		model.addAttribute("guestbook", guestbook);
		return "dlfc/help/feedback";
	}

	/**
	 * 保存意见反馈内容
	 * @param guestbook 意见反馈实体
	 * @param request 接收客户端向服务器发出请求
	 * @param redirectAttributes 对象重定向传参
	 * @return String 请求地址
	 */
	@RequestMapping(value = "save")
	public String save(CmsGuestBook cmsGuestBook, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// 获取图片地址
		String[] images = request.getParameterValues("pics");
		// 手机号
		String phone = request.getParameter("phone");
		// 邮箱
		String email = request.getParameter("email");
		// ip
		String ip = request.getRemoteAddr();
		cmsGuestBook.setIp(ip);

		// 公司名称
		String companyName = "";
		// 部门名称
		String officeName = "";
		// 公司部门
		String workunit = "";
		User user = UserUtils.getUser();
		// 获取所在公司
		if (user != null && user.getCompany() != null) {
			companyName = user.getCompany().getName();
		}
		// 获取所在部门
		if (user != null && user.getOffice() != null) {
			officeName = user.getOffice().getName();
		}
		// 获取公司部门
		if (StringUtils.isNotBlank(companyName)) {
			workunit = companyName;
		}
		// 获取公司部门
		if (StringUtils.isNotBlank(officeName)) {
			workunit = workunit + "," + officeName;
		}

		cmsGuestBook.setWorkunit(workunit);

		// 是否通过校验
		boolean flag = false;
		// 校验手机号
		if (StringUtils.isNotBlank(phone) && isMobile(phone)) {
			cmsGuestBook.setPhone(phone);
			flag = true;
		}
		// 校验邮箱
		if (StringUtils.isNotBlank(email) && isEmail(email)) {
			cmsGuestBook.setEmail(email);
			flag = true;
		}
		// 通过校验
		if (flag) {
			try {
				cmsGuestBookService.save(cmsGuestBook, images);
				addMessage(redirectAttributes, "保存留言成功");
			} catch (ApplicationException e) {
				addError(redirectAttributes, e.getMessage());
			}
			
			try {
				cmsGuestBookService.sendMessage(cmsGuestBook);
			} catch (ApplicationException e) {
				logger.error("GuestController save", e);
//				addMessage(redirectAttributes, "信息或邮件发送失败!");
//				return "redirect:" + adminPath + "/feedback/form?id=" + cmsGuestBook.getId();
			}
		} else {
			addError(redirectAttributes, "请输入正确的电话号或者邮箱!");
		}

		return "redirect:" + adminPath + "/feedback/form";
	}

	/** 正则表达式：验证手机号 */
	public static final String REGEX_MOBILE = "^[1][3,4,5,7,8][0-9]{9}$";

	/** 正则表达式：验证邮箱 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 校验手机号
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * 校验邮箱
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}

}
