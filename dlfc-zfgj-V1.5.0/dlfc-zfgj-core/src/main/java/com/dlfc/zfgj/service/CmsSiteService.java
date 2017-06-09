
/**
* @name: CmsSiteService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 
*
* @version: 1.0
* @date : 2016年12月05日 
* @author: LiJianLong 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2016年12月05日       LiJianLong        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import org.springframework.stereotype.Service;

import com.dlfc.admin.common.utils.SpringContextHolder;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.entity.CmsSite;
import com.dlfc.zfgj.entity.CmsSiteExample;
import com.dlfc.zfgj.entity.CmsSiteExample.Criteria;
import com.dlfc.zfgj.mapper.CmsSiteMapper;

/**
 * @name: CmsSiteService
 * @description: 站点Service
 * 
 * @version 1.0
 * @author LiJianLong
 *
 */
@Service("cmsSiteService")
public class CmsSiteService {
	/** 站点mapper */
	private static CmsSiteMapper cmsSiteMapper = SpringContextHolder.getBean("cmsSiteMapper");

	/**
	 * 获得站点信息
	 * 
	 * @param siteId
	 *            站点编号
	 * @return 返回站点信息
	 */
	public static CmsSite getSiteFoot(String siteId) {
		CmsSiteExample example = new CmsSiteExample();
		Criteria c = example.createCriteria();
		String id = "1";
		if (StringUtils.isNotBlank(siteId)) {
			id = siteId;
		}
		c.andIdEqualTo(id);
		CmsSite cmsSite = cmsSiteMapper.selectByPrimaryKey(id);
		return cmsSite;
	}
}
