/**
* @name: HouHouseBaseinfoService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 房源基础信息
*
* @version: 1.0
* @date : 2015年12月30日 
* @author: fancy 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年12月30日       fancy        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.HouHouseBaseinfo;
import com.dlfc.zfgj.entity.HouHouseBaseinfoExample;
import com.dlfc.zfgj.entity.HouHouseBaseinfoExample.Criteria;
import com.dlfc.zfgj.mapper.HouHouseBaseinfoMapper;

/**
 * @name: HouHouseBaseinfoService
 * @description: 房源基础信息
 * @version 1.0
 * @author fancy
 *
 */
@Service("houHouseBaseinfoService")
public class HouHouseBaseinfoService {
	/** 房源基础信息持久层对象 */
	@Autowired
	private HouHouseBaseinfoMapper mapper;

	/**
	 * 查询房源基础信息表是否已存在该房源
	 * 
	 * @param propertyIdNo
	 *            产权证件号
	 * @param propertyIdType
	 *            产权证件类型
	 * @return 房源基础信息对象列表
	 */
	public List<HouHouseBaseinfo> selectBaseinfo(String propertyIdNo, Integer propertyIdType) {
		HouHouseBaseinfoExample example = new HouHouseBaseinfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andPropertyIdNoEqualTo(propertyIdNo);
		criteria.andPropertyIdTypeEqualTo(propertyIdType);
		criteria.andDeleteFlgEqualTo((short) 0);
		criteria.andCertFlagEqualTo(1);
		criteria.andActiveStatusEqualTo(1);

		return mapper.selectByExample(example);
	}

	/**
	 * 插入房源基础信息表
	 * 
	 * @param record
	 *            房源基础信息对象
	 */
	public void insertBaseinfo(HouHouseBaseinfo record) {
		record.setIsNewRecord(true);
		record.preInsert();
		mapper.insertSelective(record);
	}
}
