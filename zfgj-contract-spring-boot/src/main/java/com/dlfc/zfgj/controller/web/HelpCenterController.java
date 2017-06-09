package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.service.web.UsrUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by K on 2017/4/12.
 */

@Slf4j
@RestController
@RequestMapping(value = "/w/help")
public class HelpCenterController {

    @Autowired
    private UsrUserService usrUserService;
    @Autowired
    private ResultError resultError;

    /**
     * 意见反馈
     *
     * @param token
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/feedBack", method = RequestMethod.GET)
    public ResultDTO<Void> feedBack(@RequestHeader String token,
                                    @RequestParam String title,
                                    @RequestParam String content,
                                    HttpServletRequest request) {
        UsrUser user = null;
        try {
            user = usrUserService.getUser(token);
        } catch (ApplicationException e) {
            log.error(e.getCode());
            this.resultError.setErrmsg(e.getMessage());
        }
        if (!usrUserService.feedBack(title, content, request, user)) {
            return ResultDTO.failure(new ResultError("提交意见反馈失败",null));
        }
        return ResultDTO.success();
    }
}
