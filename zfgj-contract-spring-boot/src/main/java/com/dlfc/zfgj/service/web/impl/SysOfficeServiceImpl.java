package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.SysOffice;
import com.dlfc.zfgj.entity.SysOfficeExample;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.mapper.SysOfficeMapper;
import com.dlfc.zfgj.service.web.AgtEmpInfoService;
import com.dlfc.zfgj.service.web.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by K on 2017/3/10.
 */

@Service
@Transactional
public class SysOfficeServiceImpl implements SysOfficeService {

    @Autowired
    private SysOfficeMapper sysOfficeMapper;
    @Autowired
    private AgtEmpInfoService agtEmpInfoService;

    @Override
    public String getOfficeIdByUser(UsrUser user) {
        AgtEmpInfo agtEmpInfo = agtEmpInfoService.getAgtEmpInfoByUserId(user.getId());
        String resultOfficeId = StringUtils.EMPTY;
        if (null != agtEmpInfo) {
            String officeId = agtEmpInfo.getOfficeId();
            SysOfficeExample sysOfficeExample = new SysOfficeExample();
            SysOfficeExample.Criteria sysOfficeCriteria1 = sysOfficeExample.createCriteria();
            sysOfficeCriteria1.andIdEqualTo(officeId);
            SysOfficeExample.Criteria sysOfficeCriteria2 = sysOfficeExample.createCriteria();
            sysOfficeCriteria2.andParentIdsLike(officeId);
            SysOffice sysOffice = sysOfficeMapper.selectByPrimaryKey(officeId);
            if (null != sysOffice && StringUtils.isNotEmpty(sysOffice.getId())) {
                resultOfficeId = sysOffice.getId();
            }
        }
        return resultOfficeId;
    }
}
