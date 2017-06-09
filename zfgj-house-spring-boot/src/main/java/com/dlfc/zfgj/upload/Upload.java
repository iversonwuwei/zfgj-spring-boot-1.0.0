package com.dlfc.zfgj.upload;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.SysInfoAtt;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by walden on 2017/2/17.
 */
public interface Upload<F> {

    Upload upload(File f) throws ApplicationException;

    void showUp(String path, HttpServletResponse response) throws IOException;

    List<SysInfoAtt> upload(F f, int imgType, String empId) throws ApplicationException;
}
