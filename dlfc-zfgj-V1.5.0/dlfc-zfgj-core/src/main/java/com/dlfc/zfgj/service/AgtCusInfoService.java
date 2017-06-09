/**
* @name: AgtCusInfoService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 客户信息增删改查，添加备注等操作
*
* @version: 1.0
* @date : 2016年2月29日 
* @author: yuanjiwei 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年2月29日      yuanjiwei       1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.zfgj.entity.AgtCusComment;
import com.dlfc.zfgj.entity.AgtCusCommentExample;
import com.dlfc.zfgj.entity.AgtCusInfo;
import com.dlfc.zfgj.entity.AgtCusInfoExample;
import com.dlfc.zfgj.entity.AgtRequirementInfo;
import com.dlfc.zfgj.mapper.AgtCusCommentMapper;
import com.dlfc.zfgj.mapper.AgtCusInfoMapper;
import com.dlfc.zfgj.mapper.AgtRequirementInfoMapper;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * 
 * @name: AgtCusInfoService
 * @description: 客户信息增删改查，添加备注等操作
 * @version 1.0
 * @author XuChengGang
 *
 */
@Service("agtCusInfoService")
public class AgtCusInfoService {
	/** 客户信息Mapper */
	@Autowired
	private AgtCusInfoMapper agtCusInfoMapper;
	/** 客户备注Mapper */
	@Autowired
	private AgtCusCommentMapper cusCommentMapper;
	/** 客户需求Mapper */
	@Autowired
	private AgtRequirementInfoMapper agtRequirementInfoMapper;

	/**
	 * 客户列表
	 * 
	 * @param page
	 *            客户信息分页对象
	 * @param entity
	 *            客户信息对象
	 * @return 客户信息分页对象
	 */
	public BasePage<AgtCusInfo> selectPage(BasePage<AgtCusInfo> page, AgtCusInfo entity) {
		entity.setPage(page);
		// 获取客户信息对象列表
		List<AgtCusInfo> list = agtCusInfoMapper.selectCustInfoList(entity);
		page.setList(list);
		return page;
	}

	/**
	 * 删除客户
	 * 
	 * @param entity
	 *            客户信息对象
	 */
	public void deleteCust(AgtCusInfo entity) {
		entity.preUpdate();
		entity.setDeleteFlg((short) 1);
		// 删除客户信息
		agtCusInfoMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 修改客户状态
	 * 
	 * @param entity
	 *            客户信息对象
	 */
	public void updateStatus(AgtCusInfo entity) {
		entity.preUpdate();
		// 修改客户状态
		agtCusInfoMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 根据特定条件更新客户表数据
	 * 
	 * @param entity
	 *            客户信息对象
	 * @param example
	 *            客户信息条件对象
	 * @author HAN.JIAQI 2016/07/22
	 */
	public void updateStatus(AgtCusInfo entity, AgtCusInfoExample example) {
		entity.preUpdate();
		// 更新客户数据
		agtCusInfoMapper.updateByExampleSelective(entity, example);
	}

	/**
	 * 添加客户
	 * 
	 * @param entity
	 *            客户信息对象
	 */
	public void insertCust(AgtCusInfo entity) {
		entity.preInsert();
		// 添加客户信息
		agtCusInfoMapper.insertSelective(entity);
	}

	/**
	 * 添加备注
	 * 
	 * @param entity
	 *            客户信息对象
	 */
	public void insertRemark(AgtCusComment entity) {
		entity.preInsert();
		entity.setEventTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		// 添加备注
		cusCommentMapper.insertSelective(entity);
	}

	/**
	 * 修改备注
	 * 
	 * @param entity:客户信息对象
	 * @author HAN.JIAQI 2016/06/16
	 */
	public void updateRemark(AgtCusComment entity) {
		entity.preUpdate();
		entity.setEventTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		AgtCusCommentExample example = new AgtCusCommentExample();
		AgtCusCommentExample.Criteria cri = example.or();
		cri.andIdEqualTo(entity.getId());
		cri.andCustomerIdEqualTo(entity.getCustomerId());

		// 关联的客户id不更新
		entity.setCustomerId(null);
		// 修改备注
		cusCommentMapper.updateByExampleSelective(entity, example);
	}

	/**
	 * 删除备注
	 * 
	 * @param entity
	 *            客户信息对象
	 * @author HAN.JIAQI 2016/06/16
	 */
	public void deleteRemark(AgtCusComment entity) {
		entity.preUpdate();
		entity.setEventTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		AgtCusCommentExample example = new AgtCusCommentExample();
		AgtCusCommentExample.Criteria cri = example.or();
		cri.andIdEqualTo(entity.getId());
		cri.andCustomerIdEqualTo(entity.getCustomerId());

		// 关联的客户id不更新
		entity.setCustomerId(null);
		entity.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		// 删除备注
		cusCommentMapper.updateByExampleSelective(entity, example);
	}

	/**
	 * 编辑客户
	 * 
	 * @param entity
	 *            客户信息对象
	 */
	public void updateCust(AgtCusInfo entity) {
		entity.preUpdate();
		// 修改客户信息
		agtCusInfoMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 删除客户需求
	 * 
	 * @param entity
	 *            客户需求信息对象
	 */
	public void deleteRequire(AgtRequirementInfo entity) {
		entity.preUpdate();
		entity.setDeleteFlg((short) 1);
		// 删除客户需求
		agtRequirementInfoMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 添加客户需求
	 * 
	 * @param entity
	 *            客户需求对象
	 */
	public void insertRequire(AgtRequirementInfo entity) {
		entity.preInsert();
		// 添加客户需求
		agtRequirementInfoMapper.insertSelective(entity);
	}

	/**
	 * 查询客户信息
	 * 
	 * @param page
	 *            客户信息分页对象
	 * @param entity
	 *            客户信息对象
	 * @return 客户信息分页对象
	 */

	public BasePage<AgtCusInfo> selectPagee(BasePage<AgtCusInfo> page, AgtCusInfo entity) {
		entity.setPage(page);
		// 获取客户信息对象列表
		List<AgtCusInfo> list = agtCusInfoMapper.selectAllCustInfoList(entity);
		page.setList(list);
		return page;
	}

}
