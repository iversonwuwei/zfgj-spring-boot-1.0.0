/**
* @Copyright: (c) 2017 DLFC. All rights reserved. 
*
* @name: TokenRequestException.java 
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
 * @name: TokenRequestException
 * @description:
 * 
 * @version 1.0
 * @author dean
 *
 */
public class TokenRequestException extends ApplicationException {

    private static final long serialVersionUID = -5998487223861139410L;

    private static final String ERROR_MESSAGE = "Token获取失败";

    public TokenRequestException() {
        super(ERROR_MESSAGE);
        setErrorCode(CaErrorCode.TOKEN_AUTHENTICATION_FAILURE.getErrorCode());
    }
}
