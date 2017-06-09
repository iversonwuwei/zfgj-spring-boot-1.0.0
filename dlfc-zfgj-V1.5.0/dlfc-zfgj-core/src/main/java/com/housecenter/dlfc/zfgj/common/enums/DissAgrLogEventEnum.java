
/**
* @name: DissAgrLogEventEnum.java 
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
 * @name: DissAgrLogEventEnum
 * @description: 
 * 
 * @version 1.0
 * @author Wang Shouzheng
 *
 */
public class DissAgrLogEventEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9201189552561875214L;

	protected DissAgrLogEventEnum(String name, int value) {
		super(name, value);
	}
	
	/**
	 * 创建
	 */
	private static final int CREATE = 0;
	
	/**
	 * 提交解约协议
	 */
	private static final int SUBMIT = 1;  
	/**
	 * 拒绝协议
	 */
	private static final int REJECT = 2;  
	/**
	 * 同意协议
	 */
	private static final int ENTER = 3;  
	/**
	 * 修改
	 */
	private static final int MODIFY = 4;
	/**
	 * 取消
	 */
	private static final int CANEL = 5;
	/**
	 * 等待审核
	 */
	private static final int APPROVE = 6;
	
	/**
	 * 创建解约协议
	 */
	public static final DissAgrLogEventEnum CREATE_ENUM = new DissAgrLogEventEnum("创建", CREATE); //创建
	/**
	 * 提交解约协议
	 */
	public static final DissAgrLogEventEnum SUBMIT_ENUM = new DissAgrLogEventEnum("提交", SUBMIT); //提交
	/**
	 * 拒绝协议
	 */
	public static final DissAgrLogEventEnum REJECT_ENUM = new DissAgrLogEventEnum("拒绝", REJECT); //拒绝
	/**
	 * 同意协议
	 */
	public static final DissAgrLogEventEnum ENTER_ENUM = new DissAgrLogEventEnum("同意", ENTER); 
	/**
	 * 修改
	 */
	public static final DissAgrLogEventEnum MODIFY_ENUM = new DissAgrLogEventEnum("修改", MODIFY);//同意
	/**
	 * 取消
	 */
	public static final DissAgrLogEventEnum CANEL_ENUM = new DissAgrLogEventEnum("取消", CANEL);	
	/**
	 * 等待审核
	 */
	public static final DissAgrLogEventEnum APPROVE_ENUM = new DissAgrLogEventEnum("等待审核", APPROVE);
	
	public static DissAgrLogEventEnum getEnum(int temp) {
		return (DissAgrLogEventEnum) getEnum(DissAgrLogEventEnum.class, temp);
	}

	
	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(DissAgrLogEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(DissAgrLogEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(DissAgrLogEventEnum.class);
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
