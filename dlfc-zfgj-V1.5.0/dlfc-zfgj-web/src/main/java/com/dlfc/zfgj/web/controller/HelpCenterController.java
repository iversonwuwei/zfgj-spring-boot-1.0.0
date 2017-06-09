/**
* @name: helpCenterController.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 帮助中心
*
* @version: 1.0
* @date : 2016年5月11日 
* @author: Liu.Jia 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年5月11日       Liu.Jia        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.CmsArticle;
import com.dlfc.zfgj.service.CmsArticleService;
import com.housecenter.dlfc.zfgj.common.persistence.Page;

/**
 * @name: helpCenterController
 * @description: 帮助中心
 * @version 1.0
 * @author Liu.Jia
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/help")
public class HelpCenterController {
	/** 文章service*/
	@Autowired
	private CmsArticleService cmsArticleService;

	/**
	 * 获取帮助中心列表 
	 * @param cmsArticle 文章实体
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequestMapping(value = "/zhxx", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String getList(CmsArticle cmsArticle, HttpServletRequest request, HttpServletResponse response, Model model){
		// 获取一级菜单id
		String gid = request.getParameter("gid");
		if (StringUtils.isBlank(gid)) {
			// 账户信息id
			gid = "45185109a1824210bd0f27f3d93f3837";
		}

		// 分页处理
		cmsArticle.setId(gid);
		BasePage<CmsArticle> page = cmsArticleService.getList(new Page<CmsArticle>(request, response), cmsArticle);

		model.addAttribute("gid", gid);
		model.addAttribute("page", page);
		return "dlfc/help/servicehall";
	}

	/**
	 * 获取帮助中心详情 
	 * @param cmsArticle 文章实体
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequestMapping(value = { "form" })
	public String infoList(CmsArticle cmsArticle, HttpServletRequest request, HttpServletResponse response, Model model){
		// 一级菜单id
		String gid = request.getParameter("gid");
		// 二级菜单id
		String id = cmsArticle.getId();
		List<CmsArticle> infoList;
		infoList = cmsArticleService.getInfoContent(id);

		model.addAttribute("infoList", infoList);
		model.addAttribute("gid", gid);
		return "dlfc/help/serviceinfo";
	}

}
