
/**
* @name: Order.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年3月4日 
* @author: liuyundong 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年3月4日       liuyundong        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.domain;

import java.util.List;

/**
 * @name: Order
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class Order {

	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 用户ID
	 */
	private String userID;
	/**
	 * 记录数
	 */
	private String recordNum;
	/**
	 * 产品名称
	 */
	private String prdName;
	/**
	 * 业务来源
	 */
	private String logicFrom;
	/**
	 * 业务去向
	 */
	private String logicTo;
	/**
	 * 付款人姓名
	 */
	private String paymentName;
	/**
	 * 付款人证件号码
	 */
	private String paymentIdno;
	/**
	 * 付款人电话号码
	 */
	private String paymentTel;
	/**
	 * 总支付金额
	 */
	private String totalAmount;
	/**
	 * 出入标识
	 */
	private String ioFlag;
	/**
	 * 关联ID
	 */
	private String lid;
	/**
	 * 系统编号
	 */
	private String sysNo;
	/**
	 * 消费项
	 */
	private List<OrderItem> items;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getLogicFrom() {
		return logicFrom;
	}
	public void setLogicFrom(String logicFrom) {
		this.logicFrom = logicFrom;
	}
	public String getLogicTo() {
		return logicTo;
	}
	public void setLogicTo(String logicTo) {
		this.logicTo = logicTo;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getIoFlag() {
		return ioFlag;
	}
	public void setIoFlag(String ioFlag) {
		this.ioFlag = ioFlag;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public String getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(String recordNum) {
		this.recordNum = recordNum;
	}
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getSysNo() {
		return sysNo;
	}
	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}
	public String getPaymentIdno() {
		return paymentIdno;
	}
	public void setPaymentIdno(String paymentIdno) {
		this.paymentIdno = paymentIdno;
	}
	public String getPaymentTel() {
		return paymentTel;
	}
	public void setPaymentTel(String paymentTel) {
		this.paymentTel = paymentTel;
	}
}
