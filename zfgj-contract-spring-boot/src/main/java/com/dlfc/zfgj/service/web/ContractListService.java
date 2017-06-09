package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by walden on 2017/2/22.
 */
public interface ContractListService {

    List<ConContract> getAllPersonalContracts(String timeType, String orderBy, UsrUser user);

    List<ConContract> getAllDepartmentalContracts(String timeType, String orderBy, UsrUser user);

    List<ConContract> getAllPersonalContractsBy(String searchInfo, String timeType, String orderBy, UsrUser user);

    List<ConContract> getAllDepartmentalContractsBy(String searchInfo, String timeType, String orderBy, UsrUser user);

    List<ConContract> getPersonalEffectiveContracts(String timeType, String orderBy, UsrUser user);

    List<ConContract> getDepartmentalEffectiveContracts(String timeType, String orderBy, UsrUser user);

    List<ConContract> getPersonalNearingContracts(String timeType, String orderBy, UsrUser user);

    List<ConContract> getDepartmentalNearingContracts(String timeType, String orderBy, UsrUser user);
}
