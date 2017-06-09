package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.SysMobileCapcha;
import com.dlfc.zfgj.entity.SysMobileCapchaExample;
import com.dlfc.zfgj.mapper.SysMobileCapchaMapper;
import com.dlfc.zfgj.service.web.SMSService;
import com.housecenter.dlfc.framework.sms.SMSFacet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

;

/**
 * Created by wsz on 2017/3/8.
 */
@Transactional
@Service("smsServiceImpl")
public class SMSServiceImpl implements SMSService {
    /**
     * 手机短信验证码Mapper
     */
    @Autowired
    private SysMobileCapchaMapper sysMobileCapchaMapper;





    /**
     * 发送短信
     *
     * @param mobile     手机号
     * @param templateNo 模版编号
     * @param params     参数
     * @return
     */
    /*@Override
    public void sendSMSByTemplate(String mobile, String templateNo, Map<String, String> params, HttpServletRequest request) {
        if (!StringUtils.isBlank(mobile) && !StringUtils.isBlank(templateNo)) {
            SMSFacet.sendSMSByTemplate(mobile, templateNo, params,request);
        }
    }*/

    @Override
    public void sendSMSByTemplate(String mobile, String templateNo, Map<String, String> params, HttpServletRequest request) {

    }

    /**
     * 查询验证码
     *
     * @param phone 手机号
     * @param vCode 验证码
     * @return 验证码
     */
    @Override
    public String selectVerCodeCapcha(String phone, String vCode) {
        SysMobileCapchaExample example = new SysMobileCapchaExample();
        SysMobileCapchaExample.Criteria cri = example.createCriteria();
        cri.andVerCodeEqualTo(vCode);
        cri.andMobileEqualTo(phone);
        List<SysMobileCapcha> list = sysMobileCapchaMapper.selectByExample(example);
        if (list != null && list.size() != 0) {
            return list.get(0).getVerCode();
        } else {
            return null;
        }
    }



    /**
     * 发送验证码
     *
     * @param mobile     手机号
     * @param templateNo 短信模板号
     * @param domain     业务域
     * @param request    接收客户端向服务器发出请求
     * @return 验证码
     * @Modification History:<br>
     * Date          Author         Version        Discription
     * 20160515      zhangyan        1.0           获取客户端IP地址不正确，使用request.getRemoteAddr();
     */
    @Override
    public String sendCapcha(String mobile, String templateNo, String domain, HttpServletRequest request) {
        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(templateNo) || StringUtils.isBlank(domain)) {
            return null;
        } else {
            String clientIP = request.getRemoteAddr();//获取客户端IP地址
            String capcha = SMSFacet.sendCapcha(mobile, request.getSession().getId(), clientIP, templateNo, domain);
            return capcha;
        }
    }


}
