package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wsz on 2017/3/16.
 */
@Getter
@Setter
public class SelectRoomCodeDTO {
    /**
     * 出租房间
     */
    private String roomName;
    /**
     * Code的值
     */
    private String codeValue;
}
