
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
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * 房源代理状态
 * 
 * @name: HouseAgentStatusEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class HouseAgentStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 未代理
	 */
	private static final int NO_AGENT = 0;
	/**
	 * 已代理
	 */
	private static final int AGENT = 1;
	
	private HouseAgentStatusEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 未代理
	 */
	public static final HouseAgentStatusEnum NO_AGENT_ENUM = new HouseAgentStatusEnum("未代理", NO_AGENT);
	/**
	 * 已代理
	 */
	public static final HouseAgentStatusEnum AGENT_ENUM = new HouseAgentStatusEnum("已代理", AGENT);

	public static HouseAgentStatusEnum getEnum(int temp) {
		return (HouseAgentStatusEnum) getEnum(HouseAgentStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(HouseAgentStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(HouseAgentStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(HouseAgentStatusEnum.class);
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
