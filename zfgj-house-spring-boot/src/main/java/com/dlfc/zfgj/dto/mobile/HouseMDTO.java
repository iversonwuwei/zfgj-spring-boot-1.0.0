package com.dlfc.zfgj.dto.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by wangna on 2017/6/1.
 */
@Getter
@Setter
@Component
public class HouseMDTO {
    /**
     * 出租信息Id
     */
    private String houseId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 发布时间
     */
    private String releaseTime;

    /**
     * 出租信息编号
     */
    private String lno;
    /**
     * 出租信息标题
     */
    private String htitle;
    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 审核拒绝原因
     */
    private String rejectReason;

    /**
     * 出租面积
     */
    private String acreage;
    /**
     * 出租信息发布状态
     */
    private int releaseStatus;
    /**
     *  厅/室
     */
    private String apartmentLayout;

    /**
     * 能否刷新
     */
    private boolean refreshable;

    /**
     * 房源图片
     */
    private String url;
}
