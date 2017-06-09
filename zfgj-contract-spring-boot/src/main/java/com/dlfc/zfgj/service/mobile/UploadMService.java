package com.dlfc.zfgj.service.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.UsrUser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by walden on 2017/2/17.
 */
public interface UploadMService<T> {

    Map getMapForView(String contractId) throws ApplicationException;

    Map DoUpload(MultipartFile[] files,
                 Integer[] types,
                 String contractId,
                 UsrUser user) throws ApplicationException;

    void showUp(String path, HttpServletResponse response) throws ApplicationException;
}
