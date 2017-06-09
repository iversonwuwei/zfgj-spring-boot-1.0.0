package com.dlfc.zfgj.common.page;

import com.dlfc.zfgj.common.impl.Paging;
import com.dlfc.zfgj.dto.web.HouseDTO;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by walden on 2017/2/28.
 */
@Slf4j
@Component
public class HouseFactory {
    @Autowired
    @Qualifier("pageInfo")
    private static Paging page;

    public static Paging getInstance(List<HouseDTO> source) throws CustomRuntimeException {
        if (source == null) {
            throw new CustomRuntimeException("", "");
        }
        page = new Paging(source);
        return page;
    }
}
