
/**
* @name: HouseSourceTypeEnum.java 
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
package com.dlfc.zfgj.enums;

import org.apache.commons.lang.enums.ValuedEnum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @name: HouseSourceTypeEnum
 * @description: 房屋来源enum
 * 
 * @version 1.0
 * @author fancy
 *
 */
public class HouseSourceTypeEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5106816813691857450L;
	//个人
	private static final int PERSON = 1;
	//经纪人
	private static final int AGENT = 2;
	
	private HouseSourceTypeEnum(String name, int value) {
		super(name, value);
	}

	public static final HouseSourceTypeEnum PERSON_ENUM = new HouseSourceTypeEnum("个人", PERSON);
	
	public static final HouseSourceTypeEnum AGENT_ENUM = new HouseSourceTypeEnum("经纪人", AGENT);

	public static HouseSourceTypeEnum getEnum(int temp) {
		return (HouseSourceTypeEnum) getEnum(HouseSourceTypeEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(HouseSourceTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(HouseSourceTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(HouseSourceTypeEnum.class);
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
