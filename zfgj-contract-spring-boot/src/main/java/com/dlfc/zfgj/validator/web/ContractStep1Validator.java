package com.dlfc.zfgj.validator.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.dto.web.ContractStep1DTO;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/2/23.
 */

@Component
public class ContractStep1Validator {
    // 直辖市：北京
    private static final String MUNICIPALITY_BEIJING_PROVINCE_ID = "110000";
    // 直辖市：上海
    private static final String MUNICIPALITY_TIANJIN_PROVINCE_ID = "120000";
    // 直辖市：天津
    private static final String MUNICIPALITY_SHANGHAI_PROVINCE_ID = "310000";
    // 直辖市：重庆
    private static final String MUNICIPALITY_CHONGQING_PROVINCE_ID = "500000";
    // 特别行政区：香港
    private static final String SPECIAL_HONGKONG_PROVINCE_ID = "810000";
    // 特别行政区：澳门
    private static final String SPECIAL_MACAO_PROVINCE_ID = "820000";
    // 台湾省
    private static final String MUNICIPALITY_TAIWAN_PROVINCE_ID = "710000";

    public void validate(ContractStep1DTO contractStep1DTO) throws ApplicationException {
        Object[] arguments;
        if (StringUtils.isEmpty(contractStep1DTO.getLessorName())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0040"));
        }
        if (contractStep1DTO.getLessorName().length() > 100) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0041"));
        }
        if (null == contractStep1DTO.getLessorIdType()) {
            arguments = new Object[]{"出租方证件类型"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getLessorId())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0042"));
        }
        if (contractStep1DTO.getLessorId().length() > 50) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0043"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getLessorPhoneNumber())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0044"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getLessorDeliverAddress())) {
            arguments = new Object[]{"出租方送达地址"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getLessorEmail())) {
            arguments = new Object[]{"出租方邮箱"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getLessorContactName())) {
            arguments = new Object[]{"出租方备用联系人姓名"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getLessorContactPhoneNumber())) {
            arguments = new Object[]{"出租方备用联系人电话"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryName())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0046"));
        }
        if (contractStep1DTO.getTenantryName().length() > 100) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0047"));
        }
        if (null == contractStep1DTO.getTenantryIdType()) {
            arguments = new Object[]{"承租方证件类型"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryId())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0048"));
        }
        if (contractStep1DTO.getTenantryId().length() > 50) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0049"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryPhoneNumber())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0050"));
        }
        if (null == contractStep1DTO.getTenantryNation()) {
            arguments = new Object[]{"承租方民族"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryProvince())) {
            arguments = new Object[]{"承租方户籍所在省"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryCity())
                && !contractStep1DTO.getTenantryProvince().equals(MUNICIPALITY_BEIJING_PROVINCE_ID)
                && !contractStep1DTO.getTenantryProvince().equals(MUNICIPALITY_TIANJIN_PROVINCE_ID)
                && !contractStep1DTO.getTenantryProvince().equals(MUNICIPALITY_SHANGHAI_PROVINCE_ID)
                && !contractStep1DTO.getTenantryProvince().equals(MUNICIPALITY_CHONGQING_PROVINCE_ID)
                && !contractStep1DTO.getTenantryProvince().equals(SPECIAL_HONGKONG_PROVINCE_ID)
                && !contractStep1DTO.getTenantryProvince().equals(SPECIAL_MACAO_PROVINCE_ID)
                && !contractStep1DTO.getTenantryProvince().equals(MUNICIPALITY_TAIWAN_PROVINCE_ID)) {
            arguments = new Object[]{"承租方户籍所在市"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryDeliverAddress())) {
            arguments = new Object[]{"承租方邮寄送达地址"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryEmail())) {
            arguments = new Object[]{"承租方邮箱"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryContactName())) {
            arguments = new Object[]{"承租方备用联系人姓名"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryContactPhoneNumber())) {
            arguments = new Object[]{"承租方备用联系人电话"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (contractStep1DTO.getLessorId().equals(contractStep1DTO.getTenantryId())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0051"));
        }
    }
}
