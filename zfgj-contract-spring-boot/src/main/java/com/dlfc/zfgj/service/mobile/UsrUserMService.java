package com.dlfc.zfgj.service.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.UsrUser;

/**
 * Created by K on 2017/2/24.
 */
public interface UsrUserMService {
    UsrUser getUserByPerId(String perId);

    UsrUser getUser(String token) throws ApplicationException;
}
