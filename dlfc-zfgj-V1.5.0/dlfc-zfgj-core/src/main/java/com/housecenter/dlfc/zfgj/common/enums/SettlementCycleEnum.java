
/**
 * 结算周期枚举
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
 * 结算周期枚举
 * 
 * @name: SettlementCycleEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class SettlementCycleEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 月付
	 */
	private static final int MONTHLY = 1;
	/**
	 * 季付
	 */
	private static final int QUARTER = 2;
	/**
	 * 半年付
	 */
	private static final int HALF_YEAR = 3;
	/**
	 * 年付
	 */
	private static final int YEAR = 4;
	
	private SettlementCycleEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 月付
	 */
	public static final SettlementCycleEnum MONTHLY_ENUM = new SettlementCycleEnum("月付",MONTHLY);
	/**
	 * 季付
	 */
	public static final SettlementCycleEnum QUARTER_ENUM = new SettlementCycleEnum("季付",QUARTER);
	/**
	 * 半年付
	 */
	public static final SettlementCycleEnum HALF_YEAR_ENUM = new SettlementCycleEnum("半年付",HALF_YEAR);
	/**
	 * 年付
	 */
	public static final SettlementCycleEnum YEAR_ENUM = new SettlementCycleEnum("年付",YEAR);

	public static SettlementCycleEnum getEnum(int temp) {
		return (SettlementCycleEnum) getEnum(SettlementCycleEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(SettlementCycleEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(SettlementCycleEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(SettlementCycleEnum.class);
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
