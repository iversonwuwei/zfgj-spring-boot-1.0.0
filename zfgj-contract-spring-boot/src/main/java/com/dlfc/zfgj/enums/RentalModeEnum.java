
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

import org.apache.commons.lang.enums.ValuedEnum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 出租方式
 * 
 * @name: RentalModeEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class RentalModeEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 整租
	 */
	private static final int WHOLE_RENT = 1;
	/**
	 * 部分
	 */
	private static final int PART_RENT = 2;
	
	private RentalModeEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 整租
	 */
	public static final RentalModeEnum WHOLE_RENT_ENUM = new RentalModeEnum("整租",WHOLE_RENT);
	/**
	 * 部分
	 */
	public static final RentalModeEnum PART_RENT_ENUM = new RentalModeEnum("合租",PART_RENT);

	public static RentalModeEnum getEnum(int temp) {
		return (RentalModeEnum) getEnum(RentalModeEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(RentalModeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(RentalModeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(RentalModeEnum.class);
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
