/**
 * 
 */
package com.housecenter.dlfc.zfgj.common.dwr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;

import com.dlfc.admin.modules.sys.utils.UserUtils;

/**
 * @author Jack
 *
 */
public class DWRScriptSessionListener implements ScriptSessionListener {
    
    /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(DWRScriptSessionListener.class);

    // 维护一个Map key为session的Id， value为ScriptSession对象
    public static final Map<String, ScriptSession> scriptSessionMap = new HashMap<String, ScriptSession>();

    /** 
     * ScriptSession创建事件 
     */
    public void sessionCreated(ScriptSessionEvent event) {
        WebContext webContext = WebContextFactory.get();
        HttpSession session = webContext.getSession();
        ScriptSession scriptSession = event.getSession();
        if (UserUtils.getUser() != null) {
            scriptSession.setAttribute("yhId", UserUtils.getUser().getLoginName());
        }
        
        scriptSessionMap.put(session.getId(), scriptSession);
        
        if (logger.isDebugEnabled() && scriptSession != null) {
            logger.debug("session: " + session.getId() + " scriptSess ion: " + scriptSession.getId() + " is created!");
        }
    }

    /** 
     * ScriptSession销毁事件 
     */
    public void sessionDestroyed(ScriptSessionEvent event) {
        WebContext webContext = WebContextFactory.get();
        HttpSession session = webContext.getSession();

        ScriptSession scriptSession = scriptSessionMap.remove(session.getId());
        
        if (logger.isDebugEnabled() && scriptSession != null) {
            logger.debug("session: " + session.getId() + " scriptSess ion: " + scriptSession.getId() + "is destroyed!");
        }
    }

    /**
     * 获取所有ScriptSession 
     */
    public static Collection<ScriptSession> getScriptSessions() {
        if (logger.isDebugEnabled()) {
            logger.debug("sessionCounts: " + scriptSessionMap.size());
        }
        return scriptSessionMap.values();
    }

}
