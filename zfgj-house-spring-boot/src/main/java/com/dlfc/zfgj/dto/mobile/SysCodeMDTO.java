package com.dlfc.zfgj.dto.mobile;

import com.dlfc.zfgj.dto.base.AbstractDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by wangna on 2017/6/1.
 */
@Getter
@Setter
@Component
public class SysCodeMDTO extends AbstractDTO {
    private String code;

    private String name;
}
