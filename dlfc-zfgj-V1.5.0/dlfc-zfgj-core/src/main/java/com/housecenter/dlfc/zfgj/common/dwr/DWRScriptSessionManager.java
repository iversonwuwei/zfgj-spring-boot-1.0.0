/**
 * 
 */
package com.housecenter.dlfc.zfgj.common.dwr;

import org.apache.log4j.Logger;
import org.directwebremoting.impl.DefaultScriptSessionManager;

/**
 * @author Jack
 *
 */
public class DWRScriptSessionManager extends DefaultScriptSessionManager {
    
    /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(DWRScriptSessionManager.class);

    public DWRScriptSessionManager() {
        // 绑定一个ScriptSession增加销毁事件的监听器
        this.addScriptSessionListener(new DWRScriptSessionListener());
        if (logger.isDebugEnabled()) {
            logger.debug("bind DWRScriptSessionListener");
        }
    }

}
