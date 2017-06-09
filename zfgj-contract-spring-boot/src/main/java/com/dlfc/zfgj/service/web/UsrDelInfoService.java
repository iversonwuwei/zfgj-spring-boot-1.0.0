package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by K on 2017/3/10.
 */
public interface UsrDelInfoService {

    List<String> getDelIdListByUser(UsrUser user);

    List<String> getDelIdListByEmpIdList(List<String> emlIdList);
}
