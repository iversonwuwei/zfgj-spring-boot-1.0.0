package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractStep1InitDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.service.web.SysAreaAreasService;
import com.dlfc.zfgj.service.web.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/3/21.
 */

@Component
public class ContractStep1InitConvertor extends AbstractConvertor<ConContract, ContractStep1InitDTO> {

    private static final String NATION = "nation";

    @Autowired
    private SysAreaAreasService sysAreaAreasServiceImpl;
    @Autowired
    private SysCodeService sysCodeService;
    @Autowired
    private SystemCodeConvertor systemCodeConvertor;

    @Override
    public ConContract toModel(ContractStep1InitDTO contractStep1InitDTO) {
        return null;
    }

    @Override
    public ContractStep1InitDTO toDTO(ConContract model, boolean forListView) {
        ContractStep1InitDTO contractStep1InitDTO = new ContractStep1InitDTO();
        if (null != model) {
            contractStep1InitDTO.setContractId(model.getId());
            // 出租方
            contractStep1InitDTO.setLessorName(model.getLessorName());
            Integer lessorIdType = model.getLessorIdType();
            if (null != lessorIdType) {
                contractStep1InitDTO.setLessorIdType(lessorIdType.toString());
            }
            contractStep1InitDTO.setLessorId(model.getLessorIdNo());
            contractStep1InitDTO.setLessorPhoneNumber(model.getLessorMobile());
            contractStep1InitDTO.setLessorDeliverAddress(model.getLessorAddress());
            contractStep1InitDTO.setLessorEmail(model.getLessorEmail());
            contractStep1InitDTO.setLessorContactName(model.getLessorBackupName());
            contractStep1InitDTO.setLessorContactPhoneNumber(model.getLessorBackupTel());
            // 承租方
            contractStep1InitDTO.setTenantryName(model.getLesseeName());
            Integer lesseeIdType = model.getLesseeIdType();
            if (null != lesseeIdType) {
                contractStep1InitDTO.setTenantryIdType(lesseeIdType.toString());
            }
            contractStep1InitDTO.setTenantryId(model.getLesseeIdNo());
            contractStep1InitDTO.setTenantryPhoneNumber(model.getLesseeMobile());
            contractStep1InitDTO.setTenantryDeliverAddress(model.getLesseeAddress());
            contractStep1InitDTO.setTenantryEmail(model.getLesseeEmail());
            contractStep1InitDTO.setTenantryContactName(model.getLesseeBackupName());
            contractStep1InitDTO.setTenantryContactPhoneNumber(model.getLesseeBackupTel());
            Integer nation = model.getLesseeNation();
            if (null != nation) {
                contractStep1InitDTO.setTenantryNation(nation.toString());
            }
            contractStep1InitDTO.setTenantryProvince(model.getLesseeHouRegProvince());
            contractStep1InitDTO.setTenantryCity(model.getLesseeHouRegCity());
//            String hkadr = model.getHkadr();
//            if (StringUtils.isNotEmpty(hkadr)) {
//                String province;
//                String city;
//                List<SysAreaProvinces> provinceList = sysAreaAreasServiceImpl.getAllProvinces();
//                if (null != provinceList) {
//                    if (hkadr.contains("省") || hkadr.contains("自治区")) {
//                        int index;
//                        if (hkadr.contains("省")) {
//                            index = hkadr.indexOf("省");
//                            province = hkadr.substring(0, index + 1);
//                            city = hkadr.substring(index + 1);
//                        } else {
//                            index = hkadr.indexOf("自治区");
//                            province = hkadr.substring(0, index + 3);
//                            city = hkadr.substring(index + 3);
//                        }
//                        String provinceId = StringUtils.EMPTY;
//                        for (SysAreaProvinces sysAreaProvinces : provinceList) {
//                            if (province.equals(sysAreaProvinces.getProvince())) {
//                                provinceId = sysAreaProvinces.getProvinceId();
//                                contractStep1InitDTO.setTenantryProvince(provinceId);
//                                break;
//                            }
//                        }
//                        if (StringUtils.isNotEmpty(provinceId)) {
//                            List<SysAreaCities> cityList = sysAreaAreasServiceImpl.getAllCities(provinceId);
//                            if (null != cityList) {
//                                for (SysAreaCities sysAreaCities : cityList) {
//                                    if (city.equals(sysAreaCities.getCity())) {
//                                        contractStep1InitDTO.setTenantryCity(sysAreaCities.getCityId());
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    } else {
//                        province = hkadr;
//                        for (SysAreaProvinces sysAreaProvinces : provinceList) {
//                            if (province.equals(sysAreaProvinces.getProvince())) {
//                                contractStep1InitDTO.setTenantryProvince(sysAreaProvinces.getProvinceId());
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
            List<SysCode> nations = sysCodeService.getCodeByType(NATION);
            if (null != nations && nations.size() > 0) {
                contractStep1InitDTO.setNationList(systemCodeConvertor.toListDTO(nations));
            }
            contractStep1InitDTO.setVersion(model.getVersion());
        }
        return contractStep1InitDTO;
    }
}
