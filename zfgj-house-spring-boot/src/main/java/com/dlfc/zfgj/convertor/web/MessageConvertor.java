package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.convertor.base.AbstractConvertor;
import com.dlfc.zfgj.dto.web.MessageDTO;
import com.dlfc.zfgj.entity.SysMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Created by walden on 2017/3/24.
 */
@Getter
@Setter
@Service(value = "messageConvertor")
public class MessageConvertor extends AbstractConvertor<SysMessage,MessageDTO> {

    @Override
    public SysMessage toModel(MessageDTO messageDTO) {
        return null;
    }

    @Override
    public MessageDTO toDTO(SysMessage model, boolean forListView) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(model.getId());
        messageDTO.setSender(model.getSenderName());
        messageDTO.setSendTime(model.getSendTime());
        messageDTO.setTitle(model.getTitle());
        messageDTO.setContent(model.getContent());
        return messageDTO;
    }
}
