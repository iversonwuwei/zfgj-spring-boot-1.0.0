
/**
 * 押金操作事件枚举
 * 
* @name: DepositOptEventEnum.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年10月15日 
* @author: liuyundong 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年9月25日       liuyundong        1.0             <修改原因描述>
*/
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * 押金操作事件
 * 
 * @name: DepositOptEventEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class DepositOptEventEnum extends ValuedEnum {

	private static final long serialVersionUID = -7631808944973691036L;
	/**
	 * 创建
	 */
	private static final int CREATE = 1;
	/**
	 * 支付
	 */
	private static final int PAY = 2;
	/**
	 * 返还
	 */
	private static final int RETURN = 3;
	/**
	 * 完成
	 */
	private static final int FINISH = 4;
	/**
	 * 修改
	 */
	private static final int MODIFY = 5;
	
	private DepositOptEventEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 创建
	 */
	public static final DepositOptEventEnum CREATE_ENUM = new DepositOptEventEnum("创建", CREATE);
	/**
	 * 支付
	 */
	public static final DepositOptEventEnum PAY_ENUM = new DepositOptEventEnum("支付", PAY);
	/**
	 * 返还
	 */
	public static final DepositOptEventEnum RETURN_ENUM = new DepositOptEventEnum("返还", RETURN);
	/**
	 * 完成
	 */
	public static final DepositOptEventEnum FINISH_ENUM = new DepositOptEventEnum("完成", FINISH);
	/**
	 * 修改
	 */
	public static final DepositOptEventEnum MODIFY_ENUM = new DepositOptEventEnum("修改", MODIFY);
	
	public static DepositOptEventEnum getEnum(int temp) {
		return (DepositOptEventEnum) getEnum(DepositOptEventEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(DepositOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(DepositOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(DepositOptEventEnum.class);
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
