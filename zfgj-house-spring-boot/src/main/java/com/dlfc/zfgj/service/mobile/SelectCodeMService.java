package com.dlfc.zfgj.service.mobile;

import com.dlfc.zfgj.entity.*;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */
public interface SelectCodeMService {
    List<SysCode> getByPledge();

    List<SysTradeAreas> getByBusinessArea(String parentId);

    List<SysAreaAreas> getByArea();

    List<SysCode> getByPay();

    SysTransportStation getByMetro(String businessId);

    List<SysCode> getByBulidingTime();

    List<SysCode> getDirection();

    List<SysCode> getRoomList();

//    List<AgtCompInfo> getCompInfo();


}
