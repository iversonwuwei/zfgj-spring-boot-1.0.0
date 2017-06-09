/**
* @Copyright: (c) 2017 DLFC. All rights reserved. 
*
* @name: IncorrectUserNameOrPasswordException.java 
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
 * @name: IncorrectUserNameOrPasswordException
 * @description:
 * 
 * @version 1.0
 * @author dean
 *
 */
public class IncorrectUserNameOrPasswordException extends ApplicationException {

    private static final long serialVersionUID = -3579828822732091899L;

    private static final String ERROR_MESSAGE = "认证失败";

    public IncorrectUserNameOrPasswordException() {
        super(ERROR_MESSAGE);
        setErrorCode(CaErrorCode.INCORRECT_USERNAME_OR_PASSWORD.getErrorCode());
    }

}
