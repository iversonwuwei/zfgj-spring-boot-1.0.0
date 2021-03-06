package com.housecenter.dlfc.modules.sign.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.housecenter.dlfc.modules.sign.entity.UsrSignResInfo;
import com.housecenter.dlfc.modules.sign.entity.UsrSignResInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface UsrSignResInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    int countByExample(UsrSignResInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    int deleteByExample(UsrSignResInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    int insert(UsrSignResInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    int insertSelective(UsrSignResInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    List<UsrSignResInfo> selectByExample(UsrSignResInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    UsrSignResInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    int updateByExampleSelective(@Param("record") UsrSignResInfo record, @Param("example") UsrSignResInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    int updateByExample(@Param("record") UsrSignResInfo record, @Param("example") UsrSignResInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    int updateByPrimaryKeySelective(UsrSignResInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_res_info
     *
     * @mbggenerated Tue Dec 06 10:07:53 CST 2016
     */
    int updateByPrimaryKey(UsrSignResInfo record);
    
    List<UsrSignResInfo> findByExample(UsrSignResInfoExample example);
}