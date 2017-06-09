package com.dlfc.zfgj.service.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by walden on 2017/2/22.
 */
public interface UserHouseMService {

    List<UsrUser> findByMobile(String mobile);

    String save(UsrUser user);

    UsrUser getUserName(String token) throws ApplicationException;

    UsrUser getUserById(String id);

    String generatePassword(String password);

    String generateUsername(String mobile);

    List<UsrUser> findByPerId(String id);

    String updateById(UsrUser user);
}
