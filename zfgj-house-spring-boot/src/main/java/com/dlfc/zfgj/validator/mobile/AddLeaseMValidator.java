package com.dlfc.zfgj.validator.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.mobile.AddHouseLeaseMDTO;
import org.springframework.stereotype.Component;


/**
 * Created by wangna on 2017/3/22.
 */
@Component
public interface AddLeaseMValidator {

    public void checkHouse(AddHouseLeaseMDTO addHouseLeaseMDTO) throws ApplicationException;
}
