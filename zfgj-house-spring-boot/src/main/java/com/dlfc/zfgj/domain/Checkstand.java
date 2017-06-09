
/**
* @name: Checkstand.java 
*
* @Copyright: (c) 2017 DLFC. All rights reserved. 
*
* @description: 收银台所需要的数据
*
* @version: 1.0
* @date : 2017年1月3日 
* @author: liuyundong 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2017年1月3日       liuyundong        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.domain;

import java.math.BigDecimal;

/**
 * @name: Checkstand
 * @description: 收银台所需要的数据
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class Checkstand {
	/**付款金额 */
	private BigDecimal payment;
	/**备注一 */
	private String remark1;
	/**备注二 */
	private String remark2;
	/**交易码 */
	private String txcode;
	/**客户信息 */
	private String regInfo;
	/**商品信息 */
	private String proInfo;
	/**客户端IP */
	private String clientip;
	/**商户柜台代码 */
	private String posid;
	/**业务定单号 */
	private String orderid;
	/**支付方式 */
	private String curcode;
	/**分期期数 */
	private String installnum;
	
	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getTxcode() {
		return txcode;
	}

	public void setTxcode(String txcode) {
		this.txcode = txcode;
	}

	public String getRegInfo() {
		return regInfo;
	}

	public void setRegInfo(String regInfo) {
		this.regInfo = regInfo;
	}

	public String getProInfo() {
		return proInfo;
	}

	public void setProInfo(String proInfo) {
		this.proInfo = proInfo;
	}

	public String getClientip() {
		return clientip;
	}

	public void setClientip(String clientip) {
		this.clientip = clientip;
	}

	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCurcode() {
		return curcode;
	}

	public void setCurcode(String curcode) {
		this.curcode = curcode;
	}

	public String getInstallnum() {
		return installnum;
	}

	public void setInstallnum(String installnum) {
		this.installnum = installnum;
	}
	
	
}
