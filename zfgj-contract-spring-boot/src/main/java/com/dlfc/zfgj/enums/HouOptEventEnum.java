
/**
 * 合同操作事件枚举
 * 
* @name: ConOptEventEnum.java 
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
package com.dlfc.zfgj.enums;

import org.apache.commons.lang.enums.ValuedEnum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 房源操作事件
 * 
 * @name: ConOptEventEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class HouOptEventEnum extends ValuedEnum {

	/**
	 * 创建
	 */
	private static final int CREATE = 1;
	/**
	 * 修改
	 */
	private static final int MODIFY = 2;
	/**
	 * 删除
	 */
	private static final int DEL = 3;
	/**
	 * 审核
	 */
	private static final int VERIFY = 4;
	/**
	 * 锁定
	 */
	private static final int LOCK = 5;
	/**
	 * 解锁
	 */
	private static final int UNLOCK = 6;
	/**
	 * 设置无效
	 */
	private static final int INVALID = 7;
	/**
	 * 再次审核
	 */
	private static final int VERIFY_AGAIN = 8;

	private HouOptEventEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 创建
	 */
	public static final HouOptEventEnum CREATE_ENUM = new HouOptEventEnum("创建", CREATE);
	/**
	 * 修改
	 */
	public static final HouOptEventEnum MODIFY_ENUM = new HouOptEventEnum("修改", MODIFY);
	/**
	 * 删除
	 */
	public static final HouOptEventEnum DEL_ENUM = new HouOptEventEnum("删除", DEL);
	/**
	 * 审核
	 */
	public static final HouOptEventEnum VERIFY_ENUM = new HouOptEventEnum("审核", VERIFY);
	/**
	 * 锁定
	 */
	public static final HouOptEventEnum LOCK_ENUM = new HouOptEventEnum("锁定", LOCK);
	/**
	 * 解锁
	 */
	public static final HouOptEventEnum UNLOCK_ENUM = new HouOptEventEnum("解锁", UNLOCK);
	/**
	 * 设置无效
	 */
	public static final HouOptEventEnum INVALID_ENUM = new HouOptEventEnum("设置无效", INVALID);
	/**
	 * 再次审核
	 */
	public static final HouOptEventEnum VERIFY_AGAIN_ENUM = new HouOptEventEnum("再次审核", VERIFY_AGAIN);
	
	public static HouOptEventEnum getEnum(int temp) {
		return (HouOptEventEnum) getEnum(HouOptEventEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(HouOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(HouOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(HouOptEventEnum.class);
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
