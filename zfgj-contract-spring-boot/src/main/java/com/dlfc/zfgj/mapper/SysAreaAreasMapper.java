package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysAreaAreas;
import com.dlfc.zfgj.entity.SysAreaAreasExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface SysAreaAreasMapper extends MyMapper<SysAreaAreas> {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	int countByExample(SysAreaAreasExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	int deleteByExample(SysAreaAreasExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	int insert(SysAreaAreas record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	int insertSelective(SysAreaAreas record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	List<SysAreaAreas> selectByExample(SysAreaAreasExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	SysAreaAreas selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	int updateByExampleSelective(@Param("record") SysAreaAreas record, @Param("example") SysAreaAreasExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	int updateByExample(@Param("record") SysAreaAreas record, @Param("example") SysAreaAreasExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	int updateByPrimaryKeySelective(SysAreaAreas record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_area_areas
	 * @mbggenerated  Mon May 23 17:09:59 CST 2016
	 */
	int updateByPrimaryKey(SysAreaAreas record);
//
//	//获取区域对应�?��商圈列表
//    List<SysAreaAreas> getAreasList(String cityId);
//
//    //获取区域名称
//    SysAreaAreas getArea(SysAreaAreas record);
//    //获取商圈名称和对应的区域名称
//    SysAreaAreas getAreaAndTrade(SysAreaAreas record);
//
//	List<SysAreaProvinces> selectAllProvinces();
//
//	List<SysAreaCities> selectAllCitys(String provinceId);
}