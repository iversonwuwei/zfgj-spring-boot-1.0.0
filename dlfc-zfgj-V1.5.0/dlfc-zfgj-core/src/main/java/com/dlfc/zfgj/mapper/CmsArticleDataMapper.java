package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.CmsArticleData;
import com.dlfc.zfgj.entity.CmsArticleDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface CmsArticleDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int countByExample(CmsArticleDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int deleteByExample(CmsArticleDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int insert(CmsArticleData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int insertSelective(CmsArticleData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    List<CmsArticleData> selectByExampleWithBLOBs(CmsArticleDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    List<CmsArticleData> selectByExample(CmsArticleDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    CmsArticleData selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int updateByExampleSelective(@Param("record") CmsArticleData record, @Param("example") CmsArticleDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int updateByExampleWithBLOBs(@Param("record") CmsArticleData record, @Param("example") CmsArticleDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int updateByExample(@Param("record") CmsArticleData record, @Param("example") CmsArticleDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int updateByPrimaryKeySelective(CmsArticleData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(CmsArticleData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_article_data
     *
     * @mbggenerated Wed Apr 27 19:51:59 CST 2016
     */
    int updateByPrimaryKey(CmsArticleData record);
}