package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysPayChannel;
import com.dlfc.zfgj.entity.SysPayChannelExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface SysPayChannelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    int countByExample(SysPayChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    int deleteByExample(SysPayChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    int insert(SysPayChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    int insertSelective(SysPayChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    List<SysPayChannel> selectByExample(SysPayChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    SysPayChannel selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    int updateByExampleSelective(@Param("record") SysPayChannel record, @Param("example") SysPayChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    int updateByExample(@Param("record") SysPayChannel record, @Param("example") SysPayChannelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    int updateByPrimaryKeySelective(SysPayChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_pay_channel
     *
     * @mbggenerated Sat Apr 23 11:25:45 CST 2016
     */
    int updateByPrimaryKey(SysPayChannel record);
    
    String findSvalue();
}