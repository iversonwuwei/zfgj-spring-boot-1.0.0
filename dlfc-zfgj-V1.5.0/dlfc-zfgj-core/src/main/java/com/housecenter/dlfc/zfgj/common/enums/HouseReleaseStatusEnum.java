
/**
* @name: HouseReleaseStatusEnum.java 
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
 * @name: HouseReleaseStatusEnum
 * @description: 出租信息发布状态enum
 * 
 * @version 1.0
 * @author fancy
 *
 */
public class HouseReleaseStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2295844921309773163L;
	//未发布
	private static final int NO = 0;
	//已发布
	private static final int YES = 1;
	//强制下架
	private static final int FORCED  = 2;
	
	private HouseReleaseStatusEnum(String name, int value) {
		super(name, value);
	}

	public static final HouseReleaseStatusEnum NO_ENUM = new HouseReleaseStatusEnum("未发布", NO);
	
	public static final HouseReleaseStatusEnum YES_ENUM = new HouseReleaseStatusEnum("已发布", YES);
	
	public static final HouseReleaseStatusEnum FORCED_ENUM = new HouseReleaseStatusEnum("强制下架", FORCED);

	public static HouseReleaseStatusEnum getEnum(int temp) {
		return (HouseReleaseStatusEnum) getEnum(HouseReleaseStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(HouseReleaseStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(HouseReleaseStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(HouseReleaseStatusEnum.class);
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
