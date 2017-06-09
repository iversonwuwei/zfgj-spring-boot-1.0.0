package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysOffice;
import com.dlfc.zfgj.entity.SysOfficeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface SysOfficeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    int countByExample(SysOfficeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    int deleteByExample(SysOfficeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    int insert(SysOffice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    int insertSelective(SysOffice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    List<SysOffice> selectByExample(SysOfficeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    SysOffice selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysOffice record, @Param("example") SysOfficeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    int updateByExample(@Param("record") SysOffice record, @Param("example") SysOfficeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    int updateByPrimaryKeySelective(SysOffice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office
     *
     * @mbggenerated Tue Nov 10 16:06:28 CST 2015
     */
    int updateByPrimaryKey(SysOffice record);
}