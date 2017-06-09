package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.HouCoOwner;

import java.util.List;

/**
 * Created by K on 2017/3/13.
 */
public interface HouCoOwnerMService {
    List<HouCoOwner> getHouCoOwnersByHid(String hid);
}
