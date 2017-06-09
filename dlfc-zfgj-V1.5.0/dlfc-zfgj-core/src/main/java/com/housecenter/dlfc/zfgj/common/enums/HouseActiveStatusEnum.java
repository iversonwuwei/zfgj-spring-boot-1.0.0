
/**
* @name: HouseCertStatusEnum.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年1月15日 
* @author: fancy 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年1月15日       fancy        1.0             <修改原因描述>
*/
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * @name: HouseCertStatusEnum
 * @description: 房源有效状态enum
 * 
 * @version 1.0
 * @author fancy
 *
 */
public class HouseActiveStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6059669416536795954L;
	//有效
	private static final int YES = 1;
	//无效
	private static final int NO = 2;
	
	private HouseActiveStatusEnum(String name, int value) {
		super(name, value);
	}

	public static final HouseActiveStatusEnum YES_ENUM = new HouseActiveStatusEnum("有效", YES);
	
	public static final HouseActiveStatusEnum NO_ENUM = new HouseActiveStatusEnum("无效", NO);

	public static HouseActiveStatusEnum getEnum(int temp) {
		return (HouseActiveStatusEnum) getEnum(HouseActiveStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(HouseActiveStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(HouseActiveStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(HouseActiveStatusEnum.class);
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
