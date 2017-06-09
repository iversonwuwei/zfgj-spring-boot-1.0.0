package com.dlfc.zfgj.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by walden on 2017/2/9.
 */
@Getter
@Setter
public abstract class AbstractDTO {

    private Long id;

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }
}
