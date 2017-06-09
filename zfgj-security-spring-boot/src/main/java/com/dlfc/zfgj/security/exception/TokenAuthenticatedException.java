/**
* @Copyright: (c) 2017 DLFC. All rights reserved. 
*
* @name: TokenAuthenticatedException.java 
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
 * @name: TokenAuthenticatedException
 * @description:
 * 
 * @version 1.0
 * @author dean
 *
 */
public class TokenAuthenticatedException extends ApplicationException {

    private static final long serialVersionUID = -1551121098623410119L;

    private static final String ERROR_MESSAGE = "Token认证失败";

    public TokenAuthenticatedException() {
        super(ERROR_MESSAGE);
        setErrorCode(CaErrorCode.TOKEN_AUTHENTICATION_FAILURE.getErrorCode());
    }
}
