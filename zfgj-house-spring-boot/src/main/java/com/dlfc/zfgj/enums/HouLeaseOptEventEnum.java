
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
package com.dlfc.zfgj.enums;

import org.apache.commons.lang.enums.ValuedEnum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 出租信息操作事件类型枚举
 * 
 * @name: AgtOptEventEnum
 * @description:
 * 
 * @version 1.0
 * @author Dai.yuming
 *
 */
public class HouLeaseOptEventEnum extends ValuedEnum {

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



	private HouLeaseOptEventEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 新建
	 */
	public static final HouLeaseOptEventEnum NEW_ENUM = new HouLeaseOptEventEnum("NEW", NEW);

	/**
	 * 修改
	 */
	public static final HouLeaseOptEventEnum EDIT_ENUM = new HouLeaseOptEventEnum("EDIT", EDIT);



	public static HouLeaseOptEventEnum getEnum(int temp) {
		return (HouLeaseOptEventEnum) getEnum(HouLeaseOptEventEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(HouLeaseOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(HouLeaseOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(HouLeaseOptEventEnum.class);
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
