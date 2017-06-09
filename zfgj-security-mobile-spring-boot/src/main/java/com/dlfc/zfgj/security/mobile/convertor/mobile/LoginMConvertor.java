package com.dlfc.zfgj.security.mobile.convertor.mobile;

import com.dlfc.zfgj.security.mobile.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.security.mobile.dto.mobile.LoginMDTO;
import com.dlfc.zfgj.security.mobile.entity.*;
import com.dlfc.zfgj.security.mobile.enums.AgtStatusEnum;
import com.dlfc.zfgj.security.mobile.enums.InfoAttFileTypeEnum;
import com.dlfc.zfgj.security.mobile.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wanglijun on 2017/3/29.
 */
@Component
public class LoginMConvertor extends AbstractConvertor<UsrUser, LoginMDTO> {

    @Autowired
    @Qualifier("smAgtEmpInfoService")
    private AgtEmpInfoService agtEmpInfoService;
    @Autowired
    @Qualifier("smSysPersonService")
    private SysPersonService sysPersonService;
    @Autowired
    @Qualifier("smAgtCompInfoService")
    private AgtCompInfoService agtCompInfoService;
    @Autowired
    @Qualifier("smSysInfoAttService")
    private SysInfoAttService sysInfoAttService;
    @Autowired
    @Qualifier("smAgtCertLinkService")
    private AgtCertLinkService agtCertLinkService;
    @Autowired
    @Qualifier("smAgtUsrCompLogInfoService")
    private AgtUsrCompLogInfoService agtUsrCompLogInfoService;

    @Override
    public UsrUser toModel(LoginMDTO loginMDTO) {
        return null;
    }

    @Override
    public LoginMDTO toDTO(UsrUser model, boolean forListView) {
        LoginMDTO loginMDTO = new LoginMDTO();
        loginMDTO.setToken("");
        String uid = model.getId();
        SysPerson sysPerson = sysPersonService.getSysPersonById(model.getPerId());
        if (null != sysPerson) {
            loginMDTO.setName(sysPerson.getName());
            // 实名认证成功
            loginMDTO.setNameResult("0");
        } else {
            loginMDTO.setNameResult("1");
        }
        List<AgtCertLink> agtCertLinkList = agtCertLinkService.getAgtCertLinkByUid(uid);
        if (null != agtCertLinkList && agtCertLinkList.size() > 0) {
            loginMDTO.setCertResult(agtCertLinkList.get(0).getIsCert().toString());
        }
        loginMDTO.setUId(uid);
        loginMDTO.setUsername(model.getUsername());
        AgtEmpInfo agtEmpInfo = agtEmpInfoService.getAgtEmpInfoByUserId(uid);
        if (null != agtEmpInfo) {
            if (0 == agtEmpInfo.getStatus()) {
                loginMDTO.setJoinResult(AgtStatusEnum.ACTIVE.getValue());
                loginMDTO.setEmpId(agtEmpInfo.getId());
                loginMDTO.setPhone(agtEmpInfo.getPhone());
                Integer grade = agtEmpInfo.getGrade();
                if (null != grade) {
                    loginMDTO.setGrade(grade.toString());
                }
                AgtCompInfo agtCompInfo = agtCompInfoService.getAgtCompInfoById(agtEmpInfo.getCompanyId());
                if (null != agtCompInfo) {
                    loginMDTO.setCompany(agtCompInfo.getName());
                }
                List<SysInfoAtt> sysInfoAtts = sysInfoAttService.getSysInfoAttByParams(
                        InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue(), uid);
                if (null != sysInfoAtts && sysInfoAtts.size() > 0) {
                    loginMDTO.setUrl(sysInfoAtts.get(0).getFilePath());
                }
            } else {
                checkLog(uid, loginMDTO);
            }
        } else {
            checkLog(uid, loginMDTO);
        }
        return loginMDTO;
    }

    private void checkLog(String uid,
                          LoginMDTO loginMDTO) {
        List<AgtUsrCompLogInfo> logs = agtUsrCompLogInfoService.findAgtUsrCompLogInfoByUid(uid);
        if (null != logs && logs.size() > 0) {
            loginMDTO.setJoinResult(logs.get(0).getStatus().toString());
        } else {
            loginMDTO.setJoinResult(AgtStatusEnum.INACTIVE.getValue());
        }
    }
}
