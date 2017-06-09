/**
* @name: AgtExamineHouInfoController.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 经纪人房源信息、 删除经纪人验房预约、修改备注等功能
*
* @version: 1.0
* @date : 2016年2月20日 
* @author: fancy 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年2月20日       fancy        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.common.web.ajax.AjaxResult;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtExamineHouInfo;
import com.dlfc.zfgj.service.AgtExamineHouInfoService;
import com.housecenter.dlfc.zfgj.common.persistence.Page;

/**
 * @name: AgtExamineHouInfoController
 * @description: 经纪人房源信息、 删除经纪人验房预约、修改备注等功能
 * @version 1.0
 * @author fancy
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/exa")
public class AgtExamineHouInfoController extends BaseController{
	/** 经纪人验房预约信息service */
	@Autowired
	private AgtExamineHouInfoService agtExamineHouInfoService;
	/** 经纪人验房预约对象session的key值*/
	private static final String KEY_EXAMINEINFO_LIST_CON_SESSION = "KEY_EXAMINEINFO_LIST_CON_SESSION";
	/** 验房预约页面根目录*/
	private static final String JSP_DIR = "dlfc/houseinfo/";
	/**
	 * 经纪人房源信息
	 * @param request 接收客户端向服务器发出请求
	 * @param response 服务端返还给客户机的一个响应内容对象
	 * @return 请求地址
	 */
	@RequestMapping(value = { "list", "" })
	public String list(HttpServletRequest request, HttpServletResponse response){
		AgtExamineHouInfo agtExamineHouInfo = null;
		// 从其他页面返回本页面时，从session里取出本页面的查询参数
		if (isRepage()) {
			agtExamineHouInfo = (AgtExamineHouInfo) getSessionAttribute(KEY_EXAMINEINFO_LIST_CON_SESSION);
		}
		// 公布时间
		String butimesort = (String) getRequestParameter("bs");
		// 检查时间
		String signtimesort = (String) getRequestParameter("ss");
		// 经纪人验房预约为空
		if (agtExamineHouInfo == null) {
			//分页示例用
			agtExamineHouInfo = new AgtExamineHouInfo();
			String sort = "";
			//按公布时间排序
			if ("asc".equalsIgnoreCase(butimesort)) {
				sort += ",A.PUBLISH_TIME";
			} else if ("desc".equalsIgnoreCase(butimesort)) {
				sort += ",A.PUBLISH_TIME DESC";
			}
			//按检查时间排序
			if ("asc".equalsIgnoreCase(signtimesort)) {
				sort += ",A.EXAMINE_TIME";
			} else if ("desc".equalsIgnoreCase(signtimesort)) {
				sort += ",A.EXAMINE_TIME DESC";
			}
			
			if (!"".equals(sort)) {
				sort = sort.substring(1);
			}
			agtExamineHouInfo.setOrderBy(sort);
		}
		String userId = UserUtils.getUser().getId();
		agtExamineHouInfo.setEid(userId);
		// 经纪人验房预约页面对象
		BasePage<AgtExamineHouInfo> page = agtExamineHouInfoService.selectPage(
				new Page<AgtExamineHouInfo>(request, response), agtExamineHouInfo);

		setRequestAttribute("page", page);
		setRequestAttribute("bs", butimesort);
		setRequestAttribute("ss", signtimesort);

		// 将查询参数存入session，以便当从其他页面返回时获得这些参数
		setSessionAttribute(KEY_EXAMINEINFO_LIST_CON_SESSION, agtExamineHouInfo);

		return JSP_DIR+"examinelist";
	}
	
	/**
	 * 删除经纪人验房预约
	 * @return 请求地址
	 */
	@RequestMapping(value = { "del", "" },method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public String deleteExamine(){
		// 验房预约ID
		String id = (String) getRequestParameter("id");
		
		String[] hisArr = id.split("_");

		for (int i = 0; i < hisArr.length; i++) {
			// 删除验房预约信息
			agtExamineHouInfoService.deleteExamine(hisArr[i]);
		}
		
		return "redirect:" + adminPath + "/exa/list";
	}
	
	/**
	 * 删除经纪人验房预约
	 * @return ajaxSuccess:返回成功
	 */
	@RequestMapping(value = { "can", "" },method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult deleteExamineById(){
		String id = (String) getRequestParameter("id");
		agtExamineHouInfoService.deleteExamine(id);
		return ajaxSuccess("成功");
	}
	
	/**
	 * 修改备注
	 * @return ajaxSuccess:返回成功
	 */
	@RequestMapping(value = { "updc", "" },method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public AjaxResult updateComment(){
		// 验房预约ID
		String id = (String) getRequestParameter("id");
		// 备注
		String comment = (String) getRequestParameter("comment");
		agtExamineHouInfoService.updateComment(id,comment);
		return ajaxSuccess("成功");
	}
}
