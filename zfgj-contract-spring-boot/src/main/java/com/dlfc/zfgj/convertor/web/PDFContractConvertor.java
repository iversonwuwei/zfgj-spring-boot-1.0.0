package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.NumberToCN;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.common.pdf.utils.ResourceLoader;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractDownloadInitDTO;
import com.dlfc.zfgj.dto.web.HouseOwnerDTO;
import com.dlfc.zfgj.dto.web.PDFConContractDTO;
import com.dlfc.zfgj.dto.web.SettlementCycleDTO;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.*;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.SysPersonMapper;
import com.dlfc.zfgj.service.web.ConHouseUserService;
import com.dlfc.zfgj.service.web.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/3/25.
 */

@Component
public class PDFContractConvertor extends AbstractConvertor<ContractDownloadInitDTO, PDFConContractDTO> {

    // 房主承担其它费用的编号
    public static final int OWNER_BEAR_OTHER_FLAG = 11;
    // 民用=>住宅
    public static final String ZHUZHAI = "住宅";
    // 签约当日甲方提供的证件原件
    private static final String LESSOR_ORIGI_LICENSE = "lessor_origi_license";
    // 签约当日乙方提供的证件原件
    private static final String LESSEE_ORIGI_LICENSE = "lessee_origi_license";
    // 分隔符：","
    public static final String SEPARATOR = ",";
    // 房主承担
    public static final String LESSOR_BEAR = "lessor_bear";
    // 证件类型
    private static final String PER_ID_TYPE = "per_id_type";

    @Autowired
    private ConHouseUserService conHouseUserServiceImpl;
    @Autowired
    private SysPersonMapper sysPersonMapper;
    @Autowired
    private AgtEmpInfoMapper agtEmpInfoMapper;
    @Autowired
    private AgtCompInfoMapper agtCompInfoMapper;
    @Autowired
    private SysCodeService sysCodeService;
    @Autowired
    private HouseUserConvertor houseUserConvertor;

    @Override
    public ContractDownloadInitDTO toModel(PDFConContractDTO pdfConContractDTO) {
        return null;
    }

