package com.dlfc.zfgj.mapper;

import com.dlfc.admin.common.persistence.MyMapper;
import com.dlfc.admin.common.persistence.annotation.MyBatisDao;
import com.dlfc.zfgj.entity.SysMenu;
import com.dlfc.zfgj.entity.SysMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao("sysMenuMapper")
public interface SysMenuMapper extends MyMapper<SysMenu> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    int countByExample(SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    int deleteByExample(SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    int insert(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    int insertSelective(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    List<SysMenu> selectByExample(SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    SysMenu selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Mon Nov 09 16:01:06 CST 2015
     */
    int updateByPrimaryKey(SysMenu record);
    
//    /**
//     * 获取经纪人系统菜单，之后重构
//     * @param menu
//     * @return
//     */
//    List<Menu> findByUserId(Menu menu);
}