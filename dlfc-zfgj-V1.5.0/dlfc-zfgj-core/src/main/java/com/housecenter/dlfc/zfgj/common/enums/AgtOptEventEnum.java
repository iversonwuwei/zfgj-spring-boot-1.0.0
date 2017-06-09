
/**
 * 经纪人操作事件类型枚举
 * 
* @name: AgtOptEventEnum.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年11月18日 
* @author: Dai.yuming 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月18日        Dai.yuming        1.0             <修改原因描述>
*/
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * 经纪人操作事件类型枚举
 * 
 * @name: AgtOptEventEnum
 * @description:
 * 
 * @version 1.0
 * @author Dai.yuming
 *
 */
public class AgtOptEventEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2693169515474991790L;

	/**
	 * 新建
	 */
	private static final int NEW = 1;
	/**
	 * 修改
	 */
	private static final int EDIT = 2;

	/**
	 * 离职
	 */
	private static final int QUIT = 3;

	/**
	 * 删除
	 */
	private static final int DELETE = 0;

	private AgtOptEventEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 新建
	 */
	public static final AgtOptEventEnum NEW_ENUM = new AgtOptEventEnum("NEW", NEW);

	/**
	 * 修改
	 */
	public static final AgtOptEventEnum EDIT_ENUM = new AgtOptEventEnum("EDIT", EDIT);

	/**
	 * 离职
	 */
	public static final AgtOptEventEnum QUIT_ENUM = new AgtOptEventEnum("QUIT", QUIT);

	/**
	 * 删除
	 */
	public static final AgtOptEventEnum DELETE_ENUM = new AgtOptEventEnum("DELETE", DELETE);

	public static AgtOptEventEnum getEnum(int temp) {
		return (AgtOptEventEnum) getEnum(AgtOptEventEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(AgtOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(AgtOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(AgtOptEventEnum.class);
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
