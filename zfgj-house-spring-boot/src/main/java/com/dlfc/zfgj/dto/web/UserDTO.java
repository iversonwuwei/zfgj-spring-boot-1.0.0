package com.dlfc.zfgj.dto.web;

import com.dlfc.zfgj.dto.base.AbstractDTO;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/9.
 */
@Component
public class UserDTO extends AbstractDTO {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

}
