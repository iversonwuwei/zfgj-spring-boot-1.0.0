package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysTradeStationLink;
import com.dlfc.zfgj.entity.SysTradeStationLinkExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface SysTradeStationLinkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_trade_station_link
     *
     * @mbggenerated Sat Apr 23 14:08:11 CST 2016
     */
    int countByExample(SysTradeStationLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_trade_station_link
     *
     * @mbggenerated Sat Apr 23 14:08:11 CST 2016
     */
    int deleteByExample(SysTradeStationLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_trade_station_link
     *
     * @mbggenerated Sat Apr 23 14:08:11 CST 2016
     */
    int insert(SysTradeStationLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_trade_station_link
     *
     * @mbggenerated Sat Apr 23 14:08:11 CST 2016
     */
    int insertSelective(SysTradeStationLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_trade_station_link
     *
     * @mbggenerated Sat Apr 23 14:08:11 CST 2016
     */
    List<SysTradeStationLink> selectByExample(SysTradeStationLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_trade_station_link
     *
     * @mbggenerated Sat Apr 23 14:08:11 CST 2016
     */
    int updateByExampleSelective(@Param("record") SysTradeStationLink record, @Param("example") SysTradeStationLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_trade_station_link
     *
     * @mbggenerated Sat Apr 23 14:08:11 CST 2016
     */
    int updateByExample(@Param("record") SysTradeStationLink record, @Param("example") SysTradeStationLinkExample example);
}