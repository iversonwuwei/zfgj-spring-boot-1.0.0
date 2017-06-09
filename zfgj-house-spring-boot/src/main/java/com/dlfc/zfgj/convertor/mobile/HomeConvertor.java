package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.mobile.HomeDTO;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.AgtStatusEnum;
import com.dlfc.zfgj.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.service.mobile.AgtUsrCompLogInfoService;
import com.dlfc.zfgj.service.web.AgtCertLinkService;
import com.dlfc.zfgj.service.web.AgtService;
import com.dlfc.zfgj.service.web.SysInfoAttrService;
import com.dlfc.zfgj.service.web.SystemPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/6/8.
 */

@Component
public class HomeConvertor extends AbstractConvertor<UsrUser, HomeDTO> {

    @Autowired
    private SystemPersonService systemPersonService;
    @Autowired
    private AgtCertLinkService agtCertLinkService;
    @Autowired
    @Qualifier("aServiceImpl")
    private AgtService agtService;
    @Autowired
    @Qualifier("sysInfoServiceImpl")
    private SysInfoAttrService sysInfoAttrService;
    @Autowired
    @Qualifier("hmAgtUsrCompLogInfoService")
    private AgtUsrCompLogInfoService agtUsrCompLogInfoService;

    @Override
    public UsrUser toModel(HomeDTO homeDTO) {
        return null;
    }

    @Override
    public HomeDTO toDTO(UsrUser model, boolean forListView) {
        HomeDTO dto = new HomeDTO();
        dto.setUId(model.getId());
        dto.setUsername(model.getUsername());
        SysPerson sysPerson = systemPersonService.getSysPersonById(model.getPerId());
        if (null != sysPerson) {
            dto.setName(sysPerson.getName());
            dto.setNameResult("0");
        } else {
            dto.setNameResult("1");
        }
        List<AgtCertLink> agtCertLinkList = agtCertLinkService.isEmpCerted(model.getId());
        if (null != agtCertLinkList && agtCertLinkList.size() > 0) {
            dto.setCertResult(agtCertLinkList.get(0).getIsCert().toString());
        }
        AgtEmpInfo agtEmpInfo = agtService.getAgtEmpInfoByUserId(model.getId());
        if (null != agtEmpInfo) {
            if (0 == agtEmpInfo.getStatus()) {
                dto.setJoinResult(AgtStatusEnum.ACTIVE.getValue());
                dto.setEmpId(agtEmpInfo.getId());
                dto.setPhone(agtEmpInfo.getPhone());
                Integer grade = agtEmpInfo.getGrade();
                if (null != grade) {
                    dto.setGrade(grade.toString());
                }
                AgtCompInfo agtCompInfo = agtService.getCompInfoById(agtEmpInfo.getCompanyId());
                if (null != agtCompInfo) {
                    dto.setCompany(agtCompInfo.getName());
                    dto.setDeliverAddress(agtCompInfo.getAddress());
                    dto.setCompanyPhone(agtCompInfo.getTel());
                }
                List<SysInfoAtt> sysInfoAtts = sysInfoAttrService.findByParams(
                        InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue(), model.getId());
                if (null != sysInfoAtts && sysInfoAtts.size() > 0) {
                    dto.setUrl(sysInfoAtts.get(0).getFilePath());
                }
            } else {
                checkLog(model.getId(), dto);
            }
        } else {
            checkLog(model.getId(), dto);
        }
        return dto;
    }

    private void checkLog(String uid,
                          HomeDTO dto) {
        List<AgtUsrCompLogInfo> logs = agtUsrCompLogInfoService.findByUid(uid);
        if (null != logs && logs.size() > 0) {
            dto.setJoinResult(logs.get(0).getStatus().toString());
        } else {
            dto.setJoinResult(AgtStatusEnum.INACTIVE.getValue());
        }
    }
}
