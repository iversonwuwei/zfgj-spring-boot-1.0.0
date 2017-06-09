package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.SysMobileCapcha;

import java.util.List;

/**
 * Created by K on 2017/6/5.
 */
public interface SysMobileCapchaMSerivce {

    List<SysMobileCapcha> findByParams(SysMobileCapcha sysMobileCapcha);
}
