package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.mobile.DownloadMService;
import com.dlfc.zfgj.service.mobile.UsrUserMService;
import com.housecenter.dlfc.framework.starter.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by K on 2017/3/7.
 */

@Slf4j
@RestController
@RequestMapping(value = "/m/download")
public class ContractDownloadMController extends BaseController {

    @Autowired
    private DownloadMService downloadMServiceImpl;
    @Autowired
    private ResultError resultError;
    @Autowired
    private UsrUserMService usrUserMService;


    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public ResultDTO<Void> pdfDownload(@RequestHeader String token,
                                       @RequestParam String contractId,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws CustomRuntimeException {
        try {
            UsrUser user;
            try {
                user = usrUserMService.getUser(token);
            } catch (ApplicationException e) {
                log.error(e.getCodeAndExplanation());
                response.setStatus(getResponse().getStatus());
                throw e;
            }
            downloadMServiceImpl.download(contractId, request, response, user);
        } catch (ApplicationException e) {
            this.resultError.setErrmsg(e.getMessage());
            return ResultDTO.failure(this.resultError);
        }
        return ResultDTO.success();
    }
}
