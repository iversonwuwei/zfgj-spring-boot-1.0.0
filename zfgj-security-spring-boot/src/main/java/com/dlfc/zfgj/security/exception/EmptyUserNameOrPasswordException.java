/**
* @Copyright: (c) 2017 DLFC. All rights reserved. 
*
* @name: EmptyUserNameOrPasswordException.java 
* @description: 
*
* @version: 1.0
* @date : Apr 18, 2017 
* @author: dean 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  Apr 18, 2017       dean        1.0             <修改原因描述>
*/

package com.dlfc.zfgj.security.exception;

import com.housecenter.dlfc.framework.exception.model.ApplicationException;

/**
 * @name: EmptyUserNameOrPasswordException
 * @description:
 * 
 * @version 1.0
 * @author dean
 *
 */
public class EmptyUserNameOrPasswordException extends ApplicationException {

    private static final long serialVersionUID = -7285587206149286200L;
    
    private static final String ERROR_MESSAGE = "用户名或密码为空";

    public EmptyUserNameOrPasswordException() {
        super(ERROR_MESSAGE);
        setErrorCode(CaErrorCode.EMPTY_USERNAME_OR_PASSWORD.getErrorCode());
    }

}
