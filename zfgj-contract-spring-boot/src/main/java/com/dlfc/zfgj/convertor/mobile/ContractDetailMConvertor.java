package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.MD5;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.ContractDetailMDTO;
import com.dlfc.zfgj.dto.moblie.HouseOwnerMDTO;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.*;
import com.dlfc.zfgj.mapper.ConHouseUserMapper;
import com.dlfc.zfgj.service.mobile.*;
import com.dlfc.zfgj.service.web.ContractStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangna on 2017/6/9.
 */
@Component
public class ContractDetailMConvertor extends AbstractConvertor<ConContract,ContractDetailMDTO>{

    // 房主承担
    private static final String LESSOR_BEAR = "lessor_bear";
    // 签约当日甲方提供的证件原件
    private static final String LESSOR_ORIGI_LICENSE = "lessor_origi_license";
    // 签约当日乙方提供的证件原件
    private static final String LESSEE_ORIGI_LICENSE = "lessee_origi_license";
    // 证件类型
    private static final String PER_ID_TYPE = "per_id_type";

    @Autowired
    private SysCodeMService sysCodeMService;
    @Autowired
    private ConHouseUserMapper conHouseUserMapper;
    @Autowired
    private HouseUserMConvertor houseUserMConvertor;
    @Autowired
    private ConHouseItemsMService conHouseItemsMService;
    @Autowired
    private HouseItemsMConvertor houseItemsMConvertor;
    @Autowired
    private ContractOtherCostMConvertor contractOtherCostMConvertor;
    @Autowired
    private ConOtherCostsMService conOtherCostsMService;
    @Autowired
    private SysInfoAttMService sysInfoAttMService;
    @Autowired
    private SystemAttachmentMConvertor systemAttachmentMConvertor;
    @Autowired
    private AgtEmpInfoMService agtEmpInfoMService;
    @Autowired
    private SysPersonMService sysPersonMService;
    @Autowired
    private ContractStepService contractStepMService;
    @Autowired
    private ConHouseUserChildrenMService conHouseUserChildrenMService;
    @Autowired
    private ConHouseUserChildrenMConvertor conHouseUserChildrenMConvertor;

    @Override
    public ConContract toModel(ContractDetailMDTO contractDetailMDTO) {
        return null;
    }

