package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.HouHouseInfoLog;
import com.dlfc.zfgj.entity.HouHouseInfoLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface HouHouseInfoLogMapper extends MyMapper<HouHouseInfoLog>{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	int countByExample(HouHouseInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	int deleteByExample(HouHouseInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	int insert(HouHouseInfoLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	int insertSelective(HouHouseInfoLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	List<HouHouseInfoLog> selectByExample(HouHouseInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	HouHouseInfoLog selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	int updateByExampleSelective(@Param("record") HouHouseInfoLog record,
			@Param("example") HouHouseInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	int updateByExample(@Param("record") HouHouseInfoLog record, @Param("example") HouHouseInfoLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	int updateByPrimaryKeySelective(HouHouseInfoLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_log
	 * @mbggenerated  Wed Feb 17 19:48:43 CST 2016
	 */
	int updateByPrimaryKey(HouHouseInfoLog record);
}