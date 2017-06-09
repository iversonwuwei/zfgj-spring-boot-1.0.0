package com.dlfc.zfgj.convertor.web;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractStep4InitDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.ConHouseItems;
import com.dlfc.zfgj.entity.ConOtherCosts;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.service.web.ConHouseItemsService;
import com.dlfc.zfgj.service.web.ConOtherCostsService;
import com.dlfc.zfgj.service.web.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@Component
public class ContractStep4InitConvertor extends AbstractConvertor<ConContract, ContractStep4InitDTO> {
    // 签约当日甲方提供的证件原件
    private static final String LESSOR_ORIGI_LICENSE = "lessor_origi_license";
    // 签约当日乙方提供的证件原件
    private static final String LESSEE_ORIGI_LICENSE = "lessee_origi_license";

    @Autowired
    private SysCodeService sysCodeServiceImpl;
    @Autowired
    private SystemCodeConvertor systemCodeConvertor;
    @Autowired
    private HouseItemsConvertor houseItemsConvertor;
    @Autowired
    private ContractOtherCostConvertor contractOtherCostConvertor;
    @Autowired
    private ConHouseItemsService conHouseItemsService;
    @Autowired
    private ConOtherCostsService conOtherCostsService;

    @Override
    public ConContract toModel(ContractStep4InitDTO contractStep4InitDTO) {
        return null;
    }

    @Override
    public ContractStep4InitDTO toDTO(ConContract model, boolean forListView) {
        ContractStep4InitDTO contractStep4InitDTO = new ContractStep4InitDTO();
        if (null != model) {
            contractStep4InitDTO.setContractId(model.getId());
            // 签约当日甲方提供的证件原件列表
            List<SysCode> lessorLicenseList = sysCodeServiceImpl.getCodeByType(LESSOR_ORIGI_LICENSE);
            contractStep4InitDTO.setLessorLicenseList(systemCodeConvertor.toListDTO(lessorLicenseList));
            // 签约当日乙方提供的证件原件列表
            List<SysCode> lesseeLicenseList = sysCodeServiceImpl.getCodeByType(LESSEE_ORIGI_LICENSE);
            contractStep4InitDTO.setLesseeLicenseList(systemCodeConvertor.toListDTO(lesseeLicenseList));
            // 修改合同时，默认信息为上次填写信息
            if (StringUtils.isNotEmpty(model.getLessorIds())) {
                contractStep4InitDTO.setLessorPapers(
                        sysCodeServiceImpl.checkOldSysCodes(model.getLessorIds().split(","), lessorLicenseList));
            }
            contractStep4InitDTO.setOtherLessorPapers(model.getLessorIdsOther());
            if (StringUtils.isNotEmpty(model.getLesseeIds())) {
                contractStep4InitDTO.setTenantryPapers(
                        sysCodeServiceImpl.checkOldSysCodes(model.getLesseeIds().split(","), lesseeLicenseList));
            }
            contractStep4InitDTO.setOtherTenantryPapers(model.getLesseeIdsOther());
            contractStep4InitDTO.setOthers(model.getAdditionalTerms());
            List<ConHouseItems> houItemsList = conHouseItemsService.getConHouseItemsByCid(model.getId());
            if (null != houItemsList) {
                contractStep4InitDTO.setDeliverItems(houseItemsConvertor.toListDTO(houItemsList));
            }
            List<ConOtherCosts> conOtherCostsList = conOtherCostsService.getConOtherCostsByCid(model.getId());
            if (null != conOtherCostsList) {
                contractStep4InitDTO.setOtherRelatedItems(
                        contractOtherCostConvertor.toListDTO(conOtherCostsList));
            }
            // 合同版本号（验证用）
            contractStep4InitDTO.setVersion(model.getVersion());
        }
        return contractStep4InitDTO;
    }
}
