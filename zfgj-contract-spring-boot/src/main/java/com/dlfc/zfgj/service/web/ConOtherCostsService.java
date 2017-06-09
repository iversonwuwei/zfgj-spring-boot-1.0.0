package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.ConOtherCosts;

import java.util.List;

/**
 * Created by K on 2017/4/1.
 */
public interface ConOtherCostsService {
    List<ConOtherCosts> getConOtherCostsByCid(String cid);
}
