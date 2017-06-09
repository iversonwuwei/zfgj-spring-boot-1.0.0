/**
 * 
 */
package com.dlfc.admin.modules.sys.web;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dlfc.admin.common.web.BaseController;
import com.dlfc.zfgj.service.MessageService;

/**
 * @author Jack
 *
 */
@Controller
@RemoteProxy(name = "msgCtl")
public class MessageController extends BaseController {

    @Autowired
    private MessageService msgService;

    /**
     * 取得新消息数量
     * 
     * @return 新消息数量
     */
    @RemoteMethod
    public int count() {
        return msgService.StatusCount();
    }
    
    @RemoteMethod
    public String getFirstTitle() {
        return msgService.firstTitle();
    }
    
    @RemoteMethod
    public void read(String id) {
        msgService.read(id);
    }
}
