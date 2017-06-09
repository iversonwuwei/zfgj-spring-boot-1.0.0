
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
 * @name: FundsOrder
 * @description: 资金系统支付账单报文
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class FundsOrder {

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
	 * 付款人姓名
	 */
	private String paymentName;
	/**
	 * 付款人电话
	 */
	private String paymentTel;
	/**
	 * 付款人证件号码
	 */
	private String paymentIdno;
	/**
	 * 出入标识
	 */
	private String ioFlag;
	/**
	 * 商户号
	 */
	private String merchantId;
	/**
	 * 支付类型
	 */
	private String payType;
	/**
	 * 支付渠道
	 */
	private String paymentChannels;
	/**
	 * 消费项
	 */
	private List<FundsOrderItem> items;
	
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
	public String getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(String recordNum) {
		this.recordNum = recordNum;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getPaymentTel() {
		return paymentTel;
	}
	public void setPaymentTel(String paymentTel) {
		this.paymentTel = paymentTel;
	}
	public String getPaymentIdno() {
		return paymentIdno;
	}
	public void setPaymentIdno(String paymentIdno) {
		this.paymentIdno = paymentIdno;
	}
	public String getIoFlag() {
		return ioFlag;
	}
	public void setIoFlag(String ioFlag) {
		this.ioFlag = ioFlag;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPaymentChannels() {
		return paymentChannels;
	}
	public void setPaymentChannels(String paymentChannels) {
		this.paymentChannels = paymentChannels;
	}
	public List<FundsOrderItem> getItems() {
		return items;
	}
	public void setItems(List<FundsOrderItem> items) {
		this.items = items;
	}
	
}
