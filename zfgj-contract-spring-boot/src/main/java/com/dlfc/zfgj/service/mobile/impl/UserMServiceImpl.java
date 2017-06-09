package com.dlfc.zfgj.service.mobile.impl;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.security.Digests;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.Encodes;
import com.dlfc.admin.common.utils.IdGen;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.domain.VerCode;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.enums.GuestbookTypeEnum;
import com.dlfc.zfgj.enums.MsgEnums;
import com.dlfc.zfgj.enums.TemplateNoEnum;
import com.dlfc.zfgj.mapper.*;
import com.dlfc.zfgj.service.mobile.SysMobileCapchaService;
import com.dlfc.zfgj.service.mobile.UserMService;
import com.housecenter.dlfc.framework.sms.SMSFacet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by walden on 2017/2/22.
 */
@Service
public class UserMServiceImpl implements UserMService {

    public final int SALT_SIZE = 8;

    public static final int HASH_INTERATIONS = 1024;

    @Autowired
    private UsrUserMapper usrUserMapper;
    @Autowired
    private SysMobileCapchaMapper sysMobileCapchaMapper;
    @Autowired
    private AgtCompInfoMapper agtCompInfoMapper;
    @Autowired
    private AgtEmpInfoMapper agtEmpInfoMapper;
    @Autowired
    private SysOfficeMapper sysOfficeMapper;
    @Autowired
    private CmsGuestBookMapper cmsGuestBookMapper;
    @Autowired
    private SysPersonMapper sysPersonMapper;
    @Autowired
    @Qualifier("contractForM")
    private SysMobileCapchaService sysMobileCapchaService;

    @Override
    public UsrUser findOne(String username) {
        UsrUserExample usrUserExample = new UsrUserExample();
        UsrUserExample.Criteria criteria = usrUserExample.or();
        criteria.andUsernameEqualTo(username);
        List<UsrUser> usrUsers = usrUserMapper.selectByExample(usrUserExample);
        return null;
    }

    /**
     * 发送验证码
     *
     * @return
     */
    @Override
    public String codeSend(String phone, String domain) throws Exception {

        String uuid = IdGen.uuid();
        VerCode verCode = new VerCode();
        verCode.setDomain(domain);
        verCode.setMobile(phone);
        verCode.setDeviceId(uuid);
        String id = verCodeSend(verCode);
        return id;
    }


    private String verCodeSend(VerCode verCode) throws Exception {

        String id = null;
        if (MsgEnums.REGESIT.getValue().equals(verCode.getDomain())) {
            id = SMSFacet.sendCapcha(verCode.getMobile(), IdGen.uuid(), verCode.getPhoneIp(), TemplateNoEnum.REGESIT_NO.getValue(), verCode.getDomain());
        } else if (MsgEnums.FINDPWDCHECKPHONE.getValue().equals(verCode.getDomain())) {
            id = SMSFacet.sendCapcha(verCode.getMobile(), IdGen.uuid(), verCode.getPhoneIp(), TemplateNoEnum.FINDPWDCHECKPHONE_NO.getValue(),
                    verCode.getDomain());
        } else if (MsgEnums.FINDPWDCHECKBANKPHONE.getValue().equals(verCode.getDomain())) {
            id = SMSFacet.sendCapcha(verCode.getMobile(), IdGen.uuid(), verCode.getPhoneIp(), TemplateNoEnum.FINDPWDCHECKBANKPHONE_NO.getValue(),
                    verCode.getDomain());
        } else if (MsgEnums.SETAPPLYPWD.getValue().equals(verCode.getDomain())) {
            id = SMSFacet.sendCapcha(verCode.getMobile(), IdGen.uuid(), verCode.getPhoneIp(), TemplateNoEnum.SETAPPLYPWD_NO.getValue(), verCode.getDomain());
        } else if (MsgEnums.FINDAPPLYPWD.getValue().equals(verCode.getDomain())) {
            id = SMSFacet.sendCapcha(verCode.getMobile(), IdGen.uuid(), verCode.getPhoneIp(), TemplateNoEnum.FINDAPPLYPWD_NO.getValue(), verCode.getDomain());
        } else if (MsgEnums.REPLACEPHONETOOLD.getValue().equals(verCode.getDomain())) {
            id = SMSFacet.sendCapcha(verCode.getMobile(), IdGen.uuid(), verCode.getPhoneIp(), TemplateNoEnum.REPLACEPHONETOOLD_NO.getValue(),
                    verCode.getDomain());
        } else if (MsgEnums.REPLACEPHONETONEW.getValue().equals(verCode.getDomain())) {
            id = SMSFacet.sendCapcha(verCode.getMobile(), IdGen.uuid(), verCode.getPhoneIp(), TemplateNoEnum.REPLACEPHONETONEW_NO.getValue(),
                    verCode.getDomain());
        } else if (MsgEnums.BINDBANKPHONE.getValue().equals(verCode.getDomain())) {
            id = SMSFacet.sendCapcha(verCode.getMobile(), IdGen.uuid(), verCode.getPhoneIp(), TemplateNoEnum.BINDBANKPHONE_NO.getValue(), verCode.getDomain());
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(id)) {
            throw new ApplicationException("发送短信失败");
        }
        return id;
    }

