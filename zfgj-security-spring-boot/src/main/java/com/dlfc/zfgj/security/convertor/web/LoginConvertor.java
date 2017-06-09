package com.dlfc.zfgj.security.convertor.web;

import com.alibaba.fastjson.JSONObject;
import com.dlfc.zfgj.security.common.json.JSONService;
import com.dlfc.zfgj.security.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.security.dto.web.LoginDTO;
import com.dlfc.zfgj.security.entity.*;
import com.dlfc.zfgj.security.enums.AgtCertEnum;
import com.dlfc.zfgj.security.enums.AgtStatusEnum;
import com.dlfc.zfgj.security.enums.YesNoEnum;
import com.dlfc.zfgj.security.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/3/29.
 */

@Component
public class LoginConvertor extends AbstractConvertor<Object, LoginDTO> {

    private static final String HOUSE_EDITABLE = "emp:house:edit";
    private static final String HOUSE_DELETABLE = "emp:house:del";
    private static final String HOUSE_VIEWABLE = "emp:house:view";
    private static final String CONTRACT_EDITABLE = "emp:cont:edit";
    private static final String CONTRACT_DELETABLE = "emp:cont:del";
    private static final String CONTRACT_VIEWABLE = "emp:cont:view";
    private static final String PERSONAL = "8";

    @Autowired
    private AgtEmpInfoService agtEmpInfoService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private JSONService jsonService;
    @Autowired
    private UsrUserService usrUserService;
    @Autowired
    private SysPermissionScopeService sysPermissionScopeService;
    @Autowired
    private AgtUsrCompLogInfoService agtUsrCompLogInfoService;
    @Autowired
    @Qualifier("swAgtCertLinkService")
    private AgtCertLinkService agtCertLinkService;

    @Override
    public Object toModel(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public LoginDTO toDTO(Object model, boolean forListView) {
        LoginDTO loginDTO = new LoginDTO();
        if (null != model) {
            JSONObject jsonObject = jsonService.toJsonObject(model);
            String result = (String) jsonService.getOjbectFromJsonObject(jsonObject, "success");
            String message = (String) jsonService.getOjbectFromJsonObject(jsonObject, "message");
            loginDTO.setMessage(message);
            if ("0".equals(result)) {
                loginDTO.setResult(false);
            } else {
                loginDTO.setResult(true);
                String userName = (String) jsonService.getOjbectFromJsonObject(jsonObject, "data");
                String token = (String) jsonService.getOjbectFromJsonObject(jsonObject, "token");
                loginDTO.setToken(token);
                UsrUser user = usrUserService.getUserByUserName(userName);
                if (null != user) {
                    // true为不在职:刚注册经纪人或者离职经纪人
                    boolean inactive = false;
                    loginDTO.setUsername(user.getUsername());
                    List<AgtEmpInfo> agtEmpInfos = agtEmpInfoService.getAgtEmpInfoByUserId(user.getId());
                    if (null == agtEmpInfos || agtEmpInfos.size() == 0) {
                        // 1为经纪人不在职
                        loginDTO.setAgtOnJob(Integer.valueOf(AgtStatusEnum.INACTIVE.getValue()));
                        // 从业资格证
                        List<AgtCertLink> agtCertLinkList = agtCertLinkService.isEmpCerted(user.getId());
                        if (null != agtCertLinkList && agtCertLinkList.size() > 0) {
                            //提交过从业资格证
                            loginDTO.setAgtJoin(YesNoEnum.NO_ENUM.getValue());
                            //从业资格证审核的状态
                            String cert = agtCertLinkList.get(0).getIsCert().toString();
                            // 0从业资格证审核中
                            if (AgtCertEnum.APPROVING.getValue().equals(cert)) {
                                loginDTO.setAgtAuditStatus(Integer.valueOf(AgtCertEnum.APPROVING.getValue()));
                            } else if (AgtCertEnum.REFUSED.getValue().equals(cert)) {
                                // 2从业资格证拒绝
                                loginDTO.setAgtAuditStatus(Integer.valueOf(AgtCertEnum.REFUSED.getValue()));
                            } else {
                                // 1从业资格证审核通过
                                loginDTO.setAgtAuditStatus(Integer.valueOf(AgtCertEnum.ACTIVE.getValue()));
                            }
                        } else {
                            //未提交过
                            loginDTO.setAgtJoin(YesNoEnum.YES_ENUM.getValue());
                        }
                    } else {
                        AgtEmpInfo agtEmpInfo = agtEmpInfos.get(0);
                        if ((short) YesNoEnum.NO_ENUM.getValue() == agtEmpInfo.getStatus()) {
                            //0为在职
                            loginDTO.setAgtOnJob(Integer.valueOf(AgtStatusEnum.ACTIVE.getValue()));
                            // 角色定位
                            SysRole sysRole = sysRoleService.getSysRoleById(agtEmpInfo.getRoleCode());
                            if (null != sysRole && !PERSONAL.equals(sysRole.getDataScope())) {
                                // 权限定位
                                String dataScope = sysRole.getDataScope();
                                // 房屋可编辑权限
                                loginDTO.setHouseEditable(sysPermissionScopeService.scopeComparator(
                                        dataScope, sysRole.getId(), HOUSE_EDITABLE));
                                // 房屋可删除权限
                                loginDTO.setHouseDeletable(sysPermissionScopeService.scopeComparator(
                                        dataScope, sysRole.getId(), HOUSE_DELETABLE));
                                // 房屋可查看权限
                                loginDTO.setHouseViewable(sysPermissionScopeService.scopeComparator(
                                        dataScope, sysRole.getId(), HOUSE_VIEWABLE));
                                // 合同可编辑权限
                                loginDTO.setContractEditable(sysPermissionScopeService.scopeComparator(
                                        dataScope, sysRole.getId(), CONTRACT_EDITABLE));
                                // 合同可删除权限
                                loginDTO.setContractDeletable(sysPermissionScopeService.scopeComparator(
                                        dataScope, sysRole.getId(), CONTRACT_DELETABLE));
                                // 合同可查看权限
                                loginDTO.setContractViewable(sysPermissionScopeService.scopeComparator(
                                        dataScope, sysRole.getId(), CONTRACT_VIEWABLE));
                            }
                        } else {
                            //提交过从业资格证
                            loginDTO.setAgtJoin(YesNoEnum.NO_ENUM.getValue());
                            // 1从业资格证审核通过
                            loginDTO.setAgtAuditStatus(Integer.valueOf(AgtCertEnum.ACTIVE.getValue()));
                            inactive = true;
                        }
                    }
                    if (inactive) {
                        //查询经纪公司列表
                        AgtUsrCompLogInfo log = agtUsrCompLogInfoService.findAgtUsrCompLog(user.getId());
                        // 用户公司对象非空，审核状态=0（审核中）
                        if (null != log && Integer.valueOf(AgtCertEnum.APPROVING.getValue()) == log.getStatus()) {
                            // 2为经纪人加入公司审核中
                            loginDTO.setAgtOnJob(Integer.valueOf(AgtStatusEnum.APPROVING.getValue()));
                        } else {
                            // 1为经纪人不在职
                            loginDTO.setAgtOnJob(Integer.valueOf(AgtStatusEnum.INACTIVE.getValue()));
                            if (null != log && Integer.valueOf(AgtCertEnum.REFUSED.getValue()) == log.getStatus()) {
                                //加入公司被拒绝
                                loginDTO.setAgtOnJob(Integer.valueOf(AgtStatusEnum.REFUSED.getValue()));
                            }
                        }
                    }
                }
            }
        }
        return loginDTO;
    }
}
