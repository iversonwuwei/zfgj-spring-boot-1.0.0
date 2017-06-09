
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
 * @description: 审核状态enum-审核状态（默认值）业务中不一致的以业务中为准
 * 
 * @version 1.0
 * @author fancy
 *
 */
public class AuditStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 792980940386960541L;

	protected AuditStatusEnum(String name, int value) {
		super(name, value);
	}

	// 未审核
	private static final int UNAUDITED  = 0;
	// 通过
	private static final int PASS = 1;
	// 拒绝
	private static final int REFUSE = 2;

	public static final AuditStatusEnum UNAUDITED_ENUM = new AuditStatusEnum("待审核", UNAUDITED);

	public static final AuditStatusEnum PASS_ENUM = new AuditStatusEnum("审核通过", PASS);

	public static final AuditStatusEnum REFUSE_ENUM = new AuditStatusEnum("审核未通过", REFUSE);

	public static AuditStatusEnum getEnum(int temp) {
		return (AuditStatusEnum) getEnum(AuditStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(AuditStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(AuditStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(AuditStatusEnum.class);
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
