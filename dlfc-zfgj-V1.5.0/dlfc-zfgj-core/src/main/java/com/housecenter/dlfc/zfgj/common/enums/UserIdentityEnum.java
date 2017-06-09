
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
 * 用户身份枚举
 * 
 * @name: UserIdentityEnum
 * @description: 
 * 
 * @version 1.0
 * @author Sun.Zhi
 *
 */
public class UserIdentityEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 后台系统用户
	 */
	private static final int SYS_USER = 0;
	/**
	 * 前台用户
	 */
	private static final int ONLINE_USER = 1;
	/**
	 * 中介公司用户
	 */
	private static final int MEDI_USER = 2;
	/**
	 * 经济人用户
	 */
	private static final int BROKER_USER = 3;
	/**
	 * 第三方用户
	 */
	private static final int THIRD_PART_USER = 4;
	/**
	 * 系统执机
	 */
	private static final int SYS_AUTO = 9;
	
	private UserIdentityEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 后台系统用户
	 */
	public static final UserIdentityEnum SYS_USER_ENUM = new UserIdentityEnum("后台系统用户",SYS_USER);
	/**
	 * 前台用户
	 */
	public static final UserIdentityEnum ONLINE_USER_ENUM = new UserIdentityEnum("前台用户",ONLINE_USER);
	/**
	 * 中介公司用户
	 */
	public static final UserIdentityEnum MEDI_USER_ENUM = new UserIdentityEnum("中介公司用户",MEDI_USER);
	/**
	 * 经济人用户
	 */
	public static final UserIdentityEnum BROKER_USER_ENUM = new UserIdentityEnum("经济人用户",BROKER_USER);
	/**
	 * 第三方用户
	 */
	public static final UserIdentityEnum THIRD_PART_USER_ENUM = new UserIdentityEnum("第三方用户",THIRD_PART_USER);
	/**
	 * 系统执机
	 */
	public static final UserIdentityEnum SYS_AUTO_ENUM = new UserIdentityEnum("系统执机", SYS_AUTO);

	public static UserIdentityEnum getEnum(int temp) {
		return (UserIdentityEnum) getEnum(UserIdentityEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(UserIdentityEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(UserIdentityEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(UserIdentityEnum.class);
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
