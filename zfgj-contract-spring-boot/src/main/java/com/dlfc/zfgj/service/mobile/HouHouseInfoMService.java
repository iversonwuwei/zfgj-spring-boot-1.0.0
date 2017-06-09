package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.UsrUser;

/**
 * Created by K on 2017/3/3.
 */
public interface HouHouseInfoMService {
    HouHouseInfo getHouseInfoByPropertyId(String propertyId);

    HouHouseInfo getHouseInfoByAddress(String address);

    String saveHouseInfo(HouHouseInfo houHouseInfo, UsrUser user);

    void updateHouseInfo(HouHouseInfo houHouseInfo, UsrUser user);

    HouHouseInfo getHouseInfoByContractInput(ConContract conContract);

    HouHouseInfo getHouHouseInfoById(String hid);
}
