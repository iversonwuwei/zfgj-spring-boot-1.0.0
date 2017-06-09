
/**
* @name: FundsBatchOrder.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年4月16日 
* @author: liuyundong 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年4月16日       liuyundong        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.domain;

/**
 * @name: FundsBatchOrder
 * @description: 资金系统批量账单报文
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class FundsBatchOrder {

	/**
	 * 交易金额
	 */
	private String amount;
	/**
	 * 入账来源
	 */
	private String fundFrom;
	/**
	 * 入账去向
	 */
	private String fundTo;
	/**
	 * 出入标识
	 */
	private String ioFlag;
	/**
	 * 消费项编号
	 */
	private String itemNo;
	/**
	 * 业务来源
	 */
	private String logicFrom;
	/**
	 * 业务代码
	 */
	private String logicNo;
	/**
	 * 业务去向
	 */
	private String logicTo;
	/**
	 * 业务类型
	 */
	private String logicType;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 支付类型
	 */
	private String payType;
	/**
	 * 支付渠道
	 */
	private String paymentChannels;
	/**
	 * 支付人证件号
	 */
	private String paymentIdno;
	/**
	 * 支付人
	 */
	private String paymentName;
	/**
	 * 支付人电话
	 */
	private String paymentTel;
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFundFrom() {
		return fundFrom;
	}
	public void setFundFrom(String fundFrom) {
		this.fundFrom = fundFrom;
	}
	public String getFundTo() {
		return fundTo;
	}
	public void setFundTo(String fundTo) {
		this.fundTo = fundTo;
	}
	public String getIoFlag() {
		return ioFlag;
	}
	public void setIoFlag(String ioFlag) {
		this.ioFlag = ioFlag;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getLogicFrom() {
		return logicFrom;
	}
	public void setLogicFrom(String logicFrom) {
		this.logicFrom = logicFrom;
	}
	public String getLogicNo() {
		return logicNo;
	}
	public void setLogicNo(String logicNo) {
		this.logicNo = logicNo;
	}
	public String getLogicTo() {
		return logicTo;
	}
	public void setLogicTo(String logicTo) {
		this.logicTo = logicTo;
	}
	public String getLogicType() {
		return logicType;
	}
	public void setLogicType(String logicType) {
		this.logicType = logicType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	public String getPaymentIdno() {
		return paymentIdno;
	}
	public void setPaymentIdno(String paymentIdno) {
		this.paymentIdno = paymentIdno;
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
	
	
}
