package com.dlfc.zfgj.convertor.mobile;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.moblie.ConHouseUserChildrenMDTO;
import com.dlfc.zfgj.entity.ConHouseUserChildren;
import org.springframework.stereotype.Component;

/**
 * Created by wangna on 2017/6/8.
 */
@Component
public class ConHouseUserChildrenMConvertor extends AbstractConvertor<ConHouseUserChildren,ConHouseUserChildrenMDTO>{
    @Override
    public ConHouseUserChildren toModel(ConHouseUserChildrenMDTO conHouseUserChildrenMDTO) {
        ConHouseUserChildren conHouseUserChildren = new ConHouseUserChildren();
        conHouseUserChildren.setName(conHouseUserChildrenMDTO.getName());
        conHouseUserChildren.setBirthday(conHouseUserChildrenMDTO.getBirthday());
        conHouseUserChildren.setGender(conHouseUserChildrenMDTO.getGender());
        conHouseUserChildren.setIdNo(conHouseUserChildrenMDTO.getIdNo());
        return conHouseUserChildren;
    }

    @Override
    public ConHouseUserChildrenMDTO toDTO(ConHouseUserChildren model, boolean forListView) {
        ConHouseUserChildrenMDTO conHouseUserChildren = new ConHouseUserChildrenMDTO();
        conHouseUserChildren.setName(model.getName());
        conHouseUserChildren.setBirthday(model.getBirthday());
        conHouseUserChildren.setGender(model.getGender());
        conHouseUserChildren.setIdNo(model.getIdNo());
        return conHouseUserChildren;
    }
}
