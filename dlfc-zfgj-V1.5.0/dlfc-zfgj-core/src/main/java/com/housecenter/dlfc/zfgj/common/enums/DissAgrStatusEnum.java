
/**
* @name: DissAgrStatusEnum.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年10月28日 
* @author: Wang Shouzheng 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年10月28日       Wang Shouzheng        1.0             <修改原因描述>
*/
package com.housecenter.dlfc.zfgj.common.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * 合同解除协议状态事件
 * @name: DissAgrStatusEnum
 * @description: 
 * 
 * @version 1.0
 * @author Wang Shouzheng
 *
 */
public class DissAgrStatusEnum extends ValuedEnum {

	protected DissAgrStatusEnum(String name, int value) {
		super(name, value);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -2971173721789184810L;
	/**
	 * 创建解约协议
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
	 * 取消协议
	 */
	private static final int CANEL = 4;
	/**
	 * 等待审核中
	 */
	private static final int APPROVE = 5;
	/**
	 * 审核拒绝
	 */
	private static final int DISAGREE = 6;
	
	/**
	 * 已完成
	 */
	private static final int FINISH = 7;
	
	/**
	 * 创建解约协议
	 */
	public static final DissAgrStatusEnum CREATE_ENUM = new DissAgrStatusEnum("创建", CREATE); //创建
	/**
	 * 提交解约协议
	 */
	public static final DissAgrStatusEnum SUBMIT_ENUM = new DissAgrStatusEnum("提交", SUBMIT); //提交
	/**
	 * 拒绝协议
	 */
	public static final DissAgrStatusEnum REJECT_ENUM = new DissAgrStatusEnum("拒绝", REJECT); //拒绝
	/**
	 * 同意协议
	 */
	public static final DissAgrStatusEnum ENTER_ENUM = new DissAgrStatusEnum("同意", ENTER); //同意
	/**
	 * 等待审核中
	 */
	public static final DissAgrStatusEnum APPROVE_ENUM = new DissAgrStatusEnum("等待审核中", APPROVE);
	
	/**
	 * 审核决绝
	 */
	public static final DissAgrStatusEnum DISAGREE_ENUM = new DissAgrStatusEnum("审核拒绝", DISAGREE);
	
	/**
	 * 取消协议
	 */
	public static final DissAgrStatusEnum CANEL_ENUM = new DissAgrStatusEnum("取消", CANEL);
	/**
	 * 已完成
	 */
	public static final DissAgrStatusEnum FINISH_ENUM = new DissAgrStatusEnum("已完成", FINISH);
	

	public static DissAgrStatusEnum getEnum(int temp) {
		return (DissAgrStatusEnum) getEnum(DissAgrStatusEnum.class, temp);
	}

	
	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(DissAgrStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(DissAgrStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(DissAgrStatusEnum.class);
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
