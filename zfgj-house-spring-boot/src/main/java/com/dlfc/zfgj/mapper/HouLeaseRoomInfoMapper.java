package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.HouLeaseRoomInfo;
import com.dlfc.zfgj.entity.HouLeaseRoomInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface HouLeaseRoomInfoMapper extends MyMapper<HouLeaseRoomInfo>{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	int countByExample(HouLeaseRoomInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	int deleteByExample(HouLeaseRoomInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	int insert(HouLeaseRoomInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	int insertSelective(HouLeaseRoomInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	List<HouLeaseRoomInfo> selectByExample(HouLeaseRoomInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	HouLeaseRoomInfo selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	int updateByExampleSelective(@Param("record") HouLeaseRoomInfo record,
                                 @Param("example") HouLeaseRoomInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	int updateByExample(@Param("record") HouLeaseRoomInfo record,
                        @Param("example") HouLeaseRoomInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	int updateByPrimaryKeySelective(HouLeaseRoomInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_room_info
	 * @mbggenerated  Fri Mar 18 10:36:59 CST 2016
	 */
	int updateByPrimaryKey(HouLeaseRoomInfo record);
}