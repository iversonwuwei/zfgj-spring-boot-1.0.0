package com.dlfc.zfgj.validator.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.dto.web.ContractStep3DTO;
import com.dlfc.zfgj.enums.SettlementCycleEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by K on 2017/2/23.
 */

@Component
public class ContractStep3Validator {

    public void validate(ContractStep3DTO contractStep3DTO, String contractid) throws ApplicationException {
        Integer monthlyRent = contractStep3DTO.getMonthlyRent();
        if (monthlyRent == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0064"));
        }
        if (monthlyRent == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0065"));
        }
        if (monthlyRent > 1000000) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0066"));
        }
        Integer cycle = contractStep3DTO.getSettlement();
        if (cycle == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0067"));
        }
        if (cycle != SettlementCycleEnum.MONTHLY_ENUM.getValue()
                && cycle != SettlementCycleEnum.QUARTER_ENUM.getValue()
                && cycle != SettlementCycleEnum.HALF_YEAR_ENUM.getValue()
                && cycle != SettlementCycleEnum.YEAR_ENUM.getValue()) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0068"));
        }
        BigDecimal agencyFee = contractStep3DTO.getAgentFee();
        if (agencyFee == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0072"));
        }
        if (agencyFee.doubleValue() == 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0073"));
        }
        if (agencyFee.doubleValue() > 1000000) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0074"));
        }
        BigDecimal depositAmt = contractStep3DTO.getDeposit();
        if (depositAmt == null) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0075"));
        }
        if (depositAmt.doubleValue() < 0) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0076"));
        }
        String ownerBearOther = contractStep3DTO.getOwnerBearOther();
        if (ownerBearOther != null && ownerBearOther.length() > 100) {
            throw new ApplicationException(PropertyUtils.getErrorMsg("SYS-0077"));
        }
    }
}
