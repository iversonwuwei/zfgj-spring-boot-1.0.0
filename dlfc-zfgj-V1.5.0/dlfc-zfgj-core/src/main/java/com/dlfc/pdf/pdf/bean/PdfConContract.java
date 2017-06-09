package com.dlfc.pdf.pdf.bean;

import java.math.BigDecimal;
import java.util.List;

import com.dlfc.pdf.pdf.AbstractDocumentVo;
import com.dlfc.zfgj.entity.ConHouseUser;
import com.dlfc.zfgj.entity.DateTimeBean;
import com.dlfc.zfgj.entity.HouCoOwner;

/**
 * @name: pdfConContract
 * @description: pdf合同数据
 * 
 * @version 1.0
 * @author hanjiaqim
 *
 */
public class PdfConContract extends AbstractDocumentVo {

	private String id;
	private String no;	// 合同编号
	
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
	private List<DateTimeBean> settlementCycleList; // 支付周期明细
	
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
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getLesseeName() {
		return lesseeName;
	}

	public void setLesseeName(String lesseeName) {
		this.lesseeName = lesseeName;
	}

	public String getLesseeIdType() {
		return lesseeIdType;
	}

	public void setLesseeIdType(String lesseeIdType) {
		this.lesseeIdType = lesseeIdType;
	}

	public String getLesseeIdNo() {
		return lesseeIdNo;
	}

	public void setLesseeIdNo(String lesseeIdNo) {
		this.lesseeIdNo = lesseeIdNo;
	}

	public String getLesseeAddress() {
		return lesseeAddress;
	}

	public void setLesseeAddress(String lesseeAddress) {
		this.lesseeAddress = lesseeAddress;
	}

	public String getLesseeMobile() {
		return lesseeMobile;
	}

	public void setLesseeMobile(String lesseeMobile) {
		this.lesseeMobile = lesseeMobile;
	}

	public String getLesseeEmail() {
		return lesseeEmail;
	}

	public void setLesseeEmail(String lesseeEmail) {
		this.lesseeEmail = lesseeEmail;
	}

	public String getLesseeBakName() {
		return lesseeBakName;
	}

	public void setLesseeBakName(String lesseeBakName) {
		this.lesseeBakName = lesseeBakName;
	}

	public String getLesseeBakMobile() {
		return lesseeBakMobile;
	}

	public void setLesseeBakMobile(String lesseeBakMobile) {
		this.lesseeBakMobile = lesseeBakMobile;
	}

	public String getLessorName() {
		return lessorName;
	}

	public void setLessorName(String lessorName) {
		this.lessorName = lessorName;
	}

	public String getLessorIdType() {
		return lessorIdType;
	}

	public void setLessorIdType(String lessorIdType) {
		this.lessorIdType = lessorIdType;
	}

	public String getLessorIdNo() {
		return lessorIdNo;
	}

	public void setLessorIdNo(String lessorIdNo) {
		this.lessorIdNo = lessorIdNo;
	}

	public String getLessorAddress() {
		return lessorAddress;
	}

	public void setLessorAddress(String lessorAddress) {
		this.lessorAddress = lessorAddress;
	}

	public String getLessorMobile() {
		return lessorMobile;
	}

	public void setLessorMobile(String lessorMobile) {
		this.lessorMobile = lessorMobile;
	}

	public String getLessorEmail() {
		return lessorEmail;
	}

	public void setLessorEmail(String lessorEmail) {
		this.lessorEmail = lessorEmail;
	}

	public String getLessorBakName() {
		return lessorBakName;
	}

	public void setLessorBakName(String lessorBakName) {
		this.lessorBakName = lessorBakName;
	}

	public String getLessorBakMobile() {
		return lessorBakMobile;
	}

