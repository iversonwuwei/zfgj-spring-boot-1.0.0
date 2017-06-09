package com.dlfc.admin.modules.sys.web;

import com.dlfc.admin.common.config.Global;
import com.dlfc.admin.common.security.shiro.session.SessionDAO;
import com.dlfc.admin.common.servlet.ValidateCodeServlet;
import com.dlfc.admin.common.utils.*;
import com.dlfc.admin.common.web.BaseController;
import com.dlfc.admin.modules.sys.entity.Menu;
import com.dlfc.admin.modules.sys.entity.User;
import com.dlfc.admin.modules.sys.security.FormAuthenticationFilter;
import com.dlfc.admin.modules.sys.security.Principal;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.SysOperateLog;
import com.dlfc.zfgj.mapper.SysMenuMapper;
import com.dlfc.zfgj.service.SysOperateLogService;
import com.dlfc.zfgj.service.UsrUserService;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 登录Controller
 * 
 * @author dlfc
 * @version 2013-5-31
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private SysOperateLogService sysOperateLogService;

    @Autowired
    private UsrUserService usrUserService;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 管理登录
     */
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        Principal principal = UserUtils.getPrincipal();

        // // 默认页签模式
        // String tabmode = CookieUtils.getCookie(request, "tabmode");
        // if (tabmode == null){
        // CookieUtils.setCookie(response, "tabmode", "1");
        // }

        if (logger.isDebugEnabled()) {
            logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
        }

        // 如果已登录，再次访问主页，则退出原账号。
        if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))) {
            CookieUtils.setCookie(response, "LOGINED", "false");
        }

        // 如果已经登录，则跳转到管理首页
        if (principal != null && !principal.isMobileLogin()) {
            return "redirect:" + adminPath;
        }
        // String view;
        // view = "/WEB-INF/views/modules/sys/sysLogin.jsp";
        // view = "classpath:";
        // view +=
        // "jar:file:/D:/GitHub/dlfc/src/main/webapp/WEB-INF/lib/jeesite.jar!";
        // view += "/"+getClass().getName().replaceAll("\\.",
        // "/").replace(getClass().getSimpleName(), "")+"view/sysLogin";
        // view += ".jsp";
        return "modules/sys/sysLogin";
    }

    /**
     * 登录失败，真正登录的POST请求由Filter完成
     */
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
        Principal principal = UserUtils.getPrincipal();

        // 如果已经登录，则跳转到管理首页
        if (principal != null) {
            return "redirect:" + adminPath;
        }

        String logintype = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_LOGINTYPE_PARAM);

        String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
        boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
        boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
        String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String message = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);

        if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")) {
            message = "用户或密码错误, 请重试.";
        }

        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
        model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);

        if (logger.isDebugEnabled()) {
            logger.debug("login fail, active session size: {}, message: {}, exception: {}",
                    sessionDAO.getActiveSessions(false).size(), message, exception);
        }

        // 非授权异常，登录失败，验证码加1。
        if (!UnauthorizedException.class.getName().equals(exception)) {
            model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
        }

        // 验证失败清空验证码
        request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());

        // 如果是手机登录，则返回JSON字符串
        if (mobile) {
            return renderString(response, model);
        }

        model.addAttribute(FormAuthenticationFilter.DEFAULT_LOGINTYPE_PARAM, logintype);
        return "modules/sys/sysLogin";
    }

    /**
     * 登录成功，进入管理首页
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "${adminPath}")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        Principal principal = UserUtils.getPrincipal();

        // 登录成功后，验证码计算器清零
        isValidateCodeLogin(principal.getLoginName(), false, true);

        if (logger.isDebugEnabled()) {
            logger.debug("show index, active session size: {}", sessionDAO.getActiveSessions(false).size());
        }

        SysOperateLog record = new SysOperateLog();
        record.preInsert();
        record.setSessionid(principal.getSessionid());
        record.setUserId(principal.getId());
        record.setUserName(principal.getLoginName());
        record.setMobile(UserUtils.getUser().getMobile());
        record.setBrowerType(RequestUtils.getRequestBrower(request));
        record.setMoblieType(RequestUtils.getRequestSystem(request));
        record.setIp(RequestUtils.getIpAddr(request));
        record.setOperateTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
        record.setDescription("登录");
        sysOperateLogService.save(record);
        
        // 设定用户名到HttpSession
        request.getSession().setAttribute("userid", principal.getLoginName());
        request.getSession().setAttribute("username", principal.getName());

        // 如果已登录，再次访问主页，则退出原账号。
        if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))) {
            String logined = CookieUtils.getCookie(request, "LOGINED");
            if (StringUtils.isBlank(logined) || "false".equals(logined)) {
                CookieUtils.setCookie(response, "LOGINED", "true");
            } else if (StringUtils.equals(logined, "true")) {
                UserUtils.getSubject().logout();
                return "redirect:" + adminPath + "/login";
            }
        }

        // 如果是手机登录，则返回JSON字符串
        String allowMobileLogin = Global.getConfig("allowMobileLogin");
        if(allowMobileLogin.equals("0")){
	        if (principal.isMobileLogin()) {
	            if (request.getParameter("login") != null) {
	                return renderString(response, principal);
	            }
	            if (request.getParameter("index") != null) {
	
	                return "modules/sys/sysIndex";
	            }
	            return "redirect:" + adminPath + "/login";
	        }
        }

        // 设置DWR控制器user login
        // ScriptSession scriptSession =
        // WebContextFactory.get().getScriptSession();
        // scriptSession.setAttribute("uid", UserUtils.getUser().getId());

        // // 登录成功后，获取上次登录的当前站点ID
        // UserUtils.putCache("siteId",
        // StringUtils.toLong(CookieUtils.getCookie(request, "siteId")));

        // System.out.println("==========================a");
        // try {
        // byte[] bytes =
        // com.dlfc.admin.common.utils.FileUtils.readFileToByteArray(
        // com.dlfc.admin.common.utils.FileUtils.getFile("c:\\sxt.dmp"));
        // UserUtils.getSession().setAttribute("kkk", bytes);
        // UserUtils.getSession().setAttribute("kkk2", bytes);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // // for (int i=0; i<1000000; i++){
        // // //UserUtils.getSession().setAttribute("a", "a");
        // // request.getSession().setAttribute("aaa", "aa");
        // // }
        // System.out.println("==========================b");
        if (principal.getLoginName().equals("thinkgem")) {
            return "modules/sys/sysIndex";
        } else {
            if (FormAuthenticationFilter.MGR_LOGIN_TYPE.equals(principal.getLoginType())) {
            	String first = (String) UserUtils.getSession().getAttribute("first");
            	if("first".equals(first)){
            		UserUtils.getSession().removeAttribute(first);
            		return "modules/sys/sysIndex";
            	}else{
            		UserUtils.getSession().setAttribute("first", "first");
            		return "redirect:"+Global.getConfig("cas.server.url")+"/logout?service="+Global.getConfig("cas.project.url")+"/";
            	}
            } else {
                Menu m = new Menu();
                User user = usrUserService.getUserByLoginName(principal.getLoginName());
                m.setUserId(user.getId());
                List<Menu> rootMenu = sysMenuMapper.findByUserId(m);
                if (rootMenu.size() != 0) {
                	// 进入正常框架
                    model.addAttribute("rootMenu", rootMenu);
                    return "modules/sys/sysIndex2";
                } else {
                	return "redirect:" + adminPath + "/agt/index";
                }

            }

        }

    }

    /**
     * 获取主题方案
     */
    @RequestMapping(value = "/theme/{theme}")
    public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request,
            HttpServletResponse response) {
        if (StringUtils.isNotBlank(theme)) {
            CookieUtils.setCookie(response, "theme", theme);
        } else {
            theme = CookieUtils.getCookie(request, "theme");
        }
        return "redirect:" + request.getParameter("url");
    }

    /**
     * 是否是验证码登录
     * 
     * @param useruame
     *            用户名
     * @param isFail
     *            计数加1
     * @param clean
     *            计数清零
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            CacheUtils.put("loginFailMap", loginFailMap);
        }
        Integer loginFailNum = loginFailMap.get(useruame);
        if (loginFailNum == null) {
            loginFailNum = 0;
        }
        if (isFail) {
            loginFailNum++;
            loginFailMap.put(useruame, loginFailNum);
        }
        if (clean) {
            loginFailMap.remove(useruame);
        }
        return loginFailNum >= 3;
    }
    
    /**
	 * 单点登录跳转，清空本地session，重新校验cas
	 * 
	 * 
	 */
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect(HttpServletRequest request, HttpServletResponse response) {
		String rUrl=request.getParameter("url");
		if(StringUtils.isBlank(rUrl)){
			rUrl = getLoginPath();
		}
		String logined = CookieUtils.getCookie(request, "LOGINED");
		if (StringUtils.isBlank(logined) || "false".equals(logined)) {
			CookieUtils.setCookie(response, "LOGINED", "true");
		} else if (StringUtils.equals(logined, "true")) {
			UserUtils.getSubject().logout();
		}
		return "redirect:" + rUrl ;
	}
	
	
	/**
	 * 获取租房管家登录地址
	 */
	public static String getLoginPath() {
		return Global.getConfig("cas.server.url")+"?service="+Global.getConfig("cas.project.url")+Global.getConfig("adminPath")+"/cas";
	}
	
	/**
	 * 获取退出地址
	 */
	public static String getLogoutPath() {
		return Global.getConfig("cas.server.url")+"/logout?service="+Global.getConfig("cas.project.url")+"/";
	}
}
