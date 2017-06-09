
/**
* @name: OrderItem.java 
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

/**
 * @name: OrderItem
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class OrderItem {

	/**
	 * 消费项编号
	 */
	private String itemNo;
	/**
	 * 消费名称
	 */
	private String itemName;
	/**
	 * 业务编号
	 */
	private String logicNo;
	/**
	 * 业务类型
	 */
	private String logicType;
	/**
	 * 支付金额
	 */
	private String amount;
	/**
	 * 数量
	 */
	private String count;
	
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getLogicNo() {
		return logicNo;
	}
	public void setLogicNo(String logicNo) {
		this.logicNo = logicNo;
	}
	public String getLogicType() {
		return logicType;
	}
	public void setLogicType(String logicType) {
		this.logicType = logicType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
}
