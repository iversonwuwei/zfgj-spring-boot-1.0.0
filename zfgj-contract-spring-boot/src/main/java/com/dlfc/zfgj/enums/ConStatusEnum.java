
/**
 * 合同状态枚举
 * 
* @name: ConStatusEnum.java 
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
package com.dlfc.zfgj.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * 合同状态
 * 
 * @name: ConStatusEnum
 * @description: 
 * 
 * @version 1.0
 * @author liuyundong
 *
 */
public class ConStatusEnum extends ValuedEnum {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;

	/**
	 * 创建中
	 */
	private static final int CREATING = 1;
	/**
	 * 待确认
	 */
	private static final int WAIT_CONFIRM = 2;
	/**
	 * 已拒绝
	 */
	private static final int REJECT = 3;
	/**
	 * 待付押金
	 */
	private static final int WAIT_PAY = 4;
	/**
	 * 已生效
	 */
	private static final int ACTIVE = 5;
	/**
	 * 已到期
	 */
	private static final int EXPIRE = 6;
	/**
	 * 已解除
	 */
	private static final int DISS_AGR = 7;
	/**
	 * 已完成
	 */
	private static final int FINISH = 8;
	/**
	 * 审核中
	 */
	private static final int APPROVING = 9;
	/**
	 * 审核拒绝
	 */
	private static final int APPROVE_REJECT= 10;
	/**
	 * 取消
	 */
	private static final int CANCEL = 11;
	/**
	 * 押金申领
	 */
	private static final int DEPOSIT_CLAIM = 12;
	/**
	 * 等待上传
	 */
	private static final int WAIT_UPLOAD = 13;
	
	private ConStatusEnum(String name, int value) {
		super(name, value);
	}

	/**
	 * 创建中
	 */
	public static final ConStatusEnum CREATING_ENUM = new ConStatusEnum("创建中", CREATING);
	/**
	 * 待确认
	 */
	public static final ConStatusEnum WAIT_CONFIRM_ENUM = new ConStatusEnum("待确认", WAIT_CONFIRM);
	/**
	 * 已拒绝
	 */
	public static final ConStatusEnum REJECT_ENUM = new ConStatusEnum("已拒绝", REJECT);
	/**
	 * 待付押金
	 */
	public static final ConStatusEnum WAIT_PAY_ENUM = new ConStatusEnum("待付押金", WAIT_PAY);
	/**
	 * 已生效
	 */
	public static final ConStatusEnum ACTIVE_ENUM = new ConStatusEnum("已生效", ACTIVE);
	/**
	 * 已到期
	 */
	public static final ConStatusEnum EXPIRE_ENUM = new ConStatusEnum("已到期", EXPIRE);
	/**
	 * 已解除
	 */
	public static final ConStatusEnum DISS_AGR_ENUM = new ConStatusEnum("已解除", DISS_AGR);
	/**
	 * 已完成
	 */
	public static final ConStatusEnum FINISH_ENUM = new ConStatusEnum("已完成", FINISH);
	/**
	 * 审核中
	 */
	public static final ConStatusEnum APPROVING_ENUM = new ConStatusEnum("审核中", APPROVING);
	/**
	 * 审核拒绝
	 */
	public static final ConStatusEnum APPROVE_REJECT_ENUM = new ConStatusEnum("审核拒绝", APPROVE_REJECT);
	/**
	 * 取消
	 */
	public static final ConStatusEnum CANCEL_ENUM = new ConStatusEnum("取消", CANCEL);
	/**
	 * 押金申领
	 */
	public static final ConStatusEnum DEPOSIT_CLAIM_ENUM = new ConStatusEnum("押金申领", DEPOSIT_CLAIM);
	/**
	 * 等待上传
	 */
	public static final ConStatusEnum WAIT_UPLOAD_ENUM = new ConStatusEnum("等待上传", WAIT_UPLOAD);
	
	public static ConStatusEnum getEnum(int temp) {
		return (ConStatusEnum) getEnum(ConStatusEnum.class, temp);
	}

	@SuppressWarnings("rawtypes")
	public static Map getEnumMap() {
		return getEnumMap(ConStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static List getEnumList() {
		return getEnumList(ConStatusEnum.class);
	}

	@SuppressWarnings("rawtypes")
	public static Iterator iterator() {
		return iterator(ConStatusEnum.class);
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