    @Override
    public ContractDetailMDTO toDTO(ConContract model, boolean forListView) {
        ContractDetailMDTO dto = new ContractDetailMDTO();
        if (null != model) {
            dto.setContractId(model.getId());
            // 房屋信息
            dto.setHouseAddress(model.getHouseAddr());
            if (null != model.getRentalMode()) {
                dto.setRentalMode(
                        RentalModeEnum.getName(model.getRentalMode().intValue()));
            }
            if (null != model.getPropertyType()) {
                dto.setHousePropertyIdType(
                        PropertyIdTypeEnum.getName(model.getPropertyType()));
            }
            if (null != model.getLeasePurpose()) {
                dto.setLeaseUse(
                        RentalPurposeEnum.getName(model.getLeasePurpose().intValue()));
            }
            dto.setHouseType(model.getHouseType());
            if (null != model.getBuildingArea()) {
                dto.setHouseArea(model.getBuildingArea().doubleValue());
            }
            dto.setLeaseDomain(model.getLeaseDomain());
            dto.setLeaseArea(model.getLeaseArea());
            // 钱
            Integer monthlyRent = model.getMonthlyRent();
            dto.setMonthlyRent(monthlyRent);
            dto.setAgentFee(model.getAgencyFee());
            dto.setDeposit(model.getDepositAmt());
            // 出租方
            dto.setLessorName(model.getLessorName());
            if (null != model.getLessorIdType()) {
                dto.setLessorIdType(
                        sysCodeMService.getNameByType(model.getLessorIdType().toString(), PER_ID_TYPE));
            }
            dto.setLessorId(model.getLessorIdNo());
            dto.setLessorPhoneNumber(model.getLessorMobile());
            dto.setLessorDeliverAddress(model.getLessorAddress());
            dto.setLessorEmail(model.getLessorEmail());
            dto.setLessorContactName(model.getLessorBackupName());
            dto.setLessorContactPhoneNumber(model.getLessorBackupTel());
            // 承租方
            dto.setTenantryName(model.getLesseeName());
            if (null != model.getLesseeIdType()) {
                dto.setTenantryIdType(
                        sysCodeMService.getNameByType(model.getLesseeIdType().toString(), PER_ID_TYPE));
            }
            dto.setTenantryId(model.getLesseeIdNo());
            dto.setTenantryPhoneNumber(model.getLesseeMobile());
            dto.setTenantryDeliverAddress(model.getLesseeAddress());
            dto.setTenantryEmail(model.getLesseeEmail());
            dto.setTenantryContactName(model.getLesseeBackupName());
            dto.setTenantryContactPhoneNumber(model.getLesseeBackupTel());
            // 房屋共有人
//            List<SysPerson> sysPersonList = new ArrayList<>();
//            SysPerson sysPerson;
//            List<HouCoOwnerTemp> houCoOwnerList = houCoOwnerTempService.getHouCoOwnersByHid(model.getHid());
//            if (null != houCoOwnerList) {
//                for (HouCoOwnerTemp houCoOwner : houCoOwnerList) {
//                    sysPerson = sysPersonService.getSyspersonsById(houCoOwner.getCoPid());
//                    if (null != sysPerson) {
//                        sysPersonList.add(sysPerson);
//                    }
//                }
//            }
//            dto.setHouseOwnerDTOList(houseOwnerConvertor.toListDTO(sysPersonList));
            HouseOwnerMDTO houseOwnerDTO = new HouseOwnerMDTO();
            houseOwnerDTO.setName(model.getHouseOwnerName());
            houseOwnerDTO.setIdNo(model.getHouseOwnerIdNo());
            if (null != model.getHouseOwnerIdType()) {
               // String idType = sysCodeMService.getNameByType(model.getHouseOwnerIdType().toString(), PER_ID_TYPE);
                houseOwnerDTO.setIdType(model.getHouseOwnerIdType());
            }
            houseOwnerDTO.setPhone(model.getHouseOwnerMobile());
            List<HouseOwnerMDTO> houseOwnerDTOList = new ArrayList<>();
            houseOwnerDTOList.add(houseOwnerDTO);
            dto.setHouseOwnerDTOList(houseOwnerDTOList);
            // 实际使用人列表
            ConHouseUserExample conHouseUserExample = new ConHouseUserExample();
            ConHouseUserExample.Criteria conHouseUserCriteria = conHouseUserExample.createCriteria();
            conHouseUserCriteria.andCidEqualTo(model.getId());
            conHouseUserCriteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
            List<ConHouseUser> conHouseUserList = conHouseUserMapper.selectByExample(conHouseUserExample);
            if (null != conHouseUserList) {
                dto.setHouUserList(houseUserMConvertor.toListDTO(conHouseUserList));
            }
            // 租赁期限
            Date startTime = model.getStartTime();
            Date endTime = model.getEndTime();
            dto.setContractStartDate(startTime);
            dto.setContractEndDate(endTime);
            Integer years = model.getLeaseTermYear();
            Integer months = model.getLeaseTermMonth();
            Integer totalMonths = null;
            if (null != years && null != months) {
                totalMonths = years * 12 + months;
                dto.setTotalMonths(totalMonths.toString());
            }
            // 租金
            Integer settlementCycle = model.getSettlementCycle();
            if (null != settlementCycle) {
                dto.setSettlement(SettlementCycleEnum.getName(settlementCycle));
            }
            if (StringUtils.isNotEmpty(model.getStages())) {
                dto.setStages(Integer.valueOf(model.getStages()));
            }
            // 费用
            StringBuffer sbForOwnerBears = new StringBuffer();
            StringBuffer sbForUserBears = new StringBuffer();
            // >房主承担列表
            List<SysCode> lessorBearList = sysCodeMService.getCodeByType(LESSOR_BEAR);
            // >>表里房主承担项目不为空时 各自承担
            if (StringUtils.isNotEmpty(model.getOwnerBear())) {
                String[] ownerBears = model.getOwnerBear().split(",");
                // >>>单次循环中房主承担项目是否存在
                boolean ownerBearExitFlg;
                for (SysCode sysCode : lessorBearList) {
                    // >>>>默认为不存在
                    ownerBearExitFlg = false;
                    for (String str : ownerBears) {
                        if (sysCode.getCode().equals(str)) {
                            // >>>>存在时 该次循环的项目由房客承担 Flg改变
                            sbForOwnerBears.append(sysCode.getName());
                            sbForOwnerBears.append("、");
                            ownerBearExitFlg = true;
                            break;
                        }
                    }
                    // >>>>不存在时 该次循环的项目由房客承担
                    if (!ownerBearExitFlg) {
                        sbForUserBears.append(sysCode.getName());
                        sbForUserBears.append("、");
                    }
                }
            }
            // >>表里房主承担项目为空时 承担项目全部由房客承担
            else {
                for (SysCode sysCode : lessorBearList) {
                    sbForUserBears.append(sysCode.getName());
                    sbForUserBears.append("、");
                }
            }
            // >>表里房主承担其他项目（手动填入）不为空时
            String ownerBearOther = model.getOwnerBearOther();
            if (StringUtils.isNotEmpty(ownerBearOther)) {
                sbForOwnerBears.append(ownerBearOther.replace(StringUtils.SPACE, "、").replace(",", "、"));
                sbForOwnerBears.append("、");
            }
            dto.setHouseOwnerBearTotal(trimEnd(sbForOwnerBears));
            dto.setHouseUserBearTotal(trimEnd(sbForUserBears));
            // 押金
            if (null != model.getPaymentMethod()) {
                dto.setDepositSuperviseFlg(1 == model.getPaymentMethod());
            }
            // 甲乙丙三方确认的附加条款
            StringBuffer stringBuffer = new StringBuffer();
            String lessorIds = getStrFromListIfExist(
                    sysCodeMService.getCodeByType(LESSOR_ORIGI_LICENSE), model.getLessorIds());
            String lessorIdsOther = model.getLessorIdsOther();
            if (StringUtils.isNotEmpty(lessorIds)) {
                stringBuffer.append(lessorIds);
                if (StringUtils.isNotEmpty(lessorIdsOther)) {
                    stringBuffer.append("、");
                    stringBuffer.append(lessorIdsOther);
                }
            }
            dto.setLessorPaperTotal(stringBuffer.toString());
            stringBuffer = new StringBuffer();
            String lesseeIds = getStrFromListIfExist(
                    sysCodeMService.getCodeByType(LESSEE_ORIGI_LICENSE), model.getLesseeIds());
            String lesseeIdsOther = model.getLesseeIdsOther();
            if (StringUtils.isNotEmpty(lesseeIds)) {
                stringBuffer.append(lesseeIds);
                if (StringUtils.isNotEmpty(lesseeIdsOther)) {
                    stringBuffer.append("，");
                    stringBuffer.append(lesseeIdsOther);
                }
            }
            dto.setLesseePaperTotal(stringBuffer.toString());
            // 甲乙丙三方其他约定
            dto.setOthers(model.getAdditionalTerms());
            // 房屋交割清单
            List<ConHouseItems> houItemsList = conHouseItemsMService.getConHouseItemsByCid(model.getId());
            if (null != houItemsList) {
                dto.setDeliverItems(houseItemsMConvertor.toListDTO(houItemsList));
            }
            // 其他相关费用
            List<ConOtherCosts> conOtherCostsList = conOtherCostsMService.getConOtherCostsByCid(model.getId());
            if (null != conOtherCostsList) {
                dto.setOtherRelatedItems(contractOtherCostMConvertor.toListDTO(conOtherCostsList));
            }
            //随住子女
            List<ConHouseUserChildren> conHouseUserChildrens = conHouseUserChildrenMService.getByCid(model.getId());
            if (null != conHouseUserChildrens){
                dto.setChildrenMDTOS(conHouseUserChildrenMConvertor.toListDTO(conHouseUserChildrens));
            }
            // 附件列表
            List<SysInfoAtt> sysInfoAttList = sysInfoAttMService.findList(model.getId(),
                    InfoAttFileTypeEnum.ONE_SIDE_ENUM.getValue(),
                    InfoAttFileTypeEnum.OTHER_SIDE_ENUM.getValue(),
                    InfoAttFileTypeEnum.SECURITY_BOOK_ENUM.getValue());
            if (null != sysInfoAttList) {
                dto.setSystemAttachmentDTOList(systemAttachmentMConvertor.toListDTO(sysInfoAttList));
            }
            // 程序读取加密（防盗链配置的图片方式）
            stringBuffer = new StringBuffer();
            stringBuffer.append(MD5.GetMD5Code("dlfc"));
            stringBuffer.append(DateUtils.getDate("HH:mm"));
            dto.setAccessKey(stringBuffer.toString());
            // 居间方
            dto.setAgtCompName(model.getAgtComName());
            AgtEmpInfo agtEmpInfo = agtEmpInfoMService.getAgtEmpInfoById(model.getEid());
            if (null != agtEmpInfo) {
                dto.setAgtPhone(agtEmpInfo.getPhone());
                SysPerson sysPerson = sysPersonMService.getSyspersonsById(agtEmpInfo.getPid());
                if (null != sysPerson) {
                    dto.setAgtName(sysPerson.getName());
                }
            }
            // 判断修改按钮是否显示（创建中）
            dto.setModifyButton(
                    ConStatusEnum.CREATING_ENUM.getValue() == model.getStatus());
            // 判断下载按钮是否显示（非 已生效）
            dto.setDownloadButton(
                    ConStatusEnum.ACTIVE_ENUM.getValue() != model.getStatus());
            // 判断上传按钮是否显示（等待上传或者审核未通过）
            dto.setUploadButton(
                    ConStatusEnum.WAIT_UPLOAD_ENUM.getValue() == model.getStatus()
                            || ConStatusEnum.APPROVE_REJECT_ENUM.getValue() == model.getStatus());
            //结算时间
            if (null != startTime
                    && null != endTime
                    && null != settlementCycle
                    && null != monthlyRent
                    && null != totalMonths) {
                dto.setPaymentExplanation(
                        contractStepMService.getPaymentExplanation(
                                startTime,
                                endTime,
                                settlementCycle,
                                monthlyRent,
                                totalMonths));
            }
            // 审核结果
            if (ConStatusEnum.APPROVE_REJECT_ENUM.getValue() == model.getStatus()) {
                dto.setExamineResult(Boolean.FALSE);
            }
            // 拒绝原因
            if (StringUtils.isNotEmpty(model.getRejectReason())) {
                dto.setRejectReason("审核失败：" + model.getRejectReason());
            }
        }
        return dto;
    }
    private String trimEnd(StringBuffer sb) {
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private String getStrFromListIfExist(List<SysCode> sysCodeList, String originStr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isNotEmpty(originStr)) {
            String[] array = originStr.split(",");
            for (SysCode sysCode : sysCodeList) {
                for (String str : array) {
                    if (sysCode.getCode().equals(str)) {
                        stringBuffer.append(sysCode.getName());
                        stringBuffer.append(StringUtils.SPACE);
                        break;
                    }
                }
            }
            if (stringBuffer.length() > 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
        }
        return stringBuffer.toString();
    }
}
