package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.AgtEmpDTO;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.*;
import com.dlfc.zfgj.service.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wsz on 2017/3/6.
 */
@Component
public class AgtConvertor extends AbstractConvertor<AgtEmpInfo, AgtEmpDTO> {

    @Autowired
    @Qualifier("aServiceImpl")
    private AgtService agtService;
    @Autowired
    @Qualifier("sysInfoServiceImpl")
    private SysInfoAttrService sysInfoAttService;
    @Autowired
    private SystemPersonService systemPersonService;
    @Autowired
    private SysOfficeService sysOfficeService;
    @Autowired
    @Qualifier("hServiceImpl")
    private HouseService houseService;
    @Autowired
    @Qualifier("hwConContractService")
    private ConContractService conContractService;
    @Autowired
    @Qualifier("hwAgtCusInfoService")
    private AgtCusInfoService agtCusInfoService;
    @Autowired
    @Qualifier("hwAgtCertLinkService")
    private AgtCertLinkService agtCertLinkService;
    @Autowired
    @Qualifier("us111333")
    private UserHouseService userHouseService;


    @Override
    public AgtEmpInfo toModel(AgtEmpDTO agtEmpDTO) {
        return null;
    }

    /**
     * 实体类转换前台DTO
     *
     * @param model 数据Model
     * @return
     */

