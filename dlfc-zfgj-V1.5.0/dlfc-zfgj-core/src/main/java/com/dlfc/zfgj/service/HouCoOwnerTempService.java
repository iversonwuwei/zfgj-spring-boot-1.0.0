/**
* @name: HouCoOwnerTempService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 共有人信息临时表插入删除操作
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.HouCoOwnerTemp;
import com.dlfc.zfgj.entity.HouCoOwnerTempExample;
import com.dlfc.zfgj.entity.HouCoOwnerTempExample.Criteria;
import com.dlfc.zfgj.mapper.HouCoOwnerTempMapper;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: HouCoOwnerTempService
 * @description: 共有人信息临时表插入删除操作
 * @version 1.0
 * @author yuanjw
 *
 */
@Service("houCoOwnerTempService")
public class HouCoOwnerTempService {
	/** 共有人临时表信息mapper*/
	@Autowired
	private HouCoOwnerTempMapper houCoOwnerTempMapper;

	/**
	 * 插入共有人信息
	 * @param record 共有人信息对象
	 */
	public void insertSelective(HouCoOwnerTemp record) {
		record.preInsert();
		houCoOwnerTempMapper.insertSelective(record);
	}

	/**
	 * 删除共有人信息信息
	 * @param record 共有人信息对象
	 */
	public void deleteCoOwnerTemp(HouCoOwnerTemp record) {
		HouCoOwnerTempExample example = new HouCoOwnerTempExample();
		Criteria criteria = example.createCriteria();
		record.preUpdate();
		record.setDeleteFlg((short) YesNoEnum.YES_ENUM.getValue());
		criteria.andHidEqualTo(record.getHid());
		houCoOwnerTempMapper.updateByExampleSelective(record, example);
	}

}
