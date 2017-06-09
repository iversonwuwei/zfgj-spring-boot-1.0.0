package com.dlfc.zfgj.service.web;

import com.dlfc.zfgj.entity.AgtCertLink;
import com.dlfc.zfgj.entity.UsrUser;

import java.util.List;

/**
 * Created by K on 2017/5/11.
 */
public interface AgtCertLinkService {
    List<AgtCertLink> isEmpCerted(String uid);

    int isAgtCardBinded(String certNo);

    String save(AgtCertLink agtCertLink, UsrUser user);
}
