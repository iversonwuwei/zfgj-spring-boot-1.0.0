package com.dlfc.zfgj.common.page.web;

import com.dlfc.zfgj.dto.web.ContractDTO;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by walden on 2017/2/28.
 */
@Slf4j
@Component
public class PageFactory {

    private static Paging page;

    public static Paging getInstance(List<ContractDTO> source) throws CustomRuntimeException {
        if (source == null) {
            throw new CustomRuntimeException("", "");
        }
        page = new Paging(source);
        return page;
    }
}
