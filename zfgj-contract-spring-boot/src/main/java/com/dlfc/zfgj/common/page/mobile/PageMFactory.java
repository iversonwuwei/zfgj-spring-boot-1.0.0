package com.dlfc.zfgj.common.page.mobile;

import com.dlfc.zfgj.dto.moblie.ContractMDTO;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by walden on 2017/2/28.
 */
@Slf4j
@Component
public class PageMFactory {

    private static PagingM page;

    public static PagingM getInstance(List<ContractMDTO> source) throws CustomRuntimeException {
        if (source == null) {
            throw new CustomRuntimeException("", "");
        }
        page = new PagingM(source);
        return page;
    }
}
