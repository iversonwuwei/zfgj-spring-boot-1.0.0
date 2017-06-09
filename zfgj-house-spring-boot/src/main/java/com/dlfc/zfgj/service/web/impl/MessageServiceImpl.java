package com.dlfc.zfgj.service.web.impl;

import com.dlfc.zfgj.entity.SysMessage;
import com.dlfc.zfgj.entity.SysMessageExample;
import com.dlfc.zfgj.mapper.SysMessageMapper;
import com.dlfc.zfgj.service.web.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by walden on 2017/3/24.
 */
@Service(value = "messageServiceImpl")
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private SysMessageMapper sysMessageMapper;


    @Override
    public List<SysMessage> findByReceIdAndStatus(String receiverId,
                                                  Short status) {
        SysMessageExample example = new SysMessageExample();
        SysMessageExample.Criteria criteria = example.createCriteria();
        criteria.andReceiverUidEqualTo(receiverId);
        criteria.andStatusEqualTo(status);
        return sysMessageMapper.selectByExample(example);
    }

    @Override
    public SysMessage findByPrimaryKey(String key) {
        return sysMessageMapper.selectByPrimaryKey(key);
    }

    @Override
    public SysMessage save(SysMessage sysMessage) {
        return null;
    }

    @Override
    public void update(SysMessage sysMessage) {
        sysMessageMapper.updateByPrimaryKey(sysMessage);
    }
}
