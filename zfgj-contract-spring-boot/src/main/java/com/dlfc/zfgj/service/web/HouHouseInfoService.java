package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.UsrUser;

/**
 * Created by K on 2017/3/3.
 */
public interface HouHouseInfoService {

    String saveHouseInfo(HouHouseInfo houHouseInfo, UsrUser user);

    void updateHouseInfo(HouHouseInfo houHouseInfo, UsrUser user);

    HouHouseInfo getHouseInfoByContractInput(ConContract conContract);

    HouHouseInfo getHouHouseInfoById(String hid);
}
