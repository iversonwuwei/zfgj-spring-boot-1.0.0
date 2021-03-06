package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysAreaAreas;
import com.dlfc.zfgj.entity.SysAreaAreasExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface SysAreaAreasMapper extends MyMapper<SysAreaAreas>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    int countByExample(SysAreaAreasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    int deleteByExample(SysAreaAreasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    int insert(SysAreaAreas record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    int insertSelective(SysAreaAreas record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    List<SysAreaAreas> selectByExample(SysAreaAreasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    SysAreaAreas selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysAreaAreas record, @Param("example") SysAreaAreasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    int updateByExample(@Param("record") SysAreaAreas record, @Param("example") SysAreaAreasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    int updateByPrimaryKeySelective(SysAreaAreas record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area_areas
     *
     * @mbggenerated Mon Nov 09 16:25:17 CST 2015
     */
    int updateByPrimaryKey(SysAreaAreas record);
    
//    //获取区域对应所有商圈列表
//    List<SysAreaAreas> getAreasList(String cityId);
}