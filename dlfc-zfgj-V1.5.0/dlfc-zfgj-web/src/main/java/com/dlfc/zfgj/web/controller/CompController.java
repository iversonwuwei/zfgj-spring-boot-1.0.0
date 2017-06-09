/**
* @name: CompController.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 公司操作
*
* @version: 1.0
* @date : 2015年11月17日 
* @author: daiym 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月17日       hanjiaqi        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.service.AgtCompInfoService;
import com.dlfc.zfgj.service.CompService;

/**
 * @name: CompController
 * @description: 公司操作
 * @version 1.0
 * @author hanjiaqi
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/comp")
public class CompController extends BaseController {
	/** 公司信息service*/
	@Autowired
	private CompService compService;
	/** 公司信息service*/
	@Autowired
	private AgtCompInfoService agtCompInfoService;
	
	/**
	 * 公司首页
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param introduction 公司介绍
	 * @return 请求地址
	 */
	@RequestMapping(value = { "index" })
	public String Introduction(Model model,String introduction) {
		//公司id（公司表的officeid）
		String companyId = UserUtils.getUser().getCompany().getId();
		AgtCompInfo selectCompInfo1 = agtCompInfoService.getById(companyId);
		if(introduction != null){
			//替换特殊字符
			introduction = introduction.replace("&lt", "<");
			introduction = introduction.replace("&gt", ">");	
			introduction = introduction.replace("amp;","");
			introduction = introduction.replace(";", "");
			AgtCompInfo updateCompInfo = new AgtCompInfo();
			updateCompInfo.setOfficeId(companyId);
			updateCompInfo.setIntroduction(introduction);
			//保存公司介绍信息
			compService.updateByOffId(updateCompInfo);
			selectCompInfo1.setIntroduction(introduction);
		}
		//获取公司在职经纪人
		int count = agtCompInfoService.getAgentTotal(companyId);
		model.addAttribute("compCompInfo", selectCompInfo1);
		model.addAttribute("count", count);
		return "dlfc/agent/compIndex";
	}
	
	/**
	 * 编辑公司
	 * @param model 数据绑定接口,实现类为ExtendedModelMap
	 * @param introduction
	 * @return 请求地址
	 */
	@RequestMapping(value = { "edit" })
	public String edit(Model model,String introduction) {
		return "dlfc/agent/compEdit";
	}
}
