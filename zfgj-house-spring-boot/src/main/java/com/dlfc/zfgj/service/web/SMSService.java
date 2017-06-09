package com.dlfc.zfgj.service.web;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wsz on 2017/3/8.
 */
public interface SMSService {

    public void sendSMSByTemplate(String mobile, String templateNo,Map<String, String> params,HttpServletRequest request);

    public String selectVerCodeCapcha(String phone, String vCode);

    public String sendCapcha(String mobile, String templateNo,String domain,HttpServletRequest request);


}
