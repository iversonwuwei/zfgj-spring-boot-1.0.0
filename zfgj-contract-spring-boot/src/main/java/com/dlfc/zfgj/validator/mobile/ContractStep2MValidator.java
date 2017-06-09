package com.dlfc.zfgj.validator.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.dto.moblie.ContractStep2MDTO;
import com.dlfc.zfgj.dto.moblie.HouseUserMDTO;
import com.dlfc.zfgj.entity.ConContract;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.RentalModeEnum;
import com.dlfc.zfgj.mapper.ConContractMapper;
import com.dlfc.zfgj.service.mobile.ContractStepMService;
import com.dlfc.zfgj.service.mobile.UsrUserMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/2/23.
 */

@Component
public class ContractStep2MValidator {
    // 电话号码正则
    private static final String REGULAR_MOBILE = "^1[34578]\\d{9}$";
    // 证件正则
    private static final String REGULAR_ID_NO = "^[0-9a-zA-Z]+$";
    // 租金分期
    private static final String BY_STAGES = "01";

    @Autowired
    private ContractStepMService contractStepMServiceImpl;
    @Autowired
    private ConContractMapper conContractMapper;
    @Autowired
    private UsrUserMService usrUserMServiceImpl;

    public void validate(ContractStep2MDTO contractStep2DTO, String contractId) throws ApplicationException {
        if (StringUtils.isEmpty(contractStep2DTO.getHouseAddress())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"), "房屋详细地址");
        }
        if (StringUtils.isEmpty(contractStep2DTO.getHousePropertyId())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"), "房屋证件编号");
        }
        // 判断如果是租金分期是否在平台已创建用户
        if (BY_STAGES.equals(contractStep2DTO.getStages())) {
            ConContract conDB = conContractMapper.selectByPrimaryKey(contractId);
            UsrUser lessor = usrUserMServiceImpl.getUserByPerId(conDB.getLessorPid());
            if (lessor == null) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0108"));
            }
            UsrUser lessee = usrUserMServiceImpl.getUserByPerId(conDB.getLesseePid());
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
            }
        } else {
            checkHouseUsers(contractStep2DTO.getHouUserList());
        }
    }

    private void checkHouseUsers(List<HouseUserMDTO> houseUsers) throws ApplicationException {
        if (houseUsers == null || houseUsers.size() == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0078"));
        } else {
            List<String> idList = new ArrayList<>();
            for (int i = 0; i < houseUsers.size(); i++) {
                if (idList.contains(houseUsers.get(i).getIdNumber())) {
                    throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0115"));
                } else {
                    idList.add(houseUsers.get(i).getIdNumber());
                    checkHouseUser(houseUsers.get(i), i + 1);
                }
            }
        }
    }

    private void checkHouseUser(HouseUserMDTO houseUser, int num) throws ApplicationException {
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

    public ContractStep2MDTO validateHouseUsers(List<HouseUserMDTO> houseUsers,UsrUser user) {
        ContractStep2MDTO contractStep2DTO = new ContractStep2MDTO();
        if (houseUsers != null && houseUsers.size() > 0) {
            for (HouseUserMDTO houseUser : houseUsers) {
                try {
                    contractStepMServiceImpl.checkIdentity(houseUser.getIdNumber(),
                            houseUser.getIdentityMode(),
                            houseUser.getHouseUserName(),
                            houseUser.getPhoneNumber(),
                            user);
                } catch (ApplicationException e) {
                    houseUser.setValidateResult(false);
                    if (contractStep2DTO.isHouUserListFlg()) {
                        contractStep2DTO.setHouUserListFlg(false);
                        contractStep2DTO.setErrorMessage(PropertyUtils.getErrorMsg("SYS-0113"));
                    }
                }
            }
        }
        contractStep2DTO.setHouUserList(houseUsers);
        return contractStep2DTO;
    }

}
