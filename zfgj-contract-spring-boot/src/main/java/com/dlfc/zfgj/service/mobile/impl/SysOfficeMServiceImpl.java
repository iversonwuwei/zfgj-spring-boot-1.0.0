package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.SysOffice;
import com.dlfc.zfgj.entity.SysOfficeExample;
import com.dlfc.zfgj.mapper.SysOfficeMapper;
import com.dlfc.zfgj.service.mobile.SysOfficeMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by K on 2017/3/10.
 */

@Service
@Transactional
public class SysOfficeMServiceImpl implements SysOfficeMService {

    @Autowired
    private SysOfficeMapper sysOfficeMapper;

    @Override
    public String getOfficeIdByUser() {
//        String officeId = UserUtils.getUser().getOffice().getId();
        String officeId = "6800013bcee7414c87b0bf54a4a0c88c";//TODO
        SysOfficeExample sysOfficeExample = new SysOfficeExample();
        SysOfficeExample.Criteria sysOfficeCriteria1 = sysOfficeExample.createCriteria();
        sysOfficeCriteria1.andIdEqualTo(officeId);
        SysOfficeExample.Criteria sysOfficeCriteria2 = sysOfficeExample.createCriteria();
        sysOfficeCriteria2.andParentIdsLike(officeId);
        SysOffice sysOffice = sysOfficeMapper.selectByPrimaryKey(officeId);
        String resultOfficeId = StringUtils.EMPTY;
        if (null != sysOffice && StringUtils.isNotEmpty(sysOffice.getId())) {
            resultOfficeId = sysOffice.getId();
        }
        return resultOfficeId;
    }
}
