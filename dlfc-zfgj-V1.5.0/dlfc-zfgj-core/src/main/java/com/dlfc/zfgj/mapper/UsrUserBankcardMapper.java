package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.UsrUserBankcard;
import com.dlfc.zfgj.entity.UsrUserBankcardExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface UsrUserBankcardMapper extends MyMapper<UsrUserBankcard> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	int countByExample(UsrUserBankcardExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	int deleteByExample(UsrUserBankcardExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	int insert(UsrUserBankcard record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	int insertSelective(UsrUserBankcard record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	List<UsrUserBankcard> selectByExample(UsrUserBankcardExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	UsrUserBankcard selectByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	int updateByExampleSelective(@Param("record") UsrUserBankcard record,
			@Param("example") UsrUserBankcardExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	int updateByExample(@Param("record") UsrUserBankcard record,
			@Param("example") UsrUserBankcardExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	int updateByPrimaryKeySelective(UsrUserBankcard record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table usr_user_bankcard
	 * @mbggenerated  Sat Apr 23 13:12:42 CST 2016
	 */
	int updateByPrimaryKey(UsrUserBankcard record);

	int isCardBinded(String cardNum);
}