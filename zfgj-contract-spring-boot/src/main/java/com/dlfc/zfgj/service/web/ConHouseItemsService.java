package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.ConHouseItems;

import java.util.List;

/**
 * Created by K on 2017/4/1.
 */
public interface ConHouseItemsService {
    List<ConHouseItems> getConHouseItemsByCid(String cid);
}
