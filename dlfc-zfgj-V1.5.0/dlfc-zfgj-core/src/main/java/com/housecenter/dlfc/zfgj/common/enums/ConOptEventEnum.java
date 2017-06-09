
/**
 * 合同操作事件枚举
 * 
* @name: ConOptEventEnum.java 
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
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * 合同操作事件
 * 
 * @name: ConOptEventEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class ConOptEventEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 创建
	 */
	private static final int CREATE = 1;
	/**
	 * 提交
	 */
	private static final int SUBMIT = 2;
	/**
	 * 拒绝
	 */
	private static final int REJECT = 3;
	/**
	 * 同意
	 */
	private static final int AGREE = 4;
	/**
	 * 修改
	 */
	private static final int MODIFY = 5;
	/**
	 * 取消
	 */
	private static final int CANCEL = 6;
	/**
	 * 支付押金
	 */
	private static final int PAY = 7;
	
	private ConOptEventEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 创建
	 */
	public static final ConOptEventEnum CREATE_ENUM = new ConOptEventEnum("创建", CREATE);
	/**
	 * 提交
	 */
	public static final ConOptEventEnum SUBMIT_ENUM = new ConOptEventEnum("提交", SUBMIT);
	/**
	 * 拒绝
	 */
	public static final ConOptEventEnum REJECT_ENUM = new ConOptEventEnum("拒绝", REJECT);
	/**
	 * 同意
	 */
	public static final ConOptEventEnum AGREE_ENUM = new ConOptEventEnum("同意", AGREE);
	/**
	 * 修改
	 */
	public static final ConOptEventEnum MODIFY_ENUM = new ConOptEventEnum("修改", MODIFY);
	/**
	 * 取消
	 */
	public static final ConOptEventEnum CANCEL_ENUM = new ConOptEventEnum("取消", CANCEL);
	/**
	 * 支付押金
	 */
	public static final ConOptEventEnum PAY_ENUM = new ConOptEventEnum("支付押金", PAY);
	
	public static ConOptEventEnum getEnum(int temp) {
		return (ConOptEventEnum) getEnum(ConOptEventEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(ConOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(ConOptEventEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(ConOptEventEnum.class);
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
