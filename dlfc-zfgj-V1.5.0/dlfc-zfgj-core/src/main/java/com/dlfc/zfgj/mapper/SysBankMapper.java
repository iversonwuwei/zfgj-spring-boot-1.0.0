package com.dlfc.zfgj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysBank;
import com.dlfc.zfgj.entity.SysBankExample;
@MyBatisDao
public interface SysBankMapper extends MyMapper<SysBank> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    int countByExample(SysBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    int deleteByExample(SysBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    int insert(SysBank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    int insertSelective(SysBank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    List<SysBank> selectByExample(SysBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    SysBank selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysBank record, @Param("example") SysBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    int updateByExample(@Param("record") SysBank record, @Param("example") SysBankExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    int updateByPrimaryKeySelective(SysBank record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_bank
     *
     * @mbggenerated Fri Nov 20 14:38:26 CST 2015
     */
    int updateByPrimaryKey(SysBank record);
}