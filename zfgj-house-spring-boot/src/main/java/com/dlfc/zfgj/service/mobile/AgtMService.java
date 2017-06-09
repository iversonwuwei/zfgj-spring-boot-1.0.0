package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.AgtEmpInfo;

import java.util.List;

/**
 * Created by wsz on 2017/3/6.
 */
public interface AgtMService {
//    AgtEmpInfo getComAll(String uid);

//    String getPhone(String uid);

//    boolean isInCom(String uid);

//    int eContract(AgtEmpInfo model);

//    int contract(AgtEmpInfo model);

//    int cus(String id);

//    int unAuHouse(String id);

//    int wHouse(String id);

//    int conHouse(String id);
//
//    int actHouse(String id);

//    List<AgtUsrCompLogInfo> findAgtUsrCompLog(String uid);

    AgtCompInfo getCompInfoById(String id);

//    List<AgtCompInfo> findList();

//    /**
//     * 修改经纪人手机号码
//     *
//     * @param aei 经纪人信息对象
//     */
//    void updatePhone(AgtEmpInfo aei);

//    /**
//     * 经纪人加入公司
//     */
//    void joinComp(String comName) throws ApplicationException;

    int reJoinComp(String id);

//    /**
//     * 根据uid查询经纪人的Eid
//     */
//    String getEid(String userId);

    List<String> getEmpIdListByOfficeId(String officeId);

    String getOfficeIdByUser();

    AgtEmpInfo getAgtEmpInfoByUserId(String userId);
}
