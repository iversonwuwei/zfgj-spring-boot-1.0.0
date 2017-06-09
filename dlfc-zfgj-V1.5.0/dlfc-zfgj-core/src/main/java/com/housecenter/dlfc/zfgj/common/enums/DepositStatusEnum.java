
/**
 * 用户身份枚举
 * 
* @name: UserIdentityEnum.java 
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
 * 押金状态
 * 
 * @name: DepositStatusEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class DepositStatusEnum extends ValuedEnum {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 待支付
	 */
	private static final int WAIT_PAY = 1;
	/**
	 * 已支付
	 */
	private static final int PAID = 2;
	/**
	 * 待返还
	 */
	private static final int WAIT_RETURN = 3;
	/**
	 * 已返还
	 */
	private static final int RETURNED = 4;
	/**
	 * 返还失败
	 */
	private static final int RETURN_FAILURE = 5;
	/**
	 * 冻结
	 */
	private static final int FREEZE = 6;
	
	private DepositStatusEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 待支付
	 */
	public static final DepositStatusEnum WAIT_PAY_ENUM = new DepositStatusEnum("待支付", WAIT_PAY);
	/**
	 * 已支付
	 */
	public static final DepositStatusEnum PAID_ENUM = new DepositStatusEnum("已支付", PAID);
	/**
	 * 待返还
	 */
	public static final DepositStatusEnum WAIT_RETURN_ENUM = new DepositStatusEnum("待返还", WAIT_RETURN);
	/**
	 * 已返还
	 */
	public static final DepositStatusEnum RETURNED_ENUM = new DepositStatusEnum("已返还", RETURNED);
	/**
	 * 返还失败
	 */
	public static final DepositStatusEnum RETURN_FAILURE_ENUM = new DepositStatusEnum("返还失败", RETURN_FAILURE);
	/**
	 * 冻结
	 */
	public static final DepositStatusEnum FREEZE_ENUM = new DepositStatusEnum("冻结", FREEZE);
	
	public static DepositStatusEnum getEnum(int temp) {
		return (DepositStatusEnum) getEnum(DepositStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(DepositStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(DepositStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(DepositStatusEnum.class);
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
