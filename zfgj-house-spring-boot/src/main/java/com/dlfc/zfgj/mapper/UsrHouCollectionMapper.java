package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.UsrHouCollection;
import com.dlfc.zfgj.entity.UsrHouCollectionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface UsrHouCollectionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    int countByExample(UsrHouCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    int deleteByExample(UsrHouCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    int insert(UsrHouCollection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    int insertSelective(UsrHouCollection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    List<UsrHouCollection> selectByExample(UsrHouCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    UsrHouCollection selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    int updateByExampleSelective(@Param("record") UsrHouCollection record, @Param("example") UsrHouCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    int updateByExample(@Param("record") UsrHouCollection record, @Param("example") UsrHouCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    int updateByPrimaryKeySelective(UsrHouCollection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_hou_collection
     *
     * @mbggenerated Tue Apr 26 09:33:18 CST 2016
     */
    int updateByPrimaryKey(UsrHouCollection record);
}