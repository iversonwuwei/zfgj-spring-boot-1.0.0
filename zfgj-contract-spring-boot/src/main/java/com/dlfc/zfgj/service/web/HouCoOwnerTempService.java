package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.HouCoOwnerTemp;

import java.util.List;

/**
 * Created by K on 2017/3/13.
 */
public interface HouCoOwnerTempService {
    List<HouCoOwnerTemp> getHouCoOwnersByHid(String hid);

    void deleteOldOwners(String hid);

    void saveHouseOwner(HouCoOwnerTemp houCoOwner);
}
