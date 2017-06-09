package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by K on 2017/3/7.
 */

@Getter
@Setter
@Component
public class SysAttDTO implements Serializable {

    /**
     * 附件表主键Id
     */
    private String id;

    /**
     * 文件类型
     */
    private String type;

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
     * 文件
     */
    private MultipartFile file;
}
