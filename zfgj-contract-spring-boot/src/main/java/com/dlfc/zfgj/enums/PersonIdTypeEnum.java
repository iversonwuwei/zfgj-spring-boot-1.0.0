
/**
 * 人员证件类型枚举
 * 
* @name: PersonIdTypeEnum.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年9月25日 
* @author: Sun.Zhi 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年9月25日       Sun.Zhi        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * 人员证件类型枚举
 * 
 * @name: PersonIdTypeEnum
 * @description: 
 * 
 * @version 1.0
 * @author Sun.Zhi
 *
 */
public class PersonIdTypeEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 身份证
	 */
	private static final int ID_CARD = 1;
	/**
	 * 军官证
	 */
	private static final int MILITARY_ID = 2;
	/**
	 * 护照
	 */
	private static final int PASSPORT = 3;
	/**
	 * 港澳通行证
	 */
	private static final int HONG_KONG_MACAU_PASS = 4;
	/**
	 * 台湾通行证
	 */
	private static final int TAIWAN_PASS = 5;
	/**
	 * 其他
	 */
	private static final int OTHER = 6;
	
	
	private PersonIdTypeEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 身份证
	 */
	public static final PersonIdTypeEnum ID_CARD_ENUM = new PersonIdTypeEnum("身份证", ID_CARD);
	/**
	 * 军官证
	 */
	public static final PersonIdTypeEnum MILITARY_ID_ENUM = new PersonIdTypeEnum("军官证", MILITARY_ID);
	/**
	 * 护照
	 */
	public static final PersonIdTypeEnum PASSPORT_ENUM = new PersonIdTypeEnum("护照", PASSPORT);
	/**
	 * 港澳通行证
	 */
	public static final PersonIdTypeEnum HONG_KONG_MACAU_PASS_ENUM = new PersonIdTypeEnum("港澳通行证", HONG_KONG_MACAU_PASS);
	/**
	 * 台湾通行证
	 */
	public static final PersonIdTypeEnum TAIWAN_PASS_ENUM = new PersonIdTypeEnum("台湾通行证", TAIWAN_PASS);
	/**
	 * 其他
	 */
	public static final PersonIdTypeEnum OTHER_ENUM = new PersonIdTypeEnum("其他", OTHER);

	public static PersonIdTypeEnum getEnum(int temp) {
		return (PersonIdTypeEnum) getEnum(PersonIdTypeEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(PersonIdTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(PersonIdTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(PersonIdTypeEnum.class);
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
