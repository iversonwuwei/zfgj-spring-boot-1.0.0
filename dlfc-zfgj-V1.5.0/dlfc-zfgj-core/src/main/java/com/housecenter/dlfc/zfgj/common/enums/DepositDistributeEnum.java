
/**
 * 押金分配操作事件枚举
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
 * 押金分配状态
 * 
 * @name: DepositStatusEnum
 * @description: 
 * 
 * @version 1.0
 * @author hanjiaqi
 *
 */
public class DepositDistributeEnum extends ValuedEnum {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 创建
	 */
	private static final int DEPOSIT_DISTRIBUTE_CREATE = 0;
	/**
	 * 提交
	 */
	private static final int DEPOSIT_DISTRIBUTE_SUBMIT = 1;
	/**
	 * 拒绝
	 */
	private static final int DEPOSIT_DISTRIBUTE_REFUSE = 2;
	/**
	 * 同意
	 */
	private static final int DEPOSIT_DISTRIBUTE_AGREE = 3;
	/**
	 * 修改
	 */
	private static final int DEPOSIT_DISTRIBUTE_ALTER = 4;
	
	
	private DepositDistributeEnum(String name, int value) {
		super(name, value);
	}
	
	/**
	 * 创建
	 */
	public static final DepositDistributeEnum DEPOSIT_DISTRIBUTE_CREATE_ENUM = new DepositDistributeEnum("创建", DEPOSIT_DISTRIBUTE_CREATE);

	/**
	 * 提交
	 */
	public static final DepositDistributeEnum DEPOSIT_DISTRIBUTE_SUBMIT_ENUM = new DepositDistributeEnum("提交", DEPOSIT_DISTRIBUTE_SUBMIT);
	/**
	 * 拒绝
	 */
	public static final DepositDistributeEnum DEPOSIT_DISTRIBUTE_REFUSE_ENUM = new DepositDistributeEnum("拒绝", DEPOSIT_DISTRIBUTE_REFUSE);
	/**
	 * 同意
	 */
	public static final DepositDistributeEnum DEPOSIT_DISTRIBUTE_AGREE_ENUM = new DepositDistributeEnum("同意", DEPOSIT_DISTRIBUTE_AGREE);
	/**
	 * 修改
	 */
	public static final DepositDistributeEnum DEPOSIT_DISTRIBUTE_ALTER_ENUM = new DepositDistributeEnum("修改", DEPOSIT_DISTRIBUTE_ALTER);
	
	public static DepositDistributeEnum getEnum(int temp) {
		return (DepositDistributeEnum) getEnum(DepositDistributeEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(DepositDistributeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(DepositDistributeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(DepositDistributeEnum.class);
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
