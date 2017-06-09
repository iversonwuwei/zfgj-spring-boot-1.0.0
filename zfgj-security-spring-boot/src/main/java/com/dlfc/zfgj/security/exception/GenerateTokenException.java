/**
* @Copyright: (c) 2017 DLFC. All rights reserved. 
*
* @name: GenerateTokenException.java 
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
 * @name: GenerateTokenException
 * @description:
 * 
 * @version 1.0
 * @author dean
 *
 */
public class GenerateTokenException extends ApplicationException {

    private static final long serialVersionUID = 5510160378374641505L;

    private static final String ERROR_MESSAGE = "Token生成失败";

    public GenerateTokenException() {
        this(null);
    }

    public GenerateTokenException(Throwable cause) {
        super(ERROR_MESSAGE, cause);
        setErrorCode(CaErrorCode.GENERATE_TOKEN_FAILURE.getErrorCode());
    }
}