    public AgtEmpDTO toDTO(AgtEmpInfo model) {
        AgtEmpDTO agtEmpDTO = new AgtEmpDTO();
        String userId = model.getUserId();
        UsrUser user = userHouseService.getUserById(userId);
        SysPerson sysPerson = systemPersonService.getSysPersonById(user.getPerId());
        if (null != sysPerson) {
            agtEmpDTO.setAgtName(sysPerson.getName());
        }
        // true为不在职:刚注册经纪人或者离职经纪人
        boolean inactive = false;
        // 0为在职，1为不在职 ，2为经纪人加入公司审核中，3为拒绝
        if (StringUtils.isNotEmpty(model.getId())) {
            agtEmpDTO.setPhone(model.getPhone());
            String eid = model.getId();
            //经纪人头像
            SysInfoAtt sysInfoAtt = sysInfoAttService.selectByLidAndType(
                    eid, InfoAttFileTypeEnum.EMP_AVATAR_ENUM.getValue());
            if (sysInfoAtt != null) {
                agtEmpDTO.setAgtPicture(sysInfoAtt.getFilePath());
            } else {
                agtEmpDTO.setAgtPicture(PropertyUtils.getSysVal("defaultLogo"));
            }
            //经纪人二维码
            SysInfoAtt sysErWeiMa = sysInfoAttService.selectByLidAndType(
                    eid, InfoAttFileTypeEnum.EMP_ERWEIMA_ENUM.getValue());
            if (sysErWeiMa != null) {
                agtEmpDTO.setAgtBarcode(sysErWeiMa.getFilePath());
            } else {
                agtEmpDTO.setAgtBarcode(PropertyUtils.getSysVal("defaultLogo"));
            }
            int actHouse = houseService.actHouse(eid);
//            int conHouse = agtService.conHouse(model.getId());
            int wHouse = houseService.countHouByStatus(eid, AuditStatusEnum.UNAUDITED_ENUM.getValue());
            int unAuHouse = houseService.countHouByStatus(eid, AuditStatusEnum.REFUSE_ENUM.getValue());
            int cus = agtCusInfoService.cus(eid);
            int contract = conContractService.contract(model);
            int eContract = conContractService.eContract(model);
            agtEmpDTO.setActHouseCount(actHouse);
//            agtEmpDTO.setConHouseCount(conHouse);
            agtEmpDTO.setWHouseCount(wHouse);
            agtEmpDTO.setUnAuHouseCount(unAuHouse);
            agtEmpDTO.setCusCount(cus);
            agtEmpDTO.setContractCount(contract);
            agtEmpDTO.setEContractCount(eContract);
            if ((short) YesNoEnum.NO_ENUM.getValue() == model.getStatus()) {
                //0为在职
                agtEmpDTO.setAgtOnJob(Integer.valueOf(AgtStatusEnum.ACTIVE.getValue()));
                AgtCompInfo agtCompInfo = agtService.getCompInfoById(model.getCompanyId());
                if (null != agtCompInfo) {
                    agtEmpDTO.setCompName(agtCompInfo.getName());
                    //公司图片
                    SysInfoAtt sysInfoAttComp = sysInfoAttService.findById(agtCompInfo.getImg1Id());
                    if (null != sysInfoAttComp) {
                        agtEmpDTO.setCompPath(sysInfoAttComp.getFilePath());
                    } else {
                        agtEmpDTO.setCompPath("/img" + PropertyUtils.getSysVal("defaultLogo"));
                    }
                }
                SysOffice sysOffice = sysOfficeService.getSysOfficeById(model.getOfficeId());
                if (null != sysOffice) {
                    agtEmpDTO.setOfficeName(sysOffice.getName());
                }
            } else {
                //提交过从业资格证
                agtEmpDTO.setAgtJoin(YesNoEnum.NO_ENUM.getValue());
                // 1从业资格证审核通过
                agtEmpDTO.setAgtAuditStatus(Integer.valueOf(AgtCertEnum.ACTIVE.getValue()));
                inactive = true;
            }
        } else {
            inactive = true;
            // 从业资格证
            List<AgtCertLink> agtCertLinkList = agtCertLinkService.isEmpCerted(userId);
            if (null != agtCertLinkList && agtCertLinkList.size() > 0) {
                //提交过从业资格证
                agtEmpDTO.setAgtJoin(YesNoEnum.NO_ENUM.getValue());
                //从业资格证审核的状态
                String cert = agtCertLinkList.get(0).getIsCert().toString();
                // 0从业资格证审核中
                if (AgtCertEnum.APPROVING.getValue().equals(cert)) {
                    agtEmpDTO.setAgtAuditStatus(Integer.valueOf(AgtCertEnum.APPROVING.getValue()));
                } else if (AgtCertEnum.REFUSED.getValue().equals(cert)) {
                    // 2从业资格证拒绝
                    agtEmpDTO.setAgtAuditStatus(Integer.valueOf(AgtCertEnum.REFUSED.getValue()));
                } else {
                    // 1从业资格证审核通过
                    agtEmpDTO.setAgtAuditStatus(Integer.valueOf(AgtCertEnum.ACTIVE.getValue()));
                }
            } else {
                //未提交过
                agtEmpDTO.setAgtJoin(YesNoEnum.YES_ENUM.getValue());
            }
            agtEmpDTO.setAgtPicture(PropertyUtils.getSysVal("defaultLogo"));
            agtEmpDTO.setAgtBarcode(PropertyUtils.getSysVal("defaultLogo"));
        }
        if (inactive) {
            //查询经纪公司列表
            AgtUsrCompLogInfo log = agtService.findAgtUsrCompLog(userId);
            // 用户公司对象非空，审核状态=0（审核中）
            if (null != log && Integer.valueOf(AgtCertEnum.APPROVING.getValue()) == log.getStatus()) {
                // 2为经纪人加入公司审核中
                agtEmpDTO.setAgtOnJob(Integer.valueOf(AgtStatusEnum.APPROVING.getValue()));
                // 加入公司的ID
                agtEmpDTO.setCompId(log.getId());
                AgtCompInfo agtCompInfo = agtService.getCompInfoById(log.getId());
                if (null != agtCompInfo) {
                    agtEmpDTO.setCompName(agtCompInfo.getFullName());
                }
            } else {
                // 1为经纪人不在职
                agtEmpDTO.setAgtOnJob(Integer.valueOf(AgtStatusEnum.INACTIVE.getValue()));
                if (null != log && Integer.valueOf(AgtCertEnum.REFUSED.getValue()) == log.getStatus()) {
                    //加入公司被拒绝
                    agtEmpDTO.setAgtOnJob(Integer.valueOf(AgtStatusEnum.REFUSED.getValue()));
                    //拒绝原因
                    agtEmpDTO.setRejectReason(log.getRejectReason());
                }
            }
        }
        return agtEmpDTO;
    }

    @Override
    public AgtEmpDTO toDTO(AgtEmpInfo model, boolean forListView) {
        return null;
    }

}
