package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.ConHouseUserChildren;

import java.util.List;

/**
 * Created by wangna on 2017/6/9.
 */
public interface ConHouseUserChildrenService {
    List<ConHouseUserChildren> getByCid(String cid);
}
