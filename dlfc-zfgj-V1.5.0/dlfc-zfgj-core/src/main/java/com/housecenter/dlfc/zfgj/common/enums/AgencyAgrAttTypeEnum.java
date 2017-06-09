
/**
 * 代理协议附件类型枚举
 * 
* @name: AgencyAgrAttTypeEnum.java 
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
 * 代理协议附件类型枚举
 * 
 * @name: AgencyAgrAttTypeEnum
 * @description: 
 * 
 * @version 1.0
 * @author Sun.Zhi
 *
 */
public class AgencyAgrAttTypeEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 房主证件
	 */
	private static final int HOU_OWNER_PAPER = 1;
	/**
	 * 代理协议
	 */
	private static final int AGENCY_AGR = 2;
	
	private AgencyAgrAttTypeEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 房主证件
	 */
	public static final AgencyAgrAttTypeEnum HOU_OWNER_PAPER_ENUM = new AgencyAgrAttTypeEnum("房主证件",HOU_OWNER_PAPER);
	/**
	 * 代理协议
	 */
	public static final AgencyAgrAttTypeEnum AGENCY_AGR_ENUM = new AgencyAgrAttTypeEnum("代理协议",AGENCY_AGR);

	public static AgencyAgrAttTypeEnum getEnum(int temp) {
		return (AgencyAgrAttTypeEnum) getEnum(AgencyAgrAttTypeEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(AgencyAgrAttTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(AgencyAgrAttTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(AgencyAgrAttTypeEnum.class);
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
