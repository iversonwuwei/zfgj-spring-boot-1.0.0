package com.dlfc.zfgj.validator.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.dto.web.ContractStep4DTO;
import com.dlfc.zfgj.dto.web.DeliverItemDTO;
import com.dlfc.zfgj.dto.web.OtherRelatedItemDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by K on 2017/2/23.
 */

@Component
public class ContractStep4Validator {

    public void validate(ContractStep4DTO contractStep4DTO) throws ApplicationException {
        if(null!=contractStep4DTO.getLessorPapers())
        if (null == contractStep4DTO.getDeliverItems()) {
            contractStep4DTO.setDeliverItems(new ArrayList<DeliverItemDTO>());
        }
        if (null == contractStep4DTO.getOtherRelatedItems()) {
            contractStep4DTO.setOtherRelatedItems(new ArrayList<OtherRelatedItemDTO>());
        } else {
            for (OtherRelatedItemDTO others : contractStep4DTO.getOtherRelatedItems()) {
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
}