    /**
     * 修改登录密码
     *
     * @return
     */
    @Override
    public boolean changePassword(String password,
                                  UsrUser usrUser) {
        boolean flag = false;
        UsrUser updateUser = new UsrUser();
        updateUser.setId(usrUser.getId());
        updateUser.setPassword(entryptPassword(password));
        updateUser.preUpdate(usrUser);
        // 执行更新
        int count = usrUserMapper.updateByPrimaryKeySelective(updateUser);
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 忘记登录密码
     *
     * @return
     */
    @Override
    public boolean forgetPassword(String phone,
                                  String password,
                                  String code,
                                  String msgId) throws Exception {
        boolean flag = false;
        int verCodeLimitSecond = Integer.valueOf(1800);
        SysMobileCapcha param = new SysMobileCapcha();
        param.setVerCode(code);
        param.setMobile(phone);
        // 重置登录密码
        param.setDomain(String.valueOf(MsgEnums.FINDPWDCHECKPHONE.getValue()));
        param.setDeviceId(msgId);
        SysMobileCapcha sysMobileCapcha = sysMobileCapchaService.selectByVerCode(param);
        if (sysMobileCapcha == null) {
            throw new ApplicationException("验证码不正确");
        }
        // 验证码是否超时
        int subSecond = DateUtils.getSecondBetweenDate(sysMobileCapcha.getCreateTime(), new Date());
        if (subSecond > verCodeLimitSecond) {
            throw new ApplicationException("验证码无效");
        }
        UsrUserExample usrUserExample = new UsrUserExample();
        UsrUserExample.Criteria criteria = usrUserExample.or();
        criteria.andMobileEqualTo(phone);
        List<UsrUser> usrUsers = usrUserMapper.selectByExample(usrUserExample);
        if (null == usrUsers || usrUsers.size() == 0) {
            throw new ApplicationException("用户信息不存在");
        }
        UsrUser updateUser = new UsrUser();
        updateUser.setId(usrUsers.get(0).getId());
        updateUser.setPassword(entryptPassword(password));
        updateUser.preUpdate(usrUsers.get(0));
        // 执行更新
        int count = usrUserMapper.updateByPrimaryKeySelective(updateUser);
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    @Override
    public boolean validatePassword(String plainPassword, String password) {

        byte[] salt = Encodes.decodeHex(password.substring(0, SALT_SIZE * 2));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    private String entryptPassword(String plainPassword) {

        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 意见反馈
     *
     * @return
     */
    @Override
    public boolean feedBack(String count, HttpServletRequest request, UsrUser usrUser) {

        boolean flag = false;
        CmsGuestBook cmsGuestbook = new CmsGuestBook();
        SysPerson sysPerson = sysPersonMapper.selectByPrimaryKey(usrUser.getPerId());
        cmsGuestbook.setName(sysPerson.getName());
        AgtEmpInfo agtEmpInfo = agtEmpInfoMapper.selectByPrimaryKey(usrUser.getEmpId());
        AgtCompInfo agtCompInfo = agtCompInfoMapper.selectByPrimaryKey(agtEmpInfo.getCompanyId());
        String comp = agtCompInfo.getName();
        SysOffice sysOffice = sysOfficeMapper.selectByPrimaryKey(agtEmpInfo.getOfficeId());
        String office = sysOffice.getName();
        String workunit = comp + "," + office;
        cmsGuestbook.setWorkunit(workunit);
        cmsGuestbook.preInsert(usrUser);
        cmsGuestbook.setCreateDate(new Date());
        cmsGuestbook.setType(GuestbookTypeEnum.APP.getValue());
        cmsGuestbook.setIp(StringUtils.getRemoteAddr(request));
        if (usrUser.getEmail() != null) {
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
