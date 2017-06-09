package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by K on 2017/2/16.
 */
public interface AgtEmpInfoMService {

    List<String> getEmpIdListByOfficeId(String officeId);

    boolean isCompLockedByUserId(UsrUser user);

    AgtEmpInfo getAgtEmpInfoByUserId(String userId);
    AgtEmpInfo getAgtEmpInfoById(String eid);

    boolean changePhone(UsrUser usrUser,String phone,String code,String msgId) throws Exception;
}
