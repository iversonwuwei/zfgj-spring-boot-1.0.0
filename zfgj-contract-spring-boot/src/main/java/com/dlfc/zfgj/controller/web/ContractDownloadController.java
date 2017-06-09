package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.DownloadService;
import com.dlfc.zfgj.service.web.UsrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by K on 2017/3/7.
 */

@RestController
@RequestMapping(value = "/w/download")
public class ContractDownloadController {

    @Autowired
    private DownloadService downloadServiceImpl;
    @Autowired
    private UsrUserService usrUserService;
    @Autowired
    private ResultError resultError;

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public ResultDTO<Void> pdfDownload(@RequestParam String contractId,
                                       @RequestParam String token,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws CustomRuntimeException {
        try {
            UsrUser user = usrUserService.getUser(token);
            downloadServiceImpl.download(contractId, request, response, user);
        } catch (Exception e) {
            return ResultDTO.failure(new ResultError(PropertyUtils.getErrorMsg("SYS-0130"),null));
        }
        return ResultDTO.success();
    }
}
