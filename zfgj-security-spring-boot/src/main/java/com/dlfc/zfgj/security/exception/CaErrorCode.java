/**
* @Copyright: (c) 2017 DLFC. All rights reserved. 
*
* @name: CaErrorCode.java 
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

import com.housecenter.dlfc.framework.exception.model.ErrorCode;

/**
 * @name: CaErrorCode
 * @description:
 * 
 * @version 1.0
 * @author dean
 *
 */
public enum CaErrorCode implements ErrorCode {

    GENERATE_TOKEN_FAILURE("Ca-1000"), GENERATE_REFRESH_TOKEN_FAILURE(
            "Ca-1001"), EMPTY_USERNAME_OR_PASSWORD("Ca-1002"), INCORRECT_USERNAME_OR_PASSWORD(
                    "Ca-1003"), TOKEN_REQUEST_FAILURE("Ca-1004"), TOKEN_AUTHENTICATION_FAILURE(
                            "Ca-1005"), REFRESH_TOKEN_EXPIRED("Ca-1006");

    private String code;

    CaErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String getErrorCode() {
        return code;
    }

}
