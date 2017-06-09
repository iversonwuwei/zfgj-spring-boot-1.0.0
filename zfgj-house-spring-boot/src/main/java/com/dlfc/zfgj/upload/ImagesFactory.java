package com.dlfc.zfgj.upload;

import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/4/14.
 */

@Slf4j
@Component
public class ImagesFactory {

    private static Images images;

    public static Images getInstance(List<SysInfoAtt> source) throws CustomRuntimeException {
        if (source == null) {
            source = new ArrayList<>();
        }
        images = new Images(source);
        return images;
    }
}
