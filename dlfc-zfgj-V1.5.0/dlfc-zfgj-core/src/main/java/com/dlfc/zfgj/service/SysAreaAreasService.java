/**
* @name: SysAreaAreasService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 获取区域信息
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

import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.SpringContextHolder;
import com.dlfc.zfgj.entity.SysAreaAreas;
import com.dlfc.zfgj.entity.SysAreaAreasExample;
import com.dlfc.zfgj.entity.SysAreaAreasExample.Criteria;
import com.dlfc.zfgj.mapper.SysAreaAreasMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @name: SysAreaAreasService 区域
 * @description: 获取区域信息
 * @version 1.0
 * @author yuanjw
 *
 */
@Service("sysAreaAreasService")
public class SysAreaAreasService {
	/**区域信息mapper*/
	private static SysAreaAreasMapper areaMapper = SpringContextHolder.getBean("sysAreaAreasMapper");

	/**
	 * 获取区域列表
	 * @param id 区域ID
	 * @return 区域列表
	 */
	public List<SysAreaAreas> getAreaName(String id) {
		SysAreaAreasExample areaExample = new SysAreaAreasExample();
		Criteria crit = areaExample.createCriteria();
		crit.andCityIdEqualTo(PropertyUtils.getSysVal("system.city.code"));
		return areaMapper.selectByExample(areaExample);
	}

	/**
	 * 获取区域列表 没有参数
	 * @return
	 */
	public List<SysAreaAreas> getAreaNames(){
		SysAreaAreasExample areaExample = new SysAreaAreasExample();
		Criteria crit = areaExample.createCriteria();
		crit.andCityIdEqualTo(PropertyUtils.getSysVal("system.city.code"));
		return areaMapper.selectByExample(areaExample);
	}

	/**
	 * 获取区域以及对应的商圈
	 * @param cityId 城市ID
	 * @return 区域列表
	 */
	public static List<SysAreaAreas> getAreasList(String cityId) {
		List<SysAreaAreas> areaList = new ArrayList<SysAreaAreas>();
		areaList = areaMapper.getAreasList(cityId);
		return areaList;
	}

}
