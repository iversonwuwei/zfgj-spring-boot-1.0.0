package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.CmsGuestBook;
import com.dlfc.zfgj.entity.CmsGuestBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface CmsGuestBookMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    int countByExample(CmsGuestBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    int deleteByExample(CmsGuestBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    int insert(CmsGuestBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    int insertSelective(CmsGuestBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    List<CmsGuestBook> selectByExample(CmsGuestBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    CmsGuestBook selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    int updateByExampleSelective(@Param("record") CmsGuestBook record, @Param("example") CmsGuestBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    int updateByExample(@Param("record") CmsGuestBook record, @Param("example") CmsGuestBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    int updateByPrimaryKeySelective(CmsGuestBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Mon Jul 04 09:33:19 CST 2016
     */
    int updateByPrimaryKey(CmsGuestBook record);
}