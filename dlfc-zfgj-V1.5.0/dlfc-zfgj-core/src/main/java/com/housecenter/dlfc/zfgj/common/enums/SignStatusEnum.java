
/**
 * 签约状态枚举
 * 
* @name: HouseAduitStatusEnum.java 
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
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * 签约状态
 * 
 * @name: AduitStatusCommonEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class SignStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 满签
	 */
	private static final int FULL_SIGN = 1;
	/**
	 * 可签
	 */
	private static final int MAY_SIGN = 2;
	/**
	 * 未签
	 */
	private static final int WAIT_SIGN = 3;	
	
	private SignStatusEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 满签
	 */
	public static final SignStatusEnum FULL_SIGN_ENUM = new SignStatusEnum("满签", FULL_SIGN);
	/**
	 * 可签
	 */
	public static final SignStatusEnum MAY_SIGN_ENUM = new SignStatusEnum("可签", MAY_SIGN);
	/**
	 * 未签
	 */
	public static final SignStatusEnum WAIT_SIGN_ENUM = new SignStatusEnum("未签", WAIT_SIGN);

	public static SignStatusEnum getEnum(int temp) {
		return (SignStatusEnum) getEnum(SignStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(SignStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(SignStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(SignStatusEnum.class);
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
