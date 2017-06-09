
/**
* @name: SysCodeService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2015年11月5日 
* @author: QiuFei 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月5日       QiuFei        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.SysCode;
import com.dlfc.zfgj.entity.SysCodeExample;
import com.dlfc.zfgj.entity.SysCodeExample.Criteria;
import com.dlfc.zfgj.mapper.SysCodeMapper;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: SysCodeService 系统code
 * @description: 获取系统code
 * @version 1.0
 * @author QiuFei
 *
 */
@Service("sysCodeService")
public class SysCodeService {
	/** 数据字典Mapper */
	@Autowired
	private SysCodeMapper codeMapper;

	/**
	 * 通过code类型获取系统code列表
	 * @param type code类型
	 * @return List<SysCode> 系统code对象列表
	 */
	public List<SysCode> getCodeByType(String type) {
		SysCodeExample sysCodeExample = new SysCodeExample();
		Criteria criteria = sysCodeExample.createCriteria();
		criteria.andTypeEqualTo(type);
		criteria.andDeleteFlgEqualTo((short)YesNoEnum.NO_ENUM.getValue());
		//按照sort字段升序排列
		if("property_id_type".equals(type)){
			sysCodeExample.setOrderByClause("sort DESC");
		}else{
			sysCodeExample.setOrderByClause("sort");
		}
		return codeMapper.selectByExample(sysCodeExample);
	}
	
	/**
	 * 通过code名称和code类型获取系统code对象列表
	 * @param name code名称
	 * @param type code类型
	 * @return List<SysCode> 系统code对象列表
	 */
	public List<SysCode> getCodeByType(String name,String type) {
		SysCodeExample sysCodeExample = new SysCodeExample();
		Criteria criteria = sysCodeExample.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andTypeEqualTo(type);
		return codeMapper.selectByExample(sysCodeExample);
	}
	
	
}
