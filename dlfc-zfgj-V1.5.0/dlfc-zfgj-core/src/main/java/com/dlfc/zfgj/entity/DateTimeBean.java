
/**
* @name: DateTimeBean.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年5月14日 
* @author: Qiu.Fei 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年5月14日       Qiu.Fei        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.entity;

/**
 * @name: DateTimeBean
 * @description: 
 * 
 * @version 1.0
 * @author Qiu.Fei
 *
 */
public class DateTimeBean {

	//支付时间年
	private String afterPayDateYYYY;
	//支付时间月
	private String afterPayDateMM;
	//支付时间日
	private String afterPayDateDD;
	//支付起始有效日期年
	private String fromDateYYYY;
	//支付起始有效日期月
	private String fromDateMM;
	//支付起始有效日期日
	private String fromDateDD;
	//支付结束有效日期年
	private String toDateYYYY;
	//支付结束有效日期月
	private String toDateMM;
	//支付结束有效日期日
	private String toDateDD;
	//支付金额
	private Double money;
	public String getAfterPayDateYYYY() {
		return afterPayDateYYYY;
	}
	public void setAfterPayDateYYYY(String afterPayDateYYYY) {
		this.afterPayDateYYYY = afterPayDateYYYY;
	}
	public String getAfterPayDateMM() {
		return afterPayDateMM;
	}
	public void setAfterPayDateMM(String afterPayDateMM) {
		this.afterPayDateMM = afterPayDateMM;
	}
	public String getAfterPayDateDD() {
		return afterPayDateDD;
	}
	public void setAfterPayDateDD(String afterPayDateDD) {
		this.afterPayDateDD = afterPayDateDD;
	}
	public String getFromDateYYYY() {
		return fromDateYYYY;
	}
	public void setFromDateYYYY(String fromDateYYYY) {
		this.fromDateYYYY = fromDateYYYY;
	}
	public String getFromDateMM() {
		return fromDateMM;
	}
	public void setFromDateMM(String fromDateMM) {
		this.fromDateMM = fromDateMM;
	}
	public String getFromDateDD() {
		return fromDateDD;
	}
	public void setFromDateDD(String fromDateDD) {
		this.fromDateDD = fromDateDD;
	}
	public String getToDateYYYY() {
		return toDateYYYY;
	}
	public void setToDateYYYY(String toDateYYYY) {
		this.toDateYYYY = toDateYYYY;
	}
	public String getToDateMM() {
		return toDateMM;
	}
	public void setToDateMM(String toDateMM) {
		this.toDateMM = toDateMM;
	}
	public String getToDateDD() {
		return toDateDD;
	}
	public void setToDateDD(String toDateDD) {
		this.toDateDD = toDateDD;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
}
