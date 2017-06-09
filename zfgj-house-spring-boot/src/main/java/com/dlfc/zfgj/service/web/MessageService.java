package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.SysMessage;

import java.util.List;

/**
 * Created by walden on 2017/3/24.
 */
public interface MessageService {

    List<SysMessage> findByReceIdAndStatus(String receiverId, Short status);

    SysMessage findByPrimaryKey(String key);

    SysMessage save(SysMessage sysMessage);

    void update(SysMessage sysMessage);
}
