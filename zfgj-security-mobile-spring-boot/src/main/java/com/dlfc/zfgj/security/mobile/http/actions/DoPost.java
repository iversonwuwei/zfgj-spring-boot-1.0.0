package com.dlfc.zfgj.security.mobile.http.actions;

import com.dlfc.zfgj.security.mobile.exception.CustomRuntimeException;

/**
 * Created by walden on 2017/3/28.
 */
public interface DoPost {

     Object post(Object object) throws CustomRuntimeException;
}
