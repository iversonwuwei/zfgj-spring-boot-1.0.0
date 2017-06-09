package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ContractStep1DTO;
import com.dlfc.zfgj.entity.ConContract;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/2/23.
 */

@Component
public class ContractStep1Convertor extends AbstractConvertor<ConContract, ContractStep1DTO> {

    @Override
    public ConContract toModel(ContractStep1DTO contractStep1DTO) {
        ConContract conContract = new ConContract();
        if (null != contractStep1DTO) {
            conContract.setId(contractStep1DTO.getContractId());
            // 出租方
            conContract.setLessorName(contractStep1DTO.getLessorName());
            conContract.setLessorIdType(contractStep1DTO.getLessorIdType());
            conContract.setLessorIdNo(contractStep1DTO.getLessorId());
            conContract.setLessorMobile(contractStep1DTO.getLessorPhoneNumber());
            conContract.setLessorAddress(contractStep1DTO.getLessorDeliverAddress());
            conContract.setLessorEmail(contractStep1DTO.getLessorEmail());
            conContract.setLessorBackupName(contractStep1DTO.getLessorContactName());
            conContract.setLessorBackupTel(contractStep1DTO.getLessorContactPhoneNumber());
            // 承租方
            conContract.setLesseeName(contractStep1DTO.getTenantryName());
            conContract.setLesseeIdType(contractStep1DTO.getTenantryIdType());
            conContract.setLesseeIdNo(contractStep1DTO.getTenantryId());
            conContract.setLesseeMobile(contractStep1DTO.getTenantryPhoneNumber());
            conContract.setLesseeAddress(contractStep1DTO.getTenantryDeliverAddress());
            conContract.setLesseeEmail(contractStep1DTO.getTenantryEmail());
            conContract.setLesseeBackupName(contractStep1DTO.getTenantryContactName());
            conContract.setLesseeBackupTel(contractStep1DTO.getTenantryContactPhoneNumber());
            conContract.setLesseeNation(contractStep1DTO.getTenantryNation());
            conContract.setLesseeHouRegProvince(contractStep1DTO.getTenantryProvince());
            conContract.setLesseeHouRegCity(contractStep1DTO.getTenantryCity());
//            StringBuffer stringBuffer = new StringBuffer();
//            String provinceId = contractStep1DTO.getTenantryProvince();
//            String cityId = contractStep1DTO.getTenantryCity();
//            List<SysAreaProvinces> sysAreaProvincesList = sysAreaAreasService.getAllProvinces();
//            for (SysAreaProvinces province : sysAreaProvincesList) {
//                if (province.getProvinceId().equals(provinceId)) {
//                    stringBuffer.append(province.getProvince());
//                    break;
//                }
//            }
//            if (!provinceId.equals(MUNICIPALITY_BEIJING_PROVINCE_ID)
//                    && !provinceId.equals(MUNICIPALITY_TIANJIN_PROVINCE_ID)
//                    && !provinceId.equals(MUNICIPALITY_SHANGHAI_PROVINCE_ID)
//                    && !provinceId.equals(MUNICIPALITY_CHONGQING_PROVINCE_ID)
//                    && !provinceId.equals(SPECIAL_HONGKONG_PROVINCE_ID)
//                    && !provinceId.equals(SPECIAL_MACAO_PROVINCE_ID)) {
//                List<SysAreaCities> sysAreaCitiesList = sysAreaAreasService.getAllCities(provinceId);
//                for (SysAreaCities city : sysAreaCitiesList) {
//                    if (city.getCityId().equals(cityId)) {
//                        stringBuffer.append(city.getCity());
//                        break;
//                    }
//                }
//            }
//            conContract.setHkadr(stringBuffer.toString());
            // 合同版本号（验证用）
            conContract.setVersion(contractStep1DTO.getVersion());
        }
        return conContract;
    }

    @Override
    public ContractStep1DTO toDTO(ConContract model, boolean forListView) {
        ContractStep1DTO contractStep1DTO = new ContractStep1DTO();
        if (null != model) {
            contractStep1DTO.setContractId(model.getId());
        }
        return contractStep1DTO;
    }
}
