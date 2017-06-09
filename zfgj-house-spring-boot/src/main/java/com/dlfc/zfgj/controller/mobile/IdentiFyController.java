package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.convertor.mobile.AgtCertLinkConvertor;
import com.dlfc.zfgj.convertor.mobile.SysPersonConvertor;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.mobile.CertIdentifyDTO;
import com.dlfc.zfgj.dto.mobile.NameIdentifyDTO;
import com.dlfc.zfgj.entity.AgtCertLink;
import com.dlfc.zfgj.entity.SysPerson;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.service.mobile.SysInfoAttrMService;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import com.dlfc.zfgj.service.web.AgtCertLinkService;
import com.dlfc.zfgj.service.web.SystemPersonService;
import com.dlfc.zfgj.validator.mobile.CertIdentifyValidator;
import com.dlfc.zfgj.validator.mobile.NameIdentifyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * Created by K on 2017/6/5.
 */

@RestController
@RequestMapping("/m/identify")
public class IdentiFyController {

    @Autowired
    private NameIdentifyValidator nameIdentifyValidator;
    @Autowired
    private CertIdentifyValidator certIdentifyValidator;
    @Autowired
    private SysPersonConvertor sysPersonConvertor;
    @Autowired
    private AgtCertLinkConvertor agtCertLinkConvertor;
    @Autowired
    private SystemPersonService systemPersonService;
    @Autowired
    @Qualifier("us111333M")
    private UserHouseMService userHouseMService;
    @Autowired
    @Qualifier("hwAgtCertLinkService")
    private AgtCertLinkService agtCertLinkService;
    @Autowired
    @Qualifier("sysInfoMServiceImpl")
    private SysInfoAttrMService sysInfoAttrMService;

    /**
     * 实名认证
     *
     * @param nameIdentifyDTO
     * @param token
     * @return
     */
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public ResultDTO<Void> name(@RequestBody NameIdentifyDTO nameIdentifyDTO,
                                @RequestHeader String token) {
        try {
            UsrUser user = userHouseMService.getUserName(token);
            nameIdentifyValidator.validate(nameIdentifyDTO);
            SysPerson sysPerson = sysPersonConvertor.toModel(nameIdentifyDTO);
            String pid = systemPersonService.save(sysPerson, user);
            user.setPerId(pid);
            userHouseMService.updateById(user);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(), null));
        }
        return ResultDTO.success();
    }

    @RequestMapping(value = "/cert", method = RequestMethod.POST)
    public ResultDTO<Void> cert(@RequestBody CertIdentifyDTO certIdentifyDTO,
                                @RequestHeader String token) {
        try {
            UsrUser user = userHouseMService.getUserName(token);
            certIdentifyDTO.setUid(user.getId());
            certIdentifyValidator.validate(certIdentifyDTO);
            AgtCertLink agtCertLink = agtCertLinkConvertor.toModel(certIdentifyDTO);
            String id = agtCertLinkService.save(agtCertLink, user);
            sysInfoAttrMService.saveWithLidAndPaths
                    (id, certIdentifyDTO.getPaths(), InfoAttFileTypeEnum.EMP_CERT_CARD_ENUM.getValue(), user);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(), null));
        }
        return ResultDTO.success();
    }
}
