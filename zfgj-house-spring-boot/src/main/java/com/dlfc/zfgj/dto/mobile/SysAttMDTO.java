package com.dlfc.zfgj.dto.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by K on 2017/3/7.
 */

@Getter
@Setter
@Component
public class SysAttMDTO implements Serializable {

    /**
     * 文件类型
     */
    private Integer type;

    /**
     * 文件名字
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件真实名字
     */
    private String realName;

    /**
     * 附件表主键Id
     */
    private String infoId;
}
