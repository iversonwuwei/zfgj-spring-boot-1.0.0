/**
* @name: CompService.java 
*
* @Copyright: (c) 2015 DLFC. All rights reserved. 
*
* @description: 公司信息
*
* @version: 1.0
* @date : 2015年11月17日 
* @author: hanjiaqi 
*
* @Modification  History:<br>
*  Date          Author         Version        Discription
*  2015年11月17日       hanjiaqi        1.0             <修改原因描述>
*/
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.AgtCompInfo;
import com.dlfc.zfgj.entity.AgtCompInfoExample;
import com.dlfc.zfgj.entity.AgtCompInfoExample.Criteria;
import com.dlfc.zfgj.mapper.AgtCompInfoMapper;

/**
 * @name: CompService
 * @description: 公司信息
 * @version 1.0
 * @author hanjiaqi
 *
 */
@Service("compService")
public class CompService {
	/** 公司信息mapper */
	@Autowired
	private AgtCompInfoMapper agtCompInfoMapper;

	/**
	 * 查询公司信息
	 * 
	 * @param id
	 *            部门ID
	 * @return 公司信息对象
	 */
	public AgtCompInfo findByOffId(String id) {
		AgtCompInfoExample emp = new AgtCompInfoExample();
		Criteria cri = emp.createCriteria();
		cri.andOfficeIdEqualTo(id);
		// 公司信息对象列表
		List<AgtCompInfo> agtCompInfoList = agtCompInfoMapper.selectByExampleList(emp);
		// 公司对象非空
		if (agtCompInfoList != null && agtCompInfoList.size() > 0) {
			return agtCompInfoList.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 更新公司信息
	 * 
	 * @param record
	 *            公司信息对象
	 * @return 更新成功失败状态
	 */
	public int updateByOffId(AgtCompInfo record) {
		record.preUpdate();
		AgtCompInfoExample emp = new AgtCompInfoExample();
		Criteria cri = emp.createCriteria();
		cri.andOfficeIdEqualTo(record.getOfficeId());
		// 更新公司信息
		return agtCompInfoMapper.updateByExampleSelective(record, emp);
	}
}
