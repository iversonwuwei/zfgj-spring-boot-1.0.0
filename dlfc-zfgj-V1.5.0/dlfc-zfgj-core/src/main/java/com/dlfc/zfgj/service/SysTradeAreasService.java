
/**
* @name: SysTradeAreasService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年12月5日 
* @author: fancy 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年12月5日       fancy        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.SysTradeAreas;
import com.dlfc.zfgj.entity.SysTradeAreasExample;
import com.dlfc.zfgj.entity.SysTradeAreasExample.Criteria;
import com.dlfc.zfgj.mapper.SysTradeAreasMapper;

/**
 * @name: SysTradeAreasService
 * @description: 区域Service
 * @version 1.0
 * @author fancy
 *
 */
@Service("sysTradeAreasService")
public class SysTradeAreasService {
	/**商圈持久层对象*/
	@Autowired
	private SysTradeAreasMapper sysTradeAreasMapper;

	/**
	 * 根据区域ID查询商圈列表
	 * 
	 * @param areaId 区域ID
	 * @return 商圈列表
	 */
	public List<SysTradeAreas> getTrade(String areaId) {
		SysTradeAreasExample example = new SysTradeAreasExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(areaId);
		return sysTradeAreasMapper.selectByExample(example);
	}

	/**
	 * 获取当前经纪人的区域ID，商圈ID
	 * 
	 * @param uid 经纪人ID
	 * @return 商圈对象列表
	 */
	public List<SysTradeAreas> getAreaIdByUid(String uid) {
		return sysTradeAreasMapper.getAreaIdByUid(uid);
	}
}
