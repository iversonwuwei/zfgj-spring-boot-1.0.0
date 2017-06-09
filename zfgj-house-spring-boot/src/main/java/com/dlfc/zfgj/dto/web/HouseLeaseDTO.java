package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by wsz on 2017/3/20.
 */
@Getter
@Setter
public class HouseLeaseDTO {
    /**
     * 主键id
     */
    private String id;
    /**
     * 出租方式
     */
    private String leaseMode;
    /**
     * 租金类型(押)
     */
    private String pledge;
    /**
     * 租金类型(付)
     */
    private String pay;

    /**
     * 租金
     */
    private Double rent;
    /**
     * 入住时间
     */
    private Date checkinTime;
    /**
     * 小区名称
     */
    private String villageName;
    /**
     * 商圈区域ID
     */
    private String areaId;
    /**
     * 所属商圈Id
     */
    private String district;
    /**
     * 地铁站ID
     */
    private String stationid;
    /**
     * 楼号
     */
    private String no;
    /**
     * 单元
     */
    private String unit;
    /**
     * 室
     */
    private String inRoom;
    /**
     * 出租面积
     */
    private String acreage;
    /**
     * 在第几层
     */
    private Integer floor;
    /**
     * 楼层
     */
    private String onFloor;
    /**
     * 建筑时间
     */
    private String buildTime;
    /**
     * 户型(室)
     */
    private String apartmentLayoutRoom;
    /**
     * 厅
     */
    private String apartmentLayoutHall;
    /**
     * 卫
     */
    private String apartmentLayoutToilet;
    /**
     * 房屋朝向
     */
    private String orientation;
    /**
     * 装修
     */
    private String decor;
    /**
     * 装修时间
     */
    private String decorTime;
    /**
     * 房屋设施
     */
    private String[] facilities;
    /**
     * 配套家电
     */
    private String[] electric;
    /**
     * 房间特色
     */
    private String[] feature;
    /**
     * 租住要求
     */
    private String[] requirement;
    /**
     * 租客承担的费用
     */
    private String[] ownerCostItems;
    /**
     * 租客承担费用其他
     */
    private String ownerCost;
    /**
     * 房源标题
     */
    private String title;
    /**
     * 房间描述
     */
    private String description;
    /**
     * 出租房间
     */
    private String room;

    /**
     * 附件表主键ID数组(图片)
     */
    private String[] sId;

    /**
     * 房屋图片地址
     */
    private List<SysAttDTO> sysAttDTOList;

    /**
     * 审核结果
     */
    private Boolean examineResult;

    /**
     * 审核拒绝原因
     */
    private String rejectReason;

    /**
     * 装修方式
     */
    private List<SysCodeDTO> houDecor;

    /**
     * 装修时间
     */
    private List<SysCodeDTO> decorTimes;

    /**
     * 房屋设施
     */
    private List<SysCodeDTO> houseFacilities;

    /**
     * 配套家电
     */
    private List<SysCodeDTO> accessoryShop;

    /**
     * 租住要求
     */
    private List<SysCodeDTO> rentalRequire;

    /**
     * 房主承担项目
     */
    private List<SysCodeDTO> tenantCostItems;

    /**
     * 房源特色
     */
    private List<SysCodeDTO> houseFeatures;
}
