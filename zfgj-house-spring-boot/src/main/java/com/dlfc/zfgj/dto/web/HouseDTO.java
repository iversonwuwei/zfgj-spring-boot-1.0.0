package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by walden on 2017/2/23.
 */
@Getter
@Setter
public class HouseDTO {

    /**
     * 出租信息Id
     */
    private String houseId;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 出租信息编号
     */

    private String lNo;
    /**
     * 出租信息标题
     */
    private String HTitle;
    /**
     * 审核状态
     */
    private Integer auditStatus;
    /**
     * 出租面积
     */
    private String acreage;
    /**
     * 出租信息发布状态
     */
    private int releaseStatus;
    /**
     * 收藏出租信息id
     */
    private String colHid;
    /**
     *  厅/室
     */
    private String apartmentLayout;
    /**
     * 经纪人姓名
     */
    private String empName;
    /**
     * 能否刷新
     */
    private boolean refreshable;
}
