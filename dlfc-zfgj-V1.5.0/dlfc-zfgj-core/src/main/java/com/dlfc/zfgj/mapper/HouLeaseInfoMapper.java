package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.HouLeaseInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface HouLeaseInfoMapper extends MyMapper<HouLeaseInfo>{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int countByExample(HouLeaseInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int deleteByExample(HouLeaseInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int insert(HouLeaseInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int insertSelective(HouLeaseInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	List<HouLeaseInfo> selectByExampleWithBLOBs(HouLeaseInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	List<HouLeaseInfo> selectByExample(HouLeaseInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	HouLeaseInfo selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int updateByExampleSelective(@Param("record") HouLeaseInfo record,
			@Param("example") HouLeaseInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int updateByExampleWithBLOBs(@Param("record") HouLeaseInfo record,
			@Param("example") HouLeaseInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int updateByExample(@Param("record") HouLeaseInfo record,
			@Param("example") HouLeaseInfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int updateByPrimaryKeySelective(HouLeaseInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int updateByPrimaryKeyWithBLOBs(HouLeaseInfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_lease_info
	 * @mbggenerated  Thu Jan 05 18:23:15 CST 2017
	 */
	int updateByPrimaryKey(HouLeaseInfo record);

	String getDistrictName(HouLeaseInfo record);
	
	int updateLeaseByExample(@Param("record") HouLeaseInfo record, @Param("example") HouLeaseInfoExample example);
}