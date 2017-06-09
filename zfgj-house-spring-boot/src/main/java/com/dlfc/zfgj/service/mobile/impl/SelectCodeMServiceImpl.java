package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.SysAreaAreasMapper;
import com.dlfc.zfgj.mapper.SysTradeAreasMapper;
import com.dlfc.zfgj.mapper.SysTradeStationLinkMapper;
import com.dlfc.zfgj.mapper.SysTransportStationMapper;
import com.dlfc.zfgj.service.mobile.AgtMService;
import com.dlfc.zfgj.service.mobile.SelectCodeMService;
import com.dlfc.zfgj.service.mobile.SyCodeMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangna on 2017/5/10.
 */
@Transactional
@Service("selectCodeM")
public class SelectCodeMServiceImpl implements SelectCodeMService{

    //房屋租金押X
    private static String RENT_DEPOSIT = "rent_deposit";
    //房屋租金付X
    private static String RENT_PAYMENT = "rent_payment";
    //所属商圈区域
    private static String AREA = "210200";
    //建筑时间
    private static String BUILD_TIME = "build_time";
    //房屋朝向
    private static String HOU_ORIENTATION = "hou_orientation";
    //出租房间
    private static String ROOM_TYPE = "room_type";

    @Autowired
    @Qualifier("sCodeMSerivice")
    private SyCodeMService sysCodeService;
    @Autowired
    private SysAreaAreasMapper sysAreaAreasMapper;
    @Autowired
    private SysTradeAreasMapper sysTradeAreasMapper;
    @Autowired
    private SysTradeStationLinkMapper sysTradeStationLinkMapper;
    @Autowired
    private SysTransportStationMapper sysTransportStationMapper;
    @Autowired
    @Qualifier("aMServiceImpl")
    private AgtMService agtService;

    /**
     * 房租租金押Select
     *
     * @return SysCode
     */
    @Override
    public List<SysCode> getByPledge() {
        List<SysCode> sysCodes = sysCodeService.getCodeByType(RENT_DEPOSIT);
        return sysCodes;
    }


    /**
     * 房屋租金付Select
     *
     * @return SysCode
     */
    @Override
    public List<SysCode> getByPay() {
        List<SysCode> sysCodes = sysCodeService.getCodeByType(RENT_PAYMENT);
        return sysCodes;
    }

    /**
     * 城市区域
     *
     * @return
     */
    @Override
    public List<SysAreaAreas> getByArea() {
        SysAreaAreasExample example = new SysAreaAreasExample();
        SysAreaAreasExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        criteria.andCityIdEqualTo(AREA);
        return sysAreaAreasMapper.selectByExample(example);
    }

    /**
     * 城市区域对应的商圈
     */
    @Override
    public List<SysTradeAreas> getByBusinessArea(String parentId) {
        SysTradeAreasExample example = new SysTradeAreasExample();
        SysTradeAreasExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        return sysTradeAreasMapper.selectByExample(example);
    }

    /**
     * 地铁查询
     */
    @Override
    public SysTransportStation getByMetro(String tradeId) {
        SysTradeAreasExample sysTradeAreasExample = new SysTradeAreasExample();
        SysTradeAreasExample.Criteria sysTradecriteria = sysTradeAreasExample.createCriteria();
        sysTradecriteria.andTradeIdEqualTo(tradeId);
        sysTradecriteria.andDeleteFlgEqualTo((short) 0);
        List<SysTradeAreas> list = sysTradeAreasMapper.selectByExample(sysTradeAreasExample);
        String tradeid = null;
        if (list != null && list.size() > 0) {
            tradeid = list.get(0).getId();
        }
        SysTradeStationLinkExample sysTradeStationLinkExample = new SysTradeStationLinkExample();
        SysTradeStationLinkExample.Criteria criteria = sysTradeStationLinkExample.createCriteria();
        criteria.andTradeIdEqualTo(tradeid);
        //商圈表查询 商圈对应的地铁关联表sysTradeStationLink 反查 地铁表主键
        List<SysTradeStationLink> slist = sysTradeStationLinkMapper.selectByExample(sysTradeStationLinkExample);
        String stationId = null;
        for (SysTradeStationLink sysTradeStationLink : slist) {
            stationId = sysTradeStationLink.getStationId();
        }
        if (StringUtils.isNotBlank(stationId)) {
            //查询地铁表信息
            SysTransportStation sysTransportStation = sysTransportStationMapper.selectByPrimaryKey(stationId);
            return sysTransportStation;
        }
        return null;

    }

    /**
     * 建筑时间
     *
     * @return
     */
    @Override
    public List<SysCode> getByBulidingTime() {
        List<SysCode> sysCodes = sysCodeService.getCodeByType(BUILD_TIME);
        return sysCodes;
    }

    /**
     * 房屋朝向
     *
     * @return
     */
    @Override
    public List<SysCode> getDirection() {
        List<SysCode> sysCodes = sysCodeService.getCodeByType(HOU_ORIENTATION);
        return sysCodes;
    }

    /**
     * 出租房间list
     *
     * @return
     */
    @Override
    public List<SysCode> getRoomList() {
        List<SysCode> sysCodes = sysCodeService.getCodeByType(ROOM_TYPE);
        return sysCodes;
    }

//    /**
//     * 查询所有公司信息
//     *
//     * @return
//     */
//
//    @Override
//    public List<AgtCompInfo> getCompInfo() {
//        //查询所有公司
//        List<AgtCompInfo> list = agtService.findList();
//        return list;
//    }
}