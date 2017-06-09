/**
* @name: KickoutSessionFilter.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 提出用户
*
* @version: 1.0
* @date : 2016年8月5日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月5日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.admin.modules.sys.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import com.dlfc.admin.modules.sys.web.LoginController;

/**
 * @name: KickoutSessionFilter
 * @description: 踢出用户
 * 
 * @version 1.0
 * @author yuanjw
 *
 */
@Service
public class KickoutSessionFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Session session = getSubject(request, response).getSession(false);
		// 判断当前用户是否被踢出
		if (session == null) {
			return true;
		}
		return session.getAttribute("SESSION_FORCE_LOGOUT_KEY") == null;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		getSubject(request, response).logout();// 强制退出
		// cas退出
		WebUtils.issueRedirect(request, response, LoginController.getLogoutPath());
		return false;
	}

}