    @Override
    public PDFConContractDTO toDTO(ContractDownloadInitDTO model, boolean forListView) {
        ConContract conContract = model.getConContract();
        String serverPath = model.getServerPath();
//        List<HouCoOwnerTemp> houCoOwnerList =
//                houCoOwnerTempService.getHouCoOwnersByHid(model.getConContract().getHid());
//        List<SysPerson> sysPersonList = new ArrayList<>();
//        SysPerson sysPerson;
//        for (HouCoOwnerTemp houCoOwnerTemp : houCoOwnerList) {
//            sysPerson = sysPersonService.getSyspersonsById(houCoOwnerTemp.getCoPid());
//            if (null != sysPerson) ;
//            sysPersonList.add(sysPerson);
//        }
        List<SettlementCycleDTO> settlementCycleList = model.getSettlementCycleList();
        PDFConContractDTO pdfConContractDTO = new PDFConContractDTO();
        //设置pdf合同属性值
        pdfConContractDTO.setNo(conContract.getNo());
        pdfConContractDTO.setLessorName(conContract.getLessorName());
        if (null != conContract.getLessorIdType()) {
            pdfConContractDTO.setLessorIdType(
                    sysCodeService.getNameByType(conContract.getLessorIdType().toString(), PER_ID_TYPE));
        }
        pdfConContractDTO.setLessorIdNo(conContract.getLessorIdNo());
        pdfConContractDTO.setLessorAddress(conContract.getLessorAddress());
        pdfConContractDTO.setLessorMobile(conContract.getLessorMobile());
        pdfConContractDTO.setLessorEmail(conContract.getLessorEmail());
        pdfConContractDTO.setLessorBakName(conContract.getLessorBackupName());
        pdfConContractDTO.setLessorBakMobile(conContract.getLessorBackupTel());
        pdfConContractDTO.setLesseeName(conContract.getLesseeName());
        if (null != conContract.getLesseeIdType()) {
            pdfConContractDTO.setLesseeIdType(
                    sysCodeService.getNameByType(conContract.getLesseeIdType().toString(), PER_ID_TYPE));
        }
        pdfConContractDTO.setLesseeIdNo(conContract.getLesseeIdNo());
        pdfConContractDTO.setLesseeAddress(conContract.getLesseeAddress());
        pdfConContractDTO.setLesseeMobile(conContract.getLesseeMobile());
        pdfConContractDTO.setLesseeEmail(conContract.getLesseeEmail());
        pdfConContractDTO.setLesseeBakName(conContract.getLesseeBackupName());
        pdfConContractDTO.setLesseeBakMobile(conContract.getLesseeBackupTel());
        AgtEmpInfo agtEmpInfo;
        AgtCompInfo agtCompInfo = null;
        if (StringUtils.isNotEmpty(conContract.getEid())) {
            agtEmpInfo = agtEmpInfoMapper.selectByPrimaryKey(conContract.getEid());
            if (null != agtEmpInfo) {
                agtCompInfo = agtCompInfoMapper.selectByPrimaryKey(agtEmpInfo.getCompanyId());
                if (null != agtCompInfo) {
                    pdfConContractDTO.setCompanyName(agtCompInfo.getFullName());
                    pdfConContractDTO.setCompanyAddress(agtCompInfo.getAddress());
                    pdfConContractDTO.setCompanyTel(agtCompInfo.getTel());
                }
                SysPerson sysPerson = sysPersonMapper.selectByPrimaryKey(agtEmpInfo.getPid());
                if (null != sysPerson) {
                    pdfConContractDTO.setManageName(sysPerson.getName());
                    pdfConContractDTO.setManageIdNo(sysPerson.getIdNo());
                }
                pdfConContractDTO.setManageMobile(agtEmpInfo.getPhone());
            }
        }

        pdfConContractDTO.setHouseAddr(conContract.getHouseAddr());
        Integer propertyType = conContract.getPropertyType();
        if (null != propertyType) {
            pdfConContractDTO.setProperType(PropertyIdTypeEnum.getName(propertyType));
        }
        pdfConContractDTO.setHouseBuildingArea(conContract.getBuildingArea());
        pdfConContractDTO.setLeaseArea(conContract.getLeaseArea());
        if (null != conContract.getLeasePurpose()) {
//            pdfConContractDTO.setLeasePurpose(RentalPurposeEnum.getName(conContract.getLeasePurpose()));
            pdfConContractDTO.setLeasePurpose(ZHUZHAI);
        }
        if (null != conContract.getHouseStructure()) {
            pdfConContractDTO.setHouseType(StructureEnum.getName(conContract.getHouseStructure()));
        }
        pdfConContractDTO.setHousePropertyIdNo(conContract.getPropertyIdNo());

        HouseOwnerDTO houseOwnerDTO = new HouseOwnerDTO();
        houseOwnerDTO.setName(conContract.getHouseOwnerName());
        houseOwnerDTO.setIdNo(conContract.getHouseOwnerIdNo());
        if (null != conContract.getHouseOwnerIdType()) {
            houseOwnerDTO.setIdType(
                    sysCodeService.getNameByType(conContract.getHouseOwnerIdType().toString(), PER_ID_TYPE));
        }
        houseOwnerDTO.setPhone(conContract.getHouseOwnerMobile());
        List<HouseOwnerDTO> houseOwnerDTOList = new ArrayList<>();
        houseOwnerDTOList.add(houseOwnerDTO);
//        pdfConContractDTO.setHouCoOwnerList(houseOwnerConvertor.toListDTO(sysPersonList));
        pdfConContractDTO.setHouCoOwnerList(houseOwnerDTOList);
        List<ConHouseUser> conHouseUserList = conHouseUserServiceImpl.getHouseUserByCId(conContract.getId());
        if (null != conHouseUserList) {
            pdfConContractDTO.setConHouseUserList(houseUserConvertor.toListDTO(conHouseUserList));
        }
        pdfConContractDTO.setLeaseTermYear(conContract.getLeaseTermYear());
        pdfConContractDTO.setLeaseTermMonth(conContract.getLeaseTermMonth());
        pdfConContractDTO.setYear(DateUtils.dateToStr(conContract.getStartTime(), "yyyy"));
        pdfConContractDTO.setMonth(DateUtils.dateToStr(conContract.getStartTime(), "MM"));
        pdfConContractDTO.setDay(DateUtils.dateToStr(conContract.getStartTime(), "dd"));
        pdfConContractDTO.setEndYear(DateUtils.dateToStr(conContract.getEndTime(), "yyyy"));
        pdfConContractDTO.setEndMonth(DateUtils.dateToStr(conContract.getEndTime(), "MM"));
        pdfConContractDTO.setEndDay(DateUtils.dateToStr(conContract.getEndTime(), "dd"));
        pdfConContractDTO.setMonthlyRent(conContract.getMonthlyRent());
        pdfConContractDTO.setMonthlyRentCN(
                NumberToCN.number2CNMontrayUnit(new BigDecimal(conContract.getMonthlyRent())));
        if (null != conContract.getSettlementCycle()) {
            pdfConContractDTO.setSettlementCycle(SettlementCycleEnum.getName(conContract.getSettlementCycle()));
        }
        pdfConContractDTO.setOwnerBear(conContract.getOwnerBear());

        if (StringUtils.isNotEmpty(conContract.getOwnerBearOther())) {
            if (StringUtils.isNotEmpty(conContract.getOwnerBear())) {
                pdfConContractDTO.setOwnerBear(pdfConContractDTO.getOwnerBear() + SEPARATOR + OWNER_BEAR_OTHER_FLAG);
            } else {
                pdfConContractDTO.setOwnerBear(String.valueOf(OWNER_BEAR_OTHER_FLAG));
            }
        }

        pdfConContractDTO.setOwnerBearOther(conContract.getOwnerBearOther());
        pdfConContractDTO.setLesseeBear(this.leaseBear(conContract.getOwnerBear()));
        pdfConContractDTO.setDepositAmt(conContract.getDepositAmt());
        pdfConContractDTO.setDepositAmtCN(NumberToCN.number2CNMontrayUnit(conContract.getDepositAmt()));
        pdfConContractDTO.setAgencyFee(conContract.getAgencyFee());
        pdfConContractDTO.setAgencyFeeCN(NumberToCN.number2CNMontrayUnit(conContract.getAgencyFee()));
        pdfConContractDTO.setLessorIds(
                this.getName(conContract.getLessorIds(), sysCodeService.getCodeByType(LESSOR_ORIGI_LICENSE)));
        pdfConContractDTO.setLessorIdsOther(conContract.getLessorIdsOther());
        pdfConContractDTO.setLesseeIds(
                this.getName(conContract.getLesseeIds(), sysCodeService.getCodeByType(LESSEE_ORIGI_LICENSE)));
        pdfConContractDTO.setLesseeIdsOther(conContract.getLesseeIdsOther());
        pdfConContractDTO.setSettlementCycleList(settlementCycleList);
        pdfConContractDTO.setImagePath(ResourceLoader.getPath("templates/pdfTemplates/images"));
        pdfConContractDTO.setAdditionalTerms(conContract.getAdditionalTerms());
        if (null != agtCompInfo
                && String.valueOf(YesNoEnum.YES_ENUM.getValue()).equals(agtCompInfo.getEchapterEnabled())) {
            pdfConContractDTO.setImageUrl(serverPath + agtCompInfo.getEchapterPath());
        }
        return pdfConContractDTO;
    }

