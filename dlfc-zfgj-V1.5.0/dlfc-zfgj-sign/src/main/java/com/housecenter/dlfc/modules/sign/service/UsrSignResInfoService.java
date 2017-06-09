/**
* @name: UsrSignResInfoService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 保存支付宝号
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
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.housecenter.dlfc.modules.sign.entity.UsrSignResInfo;
import com.housecenter.dlfc.modules.sign.entity.UsrSignResInfoExample;
import com.housecenter.dlfc.modules.sign.entity.UsrSignResInfoExample.Criteria;
import com.housecenter.dlfc.modules.sign.mapper.UsrSignResInfoMapper;

/**
 * @name: UsrSignResInfoService
 * @description: 保存支付宝号
 * 
 * @version 1.0
 * @author yuanjw
 *
 */
@Service("usrSignResInfoService")
public class UsrSignResInfoService extends BaseService{
	/** 签到结果mapper*/
	@Autowired
	private UsrSignResInfoMapper usrSignResInfoMapper;
	/** 签到信息mapper*/
	@Autowired
	private UsrSignInfoService usrSignInfoService;
	/**
	 * 保存支付宝号
	 * 
	 * @param no 支付宝号
	 */
	public void save(String no){
		//获取信息
		UsrSignResInfoExample example = new UsrSignResInfoExample();
		Criteria c = example.createCriteria();
		// 获取经纪ID
		String empId = usrSignInfoService.getEmpId();
		c.andUeidEqualTo(empId);
		c.andDeleteFlgEqualTo((short)0);
		List<UsrSignResInfo> usrSignResInfoList = usrSignResInfoMapper.selectByExample(example);
		//判断是否存在，如果存在则进行更新操作，否则进行插入操作。
		if(usrSignResInfoList != null && usrSignResInfoList.size() > 0){
			usrSignResInfoList.get(0).setPayAccount(no);
			usrSignResInfoMapper.updateByPrimaryKeySelective(usrSignResInfoList.get(0));
		}else{
			UsrSignResInfo usrSignResInfo = new UsrSignResInfo();
			usrSignResInfo.preInsert();
			usrSignResInfo.setPayAccount(no);
			usrSignResInfo.setUeid(empId);
			usrSignResInfoMapper.insertSelective(usrSignResInfo);
		}
	}
	
	/**
	 * 判断经纪人是否添加了支付宝帐号
	 * 
	 * @return true 已经添加，false 没有添加
	 */
	public boolean aliFlag(){
		//获取信息
		UsrSignResInfoExample example = new UsrSignResInfoExample();
		Criteria c = example.createCriteria();
		// 获取经纪ID
		String empId = usrSignInfoService.getEmpId();
		c.andUeidEqualTo(empId);
		c.andDeleteFlgEqualTo((short)0);
		List<UsrSignResInfo> usrSignResInfoList = usrSignResInfoMapper.selectByExample(example);
		//判断经纪人支付宝账户是否存在
		if(usrSignResInfoList != null && usrSignResInfoList.size() > 0){
			if(StringUtils.isNotBlank(usrSignResInfoList.get(0).getPayAccount())){
				return true;
			}

		}
		return false;
	}

}
