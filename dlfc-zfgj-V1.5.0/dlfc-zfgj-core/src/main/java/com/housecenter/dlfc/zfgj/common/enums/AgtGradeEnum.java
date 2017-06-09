/**
 * @name: AgtGradeEnum.java 
 *
 * @Copyright: (c) 2017 DLFC. All rights reserved. 
 *
 * @description: 
 *
 * @version: 1.0
 * @date : 2017年1月6日 
 * @author: WangLituo 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2017年1月6日       WangLituo        1.0             <修改原因描述>
 */
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * @name: AgtGradeEnum
 * @description: 
 * 
 * @version 1.0
 * @author WangLituo
 *
 */
public class AgtGradeEnum extends ValuedEnum {

	/** 序列化id */
	private static final long serialVersionUID = -1337336084726398270L;

	/** 经纪人三星 */
	private static final int AGT_THREE_STARS = 6;
	/** 经纪人四星 */
	private static final int AGT_FOUR_STARS = 8;
	/** 经纪人四星  */
	private static final int AGT_FIVE_STARS = 10;

	private AgtGradeEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 经纪人三星
	 */
	public static final AgtGradeEnum AGT_THREE_STARS_ENUM = new AgtGradeEnum("NEW", AGT_THREE_STARS);

	/**
	 * 经纪人四星
	 */
	public static final AgtGradeEnum AGT_FOUR_STARS_ENUM = new AgtGradeEnum("EDIT", AGT_FOUR_STARS);

	/**
	 * 经纪人四星
	 */
	public static final AgtGradeEnum AGT_FIVE_STARS_ENUM = new AgtGradeEnum("QUIT", AGT_FIVE_STARS);

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
