/**
* @name: ConDepositListCondition.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年12月29日 
* @author: hanjiaqi 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年12月29日       hanjiaqi        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.domain;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.util.Map;

/**
 * @name: ConDepositListCondition
 * @description: 押金监管列表搜索条件
 * 
 * @version 1.0
 * @author hanjiaqi
 *
 */
public class ConDepositListCondition extends MyDataEntity<Map<String, Object>> {
	
	/**
	 * 人员Id
	 */
	private String pid;
	/**
	 * 排序标记
	 */
	private String flag;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
