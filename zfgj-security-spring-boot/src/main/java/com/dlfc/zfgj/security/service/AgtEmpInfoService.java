package com.dlfc.zfgj.security.service;

import com.dlfc.zfgj.security.entity.AgtEmpInfo;

import java.util.List;

/**
 * Created by K on 2017/3/29.
 */
public interface AgtEmpInfoService {

    List<AgtEmpInfo> getAgtEmpInfoByUserId(String userId);
}
