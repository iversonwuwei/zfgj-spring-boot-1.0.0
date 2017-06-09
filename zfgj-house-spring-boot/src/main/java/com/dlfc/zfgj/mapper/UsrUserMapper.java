package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.entity.UsrUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface UsrUserMapper extends MyMapper<UsrUser> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	int countByExample(UsrUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	int deleteByExample(UsrUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	int insert(UsrUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	int insertSelective(UsrUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	List<UsrUser> selectByExample(UsrUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	UsrUser selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	int updateByExampleSelective(@Param("record") UsrUser record,
                                 @Param("example") UsrUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	int updateByExample(@Param("record") UsrUser record,
                        @Param("example") UsrUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	int updateByPrimaryKeySelective(UsrUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user
	 * @mbggenerated  Thu Nov 05 11:00:14 CST 2015
	 */
	int updateByPrimaryKey(UsrUser record);
	
//	/**
//	 * ，联合查询usr_user,获取系统用户sys_user,以后重构
//	 * @param user
//	 * @return
//	 */
//	User getByLoginName(User user);
//
//	int isMailBinded(String email);
//
//	int isMobileBinded(String mobile);
//
//	List<UsrUser> selectAllByExample(UsrUserExample example);
//
//	int isEmpCerted(String uid);
//
//	String isEmpCerting(String uid);
//
//	int isUserNameBinded(String userName);
//
//	List<UsrUser> selectByUsernameOrMobile(@Param("userName")String userName,
//										   @Param("mobile")String mobile);
}