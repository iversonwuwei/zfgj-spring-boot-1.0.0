
/**
 * 是与否的枚举
 * 
* @name: YesNoEnum.java 
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
 * 是与否的枚举
 * 
 * @name: YesNoEnum
 * @description: 
 * 
 * @version 1.0
 * @author Sun.Zhi
 *
 */
public class CertStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;
	
	/**
	 * 未认证
	 */
	private static final int NO = 1;

	/**
	 * 已认证
	 */
	private static final int YES = 2;
	
	private CertStatusEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 未认证
	 */
	public static final CertStatusEnum NO_ENUM = new CertStatusEnum("未认证", NO);
	/**
	 * 已认证
	 */
	public static final CertStatusEnum YES_ENUM = new CertStatusEnum("已认证", YES);

	public static CertStatusEnum getEnum(int temp) {
		return (CertStatusEnum) getEnum(CertStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(CertStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(CertStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(CertStatusEnum.class);
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
