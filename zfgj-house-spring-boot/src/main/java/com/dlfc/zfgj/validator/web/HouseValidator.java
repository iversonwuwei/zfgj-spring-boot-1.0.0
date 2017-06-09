package com.dlfc.zfgj.validator.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.web.HouseLeaseDTO;

/**
 * Created by wsz on 2017/3/8.
 */

public interface HouseValidator {
    public void validate(String code,String Phone)throws ApplicationException;
    public void checkHouse(HouseLeaseDTO HouseLeaseDTO)throws ApplicationException;

}
