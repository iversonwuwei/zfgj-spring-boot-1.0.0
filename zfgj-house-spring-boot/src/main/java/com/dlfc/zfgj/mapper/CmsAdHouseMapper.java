package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.CmsAdHouse;
import com.dlfc.zfgj.entity.CmsAdHouseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface CmsAdHouseMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	int countByExample(CmsAdHouseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	int deleteByExample(CmsAdHouseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	int insert(CmsAdHouse record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	int insertSelective(CmsAdHouse record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	List<CmsAdHouse> selectByExample(CmsAdHouseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	CmsAdHouse selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	int updateByExampleSelective(@Param("record") CmsAdHouse record, @Param("example") CmsAdHouseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	int updateByExample(@Param("record") CmsAdHouse record, @Param("example") CmsAdHouseExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	int updateByPrimaryKeySelective(CmsAdHouse record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cms_ad_house
	 * @mbggenerated  Mon Dec 05 11:17:10 CST 2016
	 */
	int updateByPrimaryKey(CmsAdHouse record);
	
//	List<CmsAdHouse> findUnderHouse(String id);
}