package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.CmsVoteTitle;
import com.dlfc.zfgj.entity.CmsVoteTitleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface CmsVoteTitleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    int countByExample(CmsVoteTitleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    int deleteByExample(CmsVoteTitleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    int insert(CmsVoteTitle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    int insertSelective(CmsVoteTitle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    List<CmsVoteTitle> selectByExample(CmsVoteTitleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    CmsVoteTitle selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    int updateByExampleSelective(@Param("record") CmsVoteTitle record, @Param("example") CmsVoteTitleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    int updateByExample(@Param("record") CmsVoteTitle record, @Param("example") CmsVoteTitleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    int updateByPrimaryKeySelective(CmsVoteTitle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    int updateByPrimaryKey(CmsVoteTitle record);
    
//    //根据倒叙时间查询投票
//    List<CmsVoteTitle> getVoteByCreatTime(String id);
    
}