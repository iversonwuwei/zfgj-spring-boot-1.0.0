/**
* @name: AgtCertController.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 提交资格认证
*
* @version: 1.0
* @date : 2015年11月9日 
* @author: QiuFei
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月9日       QiuFei        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtCertLink;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.service.AgtCertLinkService;
import com.dlfc.zfgj.service.UsrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @name: AgtCertController
 * @description: 提交资格认证
 * @version 1.0
 * @author QiuFei
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/agtc")
public class AgtCertController extends BaseController {
	/** 用户信息service */
	@Autowired
	private UsrUserService usrUserService;
	/** 经纪人认证service */
	@Autowired
	private AgtCertLinkService agtCertLinkService;

	/**
	 * 提交资格认证
	 * 
	 * @param certCardNo
	 *            资格证号
	 * @param imgId
	 *            资格证图片id
	 * @param request
	 *            接收客户端向服务器发出请求
	 * @param response
	 *            服务端返还给客户机的一个响应内容对象
	 * @param model
	 *            数据绑定接口,实现类为ExtendedModelMap
	 * @return ajaxSuccess：操作成功，ajaxFail：操作失败
	 */
	@RequestMapping(value = "/agtcs", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult agtSubmit(String certCardNo, String imgId, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String errorInfo = "";
		// 资格证号为空验证
		if (StringUtils.isBlank(certCardNo)) {
			errorInfo = "请输入资格证号";
			// 资格证图片为空验证
		} else if (StringUtils.isBlank(imgId)) {
			errorInfo = "请上传从业资格证";
		}
		// 校验资格证号是否唯一
		if (agtCertLinkService.isAgtCardBinded(certCardNo)) {
			errorInfo = "此资格证号已被使用";
		}
		// 错误信息提示判断
		if (StringUtils.isNotBlank(errorInfo)) {
			return ajaxFail(errorInfo);
		}
		User userInfo = UserUtils.getUser();
		AgtCertLink acl = new AgtCertLink();
		acl.preInsert();
		acl.setId(acl.getUUID());
		acl.setCertCardNo(certCardNo);
		acl.setCertImgId(imgId);
		acl.setUserId(userInfo.getId());
		acl.setIsCert(0);
		// 根据id获取用户信息
		UsrUser usr = usrUserService.findById(userInfo.getId());
		acl.setPid(usr.getPerId());
		agtCertLinkService.insert(acl);
		return ajaxSuccess("资格认证提交");

	}

}
