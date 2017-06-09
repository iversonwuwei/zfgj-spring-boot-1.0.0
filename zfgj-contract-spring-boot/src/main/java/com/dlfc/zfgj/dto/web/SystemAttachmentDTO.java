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
public class SystemAttachmentDTO implements Serializable {

    /**
     * 附件表主键ID
     */
    private String id;

    /**
     * 文件类型
     */
    private Integer type;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件原名
     */
    private String realName;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件
     */
    private MultipartFile file;
}
