
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
 * 租赁用途
 * 
 * @name: RentalPurposeEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class RentalPurposeEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 民用
	 */
	private static final int CIVIL_USE = 1;
	/**
	 * 商用
	 */
	private static final int BUSINESS_USE = 2;
	
	private RentalPurposeEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 民用
	 */
	public static final RentalPurposeEnum CIVIL_USE_ENUM = new RentalPurposeEnum("民用",CIVIL_USE);
	/**
	 * 商用
	 */
	public static final RentalPurposeEnum BUSINESS_USE_ENUM = new RentalPurposeEnum("商用",BUSINESS_USE);

	public static RentalPurposeEnum getEnum(int temp) {
		return (RentalPurposeEnum) getEnum(RentalPurposeEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(RentalPurposeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(RentalPurposeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(RentalPurposeEnum.class);
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
