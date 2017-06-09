package com.dlfc.zfgj.validator.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.dto.web.ContractStep2DTO;
import com.dlfc.zfgj.dto.web.HouseUserDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.PropertyIdTypeEnum;
import com.dlfc.zfgj.enums.RentalModeEnum;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.service.web.UsrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/2/23.
 */

@Component
public class ContractStep2Validator {
    // 电话号码正则
    private static final String REGULAR_MOBILE = "^1[34578]\\d{9}$";
    // 证件正则
    private static final String REGULAR_ID_NO = "^[0-9a-zA-Z]+$";
    // 租金分期
    private static final String BY_STAGES = "01";

    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private UsrUserService usrUserServiceImpl;

    public void validate(ContractStep2DTO contractStep2DTO) throws ApplicationException {
        Object[] arguments;
        if (null == contractStep2DTO.getHousePropertyIdType()) {
            arguments = new Object[]{"房屋证件编号类型"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        } else if (PropertyIdTypeEnum.OTHER_ENUM.getValue() != contractStep2DTO.getHousePropertyIdType()) {
            if (StringUtils.isEmpty(contractStep2DTO.getHousePropertyId())) {
                arguments = new Object[]{"房屋证件编号"};
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
            }
        }
        if (StringUtils.isEmpty(contractStep2DTO.getHouseAddress())) {
            arguments = new Object[]{"房屋详细地址"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (null == contractStep2DTO.getHouseStructure()) {
            arguments = new Object[]{"房屋结构"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        if (null == contractStep2DTO.getHouseArea()) {
            arguments = new Object[]{"房屋建筑面积"};
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116", arguments));
        }
        // 判断如果是租金分期是否在平台已创建用户
        if (BY_STAGES.equals(contractStep2DTO.getStages())) {
            ConContract conDB = conContractMapper.selectByPrimaryKey(contractStep2DTO.getContractId());
            UsrUser lessor = usrUserServiceImpl.getUserByPerId(conDB.getLessorPid());
            if (lessor == null) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0108"));
            }
            UsrUser lessee = usrUserServiceImpl.getUserByPerId(conDB.getLesseePid());
            if (lessee == null) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0109"));
            }
        }
        if (contractStep2DTO.getContractStartDate() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0054"));
        } else if (contractStep2DTO.getContractEndDate() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0055"));
        } else if (contractStep2DTO.getYears() == null
                || contractStep2DTO.getMonths() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0056"));
        } else if (contractStep2DTO.getYears() == 0
                && contractStep2DTO.getMonths() == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0057"));
        } else if (contractStep2DTO.getRentalMode() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0058"));
        } else if (contractStep2DTO.getLeaseUse() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0059"));
        } else if (RentalModeEnum.PART_RENT_ENUM.equals(contractStep2DTO.getRentalMode())) {
            if (StringUtils.isEmpty(contractStep2DTO.getLeaseDomain())) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0060"));
            } else if (contractStep2DTO.getLeaseDomain().length() > 50) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0061"));
            } else if (contractStep2DTO.getLeaseArea() == null) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0062"));
            } else if (contractStep2DTO.getLeaseArea().doubleValue() < 4) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0063"));
            } else if (contractStep2DTO.getLeaseArea().doubleValue() > contractStep2DTO.getHouseArea()) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0103"));
            }
        } else {
            checkHouseUsers(contractStep2DTO.getHouUserList());
        }
    }

    private void checkHouseUsers(List<HouseUserDTO> houseUsers) throws ApplicationException {
        if (houseUsers == null || houseUsers.size() == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0078"));
        } else if (houseUsers.size() > 10) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0136"));
        } else {
            List<String> idList = new ArrayList<>();
            for (int i = 0; i < houseUsers.size(); i++) {
                // 验证实际使用者是否重复
                if (idList.contains(houseUsers.get(i).getIdNumber())) {
                    throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0137"));
                } else {
                    idList.add(houseUsers.get(i).getIdNumber());
                    checkHouseUser(houseUsers.get(i), i + 1);
                }
            }
        }
    }

    private void checkHouseUser(HouseUserDTO houseUser, int num) throws ApplicationException {
        Object[] arguments = {num};
        if (StringUtils.isEmpty(houseUser.getHouseUserName())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0079", arguments));
        } else if (houseUser.getHouseUserName().length() > 30) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0080", arguments));
        } else if (StringUtils.isEmpty(houseUser.getPhoneNumber())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0081", arguments));
        } else if (!houseUser.getPhoneNumber().matches(REGULAR_MOBILE)) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0082", arguments));
        } else if (houseUser.getIdNumber() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0083", arguments));
        } else if (StringUtils.isEmpty(houseUser.getIdNumber())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0084", arguments));
        } else if (houseUser.getIdNumber().length() > 50) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0085", arguments));
        } else if (!houseUser.getIdNumber().matches(REGULAR_ID_NO)) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0086", arguments));
        }
    }

}
