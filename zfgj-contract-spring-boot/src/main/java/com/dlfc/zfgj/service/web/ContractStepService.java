package com.dlfc.zfgj.service.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/3/12.
 */
public interface ContractStepService {

    ConContract getContractByCid(String cid);

    void checkContractId(String contractId) throws ApplicationException;

    void cancelContract(String contractId, UsrUser user) throws ApplicationException;

    void saveStep1(ConContract conContract, UsrUser user) throws ApplicationException;

    void saveStep2(ConContract conContract, UsrUser user) throws ApplicationException;

    void saveStep3(ConContract conContract, UsrUser user) throws ApplicationException;

    void saveStep4(ConContract conContract, UsrUser user) throws ApplicationException;

    ConContract getLatestContract(ConContract conContract);

    String getStrFromArray(String[] list);

    List<String> getListFromStr(String str, List<SysCode> sysCodeList);

    void saveStep5(String contractId, UsrUser user) throws ApplicationException;

    List<String> getPaymentExplanation(Date startDate,
                                       Date endDate,
                                       Integer settlementCycle,
                                       Integer monthlyRent,
                                       Integer totalMonth);

    List<String> getPaymentExplanationForView(String contractId,
                                              Integer monthlyRent,
                                              Integer settlement);

    Date getContractEndDate(Long startDateValue, String years, String months);

    void setStatus(int status, String contractId, UsrUser user);
}
