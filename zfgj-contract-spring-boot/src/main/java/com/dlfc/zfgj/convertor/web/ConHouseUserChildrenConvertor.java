package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.ConHouseUserChildrenDTO;
import com.dlfc.zfgj.entity.ConHouseUserChildren;
import org.springframework.stereotype.Component;

/**
 * Created by wangna on 2017/6/9.
 */
@Component
public class ConHouseUserChildrenConvertor extends AbstractConvertor<ConHouseUserChildren,ConHouseUserChildrenDTO>{
    @Override
    public ConHouseUserChildren toModel(ConHouseUserChildrenDTO conHouseUserChildrenDTO) {
        ConHouseUserChildren conHouseUserChildren = new ConHouseUserChildren();
        conHouseUserChildren.setName(conHouseUserChildrenDTO.getName());
        conHouseUserChildren.setBirthday(conHouseUserChildrenDTO.getBirthday());
        conHouseUserChildren.setGender(conHouseUserChildrenDTO.getGender());
        conHouseUserChildren.setIdNo(conHouseUserChildrenDTO.getIdNo());
        return conHouseUserChildren;
    }

    @Override
    public ConHouseUserChildrenDTO toDTO(ConHouseUserChildren model, boolean forListView) {
        ConHouseUserChildrenDTO conHouseUserChildren = new ConHouseUserChildrenDTO();
        conHouseUserChildren.setName(model.getName());
        conHouseUserChildren.setBirthday(model.getBirthday());
        conHouseUserChildren.setGender(model.getGender());
        conHouseUserChildren.setIdNo(model.getIdNo());
        return conHouseUserChildren;
    }
}
