/**
* @name: SignService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 经纪人签到service
*
* @version: 1.0
* @date : 2016年12月6日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年12月6日       yuanjw        1.0             <修改原因描述>
*/
package com.housecenter.dlfc.modules.sign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.service.BaseService;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.AgtEmpInfo;
import com.dlfc.zfgj.entity.SysParam;
import com.dlfc.zfgj.service.AgtEmpInfoService;
import com.dlfc.zfgj.service.SysParamService;
import com.housecenter.dlfc.modules.sign.entity.UsrSignInfo;
import com.housecenter.dlfc.modules.sign.entity.UsrSignInfoExample;
import com.housecenter.dlfc.modules.sign.entity.UsrSignInfoExample.Criteria;
import com.housecenter.dlfc.modules.sign.mapper.UsrSignInfoMapper;

/**
 * @name: SignService
 * @description: 经纪人签到service
 * 
 * @version 1.0
 * @author yuanjw
 *
 */
@Service("usrSignInfoService")
public class UsrSignInfoService extends BaseService {

	/** 签到信息表mapper */
	@Autowired
	private UsrSignInfoMapper usrSignInfoMapper;
	/** 系统参数service */
	@Autowired
	private SysParamService sysParamService;
	/** 经纪人service */
	@Autowired
	private AgtEmpInfoService agtEmpInfoService;

	/**
	 * 是否可以签到
	 * 
	 * @return true 可以签到，false 不可以签到
	 */
	public boolean isSign() {
		// 判断签到开关是否打开
		SysParam sysParam = new SysParam();
		sysParam.setScope("sign");
		sysParam.setSkey("on-off");
		if (sysParamService.isOn(sysParam)) {
			// 判断今天是否签到
			if (isSignToday()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 今天是否可以签到
	 * 
	 * @return true 可以签到，false 不可以签到
	 */
	public boolean isSignToday() {
		// 获取经纪ID
		String empId = getEmpId();
		List<UsrSignInfo> signList = usrSignInfoMapper.getUsrSignInfo(empId);
		// 判断今天是否签到
		if (signList != null && signList.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 签到
	 * 
	 * @return true 签到成功，false 签到失败
	 */
	public boolean sign() {
		// 判断是否可以签到
		if (isSign()) {
			// 签到
			UsrSignInfo usrSignInfo = new UsrSignInfo();
			usrSignInfo.preInsert();
			usrSignInfo.setSdate(DateUtils.getSynchTime());
			String empId = getEmpId();
			// 判断经纪人ID是否存在
			if(StringUtils.isNotBlank(empId)){
				usrSignInfo.setUeid(empId);
			}else{
				return false;
			}
			// 签到
			usrSignInfoMapper.insertSelective(usrSignInfo);
			return true;
		}
		return false;
	}

	/**
	 * 获取签到天数
	 * 
	 * @return 签到天数
	 */
	public int getDays() {
		UsrSignInfoExample example = new UsrSignInfoExample();
		Criteria c = example.createCriteria();
		c.andUeidEqualTo(getEmpId());
		c.andDeleteFlgEqualTo((short) 0);
		return usrSignInfoMapper.countByExample(example);
	}

	/**
	 * 获取经纪人ID
	 * 
	 * @return 经纪人ID
	 */
	public String getEmpId() {
		// 获取登录用户ID
		String userId = UserUtils.getUser().getId();
		// 获取经纪ID
		String empId = "";
		List<AgtEmpInfo> agtEmpList = agtEmpInfoService.getEmpInfo(userId);
		if(agtEmpList != null && agtEmpList.size() > 0){
			empId = agtEmpList.get(0).getId();
		}
		return empId;
	}
}
