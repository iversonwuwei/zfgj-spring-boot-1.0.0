
/**
* @name: EntrustStateEnum.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年10月29日 
* @author: Wang Shouzheng 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年10月29日       Wang Shouzheng        1.0             <修改原因描述>
*/
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * @name: EntrustStateEnum
 * @description: 
 * 
 * @version 1.0
 * @author Wang Shouzheng
 *
 */
public class EntrustStateEnum extends ValuedEnum {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2562600113198673928L;

	protected EntrustStateEnum(String name, int value) {
		super(name, value);
	}
	
	/**
	 * 委托中
	 */
	private static final int COMMISSIONED = 0;
	
	/**
	 * 进行中
	 */
	private static final int RESERVATION = 1;
	/**
	 * 已完成
	 */
	private static final int PUBLISH = 2;
	/**
	 * 委托中
	 */
	public static final EntrustStateEnum COMMISSIONED_ENUM = new EntrustStateEnum("委托中", COMMISSIONED);
	/**
	 * 进行中
	 */
	public static final EntrustStateEnum RESERVATION_ENUM = new EntrustStateEnum("进行中", RESERVATION);
	/**
	 * 已完成
	 */
	public static final EntrustStateEnum PUBLISH_ENUM = new EntrustStateEnum("已完成", PUBLISH);
	
	public static EntrustStateEnum getEnum(int temp) {
		return (EntrustStateEnum) getEnum(EntrustStateEnum.class, temp);
	}

	
	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(EntrustStateEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(EntrustStateEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(EntrustStateEnum.class);
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
