package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wsz on 2017/3/6.
 */
@Getter
@Setter
public class AgtEmpDTO {
    /**
     * 经纪人姓名
     */
    private String agtName;
    /**
     *经纪人电话
     */
    private String phone;
    /**
     * 经纪人是否在职
     */
    private int agtOnJob;
    /**
     * 经纪人公司名
     */
    private String compName;
    /**
     * 经纪人公司分店名字
     */
    private String officeName;

    /**
     * 经纪人有效出租信息数量
     */
    private int actHouseCount;
    /**
     *经纪人签合同房源
     */
    private int conHouseCount;
    /**
     *经纪人等待审核出租信息
     */
    private int wHouseCount;
    /**
     *审核未通过出租信息
     */
    private int unAuHouseCount;
    /**
     * 经纪人id获取客源数量
     */
    private int cusCount;
    /**
     * 经纪人合同数量
     */
    private int contractCount;
    /**
     *经纪人即将到期合同数量
     */
    private int eContractCount;
    /**
     * 经纪人是否有资格证
     */
    private int agtJoin;
    /**
     * 经纪人审核状态
     */
    private int agtAuditStatus;
    /**
     * 经纪公司名字+名称简拼+名称全拼
     */
    private String agtCompSAName;
    /**
     * 拒绝原因
     */
    private String rejectReason;
    /**
     * 经纪人头像图片地址
     */
    private String agtPicture;
    /**
     * 经纪人二维码图片地址
     */
    private String agtBarcode;
    /**
     * 验证码
     */
    private String checkCode;

    /**
     * 加入公司的ID
     */
    private String compId;
    /**
     * 经纪公司图片地址
     */
    private String compPath;
}
