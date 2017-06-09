package com.dlfc.zfgj.service.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.*;

import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/12.
 */
public interface ContractStepMService {

    ConContract getContractByCid(String cid);

    void checkContractId(String contractId) throws ApplicationException;

//    void cancelContract(String contractId) throws ApplicationException;

    void saveStep1(ConContract conContract, UsrUser usrUser) throws ApplicationException;

//    void saveStep2(String contractId, ConContractV2 conContract) throws ApplicationException;
//
//    void saveStep3(String contractId, ConContractV2 conContract) throws ApplicationException;
//
//    void saveStep4(String contractId, ConContractV2 conContract) throws ApplicationException;

//    void checkIdentity(String id, Integer idType, String name, String phoneNo) throws ApplicationException;

//    ConContract getLatestContract(ConContractV2 model);

    String getStrFromList(List<String> list);

    List<String> getListFromStr(String str, List<SysCode> sysCodeList);

    void checkIdentity(String idNumber, Integer identityMode, String houseUserName, String phoneNumber, UsrUser user) throws ApplicationException;

//    void saveStep5(String contractId)throws ApplicationException;

//    String checkRentArea(String contractId)throws ApplicationException;
    List<String> getPaymentExplanation(Date startDate,
                                   Date endDate,
                                   Integer settlementCycle,
                                   Integer monthlyRent,
                                   Integer totalMonth);
    List<String> getPaymentExplanationForView(String contractId,
                                              Integer monthlyRent,
                                              Integer settlement);
    List<SysInfoAtt> getContractSign(String cid);

    void upateContractStatus(String contractId,UsrUser usrUser) throws ApplicationException;
}
