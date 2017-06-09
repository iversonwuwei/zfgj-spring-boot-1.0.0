/**
 * 
 */
package com.housecenter.dlfc.zfgj.common.dwr;

import java.util.Collection;
import java.util.List;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

/**
 * @author Jack
 *
 */
public class SendMessageAutoUtil {
    
    // 消息推送 指定某个客户端的页面显示
    public static void sendMessageAuto(String userid, String message, String showScriptMethod) {
        final String yhId = userid;
        // 页面标志
        final String autoMessage = message;// 返回的数据
        final String autoShowScriptMethod = showScriptMethod;
        // 返回页面要调用的JS方法 
        //执行推送
        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
            public boolean match(ScriptSession scriptSession) {
                String yhid = (String) scriptSession.getAttribute("yhId");
                if (yhid != null) {
                    return yhId.equals(yhid);
                } else {
                    return false;
                }
            }
        }, new Runnable() {
            private ScriptBuffer script = new ScriptBuffer();

            public void run() {
                // 设置要调用的 js及参数
                script.appendCall(autoShowScriptMethod, autoMessage);
                // 得到过滤之后的ScriptSession
                Collection<ScriptSession> sessions = Browser.getTargetSessions();
                // 遍历每一个ScriptSession
                for (ScriptSession scriptSession : sessions) {
                    scriptSession.addScript(script);
                }
            }
        });// 注意这里调用了有filter功能的方法 }
           // 消息推送 指定某个客户端的页面显示 此方法是把要推送的ID放到集合
    }

    public static void sendMessageListAuto(List<String> targetYhidList, String showScriptMethod) {
        final List<String> targetIdList = targetYhidList;
        final String autoShowScriptMethod = showScriptMethod;
        // 执行推送
        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
            public boolean match(ScriptSession scriptSession) {
                String yhid = (String) scriptSession.getAttribute("yhId");
                if (yhid != null && targetIdList.contains(yhid)) {
                    // return yhid.equals(yhId);
                    targetIdList.remove(yhid);// 如果找到了，说明将被推送，所以不用再
                                              // 处理,剩下的都是要被处理的
                    return true;
                } else {
                    return false;
                }
            }
        }, new Runnable() {
            private ScriptBuffer script = new ScriptBuffer();

            public void run() {
                // 设置要调用的 js及参数
                script.appendCall(autoShowScriptMethod);
                // 得到过滤之后的ScriptSession
                Collection<ScriptSession> sessions = Browser.getTargetSessions();
                // 遍历每一个ScriptSession
                for (ScriptSession scriptSession : sessions) {
                    scriptSession.addScript(script);
                }
            }
        });// 注意这里调用了有filter功能的方法
    }
}
