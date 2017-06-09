package com.dlfc.zfgj.security.mobile.service;

import com.dlfc.zfgj.security.mobile.entity.AgtUsrCompLogInfo;

import java.util.List;

/**
 * Created by K on 2017/6/6.
 */
public interface AgtUsrCompLogInfoService {
    List<AgtUsrCompLogInfo> findAgtUsrCompLogInfoByUid(String uid);
}
