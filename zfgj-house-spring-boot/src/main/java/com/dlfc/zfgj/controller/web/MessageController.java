package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.convertor.web.MessageConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.web.MessageDTO;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.SysMessage;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.AgtService;
import com.dlfc.zfgj.service.web.MessageService;
import com.dlfc.zfgj.service.web.UserHouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walden on 2017/3/24.
 */
@RestController
@RequestMapping(value = "/w/messages")
public class MessageController {

    @Autowired
    @Qualifier(value = "messageServiceImpl")
    private MessageService messageService;
    @Autowired
    private MessageConvertor messageConvertor;
    @Autowired
    @Qualifier(value = "us111333")
    private UserHouseService userHouseService;
    @Autowired
    @Qualifier(value = "aServiceImpl")
    private AgtService agtService;

    @RequestMapping(method = RequestMethod.GET)
    public ListResultDTO<MessageDTO> messages(@RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser user = userHouseService.getUser(token);
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(user.getId());
        List<SysMessage> sysMessages=new ArrayList<>();
        if (null != agtEmpInfo) {
            sysMessages = messageService.findByReceIdAndStatus(agtEmpInfo.getId(), (short) 1);
        }
        return messageConvertor.toResultDTO(sysMessages);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultDTO<Void> updateStatus(@PathVariable String id, @RequestParam Short status) throws CustomRuntimeException {
        SysMessage sysMessage = messageService.findByPrimaryKey(id);
        if (sysMessage == null) {
            throw new CustomRuntimeException("", "");
        }
        sysMessage.setStatus(status);
        messageService.update(sysMessage);

        return ResultDTO.success();
    }
}
