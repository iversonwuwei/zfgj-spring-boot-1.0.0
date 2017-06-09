package com.dlfc.zfgj.upload;

import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.exception.CustomRuntimeException;

/**
 * Created by K on 2017/4/14.
 */
public interface Imageable {

    void init() throws CustomRuntimeException;

    void add(SysInfoAtt sysInfoAtt) throws CustomRuntimeException;

    void delete(int index) throws CustomRuntimeException;

    void replace(int index,
                 SysInfoAtt sysInfoAtt) throws CustomRuntimeException;
}
