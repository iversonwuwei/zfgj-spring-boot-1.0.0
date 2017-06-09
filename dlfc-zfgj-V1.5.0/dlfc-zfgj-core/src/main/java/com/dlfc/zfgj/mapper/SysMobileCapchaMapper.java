package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysMobileCapcha;
import com.dlfc.zfgj.entity.SysMobileCapchaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface SysMobileCapchaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    int countByExample(SysMobileCapchaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    int deleteByExample(SysMobileCapchaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    int insert(SysMobileCapcha record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    int insertSelective(SysMobileCapcha record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    List<SysMobileCapcha> selectByExample(SysMobileCapchaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    SysMobileCapcha selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    int updateByExampleSelective(@Param("record") SysMobileCapcha record, @Param("example") SysMobileCapchaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    int updateByExample(@Param("record") SysMobileCapcha record, @Param("example") SysMobileCapchaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    int updateByPrimaryKeySelective(SysMobileCapcha record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mobile_capcha
     *
     * @mbggenerated Fri Aug 05 14:58:22 CST 2016
     */
    int updateByPrimaryKey(SysMobileCapcha record);
}