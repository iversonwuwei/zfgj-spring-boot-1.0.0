/**
 * @name: UsrHouCollectionService.java 
 *
 * @Copyright: (c) 2016 DLFC. All rights reserved. 
 *
 * @description: 
 *
 * @version: 1.0
 * @date : 2016年1月28日 
 * @author: fancy 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2016年1月28日       fancy        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.UsrHouCollection;
import com.dlfc.zfgj.entity.UsrHouCollectionExample;
import com.dlfc.zfgj.entity.UsrHouCollectionExample.Criteria;
import com.dlfc.zfgj.mapper.AgtEmpInfoMapper;
import com.dlfc.zfgj.mapper.UsrHouCollectionMapper;
import com.dlfc.admin.modules.sys.utils.UserUtils;

/**
 * @name: UsrHouCollectionService
 * @description:用户收藏房源表Service
 * @version 1.0
 * @author fancy
 *
 */
@Service("usrHouCollectionService")
public class UsrHouCollectionService {
	/**用户收藏房源持久侧对象*/
	@Autowired
	private UsrHouCollectionMapper mapper;
	/**经纪人持久层对象*/
	@Autowired
	private AgtEmpInfoMapper agtEmpInfoMapper;

	/**
	 * 收藏(想租)
	 * 
	 * @param hid 房源ID
	 * @param collectType 收藏类型
	 */
	public void doWant(String hid, String collectType) {
		if (collectType != null && !"".equals(collectType)&& !"1".equals(collectType)) {
			delete(hid);
		} else {
			insert(hid);
		}
	}

	/**
	 * 保存经纪人收藏的房源
	 * 
	 * @param hid 房源ID
	 */
	public void insert(String hid){
		//根据uid获取经纪人id
		String eid = agtEmpInfoMapper.getEid(UserUtils.getUser().getId());
		UsrHouCollection record = new UsrHouCollection();
		record.setHid(hid);
		record.setUid(eid);
		record.setCtime(com.dlfc.admin.common.utils.DateUtils.getSynchTime());
		record.preInsert();
		record.setIsNewRecord(true);
		mapper.insertSelective(record);
	}
	
	/**
	 * 收藏数
	 * 
	 * @param hid 房源ID
	 * @return 收藏数量
	 */
	public int selectCount(String hid) {
		UsrHouCollectionExample example = new UsrHouCollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andHidEqualTo(hid);
		List<UsrHouCollection> collections = mapper.selectByExample(example);
		return collections.size();
	}

	/**
	 * 
	 * 取消收藏
	 * @param hid 房源ID
	 */
	public void delete(String hid) {
		//根据uid获取经纪人id
		String eid = agtEmpInfoMapper.getEid(UserUtils.getUser().getId());
		UsrHouCollectionExample example = new UsrHouCollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(eid);
		criteria.andHidEqualTo(hid);
		mapper.deleteByExample(example);
	}

	/**
	 * 查看当前登录人是否想租过该房源
	 * 
	 * @param hid 房源id
	 * @return 收藏数量
	 */
	public int selectCurrent(String hid){
		//根据uid获取经纪人id
		String eid = agtEmpInfoMapper.getEid(UserUtils.getUser().getId());
		UsrHouCollectionExample example = new UsrHouCollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(eid);
		criteria.andHidEqualTo(hid);
		List<UsrHouCollection> collections = mapper.selectByExample(example);
		return collections.size();
	}
}
