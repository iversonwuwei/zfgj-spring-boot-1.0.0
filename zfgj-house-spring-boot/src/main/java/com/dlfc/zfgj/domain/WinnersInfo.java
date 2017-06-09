
/**
* @name: WinnersInfo.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年10月27日 
* @author: HAN.JIAQI 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年10月27日       HAN.JIAQI        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.domain;

/**
 * @name: WinnersInfo
 * @description: 中奖信息
 * 
 * @version 1.0
 * @author HAN.JIAQI
 *
 */
public class WinnersInfo {
	
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 地址
	 */
	private String adr;
	/**
	 * 支付宝账号
	 */
	private String payNo;
	/**
	 * 奖品ID
	 */
	private String wid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
}
