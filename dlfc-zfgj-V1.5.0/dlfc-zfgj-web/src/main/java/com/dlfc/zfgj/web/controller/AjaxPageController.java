
/**
* @name: AjaxPageController.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: AJAX加载页面
*
* @version: 1.0
* @date : 2016年12月5日 
* @author: LiJlianLong
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年12月5日       LiJianLong        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @name: AjaxPageController
 * @description: AJAX加载页面
 * 
 * @version 1.0
 * @author LiJianLong
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/head")
public class AjaxPageController {

	/**
	 * 取得底部页面
	 * 
	 * @return 网站底部jsp视图
	 */
	@RequestMapping(value = "/bottom")
	public String geBtottomTop() {

		return "include/bottom";
	}
}
