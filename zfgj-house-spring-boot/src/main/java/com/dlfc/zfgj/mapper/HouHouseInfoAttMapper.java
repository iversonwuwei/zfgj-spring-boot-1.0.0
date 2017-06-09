package com.dlfc.zfgj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.HouHouseInfoAtt;
import com.dlfc.zfgj.entity.HouHouseInfoAttExample;
@MyBatisDao
public interface HouHouseInfoAttMapper extends MyMapper<HouHouseInfoAtt>{

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	int countByExample(HouHouseInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	int deleteByExample(HouHouseInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	int insert(HouHouseInfoAtt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	int insertSelective(HouHouseInfoAtt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	List<HouHouseInfoAtt> selectByExample(HouHouseInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	HouHouseInfoAtt selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	int updateByExampleSelective(@Param("record") HouHouseInfoAtt record,
                                 @Param("example") HouHouseInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	int updateByExample(@Param("record") HouHouseInfoAtt record,
                        @Param("example") HouHouseInfoAttExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	int updateByPrimaryKeySelective(HouHouseInfoAtt record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_info_att
	 * @mbggenerated  Wed Dec 16 20:25:00 CST 2015
	 */
	int updateByPrimaryKey(HouHouseInfoAtt record);
	
}