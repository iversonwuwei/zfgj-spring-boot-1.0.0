/**
 * @name: PermissionScopeEnum.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 
 *
 * @version: 1.0
 * @date : 2015年11月27日 
 * @author: Alex.Ge 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年11月27日       Alex.Ge        1.0             <修改原因描述>
 */
package com.housecenter.dlfc.zfgj.common.enums;

import org.apache.commons.lang.enums.ValuedEnum;

/**
 * @name: PermissionScopeEnum
 * @description:
 * 
 * @version 1.0
 * @author Alex.Ge
 *
 */
public class PermissionScopeEnum extends ValuedEnum {

	protected PermissionScopeEnum(String name, int value) {
		super(name, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3305561255485119822L;
	
	private static final int PERMISSION_SCOPE_ALL = 1;
	
	private static final int PERMISSION_SCOPE_COMP_ALL = 2;
	private static final int PERMISSION_SCOPE_COMP_ONLY = 3;
	private static final int PERMISSION_SCOPE_OFFICE_ALL = 4;
	private static final int PERMISSION_SCOPE_OFFICE_ONLY = 5;
	/*private static final int PERMISSION_SCOPE_ = 6;
	private static final int PERMISSION_SCOPE_ = 7;*/
	private static final int PERMISSION_SCOPE_PERSONAL = 8;
	private static final int PERMISSION_SCOPE_CONFIG = 9;
	

}
