package com.dlfc.pdf.pdf.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.dlfc.pdf.pdf.AbstractDocumentVo;

/**
 * @name: pdfConContract
 * @description: pdf解约协议合同数据
 * 
 * @version 1.0
 * @author hanjiaqi
 *
 */
public class PdfCancelContract extends AbstractDocumentVo {

	private String id;
	private String leaseDomain; // 出租区域
	private String houseAddr; // 房屋地址
	private BigDecimal depositAmt; // 履约保证金
	private BigDecimal monthlyRent; // 租金
	private Date startTime;
	private Date endTime;

	private Date effectiveTime; // 解约合同生效时间
	private String description; // 解约原因
	private BigDecimal returnRentAmt; // 返还租金
	private BigDecimal paymentAmt; // 赔付金额
	private String paymentDepositAmt; // 赔付保证金
	private String content; // 补充说明
	
	private String ImagePath;

	public String getLeaseDomain() {
		return leaseDomain;
	}

	public void setLeaseDomain(String leaseDomain) {
		this.leaseDomain = leaseDomain;
	}

	public String getHouseAddr() {
		return houseAddr;
	}

	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}

	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	public BigDecimal getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(BigDecimal monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getReturnRentAmt() {
		return returnRentAmt;
	}

	public void setReturnRentAmt(BigDecimal returnRentAmt) {
		this.returnRentAmt = returnRentAmt;
	}

	public BigDecimal getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentAmt(BigDecimal paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public String getPaymentDepositAmt() {
		return paymentDepositAmt;
	}

	public void setPaymentDepositAmt(String paymentDepositAmt) {
		this.paymentDepositAmt = paymentDepositAmt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String findPrimaryKey() {
		return this.id;
	}

	public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
}