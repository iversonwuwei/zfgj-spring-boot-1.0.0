/**
* @Copyright: (c) 2017 DLFC. All rights reserved. 
*
* @name: GenerateRefreshTokenException.java 
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
 * @name: GenerateRefreshTokenException
 * @description:
 * 
 * @version 1.0
 * @author dean
 *
 */
public class GenerateRefreshTokenException extends ApplicationException {

    private static final long serialVersionUID = -7562264932294508026L;

    private static final String ERROR_MESSAGE = "Refresh Token生成失败";

    public GenerateRefreshTokenException() {
        this(null);
    }

    public GenerateRefreshTokenException(Throwable cause) {
        super(ERROR_MESSAGE, cause);
        setErrorCode(CaErrorCode.GENERATE_REFRESH_TOKEN_FAILURE.getErrorCode());
    }
}
