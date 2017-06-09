/**
 * @name: PropertyIdTypeEnum.java 
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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * @name: PropertyIdTypeEnum
 * @description: 产权证件类型enum
 * 
 * @version 1.0
 * @author fancy
 *
 */
public class PropertyIdTypeEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1204488915913438534L;

	protected PropertyIdTypeEnum(String name, int value) {
		super(name, value);
	}

	// 产权
	private static final int PROPERTY = 1;
	// 使用权
	private static final int USUS = 2;
	// 不动产登记
	private static final int RORE = 3;
	// 其他
	private static final int OTHER = 4;

	public static final PropertyIdTypeEnum PROPERTY_ENUM = new PropertyIdTypeEnum("产权", PROPERTY);

	public static final PropertyIdTypeEnum USUS_ENUM = new PropertyIdTypeEnum("使用权", USUS);
	
	public static final PropertyIdTypeEnum RORE_ENUM = new PropertyIdTypeEnum("不动产登记", RORE);

	public static final PropertyIdTypeEnum OTHER_ENUM = new PropertyIdTypeEnum("其他", OTHER);

	public static PropertyIdTypeEnum getEnum(int temp) {
		return (PropertyIdTypeEnum) getEnum(PropertyIdTypeEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(PropertyIdTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(PropertyIdTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(PropertyIdTypeEnum.class);
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
