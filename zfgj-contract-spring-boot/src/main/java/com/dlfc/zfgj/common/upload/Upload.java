package com.dlfc.zfgj.common.upload;

import com.dlfc.admin.common.exception.ApplicationException;

/**
 * Created by walden on 2017/2/17.
 */
public interface Upload<F> {

    Upload upload(F f);

    void upload(F file, String fileName) throws ApplicationException;
}
