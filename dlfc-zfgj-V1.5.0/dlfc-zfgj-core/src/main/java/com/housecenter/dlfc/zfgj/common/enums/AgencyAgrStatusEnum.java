
/**
 * 代理协议状态枚举
 * 
* @name: AgencyAgrStatusEnum.java 
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
 * 代理协议状态枚举
 * 
 * @name: AgencyAgrStatusEnum
 * @description: 
 * 
 * @version 1.0
 * @author Sun.Zhi
 *
 */
public class AgencyAgrStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;
	
	/**
	 * 否
	 */
	private static final int NORMAL = 0;

	/**
	 * 是
	 */
	private static final int YES = 1;
	
	private AgencyAgrStatusEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 否
	 */
	public static final AgencyAgrStatusEnum NO_ENUM = new AgencyAgrStatusEnum("否", NORMAL);
	/**
	 * 是
	 */
	public static final AgencyAgrStatusEnum YES_ENUM = new AgencyAgrStatusEnum("是", YES);

	public static AgencyAgrStatusEnum getEnum(int temp) {
		return (AgencyAgrStatusEnum) getEnum(AgencyAgrStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(AgencyAgrStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(AgencyAgrStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(AgencyAgrStatusEnum.class);
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
