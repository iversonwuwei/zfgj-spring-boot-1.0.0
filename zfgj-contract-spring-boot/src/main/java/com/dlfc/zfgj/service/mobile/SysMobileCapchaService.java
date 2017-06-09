package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.SysMobileCapcha;

/**
 * Created by K on 2017/5/10.
 */
public interface SysMobileCapchaService {

    boolean getCaptchaCountByMobile(String phone, String domain);

    SysMobileCapcha selectByVerCode(SysMobileCapcha sysMobileCapcha);
}
