
/**
* @name: SysOfficeAreaLinkService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年8月15日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年8月15日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.SysOfficeAreaLink;
import com.dlfc.zfgj.entity.SysOfficeAreaLinkExample;
import com.dlfc.zfgj.entity.SysOfficeAreaLinkExample.Criteria;
import com.dlfc.zfgj.mapper.SysOfficeAreaLinkMapper;

/**
 * @name: SysOfficeAreaLinkService
 * @description: 部门商圈对应关系
 * @version 1.0
 * @author yuanjw
 */
@Service("sysOfficeAreaLinkService")
public class SysOfficeAreaLinkService {
	/** 部门商圈mapper*/
	@Autowired
	private SysOfficeAreaLinkMapper sysOfficeAreaLinkMapper;
	
	/**
	 * 获取区域内商圈名称
	 * @param officeId 部门信息ID
	 * @return List<SysOfficeAreaLink> 部门商圈对象列表
	 */
	public List<SysOfficeAreaLink> find(String officeId) {
		SysOfficeAreaLink sysOfficeBusiness = new SysOfficeAreaLink();
		sysOfficeBusiness.setOfficeId(officeId);	
		return sysOfficeAreaLinkMapper.selectByOfficeId(sysOfficeBusiness);
	}
	
	/**
	 * 插入部门商圈信息
	 * @param officeId 部门信息ID
	 * @param areaId 商圈信息ID
	 */
	public void  insert(String officeId,String areaId) {
		SysOfficeAreaLink sysOfficeAreaLink = new SysOfficeAreaLink();
		sysOfficeAreaLink.setOfficeId(officeId);	
		sysOfficeAreaLink.setAreaId(areaId);
		sysOfficeAreaLink.preInsert();
		sysOfficeAreaLinkMapper.insertSelective(sysOfficeAreaLink);
	}
	
	/**
	 * 删除部门与商圈对应关系
	 * @param officeId 部门信息ID
	 */
	public void delete(String officeid) {
		SysOfficeAreaLinkExample sysOfficeBusinessExample = new SysOfficeAreaLinkExample();
		Criteria criteria = sysOfficeBusinessExample.createCriteria();
		criteria.andOfficeIdEqualTo(officeid);
		sysOfficeAreaLinkMapper.deleteByExample(sysOfficeBusinessExample);
	}

}
