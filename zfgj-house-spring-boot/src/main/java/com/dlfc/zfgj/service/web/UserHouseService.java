package com.dlfc.zfgj.service.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.UsrUser;

/**
 * Created by walden on 2017/2/22.
 */
public interface UserHouseService {

    UsrUser getUser(String token) throws ApplicationException;

    UsrUser getUserById(String id);


}
