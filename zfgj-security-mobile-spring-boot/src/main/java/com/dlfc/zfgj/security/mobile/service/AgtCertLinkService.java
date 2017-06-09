package com.dlfc.zfgj.security.mobile.service;

import com.dlfc.zfgj.security.mobile.entity.AgtCertLink;

import java.util.List;

/**
 * Created by K on 2017/5/13.
 */
public interface AgtCertLinkService {
    List<AgtCertLink> getAgtCertLinkByUid(String id);
}
