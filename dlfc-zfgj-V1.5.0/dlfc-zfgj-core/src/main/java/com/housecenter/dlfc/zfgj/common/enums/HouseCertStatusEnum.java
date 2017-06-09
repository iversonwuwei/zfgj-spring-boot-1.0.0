
/**
* @name: HouseCertStatusEnum.java 
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
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * @name: HouseCertStatusEnum
 * @description: 房源认证状态enum
 * 
 * @version 1.0
 * @author fancy
 *
 */
public class HouseCertStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4311089614582643089L;

	//未认证
	private static final int UNCERT = 0;
	//已认证
	private static final int YES = 1;
	//认证失败
	private static final int NO = 2;
	
	private HouseCertStatusEnum(String name, int value) {
		super(name, value);
	}

	public static final HouseCertStatusEnum UNCERT_ENUM = new HouseCertStatusEnum("未认证", UNCERT);
	
	public static final HouseCertStatusEnum YES_ENUM = new HouseCertStatusEnum("已认证", YES);
	
	public static final HouseCertStatusEnum NO_ENUM = new HouseCertStatusEnum("认证失败", NO);

	public static HouseCertStatusEnum getEnum(int temp) {
		return (HouseCertStatusEnum) getEnum(HouseCertStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(HouseCertStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(HouseCertStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(HouseCertStatusEnum.class);
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
