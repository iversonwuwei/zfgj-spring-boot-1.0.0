package com.dlfc.zfgj.validator.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.dto.moblie.DeliverItemMDTO;
import com.dlfc.zfgj.dto.moblie.HouseUserMDTO;
import com.dlfc.zfgj.dto.moblie.NewContractDTO;
import com.dlfc.zfgj.dto.moblie.OtherRelatedItemMDTO;
import com.dlfc.zfgj.enums.RentalModeEnum;
import com.dlfc.zfgj.enums.SettlementCycleEnum;
import com.dlfc.zfgj.service.mobile.ContractStepMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/2/23.
 */

@Component
public class ContractStep1MValidator {
    // 电话号码正则
    private static final String REGULAR_MOBILE = "^1[34578]\\d{9}$";
    // 证件正则
    private static final String REGULAR_ID_NO = "^[0-9a-zA-Z]+$";
    // 租金分期
    private static final String BY_STAGES = "01";
    //证件类型（身份证）
    private static final Integer perIdentityType = 1;

    @Autowired
    private ContractStepMService contractStepMServiceImpl;

    public void validate(NewContractDTO contractStep1DTO) throws ApplicationException {

        if (StringUtils.isEmpty(contractStep1DTO.getLessorName())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0040"));
        }
        if (contractStep1DTO.getLessorName().length() > 100) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0041"));
        }
        if (null == contractStep1DTO.getLessorIdType()) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0117"));
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
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0116"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getLessorEmail())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0118"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getLessorContactName())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0119"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getLessorContactPhoneNumber())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0120"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryName())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0046"));
        }
        if (contractStep1DTO.getTenantryName().length() > 100) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0047"));
        }
        if (null == contractStep1DTO.getTenantryIdType()) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0121"));
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
        if (contractStep1DTO.getTenantryNation() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0122"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryProvince())) {
            throw new ApplicationException("承租方户籍所在省不能为空");
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryCity())) {
            throw new ApplicationException("承租方户籍所在市不能为空");
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryDeliverAddress())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0125"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryEmail())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0124"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryContactName())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0126"));
        }
        if (StringUtils.isEmpty(contractStep1DTO.getTenantryContactPhoneNumber())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0127"));
        }
        if (contractStep1DTO.getLessorId().equals(contractStep1DTO.getTenantryId())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0051"));
        }
//        contractStepMServiceImpl.checkIdentity(contractStep1DTO.getLessorId(),
//                contractStep1DTO.getLessorIdType(),
//                contractStep1DTO.getLessorName(),
//                contractStep1DTO.getLessorPhoneNumber());
//        contractStepMServiceImpl.checkIdentity(contractStep1DTO.getTenantryId(),
//                contractStep1DTO.getTenantryIdType(),
//                contractStep1DTO.getTenantryName(),
//                contractStep1DTO.getTenantryPhoneNumber());
        if (StringUtils.isEmpty(contractStep1DTO.getHouseAddress())) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0097"));
        }
//        if (StringUtils.isEmpty(contractStep1DTO.getHousePropertyId())) {
//            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0112"), "房屋证件编号");
//        }
        if (contractStep1DTO.getContractStartDate() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0054"));
        } else if (contractStep1DTO.getContractEndDate() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0055"));
        } else if (contractStep1DTO.getYears() == null
                || contractStep1DTO.getMonths() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0056"));
        } else if (contractStep1DTO.getYears() == 0
                && contractStep1DTO.getMonths() == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0057"));
        } else if (contractStep1DTO.getRentalMode() == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0058"));
//        } else if (contractStep1DTO.getLeaseUse() == null) {
//            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0059"));
        } else if (RentalModeEnum.PART_RENT_ENUM.equals(contractStep1DTO.getRentalMode())) {
            if (StringUtils.isEmpty(contractStep1DTO.getLeaseDomain())) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0060"));
            } else if (contractStep1DTO.getLeaseDomain().length() > 50) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0061"));
            } else if (contractStep1DTO.getLeaseArea() == null) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0062"));
            } else if (contractStep1DTO.getLeaseArea().doubleValue() < 4) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0063"));
            }
        } else {
            checkHouseUsers(contractStep1DTO.getHouUserList());
        }

        Integer monthlyRent = contractStep1DTO.getMonthlyRent();
        if (monthlyRent == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0064"));
        }
        if (monthlyRent == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0065"));
        }
        if (monthlyRent > 1000000) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0066"));
        }
        Integer cycle = contractStep1DTO.getSettlement();
        if (cycle == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0067"));
        }
        if (cycle != SettlementCycleEnum.MONTHLY_ENUM.getValue() && cycle != SettlementCycleEnum.QUARTER_ENUM.getValue() && cycle != SettlementCycleEnum.HALF_YEAR_ENUM.getValue() && cycle != SettlementCycleEnum.YEAR_ENUM.getValue()) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0068"));
        }
        BigDecimal agencyFee = contractStep1DTO.getAgentFee();
        if (agencyFee == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0072"));
        }
        if (agencyFee.doubleValue() == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0073"));
        }
        if (agencyFee.doubleValue() > 1000000) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0074"));
        }
        BigDecimal depositAmt = contractStep1DTO.getDeposit();
        Short flag = contractStep1DTO.isDepositSuperviseFlg() ? (short) 1 : 0;
        if (flag == 1) {
            if (depositAmt == null) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0075"));
            }
            if (depositAmt.doubleValue() < 0) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0076"));
            }
            String OwnerBearOther = contractStep1DTO.getOwnerBearOther();
            if (OwnerBearOther != null && OwnerBearOther.length() > 100) {
                throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0077"));
            }
        }
        if (null == contractStep1DTO.getDeliverItems() && contractStep1DTO.getOtherRelatedItems() == null) {
            contractStep1DTO.setDeliverItems(new ArrayList<DeliverItemMDTO>());
        }
        if (null == contractStep1DTO.getOtherRelatedItems() && contractStep1DTO.getOtherRelatedItems() == null) {
            contractStep1DTO.setOtherRelatedItems(new ArrayList<OtherRelatedItemMDTO>());
        } else {
            for (OtherRelatedItemMDTO others : contractStep1DTO.getOtherRelatedItems()) {
                if (StringUtils.isNotEmpty(others.getItem()) && others.getItem().length() > 50) {
                    throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0087"));
                }
                if (StringUtils.isNotEmpty(others.getUnit()) && others.getUnit().length() > 50) {
                    throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0088"));
                }
                if (others.getPrice() != null) {
                    if (others.getPrice().doubleValue() < 0 || others.getPrice().doubleValue() > 999999) {
                        throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0089"));
                    }
                }
                if (others.getStartDate() != null) {
                    if (DateUtils.strToDate(DateUtils.formatDate(others.getStartDate()), DateUtils.DATE_Y_M_D_PATERN) == null) {
                        throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0090"));
                    }
                }
                if (StringUtils.isNotEmpty(others.getMinQuantity()) && others.getMinQuantity().length() > 8) {
                    throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0091"));
                }
            }
        }
    }





    private void checkHouseUsers(List<HouseUserMDTO> houseUsers) throws ApplicationException {
        if (houseUsers == null || houseUsers.size() == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0078"));
        } else {
            if(houseUsers.size() > 10){
                throw new ApplicationException("实际使用人数不能大于10人");
            }else{
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
        } else if (houseUser.getIdentityMode() == perIdentityType &&!houseUser.getIdNumber().matches(REGULAR_ID_NO)) {
           //只有身份证判断证件号码的正则
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0086", arguments));
        }
    }


}
