
/**
 * 押金分配协议状态枚举
 * 
* @name: UserIdentityEnum.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年11月20日 
* @author: hanjiaqi 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月20日       hanjiaqi        1.0             <修改原因描述>
*/
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * 押金分配协议状态
 * 
 * @name: DepositStatusEnum
 * @description: 
 * 
 * @version 1.0
 * @author hanjiaqi
 *
 */
public class DepositDistributeStatusEnum extends ValuedEnum {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 已创建
	 */
	private static final int DEPOSIT_DISTRIBUTE_STATUS_CREATE = 0;
	/**
	 * 已提交
	 */
	private static final int DEPOSIT_DISTRIBUTE_STATUS_SUBMIT = 1;
	/**
	 * 已拒绝
	 */
	private static final int DEPOSIT_DISTRIBUTE_STATUS_REFUSE = 2;
	/**
	 * 已同意
	 */
	private static final int DEPOSIT_DISTRIBUTE_STATUS_AGREE = 3;
	/**
	 * 已完成
	 */
	private static final int DEPOSIT_DISTRIBUTE_STATUS_ALTER = 4;
	/**
	 * 等待审核
	 */
	private static final int DEPOSIT_DISTRIBUTE_STATUS_VERIFYING = 5;
	/**
	 * 审核拒绝
	 */
	private static final int DEPOSIT_DISTRIBUTE_STATUS_VERIFYREFUSE = 6;
	
	
	private DepositDistributeStatusEnum(String name, int value) {
		super(name, value);
	}
	/**
	 * 创建
	 */
	public static final DepositDistributeStatusEnum DEPOSIT_DISTRIBUTE_STATUS_CREATE_ENUM = new DepositDistributeStatusEnum("创建", DEPOSIT_DISTRIBUTE_STATUS_CREATE);
	/**
	 * 提交
	 */
	public static final DepositDistributeStatusEnum DEPOSIT_DISTRIBUTE_STATUS_SUBMIT_ENUM = new DepositDistributeStatusEnum("提交", DEPOSIT_DISTRIBUTE_STATUS_SUBMIT);
	/**
	 * 拒绝
	 */
	public static final DepositDistributeStatusEnum DEPOSIT_DISTRIBUTE_STATUS_REFUSE_ENUM = new DepositDistributeStatusEnum("拒绝", DEPOSIT_DISTRIBUTE_STATUS_REFUSE);
	/**
	 * 同意
	 */
	public static final DepositDistributeStatusEnum DEPOSIT_DISTRIBUTE_STATUS_AGREE_ENUM = new DepositDistributeStatusEnum("同意", DEPOSIT_DISTRIBUTE_STATUS_AGREE);
	/**
	 * 修改
	 */
	public static final DepositDistributeStatusEnum DEPOSIT_DISTRIBUTE_STATUS_ALTER_ENUM = new DepositDistributeStatusEnum("修改", DEPOSIT_DISTRIBUTE_STATUS_ALTER);
	/**
	 * 等待审核
	 */
	public static final DepositDistributeStatusEnum DEPOSIT_DISTRIBUTE_STATUS_VERIFYING_ENUM = new DepositDistributeStatusEnum("等待审核", DEPOSIT_DISTRIBUTE_STATUS_VERIFYING);
	/**
	 * 审核拒绝
	 */
	public static final DepositDistributeStatusEnum DEPOSIT_DISTRIBUTE_STATUS_VERIFYREFUSE_ENUM = new DepositDistributeStatusEnum("审核拒绝", DEPOSIT_DISTRIBUTE_STATUS_VERIFYREFUSE);
	
	public static DepositDistributeStatusEnum getEnum(int temp) {
		return (DepositDistributeStatusEnum) getEnum(DepositDistributeStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(DepositDistributeStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(DepositDistributeStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(DepositDistributeStatusEnum.class);
	}
	
	public static String getName(int value) {
		return getName(String.valueOf(value));
	}
	
	@SuppressWarnings("rawtypes")
	public static String getName(String value) {
		List list = getEnumList();
		for (int i = 0; i < list.size(); i++) {
			ValuedEnum type = (ValuedEnum) list.get(i);
			String val = String.valueOf(type.getValue());
			if (val.equals(value)) {
				return type.getName();
			}
		}
		
		return null;
	}
}
