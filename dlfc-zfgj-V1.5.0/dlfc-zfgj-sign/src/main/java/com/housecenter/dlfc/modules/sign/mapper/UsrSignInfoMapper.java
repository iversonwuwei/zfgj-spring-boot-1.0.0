package com.housecenter.dlfc.modules.sign.mapper;


import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.housecenter.dlfc.modules.sign.entity.UsrSignInfo;
import com.housecenter.dlfc.modules.sign.entity.UsrSignInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface UsrSignInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    int countByExample(UsrSignInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    int deleteByExample(UsrSignInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    int insert(UsrSignInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    int insertSelective(UsrSignInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    List<UsrSignInfo> selectByExample(UsrSignInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    UsrSignInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    int updateByExampleSelective(@Param("record") UsrSignInfo record, @Param("example") UsrSignInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    int updateByExample(@Param("record") UsrSignInfo record, @Param("example") UsrSignInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    int updateByPrimaryKeySelective(UsrSignInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usr_sign_info
     *
     * @mbggenerated Mon Dec 05 17:53:56 CST 2016
     */
    int updateByPrimaryKey(UsrSignInfo record);
    
    List<UsrSignInfo> getUsrSignInfo(String empId);
    
    int getDays();
}