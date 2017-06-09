package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.ConHouseItems;

import java.util.List;

/**
 * Created by wangna on 2017/6/9.
 */
public interface ConHouseItemsMService {
    List<ConHouseItems> getConHouseItemsByCid(String cid);
}
