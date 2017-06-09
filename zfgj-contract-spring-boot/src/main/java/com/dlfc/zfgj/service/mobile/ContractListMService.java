package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by walden on 2017/2/22.
 */
public interface ContractListMService {

    List<ConContract> getAllPersonalContracts(Integer beginPos,Integer count,UsrUser usrUser , Integer... conStatus);

    List<ConContract> getPersonalContractsCount(UsrUser usrUser,Integer... conStatus);
}
