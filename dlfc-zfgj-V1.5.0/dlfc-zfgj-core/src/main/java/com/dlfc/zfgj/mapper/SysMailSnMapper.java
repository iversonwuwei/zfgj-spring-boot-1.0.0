package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysMailSn;
import com.dlfc.zfgj.entity.SysMailSnExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface SysMailSnMapper extends MyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    int countByExample(SysMailSnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    int deleteByExample(SysMailSnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    int insert(SysMailSn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    int insertSelective(SysMailSn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    List<SysMailSn> selectByExample(SysMailSnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    SysMailSn selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysMailSn record, @Param("example") SysMailSnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    int updateByExample(@Param("record") SysMailSn record, @Param("example") SysMailSnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    int updateByPrimaryKeySelective(SysMailSn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mail_sn
     *
     * @mbggenerated Tue Nov 10 15:06:30 CST 2015
     */
    int updateByPrimaryKey(SysMailSn record);
    
    int find15(SysMailSn record);
}