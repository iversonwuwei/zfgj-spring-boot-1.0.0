
/**
* @name: CmsAdService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年12月06日 
* @author:  
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年12月06日      LiJianLong        1.0             <修改原因描述>
*/

package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dlfc.admin.common.utils.SpringContextHolder;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.CmsAdHouse;
import com.dlfc.zfgj.entity.CmsAdHouseExample;
import com.dlfc.zfgj.entity.CmsAdHouseExample.Criteria;
import com.dlfc.zfgj.entity.CmsAdposition;
import com.dlfc.zfgj.mapper.CmsAdHouseMapper;
import com.dlfc.zfgj.mapper.CmsAdpositionMapper;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: CmsAdService
 * @description: 提供静态方法获取广告栏目
 * @version 1.0
 * @author LiJianLong
 *
 */
@Service("cmsAdService")
public class CmsAdService {
	/** 广告栏目 */
	private static CmsAdpositionMapper cmsAdpositionMapper = SpringContextHolder.getBean("cmsAdpositionMapper");
	/** 房源类型的广告栏目 */
	private static CmsAdHouseMapper cmsAdHouseMapper = SpringContextHolder.getBean("cmsAdHouseMapper");

	/**
	 * 获取广告栏目
	 * 
	 * @param enname
	 *            广告栏目英文名称
	 * @param displayNumber
	 *            查询数目
	 * @return List<CmsAdposition> 广告位列表
	 */
	public static List<CmsAdposition> getAd(String enname, String displayNumber) {
		// 获取广告位
		CmsAdposition cmsAdposition = cmsAdpositionMapper.getAdByEnname(enname);
		// 获取显示条数
		int num = 0;
		if (cmsAdposition != null) {
			num = cmsAdposition.getDisplayNumber();
		}
		// 如果前台设置了显示条数 使用前台的数字
		if (StringUtils.isNotBlank(displayNumber)) {
			cmsAdposition.setDisplayNumber(Integer.parseInt(displayNumber));
			cmsAdpositionMapper.updateByPrimaryKey(cmsAdposition);
		}

		// 查询广告栏目
		List<CmsAdposition> ad = cmsAdpositionMapper.findList(cmsAdposition);
		if (StringUtils.isNotBlank(displayNumber)) {
			cmsAdposition.setDisplayNumber(num);
			cmsAdpositionMapper.updateByPrimaryKey(cmsAdposition);
		}
		// 如果上架的数量小于要展示的数量，将已下架的房源添加进去
		if (ad != null && ad.size() > 0 && ad.get(0) != null && ad.get(0).getType() == 0
				&& ad.get(0).getHouses() != null 
				&& ad.get(0).getHouses().size() < Integer.parseInt(displayNumber)) {
			// 获取缺少的数量
			int adNumber = Integer.parseInt(displayNumber) - ad.get(0).getHouses().size();
			// 获取下架的出租信息
			List<CmsAdHouse> houses = cmsAdHouseMapper.findUnderHouse(cmsAdposition.getId());
			if (houses != null && houses.size() < adNumber) {
				adNumber = houses.size();
			}
			// 将下架的出租信息添加到返回的广告栏目列表中
			for (int i = 0; i < adNumber; i++) {
				if (houses != null) {
					ad.get(0).getHouses().add(houses.get(i));
				}
			}
		}

		return ad;
	}
	
	/**
	 * 查询所有房源类型广告栏目
	 * 
	 * @return 返回所有房源类型广告栏目List<房源类型广告栏目实体>
	 */
	public List<CmsAdHouse> getCmsAdHouseList() {
		CmsAdHouseExample example = new CmsAdHouseExample();
		Criteria criteria = example.createCriteria();
		criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		return cmsAdHouseMapper.selectByExample(example);
	}
}