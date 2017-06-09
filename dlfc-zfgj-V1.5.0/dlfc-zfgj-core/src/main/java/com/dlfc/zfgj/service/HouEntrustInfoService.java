/**
* @name: HouEntrustInfoService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 房源委托
*
* @version: 1.0
* @date : 2016年3月25日 
* @author: fancy 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年3月25日       fancy        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.persistence.BasePage;
import com.dlfc.admin.modules.sys.utils.UserUtils;
import com.dlfc.zfgj.entity.HouEntrustInfo;
import com.dlfc.zfgj.entity.HouEntrustInfoExample;
import com.dlfc.zfgj.entity.HouEntrustInfoExample.Criteria;
import com.dlfc.zfgj.mapper.HouEntrustInfoMapper;
import com.housecenter.dlfc.zfgj.common.enums.EntrustStateEnum;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: HouEntrustInfoService
 * @description: 房源委托
 * @version 1.0
 * @author fancy
 *
 */
@Service("houEntrustInfoService")
public class HouEntrustInfoService {
	/** 房源委托对象mapper*/
	@Autowired
	private HouEntrustInfoMapper houEntrustInfoMapper;

	/**
	 * 查询分页数据-显示房源
	 * @param page 分页对象
	 * @param entity 房源委托对象
	 * @return 房源委托页面对象
	 */
	public BasePage<HouEntrustInfo> selectPage(BasePage<HouEntrustInfo> page, HouEntrustInfo entity) {
		entity.setPage(page);
		// 获取房源委托对象列表
		entity.setCurrentTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		List<HouEntrustInfo> list = houEntrustInfoMapper.selectEntrustInfoList(entity);
		// 房源委托对象列表非空
		if (list != null && !list.isEmpty()) {
			for (HouEntrustInfo houEntrustInfo : list) {
				// 隐藏房主名字
				houEntrustInfo.setName(hideOwnerName(houEntrustInfo.getName(), houEntrustInfo.getGender()));
			}
		}
		page.setList(list);
		return page;
	}

	/**
	 * 获取房主ID
	 * @param id 房源委托ID
	 * @return 房主UID
	 */
	public String getUid(String id) {
		String uid = "";
		// 获取房源委托对象
		HouEntrustInfo entrustInfo = selectEntInfo(id);
		// 房源委托对象非空
		if (entrustInfo != null) {
			uid = entrustInfo.getUid();
		}

		return uid;
	}

	/**
	 * 隐藏房主名字
	 * @param ownerName 房主全名
	 * @param gender 性别
	 * @return 房主姓氏
	 */
	private String hideOwnerName(String ownerName, int gender) {
		String name = ownerName;
		if (StringUtils.isNotEmpty(ownerName)) {
			// 房主姓名长度为2个字
			if (2 >= ownerName.length()) {
				name = ownerName.substring(0, 1);
			} else {
				// 房主名字2个字以上
				String surname = ownerName.substring(0, 2);
				int count = houEntrustInfoMapper.selectSurnamelist(surname);
				// 纯在复姓表里
				if (count > 0) {
					name = surname;
				} else {
					name = ownerName.substring(0, 1);
				}
			}
		}
		return name;
	}

	/**
	 * 获取委托信息
	 * @param id 房源委托ID
	 * @return 房源委托对象
	 */
	public HouEntrustInfo selectEntInfo(String id) {
		HouEntrustInfoExample example = new HouEntrustInfoExample();
		Criteria criteria = example.createCriteria();

		criteria.andIdEqualTo(id);
		criteria.andStatusEqualTo(EntrustStateEnum.COMMISSIONED_ENUM.getValue());
		criteria.andDeleteFlgEqualTo((short) YesNoEnum.YES_ENUM.getValue());
		return houEntrustInfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 锁定房源
	 * @param id 房源委托ID
	 */
	public void lockHouse(String id) {
		HouEntrustInfo record = new HouEntrustInfo();
		record.setId(id);
		record.preUpdate();
		record.setLockEid(UserUtils.getUser().getEmpId());
		record.setLockTime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		houEntrustInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 取消锁定房源
	 * @param id 房源委托ID
	 */
	public void lockCancel(String id) {
		houEntrustInfoMapper.updateByLock(id);
	}

	/**
	 * 通过主键更新
	 * @param record 房源委托对象
	 */
	public void updateByPrimaryKeySelective(HouEntrustInfo record) {
		record.preUpdate();
		houEntrustInfoMapper.updateByPrimaryKeySelective(record);
	}
}
