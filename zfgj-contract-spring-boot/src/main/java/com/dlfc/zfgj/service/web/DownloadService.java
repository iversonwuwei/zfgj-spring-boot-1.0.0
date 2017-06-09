package com.dlfc.zfgj.service.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.UsrUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by K on 2017/3/13.
 */
public interface DownloadService {
    void download(String contractId,
                  HttpServletRequest request,
                  HttpServletResponse response, UsrUser user) throws Exception;

    int getCycleFromType(int type);
}
