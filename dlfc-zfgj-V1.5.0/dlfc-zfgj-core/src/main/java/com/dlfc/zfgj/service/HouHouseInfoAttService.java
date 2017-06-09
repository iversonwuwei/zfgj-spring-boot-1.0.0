/**
* @name: HouHouseInfoAttService.java 
*
* @Copyright: (c) 2016 DLFC. All rights reserved. 
*
* @description: 房源图片
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

import com.dlfc.zfgj.entity.HouHouseInfoAtt;
import com.dlfc.zfgj.entity.HouHouseInfoAttExample;
import com.dlfc.zfgj.entity.HouHouseInfoAttExample.Criteria;
import com.dlfc.zfgj.mapper.HouHouseInfoAttMapper;

/**
 * @name: HouHouseInfoAttService 
 * @description: 房源图片
 * @version 1.0
 * @author yuanjw
 *
 */
@Service("houHouseAttService")
public class HouHouseInfoAttService {
	/**房源图片持久层对象*/
	@Autowired
	private HouHouseInfoAttMapper mapper;
	
	/**
	 * 新建房源-增加附件
	 * @param houseAtt 房源图片对象
	 */
	public void saveHouseAtt(HouHouseInfoAtt houseAtt) {
		houseAtt.preInsert();
		mapper.insertSelective(houseAtt);
	}

	/**
	 * 修改房源-修改房源附件
	 * @param houseAtt 房源图片对象
	 * @throws Exception
	 */
	public void updateHouseAtt(HouHouseInfoAtt houseAtt) {
		HouHouseInfoAttExample example = new HouHouseInfoAttExample();
		Criteria criteria = example.createCriteria();
		criteria.andHidEqualTo(houseAtt.getHid());
		houseAtt.preUpdate();
		
		mapper.updateByExampleSelective(houseAtt, example);
	}

	/**
	 * 修改房源-修改房源附件
	 * @param houseAtt 房源图片对象
	 * @throws Exception
	 */
	public void updateHouseAttById(HouHouseInfoAtt houseAtt) {
		houseAtt.preUpdate();
		mapper.updateByPrimaryKeySelective(houseAtt);
	}
	
	/**
	 * 修改房源-删除原有房源附件
	 * @param houseAtt 房源图片对象
	 */
	public void deleteHouseAtt(HouHouseInfoAtt houseAtt) {
		HouHouseInfoAttExample example = new HouHouseInfoAttExample();
		Criteria criteria = example.createCriteria();
		criteria.andHidEqualTo(houseAtt.getHid());
		
		houseAtt.setDeleteFlg((short)1);
		houseAtt.preUpdate();
		mapper.updateByExampleSelective(houseAtt, example);
	}
	
	/**
	 * 获取房源附件
	 * @param hid 房源ID
	 * @return 房源图片对象列表
	 */
	public List<HouHouseInfoAtt> getAtts(String hid){
		HouHouseInfoAttExample example = new HouHouseInfoAttExample();
		Criteria criteria = example.createCriteria();
		criteria.andHidEqualTo(hid);
		criteria.andDeleteFlgEqualTo((short)0);
		return mapper.selectByExample(example);
	}
}
