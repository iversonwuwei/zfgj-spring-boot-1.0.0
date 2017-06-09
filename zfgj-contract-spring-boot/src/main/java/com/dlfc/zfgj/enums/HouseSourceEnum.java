
/**
* @name: HouseSourceEnum.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年8月15日 
* @author: Liu.Jia 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月15日       Liu.Jia        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.enums;

import org.apache.commons.lang.enums.ValuedEnum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @name: HouseSourceEnum
 * @description: 房源来源枚举
 * 
 * @version 1.0
 * @author Liu.Jia
 *
 */
public class HouseSourceEnum extends ValuedEnum{

	private static final long serialVersionUID = 1L;
	
	protected HouseSourceEnum(String name, int value) {
		super(name, value);
	}
	
	/**
	 * 网站创建的房源
	 */
	private static final int WEBSITE = 1;
	/**
	 * 租房管家创建的房源
	 */
	private static final int ZFGJ = 2;
	/**
	 * 合同系统创建的房源
	 */
	private static final int CONTRACT = 3;
	
	/**
	 * 网站创建的房源
	 */
	public static final HouseSourceEnum  WEBSITE_ENUMM = new HouseSourceEnum("网站房源", WEBSITE);
	/**
	 * 租房管家创建的合同
	 */
	public static final HouseSourceEnum ZFGJ_ENUM = new HouseSourceEnum("租房管家房源", ZFGJ);
	/**
	 * 合同系统创建的房源
	 */
	public static final HouseSourceEnum CONTRACT_ENU = new HouseSourceEnum("合同房源", CONTRACT);
	
	public static HouseSourceEnum getEnum(int temp) {
		return (HouseSourceEnum) getEnum(HouseSourceEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(HouseSourceEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(HouseSourceEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(HouseSourceEnum.class);
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
