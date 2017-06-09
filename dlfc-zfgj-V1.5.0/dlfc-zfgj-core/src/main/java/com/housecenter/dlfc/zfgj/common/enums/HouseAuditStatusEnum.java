
/**
* @name: HouseAuditStatusEnum.java 
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
 * @name: HouseAuditStatusEnum
 * @description: 房源有效状态enum
 * 
 * @version 1.0
 * @author fancy
 *
 */
public class HouseAuditStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 792980940386960541L;

	protected HouseAuditStatusEnum(String name, int value) {
		super(name, value);
	}

	// 未审核
	private static final int UNAUDITED  = 1;
	// 通过
	private static final int PASS = 2;
	// 拒绝
	private static final int REFUSE = 3;

	public static final HouseAuditStatusEnum UNAUDITED_ENUM = new HouseAuditStatusEnum("未审核", UNAUDITED);

	public static final HouseAuditStatusEnum PASS_ENUM = new HouseAuditStatusEnum("通过", PASS);

	public static final HouseAuditStatusEnum REFUSE_ENUM = new HouseAuditStatusEnum("拒绝", REFUSE);

	public static HouseAuditStatusEnum getEnum(int temp) {
		return (HouseAuditStatusEnum) getEnum(HouseAuditStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(HouseAuditStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(HouseAuditStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(HouseAuditStatusEnum.class);
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
