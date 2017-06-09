package com.dlfc.zfgj.entity;

import com.dlfc.zfgj.common.data.DataEntity;

import java.io.Serializable;
import java.util.Date;

public class CmsGuestBook extends DataEntity<CmsGuestBook> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.type
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.content
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.name
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.email
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.phone
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.workunit
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String workunit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.ip
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.create_date
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.re_user_id
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String reUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.re_date
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private Date reDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.re_content
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String reContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_guestbook.del_flag
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private String delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cms_guestbook
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.type
     *
     * @return the value of cms_guestbook.type
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.type
     *
     * @param type the value for cms_guestbook.type
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.content
     *
     * @return the value of cms_guestbook.content
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.content
     *
     * @param content the value for cms_guestbook.content
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.name
     *
     * @return the value of cms_guestbook.name
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.name
     *
     * @param name the value for cms_guestbook.name
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.email
     *
     * @return the value of cms_guestbook.email
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.email
     *
     * @param email the value for cms_guestbook.email
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.phone
     *
     * @return the value of cms_guestbook.phone
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.phone
     *
     * @param phone the value for cms_guestbook.phone
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.workunit
     *
     * @return the value of cms_guestbook.workunit
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getWorkunit() {
        return workunit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.workunit
     *
     * @param workunit the value for cms_guestbook.workunit
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setWorkunit(String workunit) {
        this.workunit = workunit == null ? null : workunit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.ip
     *
     * @return the value of cms_guestbook.ip
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.ip
     *
     * @param ip the value for cms_guestbook.ip
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.create_date
     *
     * @return the value of cms_guestbook.create_date
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.create_date
     *
     * @param createDate the value for cms_guestbook.create_date
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.re_user_id
     *
     * @return the value of cms_guestbook.re_user_id
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getReUserId() {
        return reUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.re_user_id
     *
     * @param reUserId the value for cms_guestbook.re_user_id
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setReUserId(String reUserId) {
        this.reUserId = reUserId == null ? null : reUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.re_date
     *
     * @return the value of cms_guestbook.re_date
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public Date getReDate() {
        return reDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.re_date
     *
     * @param reDate the value for cms_guestbook.re_date
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setReDate(Date reDate) {
        this.reDate = reDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.re_content
     *
     * @return the value of cms_guestbook.re_content
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getReContent() {
        return reContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.re_content
     *
     * @param reContent the value for cms_guestbook.re_content
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setReContent(String reContent) {
        this.reContent = reContent == null ? null : reContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_guestbook.del_flag
     *
     * @return the value of cms_guestbook.del_flag
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_guestbook.del_flag
     *
     * @param delFlag the value for cms_guestbook.del_flag
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_guestbook
     *
     * @mbggenerated Thu Feb 25 09:47:23 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", workunit=").append(workunit);
        sb.append(", ip=").append(ip);
        sb.append(", createDate=").append(createDate);
        sb.append(", reUserId=").append(reUserId);
        sb.append(", reDate=").append(reDate);
        sb.append(", reContent=").append(reContent);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}