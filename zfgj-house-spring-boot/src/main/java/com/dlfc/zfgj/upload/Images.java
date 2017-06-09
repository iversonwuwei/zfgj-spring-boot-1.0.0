package com.dlfc.zfgj.upload;

import com.dlfc.zfgj.entity.SysInfoAtt;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2017/4/14.
 */

@Getter
@Setter
@Component
public class Images {

    private List sysInfoAttList = new ArrayList();

    public Images() {
    }

    public Images(List sysInfoAttList) {
        this.sysInfoAttList = sysInfoAttList;
    }

    public Images add(SysInfoAtt sysInfoAtt) {
        this.sysInfoAttList.add(sysInfoAtt);
        return this;
    }

    public Images delete(int index) {
        this.sysInfoAttList.remove(index);
        return this;
    }

    public Images replace(int index,
                          SysInfoAtt sysInfoAtt) {
        this.sysInfoAttList.remove(index);
        this.sysInfoAttList.add(index, sysInfoAtt);
        return this;
    }
}
