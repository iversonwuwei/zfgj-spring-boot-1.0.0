package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.Utils.CurrentUser;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.GuestbookTypeEnum;
import com.dlfc.zfgj.enums.YesNoEnum;
import com.dlfc.zfgj.mapper.*;
import com.dlfc.zfgj.service.web.AgtEmpInfoService;
import com.dlfc.zfgj.service.web.UsrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by K on 2017/2/24.
 */

@Transactional
@Service
public class UsrUserServiceImpl implements UsrUserService {

    @Autowired
    private UsrUserMapper usrUserMapper;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private SysPersonMapper sysPersonMapper;
    @Autowired
    private CmsGuestBookMapper cmsGuestBookMapper;
    @Autowired
    private AgtCompInfoMapper agtCompInfoMapper;
    @Autowired
    private SysOfficeMapper sysOfficeMapper;
    @Autowired
    private AgtEmpInfoService agtEmpInfoService;

    /**
     * 通过用户信息ID查询用户对象
     *
     * @param perId 用户信息ID
     * @return 用户对象
     */
    public UsrUser getUserByPerId(String perId) {
        UsrUserExample example = new UsrUserExample();
        UsrUserExample.Criteria criteria = example.createCriteria();
        criteria.andPerIdEqualTo(perId);
        criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
        List<UsrUser> list = usrUserMapper.selectByExample(criteria);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过token查询当前登录用户信息
     *
     * @param token
     * @return
     */
    @Override
    public UsrUser getUser(String token) throws ApplicationException {
        String username = currentUser.getCurrentUser(token);
        UsrUserExample usrUserExample = new UsrUserExample();
        UsrUserExample.Criteria criteria = usrUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andDeleteFlgEqualTo((short) 0);
        criteria = usrUserExample.createCriteria();
        criteria.andMobileEqualTo(username);
        criteria.andDeleteFlgEqualTo((short) 0);
        usrUserExample.or(criteria);
        List<UsrUser> usrUsers = usrUserMapper.selectByExample(usrUserExample);
        if (null != usrUsers && usrUsers.size() == 1) {
            return usrUsers.get(0);
        }
        return null;
    }

    /**
     * 意见反馈
     *
     * @return
     */
    @Override
    public boolean feedBack(String title,
                            String count,
                            HttpServletRequest request,
                            UsrUser usrUser) {
        boolean flag = false;
        CmsGuestBook cmsGuestbook = new CmsGuestBook();
        SysPerson sysPerson = sysPersonMapper.selectByPrimaryKey(usrUser.getPerId());
        if (null != sysPerson) {
            cmsGuestbook.setName(sysPerson.getName());
        }
        String companyName = StringUtils.EMPTY;
        AgtEmpInfo agtEmpInfo = agtEmpInfoService.getAgtEmpInfoByUserId(usrUser.getId());
        if (null != agtEmpInfo) {
            AgtCompInfo agtCompInfo = agtCompInfoMapper.selectByPrimaryKey(agtEmpInfo.getCompanyId());
            companyName = agtCompInfo.getName();
        }
        String officeName = StringUtils.EMPTY;
        SysOffice sysOffice = sysOfficeMapper.selectByPrimaryKey(agtEmpInfo.getOfficeId());
        if (null != sysOffice) {
            officeName = sysOffice.getName();
        }
        String workunit = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(companyName)
                && StringUtils.isNotEmpty(officeName)) {
            workunit = companyName + "," + officeName;
        }
        cmsGuestbook.setWorkunit(workunit);
        cmsGuestbook.preInsert(usrUser);
        cmsGuestbook.setCreateDate(new Date());
        cmsGuestbook.setType(GuestbookTypeEnum.ZFGJ.getValue());
        cmsGuestbook.setIp(StringUtils.getRemoteAddr(request));
        if (StringUtils.isNotEmpty(usrUser.getEmail())) {
            cmsGuestbook.setEmail(usrUser.getEmail());
        }
        cmsGuestbook.setPhone(usrUser.getMobile());
        cmsGuestbook.setContent(count);
        if (cmsGuestBookMapper.insertSelective(cmsGuestbook) > 0) {
            flag = true;
        }
        return flag;
    }
}