	public void setLessorBakMobile(String lessorBakMobile) {
		this.lessorBakMobile = lessorBakMobile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getManageName() {
		return manageName;
	}

	public void setManageName(String manageName) {
		this.manageName = manageName;
	}

	public String getManageIdNo() {
		return manageIdNo;
	}

	public void setManageIdNo(String manageIdNo) {
		this.manageIdNo = manageIdNo;
	}

	public String getManageMobile() {
		return manageMobile;
	}

	public void setManageMobile(String manageMobile) {
		this.manageMobile = manageMobile;
	}

	public String getHouseAddr() {
		return houseAddr;
	}

	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getProperType() {
		return properType;
	}

	public void setProperType(String properType) {
		this.properType = properType;
	}

	public String getHousePropertyIdNo() {
		return housePropertyIdNo;
	}

	public void setHousePropertyIdNo(String housePropertyIdNo) {
		this.housePropertyIdNo = housePropertyIdNo;
	}

	public BigDecimal getHouseBuildingArea() {
		return houseBuildingArea;
	}

	public void setHouseBuildingArea(BigDecimal houseBuildingArea) {
		this.houseBuildingArea = houseBuildingArea;
	}

	public BigDecimal getLeaseArea() {
		return leaseArea;
	}

	public void setLeaseArea(BigDecimal leaseArea) {
		this.leaseArea = leaseArea;
	}

	public int getLesseeCount() {
		if(conHouseUserList != null && conHouseUserList.size() > 0){
			lesseeCount = conHouseUserList.size();
		}else{
			lesseeCount = 0;
		}
		return lesseeCount;
	}

	public String getLeasePurpose() {
		return leasePurpose;
	}

	public void setLeasePurpose(String leasePurpose) {
		this.leasePurpose = leasePurpose;
	}

	public List<HouCoOwner> getHouCoOwnerList() {
		return houCoOwnerList;
	}

	public void setHouCoOwnerList(List<HouCoOwner> houCoOwnerList) {
		this.houCoOwnerList = houCoOwnerList;
	}

	public List<ConHouseUser> getConHouseUserList() {
		return conHouseUserList;
	}

	public void setConHouseUserList(List<ConHouseUser> conHouseUserList) {
		this.conHouseUserList = conHouseUserList;
	}

	public Integer getLeaseTermYear() {
		return leaseTermYear;
	}

	public void setLeaseTermYear(Integer leaseTermYear) {
		this.leaseTermYear = leaseTermYear;
	}

	public Integer getLeaseTermMonth() {
		return leaseTermMonth;
	}

	public void setLeaseTermMonth(Integer leaseTermMonth) {
		this.leaseTermMonth = leaseTermMonth;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public Integer getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(Integer monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	public String getSettlementCycle() {
		return settlementCycle;
	}

	public void setSettlementCycle(String settlementCycle) {
		this.settlementCycle = settlementCycle;
	}

	public String getOwnerBear() {
		return ownerBear;
	}

	public void setOwnerBear(String ownerBear) {
		this.ownerBear = ownerBear;
	}

	public String getOwnerBearOther() {
		return ownerBearOther;
	}

	public void setOwnerBearOther(String ownerBearOther) {
		this.ownerBearOther = ownerBearOther;
	}

	public String getLesseeBear() {
		return lesseeBear;
	}

	public void setLesseeBear(String lesseeBear) {
		this.lesseeBear = lesseeBear;
	}

	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	public BigDecimal getAgencyFee() {
		return agencyFee;
	}

	public void setAgencyFee(BigDecimal agencyFee) {
		this.agencyFee = agencyFee;
	}

	public String getLessorIds() {
		return lessorIds;
	}

	public void setLessorIds(String lessorIds) {
		this.lessorIds = lessorIds;
	}

	public String getLessorIdsOther() {
		return lessorIdsOther;
	}

	public void setLessorIdsOther(String lessorIdsOther) {
		this.lessorIdsOther = lessorIdsOther;
	}

	public String getLesseeIds() {
		return lesseeIds;
	}

	public void setLesseeIds(String lesseeIds) {
		this.lesseeIds = lesseeIds;
	}

	public String getLesseeIdsOther() {
		return lesseeIdsOther;
	}

	public void setLesseeIdsOther(String lesseeIdsOther) {
		this.lesseeIdsOther = lesseeIdsOther;
	}

	public String getAdditionalTerms() {
		return additionalTerms;
	}

	public void setAdditionalTerms(String additionalTerms) {
		this.additionalTerms = additionalTerms;
	}

	/***甲乙丙三方其它约定***/
	private String additionalTerms; // 其它约定

	@Override
	public String findPrimaryKey() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getMonthlyRentCN() {
		return monthlyRentCN;
	}

	public void setMonthlyRentCN(String monthlyRentCN) {
		this.monthlyRentCN = monthlyRentCN;
	}

	public String getDepositAmtCN() {
		return depositAmtCN;
	}

	public void setDepositAmtCN(String depositAmtCN) {
		this.depositAmtCN = depositAmtCN;
	}

	public String getAgencyFeeCN() {
		return agencyFeeCN;
	}

	public void setAgencyFeeCN(String agencyFeeCN) {
		this.agencyFeeCN = agencyFeeCN;
	}

	public List<DateTimeBean> getSettlementCycleList() {
		return settlementCycleList;
	}

	public void setSettlementCycleList(List<DateTimeBean> settlementCycleList) {
		this.settlementCycleList = settlementCycleList;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}