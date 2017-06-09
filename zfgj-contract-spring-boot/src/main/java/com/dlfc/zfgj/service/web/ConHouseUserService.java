package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.ConHouseUser;

import java.util.List;

/**
 * Created by K on 2017/3/14.
 */
public interface ConHouseUserService {

    List<ConHouseUser> getHouseUserByCId(String cid);
}
