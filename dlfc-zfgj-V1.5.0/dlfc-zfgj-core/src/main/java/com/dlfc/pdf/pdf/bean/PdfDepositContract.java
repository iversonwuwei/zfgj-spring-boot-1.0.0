package com.dlfc.pdf.pdf.bean;

import java.math.BigDecimal;

import com.dlfc.pdf.pdf.AbstractDocumentVo;

/**
 * @name: pdfConContract
 * @description: pdf合同数据
 * 
 * @version 1.0
 * @author hanjiaqi
 *
 */
public class PdfDepositContract extends AbstractDocumentVo {

	/**
	 * 承租方信息
	 */
	private String lesseeName; 
	private Integer lesseeIdType;
	private String lesseeIdNo;
	/**
	 * 出租房信息
	 */
	private String lessorName; 
	private Integer lessorIdType;
	private String lessorIdNo;
	/**
	 * 经纪人信息
	 */
	private String manageName; 
	private String manageIdNo; 
	private Integer manageIdType;
	
	private String id;
	private String no; // 合同编号
	private BigDecimal depositAmt; // 履约保证金
	private BigDecimal deductAmt; // 出租房扣除金额
	private BigDecimal returnRentAmt; // 返还承租方金额
	
	private String ImagePath;

	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	public BigDecimal getDeductAmt() {
		return deductAmt;
	}

	public void setDeductAmt(BigDecimal deductAmt) {
		this.deductAmt = deductAmt;
	}

	public BigDecimal getReturnRentAmt() {
		return returnRentAmt;
	}

	public void setReturnRentAmt(BigDecimal returnRentAmt) {
		this.returnRentAmt = returnRentAmt;
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

	public String getLesseeName() {
		return lesseeName;
	}

	public void setLesseeName(String lesseeName) {
		this.lesseeName = lesseeName;
	}

	public Integer getLesseeIdType() {
		return lesseeIdType;
	}

	public void setLesseeIdType(Integer lesseeIdType) {
		this.lesseeIdType = lesseeIdType;
	}

	public String getLesseeIdNo() {
		return lesseeIdNo;
	}

	public void setLesseeIdNo(String lesseeIdNo) {
		this.lesseeIdNo = lesseeIdNo;
	}

	public String getLessorName() {
		return lessorName;
	}

	public void setLessorName(String lessorName) {
		this.lessorName = lessorName;
	}

	public Integer getLessorIdType() {
		return lessorIdType;
	}

	public void setLessorIdType(Integer lessorIdType) {
		this.lessorIdType = lessorIdType;
	}

	public String getLessorIdNo() {
		return lessorIdNo;
	}

	public void setLessorIdNo(String lessorIdNo) {
		this.lessorIdNo = lessorIdNo;
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

	public Integer getManageIdType() {
		return manageIdType;
	}

	public void setManageIdType(Integer manageIdType) {
		this.manageIdType = manageIdType;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}