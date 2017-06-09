package com.dlfc.zfgj.upload.impl;

import com.dlfc.zfgj.entity.SysInfoAtt;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.upload.Imageable;
import com.dlfc.zfgj.upload.Images;
import com.dlfc.zfgj.upload.ImagesFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K on 2017/4/14.
 */

@Getter
@Setter
@Component
public class CutomizedImages implements Imageable {

    private Images images;

    private List<SysInfoAtt> sysInfoAttList;

    @Override
    public void init() throws CustomRuntimeException {
        this.images = ImagesFactory.getInstance(this.sysInfoAttList);
    }

    @Override
    public void add(SysInfoAtt sysInfoAtt) throws CustomRuntimeException {
        if (null == this.images) {
            this.init();
        }
        this.images = this.images.add(sysInfoAtt);
    }

    @Override
    public void delete(int index) throws CustomRuntimeException {
        this.images = this.images.delete(index);
    }

    @Override
    public void replace(int index,
                        SysInfoAtt sysInfoAtt) throws CustomRuntimeException {
        this.images = this.images.replace(index, sysInfoAtt);
    }
}
