package com.dlfc.zfgj.dto.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 发布房源
 * Created by wangna on 2017/3/21.
 */
@Getter
@Setter
@Component
public class AddHouseLeaseMDTO {

    /**
     * 出租信息id
     */
    private String houseId;

    /**
     * 详细地址
     */
    private String houseAddress;

    /**
     * 建筑面积
     */
    private String  buildingArea;

    /**
     * 出租方式
     */
    private String leaseMode;

//    /**
//     * 房屋结构
//     */
//    private Integer houseStructure;

//    /**
//     * 　是否允许分期支付房租 00：否 01 ：是
//     */
//    private String stages;

    /**
     * 所在商圈
     */
    private String district;

    /**
     * 相邻地铁站
     */
    private String stationId;

    /**
     * 所在小区
     */
    private String villageName;

    /**
     * 楼号
     */
    private String no;

    /**
     *  单元
     */
    private String unit;

    /**
     * 　室
     */
    private String door;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 户型
     */
    private String apartmentLayout;

    /**
     * 房屋朝向
     */
    private String orientation;

    /**
     * 建筑时间
     */
    private String buildTime;

    /**
     * 装修时间
     */
    private String decorTime;

    /**
     * 装修程度
     */
    private String decor;

    /**
     * 房源描述
     */
    private String description;

    /**
     * 房屋设施
     */
    private String facilities;

    /**
     * 房源特色
     */
    private String feature;

    /**
     * 配套家电
     */
    private String Electric;

    /**
     * 租住要求
     */
    private String requirement;

    /**
     * 房主承担费用其他
     */
    private String ownerCost;

    /**
     * 房主承担费用
     */
    private String ownerCostItems;



    /**
     * 意向租金
     */
    private String rent;

    /**
     * 付款方式
     */
    private String rentType;

    /**
     * 可入住时间
     */
    private String checkinTime;

//    /**
//     * 房间信息
//     */
//    List<AddLeaseRoomMDTO> leaseRoomMDTOList;

    /**
     * 房间名称
     */
    private String room;

    /**
     * 房间面积
     */
    private String roomArea;

    /**
     * 图片id
     */
    private String[]  sysInfoId;

}
