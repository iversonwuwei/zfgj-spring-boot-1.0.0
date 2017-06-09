package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.HouCoOwnerTemp;
import com.dlfc.zfgj.entity.HouCoOwnerTempExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface HouCoOwnerTempMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    int countByExample(HouCoOwnerTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    int deleteByExample(HouCoOwnerTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    int insert(HouCoOwnerTemp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    int insertSelective(HouCoOwnerTemp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    List<HouCoOwnerTemp> selectByExample(HouCoOwnerTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    HouCoOwnerTemp selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    int updateByExampleSelective(@Param("record") HouCoOwnerTemp record, @Param("example") HouCoOwnerTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    int updateByExample(@Param("record") HouCoOwnerTemp record, @Param("example") HouCoOwnerTempExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    int updateByPrimaryKeySelective(HouCoOwnerTemp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_co_owner_temp
     *
     * @mbggenerated Thu Feb 18 11:15:19 CST 2016
     */
    int updateByPrimaryKey(HouCoOwnerTemp record);
    
    List<HouCoOwnerTemp> selectByExampleWithPerson(HouCoOwnerTempExample example);
    
    /**
     * 查询房主和共有人
     * @param hid 房源id
     * @return
     */
    List<HouCoOwnerTemp> selectByOwnerTemp(String hid);
}