package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.AgtUsrCompLogInfo;

import java.util.List;

/**
 * Created by K on 2017/6/8.
 */
public interface AgtUsrCompLogInfoService {
    List<AgtUsrCompLogInfo> findByUid(String uid);
}
