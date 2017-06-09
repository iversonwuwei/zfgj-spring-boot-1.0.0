/**
 * @name: CasRealm.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 
 *
 * @version: 1.0
 * @date : 2015年12月21日 
 * @author: Alex.Ge 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年12月21日       Alex.Ge        1.0             <修改原因描述>
 */
package com.dlfc.admin.modules.sys.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.config.Global;
import com.dlfc.admin.common.utils.CacheUtils;
import com.dlfc.admin.common.utils.SpringContextHolder;
import com.dlfc.admin.common.web.Servlets;
import com.dlfc.admin.modules.sys.entity.Menu;
import com.dlfc.admin.modules.sys.entity.Role;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.service.SystemService;
import com.dlfc.admin.modules.sys.utils.LogUtils;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.service.UsrUserService;
import com.housecenter.dlfc.zfgj.common.enums.UserIdentityEnum;

/**
 * @name: CasRealm
 * @description:
 * 
 * @version 1.0
 * @author Alex.Ge
 *
 */
@Service
public class CasRealm extends org.apache.shiro.cas.CasRealm {

	private SystemService systemService;
	@Autowired
	private UsrUserService usrUserService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		// 获取当前已登录的用户
		if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))) {
			Collection<Session> sessions = getSystemService().getSessionDao()
					.getActiveSessions(true, principal, UserUtils.getSession());
			if (sessions.size() > 0) {
				// 如果是登录进来的，则踢出已在线用户
				if (UserUtils.getSubject().isAuthenticated()) {
					for (Session session : sessions) {
						getSystemService().getSessionDao().delete(session);
					}
				}
				// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
				else {
					UserUtils.getSubject().logout();
					throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
				}
			}
		}
		User user = getSystemService().getUserByLoginName(
				principal.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Menu> list;
			if (FormAuthenticationFilter.MGR_LOGIN_TYPE.equals(principal
					.getLoginType())) {
				list = UserUtils.getMenuList();
			} else {
				Menu m = new Menu();
				m.setUserId(user.getId());
				list = usrUserService.getMenuList(m);
			}
			for (Menu menu : list) {
				if (StringUtils.isNotBlank(menu.getPermission())) {
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(
							menu.getPermission(), ",")) {
						info.addStringPermission(permission);
					}
				}
			}
			// 添加用户权限
			info.addStringPermission("user");
			// 添加用户角色信息
			for (Role role : user.getRoleList()) {
				info.addRole(role.getEnname());
			}
			// 更新登录IP和时间
			getSystemService().updateUserLoginInfo(user);
			// 记录登录日志
			LogUtils.saveLog(Servlets.getRequest(), "系统登录");
			return info;
		} else {
			return null;
		}
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		CasToken casToken = (CasToken) token;
		if (token == null)
			return null;
		String ticket = (String) casToken.getCredentials();
		// if(!StringUtils.hasText(ticket))
		// return null;
		TicketValidator ticketValidator = ensureTicketValidator();

		try {
			Assertion casAssertion = ticketValidator.validate(ticket,
					getCasService());
			AttributePrincipal casPrincipal = casAssertion.getPrincipal();
			String userId = casPrincipal.getName();
			Map<?, ?> attributes = casPrincipal.getAttributes();
			String loginType = (String) attributes.get("loginType");
			Principal pp = null;
			// 本公司管理员登录验证
			if (FormAuthenticationFilter.MGR_LOGIN_TYPE.equals(loginType)) {
				// 校验用户名密码
				CacheUtils.remove("userCache", "ln" + userId);
				User user = getSystemService().getUserByLoginName(userId);

				if (user != null) {
					if (!("" + UserIdentityEnum.MEDI_USER_ENUM.getValue())
							.equals(user.getUserType())
							&& !("" + UserIdentityEnum.BROKER_USER_ENUM
									.getValue()).equals(user.getUserType())) {
						return null;

					}
					if (Global.NO.equals(user.getLoginFlag())) {
						throw new AuthenticationException("msg:该帐号已禁止登录.");
					}
					pp = new Principal(user, false, loginType);
				} 
			} else {
				// 其他人登录验证

				// 校验用户名密码
				User user = usrUserService.getUserByLoginName(userId);
//				if(!usrUserService.isEmpCerted(user)){
//					user=null;
//				}
				if (user != null) {
					if (Global.NO.equals(user.getLoginFlag())) {
						throw new AuthenticationException("msg:该帐号已禁止登录.");
					}
					 pp = new Principal(user, false, loginType);
				} 
			}

			PrincipalCollection principalCollection = new SimplePrincipalCollection(
					pp, getName());
			return new SimpleAuthenticationInfo(principalCollection, ticket);
		} catch (TicketValidationException e) {
			// TODO Auto-generated catch block
			throw new CasAuthenticationException((new StringBuilder())
					.append("Unable to validate ticket [").append(ticket)
					.append("]").toString(), e);
		}

		// TODO Auto-generated method stub
		// return super.doGetAuthenticationInfo(token);
	}

	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService() {
		if (systemService == null) {
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}

	

}