    /**
     * 获取承租方承担的费用
     *
     * @param lessorBear
     * @returnd
     */
    private String leaseBear(String lessorBear) {
        String lesseeBear = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(lessorBear)) {
            String[] lessorBearArray = lessorBear.split(SEPARATOR);
            List<SysCode> bears = sysCodeService.getCodeByType(LESSOR_BEAR);
            if (bears != null) {
                for (SysCode code : bears) {
                    boolean flg = true;
                    if (lessorBear != null) {
                        for (String bear : lessorBearArray) {
                            if (code.getCode().equals(bear)) {
                                flg = false;
                                break;
                            }
                        }
                    }
                    if (flg) {
                        lesseeBear = lesseeBear + SEPARATOR + code.getCode();
                    }
                }
            }
            if (lesseeBear.startsWith(SEPARATOR)) {
                lesseeBear = lesseeBear.substring(1);
            }
        }
        return lesseeBear;
    }

    /**
     * 根据code值获取标签名称
     *
     * @param codeList
     * @param feature  code字符串
     * @return 标签名字
     */
    private String getName(String feature, List<SysCode> codeList) {
        // 标签名字
        String featureName = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(feature)) {
            // 获取code字符数组
            String[] featureArry = sysCodeService.checkOldSysCodes(feature.split(SEPARATOR), codeList);
            for (String f : featureArry) {
                for (SysCode code : codeList) {
                    if (StringUtils.isNotEmpty(f) && f.equals(code.getCode())) {
                        featureName = featureName + StringUtils.SPACE + code.getName();
                    }
                }
            }
        }
        return featureName;
    }
}
