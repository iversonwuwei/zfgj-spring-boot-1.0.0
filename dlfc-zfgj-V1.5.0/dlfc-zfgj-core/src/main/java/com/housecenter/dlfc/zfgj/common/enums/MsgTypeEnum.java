
/**
 * 是与否的枚举
 * 
* @name: YesNoEnum.java 
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
 * 消息类型的枚举
 * 
 * @name: YesNoEnum
 * @description: 
 * 
 * @version 1.0
 * @author Sun.Zhi
 *
 */
public class MsgTypeEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;
	
	/**
	 * 合同消息
	 */
	private static final int CON = 1;

	/**
	 * 房源消息
	 */
	private static final int HOU = 2;
	/**
	 * 账户消息
	 */
	private static final int ACC = 3;
	/**
	 * 服务消息
	 */
	private static final int SER = 4;
	
	private MsgTypeEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 合同
	 */
	public static final MsgTypeEnum CON_ENUM = new MsgTypeEnum("合同消息", CON);
	/**
	 * 已认证
	 */
	public static final MsgTypeEnum HOU_ENUM = new MsgTypeEnum("房源消息", HOU);
	/**
	 * 未认证
	 */
	public static final MsgTypeEnum ACC_ENUM = new MsgTypeEnum("账户消息", ACC);
	/**
	 * 已认证
	 */
	public static final MsgTypeEnum SER_ENUM = new MsgTypeEnum("服务消息", SER);

	public static MsgTypeEnum getEnum(int temp) {
		return (MsgTypeEnum) getEnum(MsgTypeEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(MsgTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(MsgTypeEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(MsgTypeEnum.class);
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
