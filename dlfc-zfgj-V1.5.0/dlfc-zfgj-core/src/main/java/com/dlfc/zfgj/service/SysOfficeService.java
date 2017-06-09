
/**
* @name: SysOfficeService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年7月11日 
* @author: yuanjw 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年7月11日       yuanjw        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.SysOffice;
import com.dlfc.zfgj.entity.SysOfficeExample;
import com.dlfc.zfgj.entity.SysOfficeExample.Criteria;
import com.dlfc.zfgj.mapper.SysOfficeMapper;

/**
 * @name: SysOfficeService
 * @description: 部门
 * @version 1.0
 * @author yuanjw
 *
 */
@Service("sysOfficeService")
public class SysOfficeService {
	/**部门mapper*/
	@Autowired
	SysOfficeMapper sysOfficeMapper;

	/**
	 * 通过name和parentId查找二级菜单
	 * 
	 * @param id 部门ID
	 * @param parentId 父ID
	 * @param name 部门名称
	 * @return 部门列表
	 */
	public List<SysOffice> getOfficeByParentId(String id, String parentId, String name) {
		SysOfficeExample example = new SysOfficeExample();
		Criteria c = example.createCriteria();
		if (StringUtils.isNotBlank(id)) {
			example.or().andParentIdsLike("%" + id + "%").andNameEqualTo(name).andDelFlagEqualTo("0");
			example.or().andParentIdEqualTo(parentId).andNameEqualTo(name).andDelFlagEqualTo("0");
		} else {
			c.andParentIdEqualTo(parentId);
			c.andNameEqualTo(name);
			c.andDelFlagEqualTo("0");
		}
		return sysOfficeMapper.selectByExample(example);
	}

	/**
	 * 通过name和parentId查找二级一下所有菜单（包含二级菜单）
	 * 
	 * @param parentId 父ID
	 * @param name 部门名称
	 * @return 部门列表
	 */
	public List<SysOffice> getOfficeByParentIdLike(String parentId, String name) {
		SysOfficeExample example = new SysOfficeExample();
		example.or().andIdEqualTo(parentId).andNameEqualTo(name).andDelFlagEqualTo("0");
		example.or().andParentIdsLike("%" + parentId + "%").andNameEqualTo(name).andDelFlagEqualTo("0");
		return sysOfficeMapper.selectByExample(example);
	}
	
	public SysOffice findByPrimaryKey(String primaryKey){
		return sysOfficeMapper.selectByPrimaryKey(primaryKey);
	}

	public void save(SysOffice office){
	    sysOfficeMapper.updateByPrimaryKey(office);
    }

}
