package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by K on 2017/2/16.
 */
public interface AgtEmpInfoService {

    List<String> getEmpIdListByOfficeId(String officeId);

    boolean isCompLockedByUserId(UsrUser user);

    AgtEmpInfo getAgtEmpInfoByUserId(String userId);

    AgtEmpInfo getAgtEmpInfoById(String eid);

    List<String> getEmpIdListByCompanyId(String companyId);
}
