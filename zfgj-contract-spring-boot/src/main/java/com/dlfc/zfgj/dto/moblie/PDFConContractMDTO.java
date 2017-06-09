package com.dlfc.zfgj.dto.moblie;

import com.dlfc.zfgj.common.pdf.pdf.AbstractDocumentVo;
import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.HouCoOwner;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by K on 2017/3/13.
 */

@Getter
@Setter
@Component
public class PDFConContractMDTO extends AbstractDocumentVo {
    private String id;
    private String no;    // 合同编号

    private String lesseeName; // 承租方姓名
    private String lesseeIdType; // 承租方有效身份证件
    private String lesseeIdNo; // 承租方证件号码
    private String lesseeAddress; // 承租方送达地址
    private String lesseeMobile; // 承租方电话
    private String lesseeEmail; // 承租方邮箱
    private String lesseeBakName; // 承租方备用联系人姓名
    private String lesseeBakMobile; // 承租方备用联系人电话

    private String lessorName; // 出租方姓名
    private String lessorIdType; // 出租方有效身份证件
    private String lessorIdNo; // 出租方证件号码
    private String lessorAddress; // 出租方送达地址
    private String lessorMobile; // 出租方电话
    private String lessorEmail; // 出租方邮箱
    private String lessorBakName; // 出租方备用联系人姓名
    private String lessorBakMobile; // 出租方备用联系人电话

    private String companyName; // 公司名称
    private String companyAddress; // 公司送达地址
    private String companyTel; // 公司电话

    private String manageName; // 经纪人姓名
    private String manageIdNo; // 经纪人证件号
    private String manageMobile; // 经纪人个人电话号

    /***租赁标的***/
    private String houseAddr; // 出租房屋地址
    private String houseType; // 结构
    private String properType; // 权属 = 产权类型
    private String housePropertyIdNo; // 房屋证号
    private BigDecimal houseBuildingArea; // 建筑面积
    private BigDecimal leaseArea; // 实际使用面积
    private int lesseeCount; // 承租使用人数目
    private String leasePurpose; // 租赁用途

    /***房屋所有权人***/
    private List<HouCoOwner> houCoOwnerList;

    /***实际承租使用人信息***/
    private List<ConHouseUser> conHouseUserList;

    /***租赁期限***/
    private Integer leaseTermYear;// 租期-年
    private Integer leaseTermMonth;// 租期-月
    private String year;// 起始-年 == 合同生效-年 == 交付押金-年
    private String month;// 起始-月 == 合同生效-月  == 交付押金-月
    private String day;// 起始-日 == 合同生效-日 == 交付押金-日
    private String endYear;// 终止-年
    private String endMonth;// 终止-月
    private String endDay;// 终止-日

    /***租金及费用***/
    private Integer monthlyRent; // 租金
    private String monthlyRentCN;
    private List<SettlementCycleMDTO> settlementCycleList; // 支付周期明细

    private String settlementCycle;// 支付周期
    private String ownerBear; // 房主承担
    private String ownerBearOther; // 房主承担其它
    private String lesseeBear; // 房客承担

    /***押金规定***/
    private BigDecimal depositAmt; // 押金
    private String depositAmtCN;

    /***居间服务费***/
    private BigDecimal agencyFee; // 服务费（中介费）
    private String agencyFeeCN;

    /***甲乙丙三方确认的附加条款***/
    private String lessorIds; // 甲方提供证件原件
    private String lessorIdsOther; // 甲方提供其他证件
    private String lesseeIds; // 乙方提供证件原件
    private String lesseeIdsOther; // 乙方提供其他证件

    private String imagePath;

    private String imageUrl;

    /***甲乙丙三方其它约定***/
    private String additionalTerms; // 其它约定

    @Override
    public String findPrimaryKey() {
        return this.id;
    }

    public int getLesseeCount() {
        if(conHouseUserList != null && conHouseUserList.size() > 0){
            lesseeCount = conHouseUserList.size();
        }else{
            lesseeCount = 0;
        }
        return lesseeCount;
    }
}
