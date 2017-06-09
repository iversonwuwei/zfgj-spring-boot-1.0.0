package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.HouCoOwnerTemp;

import java.util.List;

/**
 * Created by wanglijun on 2017/4/14.
 */
public interface HouCoOwnerTempMService {
    List<HouCoOwnerTemp> getHouCoOwnersByHid(String hid);

    void deleteOldOwners(String hid);

    void saveHouseOwner(HouCoOwnerTemp houCoOwner);
}
