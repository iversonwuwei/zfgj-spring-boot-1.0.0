
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
 * 出租方式
 * 
 * @name: RentalModeEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class StructureEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 砖混
	 */
	private static final int Zhuan_Hun = 1;
	/**
	 * 框架
	 */
	private static final int Kuang_Jiya = 2;
	/**
	 * 其他
	 */
	private static final int Qi_Ta = 3;
	
	
	private StructureEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 砖混
	 */
	public static final StructureEnum ZHUAN_HUN_ENUM = new StructureEnum("砖混",Zhuan_Hun);
	/**
	 * 框架
	 */
	public static final StructureEnum KUANG_JIYA_ENUM = new StructureEnum("框架",Kuang_Jiya);
	/**
	 * 其他
	 */
	public static final StructureEnum QI_TA_ENUM = new StructureEnum("其他",Qi_Ta);

	public static StructureEnum getEnum(int temp) {
		return (StructureEnum) getEnum(StructureEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(StructureEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(StructureEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(StructureEnum.class);
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
