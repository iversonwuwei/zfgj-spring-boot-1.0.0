package com.dlfc.zfgj.dto.web;

import com.dlfc.zfgj.dto.base.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by walden on 2017/3/24.
 */
@Getter
@Setter
public class MessageDTO extends AbstractDTO{

    private String title;

    private Date sendTime;

    private String sender;

    private String content;
}
