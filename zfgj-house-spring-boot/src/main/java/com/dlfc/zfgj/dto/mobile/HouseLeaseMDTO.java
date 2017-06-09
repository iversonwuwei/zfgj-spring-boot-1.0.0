package com.dlfc.zfgj.dto.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangna on 2017/6/1.
 */
@Getter
@Setter
@Component
public class HouseLeaseMDTO {


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
     *  楼号
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

    /**
     * 房间名称
     */
    private String room;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 审核拒绝原因
     */
    private String rejectReason;

    /**
     * 初始化
     */
    private List<SysAttMDTO> sysAttMDTOList;

    /**
     * 装修方式
     */
    private List<SysCodeMDTO> houDecor;

    /**
     * 装修时间
     */
    private List<SysCodeMDTO> decorTimes;

    /**
     * 房屋设施
     */
    private List<SysCodeMDTO> houseFacilities;

    /**
     * 配套家电
     */
    private List<SysCodeMDTO> accessoryShop;

    /**
     * 租住要求
     */
    private List<SysCodeMDTO> rentalRequire;

    /**
     * 房主承担项目
     */
    private List<SysCodeMDTO> tenantCostItems;

    /**
     * 房源特色
     */
    private List<SysCodeMDTO> houseFeatures;

    /**
     * 房屋朝向
     */
    private List<SysCodeMDTO> houOrientation;
}
