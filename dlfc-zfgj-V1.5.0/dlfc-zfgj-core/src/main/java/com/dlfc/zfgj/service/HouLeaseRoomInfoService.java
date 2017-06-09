/**
 * @name: HouLeaseRoomInfoService.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 出租房间信息操作
 *
 * @version: 1.0
 * @date : 2015年12月7日 
 * @author: fancy 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年12月7日       fancy        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlfc.zfgj.entity.HouLeaseRoomInfo;
import com.dlfc.zfgj.entity.HouLeaseRoomInfoExample;
import com.dlfc.zfgj.entity.HouLeaseRoomInfoExample.Criteria;
import com.dlfc.zfgj.mapper.HouLeaseRoomInfoMapper;
import com.housecenter.dlfc.zfgj.common.enums.YesNoEnum;

/**
 * @name: HouLeaseRoomInfoService
 * @description: 出租房间信息操作
 * @version 1.0
 * @author fancy
 *
 */
@Service("houLeaseRoomInfoService")
public class HouLeaseRoomInfoService {
	/**出租房间信息持久层对象*/
	@Autowired
	private HouLeaseRoomInfoMapper houLeaseInfoMapper;

	/**
	 * 插入房间信息
	 * @param record 出租房间信息对象
	 */
	public void insertRoom(HouLeaseRoomInfo record) {
		record.setIsNewRecord(true);
		record.preInsert();
		houLeaseInfoMapper.insertSelective(record);
	}

	/**
	 * 根据出租信息ID删除房间
	 * @param leaseid 出租信息ID
	 */
	public void deleteRoom(String leaseid) {
		HouLeaseRoomInfo record = new HouLeaseRoomInfo();
		HouLeaseRoomInfoExample example = new HouLeaseRoomInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andLeaseidEqualTo(leaseid);
		record.setDeleteFlg((short) 1);
		record.preUpdate();
		houLeaseInfoMapper.updateByExampleSelective(record, example);
	}

	/**
	 * 获取房间对象列表
	 * @param id 出租信息ID
	 * @param 出租信息房间对象列表
	 */
	public List<HouLeaseRoomInfo> selectRoom(String id) {
		HouLeaseRoomInfoExample example = new HouLeaseRoomInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andLeaseidEqualTo(id);
		criteria.andDeleteFlgEqualTo((short) 0);
		return houLeaseInfoMapper.selectByExample(example);
	}

	/**
	 * 改变房间状态
	 * @param id 房间ID
	 * @param status 房间状态
	 */
	public void changeRoomStatus(String id, String status) {
		HouLeaseRoomInfo record = new HouLeaseRoomInfo();
		record.setId(id);
		record.setStatus(Integer.parseInt(status));
		record.preUpdate();
		houLeaseInfoMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 通过房间ID获取出租信息ID
	 * @param id 房间ID
	 * @return 出租信息ID
	 */
	public String selectLeaseid(String id) {
		return houLeaseInfoMapper.selectByPrimaryKey(id).getLeaseid();
	}

	/**
	 * 获取可租房间数
	 * @param id 出租信息ID
	 * @return 出租信息对应的房间数
	 */
	public int selectRoomCount(String id) {
		HouLeaseRoomInfoExample example = new HouLeaseRoomInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andLeaseidEqualTo(id);
		criteria.andDeleteFlgEqualTo((short) YesNoEnum.NO_ENUM.getValue());
		criteria.andStatusEqualTo(YesNoEnum.NO_ENUM.getValue());
		List<HouLeaseRoomInfo> infos = houLeaseInfoMapper.selectByExample(example);
		return infos.size();
	}
}
