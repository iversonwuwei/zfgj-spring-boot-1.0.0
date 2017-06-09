package com.dlfc.zfgj.service.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.AgtUsrCompLogInfo;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by wsz on 2017/3/6.
 */
public interface AgtService {
    AgtEmpInfo getComAll(String uid);

//    String getPhone(String uid);

    AgtEmpInfo findById(String id);

    boolean isInCom(String uid);

//    int eContract(AgtEmpInfo model);

//    int contract(AgtEmpInfo model);

//    int cus(String id);

//    int unAuHouse(String id);

//    int wHouse(String id);

//    int conHouse(String id);

    AgtUsrCompLogInfo findAgtUsrCompLog(String uid);

    AgtCompInfo getCompInfoById(String id);

//    List<AgtCompInfo> findList();

    /**
     * 修改经纪人手机号码
     *
     * @param aei 经纪人信息对象
     */
    void updatePhone(AgtEmpInfo aei);

    /**
     * 经纪人加入公司
     */
    void joinComp(String comName, UsrUser usrUser) throws ApplicationException;

    int reJoinComp(String id);

    /**
     * 根据uid查询经纪人的officeId
     */
    String getOfficeId(String userId);

    List<String> getEmpIdListByOfficeId(String officeId);

    AgtEmpInfo getAgtEmpInfoByUserId(String userId);

    void save(AgtEmpInfo agtEmpInfo);

    List<AgtCompInfo> getCompInfos();
}
