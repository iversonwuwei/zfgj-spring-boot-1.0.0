package com.dlfc.zfgj.entity;

import com.dlfc.zfgj.common.data.DataEntity;

import java.io.Serializable;
import java.util.Date;

public class ConHouseUserChildrenLog extends DataEntity<ConHouseUserChildrenLog> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.ID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.PINDEX
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.CON_LOG_ID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private String conLogId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.CID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private String cid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.CHILD_ID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private String childId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.NAME
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.GENDER
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private Integer gender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.BIRTHDAY
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.ID_NO
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private String idNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.CREATE_USER
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.CREATE_USER_IDENTITY
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private Short createUserIdentity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.CREATE_TIME
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.MODIFY_USER
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private String modifyUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.MODIFY_USER_IDENTITY
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private Short modifyUserIdentity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.MODIFY_TIME
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.DELETE_FLG
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private Short deleteFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user_children_log.VERSION
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.ID
     *
     * @return the value of con_house_user_children_log.ID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.ID
     *
     * @param id the value for con_house_user_children_log.ID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.PINDEX
     *
     * @return the value of con_house_user_children_log.PINDEX
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.PINDEX
     *
     * @param pindex the value for con_house_user_children_log.PINDEX
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.CON_LOG_ID
     *
     * @return the value of con_house_user_children_log.CON_LOG_ID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public String getConLogId() {
        return conLogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.CON_LOG_ID
     *
     * @param conLogId the value for con_house_user_children_log.CON_LOG_ID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setConLogId(String conLogId) {
        this.conLogId = conLogId == null ? null : conLogId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.CID
     *
     * @return the value of con_house_user_children_log.CID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public String getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.CID
     *
     * @param cid the value for con_house_user_children_log.CID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.CHILD_ID
     *
     * @return the value of con_house_user_children_log.CHILD_ID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public String getChildId() {
        return childId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.CHILD_ID
     *
     * @param childId the value for con_house_user_children_log.CHILD_ID
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setChildId(String childId) {
        this.childId = childId == null ? null : childId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.NAME
     *
     * @return the value of con_house_user_children_log.NAME
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.NAME
     *
     * @param name the value for con_house_user_children_log.NAME
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.GENDER
     *
     * @return the value of con_house_user_children_log.GENDER
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.GENDER
     *
     * @param gender the value for con_house_user_children_log.GENDER
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.BIRTHDAY
     *
     * @return the value of con_house_user_children_log.BIRTHDAY
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.BIRTHDAY
     *
     * @param birthday the value for con_house_user_children_log.BIRTHDAY
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.ID_NO
     *
     * @return the value of con_house_user_children_log.ID_NO
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.ID_NO
     *
     * @param idNo the value for con_house_user_children_log.ID_NO
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.CREATE_USER
     *
     * @return the value of con_house_user_children_log.CREATE_USER
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.CREATE_USER
     *
     * @param createUser the value for con_house_user_children_log.CREATE_USER
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.CREATE_USER_IDENTITY
     *
     * @return the value of con_house_user_children_log.CREATE_USER_IDENTITY
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public Short getCreateUserIdentity() {
        return createUserIdentity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.CREATE_USER_IDENTITY
     *
     * @param createUserIdentity the value for con_house_user_children_log.CREATE_USER_IDENTITY
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setCreateUserIdentity(Short createUserIdentity) {
        this.createUserIdentity = createUserIdentity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.CREATE_TIME
     *
     * @return the value of con_house_user_children_log.CREATE_TIME
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.CREATE_TIME
     *
     * @param createTime the value for con_house_user_children_log.CREATE_TIME
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.MODIFY_USER
     *
     * @return the value of con_house_user_children_log.MODIFY_USER
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.MODIFY_USER
     *
     * @param modifyUser the value for con_house_user_children_log.MODIFY_USER
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.MODIFY_USER_IDENTITY
     *
     * @return the value of con_house_user_children_log.MODIFY_USER_IDENTITY
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public Short getModifyUserIdentity() {
        return modifyUserIdentity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.MODIFY_USER_IDENTITY
     *
     * @param modifyUserIdentity the value for con_house_user_children_log.MODIFY_USER_IDENTITY
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setModifyUserIdentity(Short modifyUserIdentity) {
        this.modifyUserIdentity = modifyUserIdentity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.MODIFY_TIME
     *
     * @return the value of con_house_user_children_log.MODIFY_TIME
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.MODIFY_TIME
     *
     * @param modifyTime the value for con_house_user_children_log.MODIFY_TIME
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.DELETE_FLG
     *
     * @return the value of con_house_user_children_log.DELETE_FLG
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public Short getDeleteFlg() {
        return deleteFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.DELETE_FLG
     *
     * @param deleteFlg the value for con_house_user_children_log.DELETE_FLG
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setDeleteFlg(Short deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user_children_log.VERSION
     *
     * @return the value of con_house_user_children_log.VERSION
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user_children_log.VERSION
     *
     * @param version the value for con_house_user_children_log.VERSION
     *
     * @mbggenerated Thu Jun 08 10:18:23 CST 2017
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}