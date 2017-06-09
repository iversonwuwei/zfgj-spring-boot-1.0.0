package com.dlfc.zfgj.service.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.SysInfoAtt;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by walden on 2017/2/17.
 */
public interface UploadMService<T> {

    List<SysInfoAtt> upload(T[] ts, int imgType, String empId) throws ApplicationException;

    void showUp(String path, HttpServletResponse response) throws ApplicationException;

    SysInfoAtt insertSysInfoAtt(int imgType,String empId,String fileName,String fileRealname, String path);

    String uploadTemp(MultipartFile file) throws ApplicationException;

    String uploadSingleFile(MultipartFile file, String realDir) throws ApplicationException;

    List<String> uploadFiles(MultipartFile[] files, int type) throws ApplicationException;
}
