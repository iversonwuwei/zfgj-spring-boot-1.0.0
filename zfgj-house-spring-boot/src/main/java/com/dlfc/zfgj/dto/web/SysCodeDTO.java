package com.dlfc.zfgj.dto.web;

import com.dlfc.zfgj.dto.base.AbstractDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by K on 2017/5/11.
 */

@Getter
@Setter
@Component
public class SysCodeDTO extends AbstractDTO {

    private String code;

    private String name;
}
