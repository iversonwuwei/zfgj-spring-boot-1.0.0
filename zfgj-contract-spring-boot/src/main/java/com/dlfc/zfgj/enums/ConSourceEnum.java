
/**
 * 合同创建来源枚举
 * 
* @name: ConStatusEnum.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年10月15日 
* @author: liuyundong 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年9月25日       liuyundong        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.enums;

import org.apache.commons.lang.enums.ValuedEnum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 合同from
 * 
 * @name: ConStatusEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class ConSourceEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5463225982329490779L;

	/**
	 * 合同系统
	 */
	private static final int CONTRACT = 1;
	/**
	 * 租房管家
	 */
	private static final int ZFGJ = 2;
	/**
	 * 网站
	 */
	private static final int WEBSITE = 3;
	/**
	 * 网站APP
	 */
	private static final int APP = 4;
	/**
	 * 租房管家APP
	 */
	private static final int ZFGJ_APP = 5;
	
	private ConSourceEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 合同系统
	 */
	public static final ConSourceEnum CONTRACT_ENUM = new ConSourceEnum("合同系统", CONTRACT);
	/**
	 * 租房管家
	 */
	public static final ConSourceEnum ZFGJ_ENUM = new ConSourceEnum("租房管家", ZFGJ);
	/**
	 * 网站
	 */
	public static final ConSourceEnum WEBSITE_ENUM = new ConSourceEnum("网站", WEBSITE);
	/**
	 * 网站APP
	 */
	public static final ConSourceEnum APP_ENUM = new ConSourceEnum("网站APP", APP);
	/**
	 * 租房管家APP
	 */
	public static final ConSourceEnum ZFGJ_APP_ENUM = new ConSourceEnum("租房管家APP", ZFGJ_APP);
	 
	
	public static ConSourceEnum getEnum(int temp) {
		return (ConSourceEnum) getEnum(ConSourceEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(ConSourceEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(ConSourceEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(ConSourceEnum.class);
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
