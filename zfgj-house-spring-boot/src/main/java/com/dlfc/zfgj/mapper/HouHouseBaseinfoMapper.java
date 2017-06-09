package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.HouHouseBaseinfo;
import com.dlfc.zfgj.entity.HouHouseBaseinfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface HouHouseBaseinfoMapper extends MyMapper<HouHouseBaseinfo>{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	int countByExample(HouHouseBaseinfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	int deleteByExample(HouHouseBaseinfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	int insert(HouHouseBaseinfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	int insertSelective(HouHouseBaseinfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	List<HouHouseBaseinfo> selectByExample(HouHouseBaseinfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	HouHouseBaseinfo selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	int updateByExampleSelective(@Param("record") HouHouseBaseinfo record,
                                 @Param("example") HouHouseBaseinfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	int updateByExample(@Param("record") HouHouseBaseinfo record, @Param("example") HouHouseBaseinfoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	int updateByPrimaryKeySelective(HouHouseBaseinfo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue May 10 17:01:09 CST 2016
	 */
	int updateByPrimaryKey(HouHouseBaseinfo record);
}