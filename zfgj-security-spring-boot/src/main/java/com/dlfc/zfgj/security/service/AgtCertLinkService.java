package com.dlfc.zfgj.security.service;

import com.dlfc.zfgj.security.entity.AgtCertLink;

import java.util.List;

/**
 * Created by K on 2017/5/11.
 */
public interface AgtCertLinkService {
    List<AgtCertLink> isEmpCerted(String uid);
}
