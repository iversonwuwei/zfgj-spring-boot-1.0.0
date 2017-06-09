/**
* @name: CmsArticeController.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 获取新闻文章公告
*
* @version: 1.0
* @date : 2016年4月28日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年4月28日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.CmsArticle;
import com.dlfc.zfgj.service.CmsArticleService;

/**
 * @name: CmsArticeController 新闻文章
 * @description: 获取新闻文章公告
 * @version 1.0
 * @author yuanjw
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/article")
public class CmsArticeController {
	/** 文章service */
	@Autowired
	private CmsArticleService cmsArticleService;

	/**
	 * 获取文章
	 * @param id 文章ID
	 * @return CmsArticle 文章对象
	 */
	@ModelAttribute
	public CmsArticle get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			// 获取文章对象
			return cmsArticleService.getNoticeDetail(id);
		} else {
			return new CmsArticle();
		}
	}

	/**
	 * 获取文章
	 * @param cmsArticle 文章对象
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @return 请求地址
	 */
	@RequestMapping(value = { "detail", "" })
	public String detail(CmsArticle cmsArticle, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("cmsArticle", cmsArticle);
		return "dlfc/agent/articleDetail";
	}

	/**
	 * 公告详细
	 * @param id 文章ID
	 * @return CmsArticle 文章对象
	 */
	@RequestMapping(value = "detailAjax", method = RequestMethod.POST)
	@ResponseBody
	public CmsArticle detailAjax(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			// 获取文章对象
			return cmsArticleService.getNoticeDetail(id);
		} else {
			return new CmsArticle();
		}
	}

}
