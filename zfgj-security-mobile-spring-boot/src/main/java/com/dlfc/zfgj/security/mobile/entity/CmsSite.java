package com.dlfc.zfgj.security.mobile.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.util.Date;

public class CmsSite extends MyDataEntity<CmsSite> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.name
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.title
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.logo
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String logo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.domain
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String domain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.description
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.keywords
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String keywords;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.theme
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String theme;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.custom_index_view
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String customIndexView;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.create_by
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.create_date
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.update_by
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.update_date
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.remarks
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.del_flag
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_site.copyright
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private String copyright;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cms_site
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.name
     *
     * @return the value of cms_site.name
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.name
     *
     * @param name the value for cms_site.name
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.title
     *
     * @return the value of cms_site.title
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.title
     *
     * @param title the value for cms_site.title
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.logo
     *
     * @return the value of cms_site.logo
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getLogo() {
        return logo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.logo
     *
     * @param logo the value for cms_site.logo
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.domain
     *
     * @return the value of cms_site.domain
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getDomain() {
        return domain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.domain
     *
     * @param domain the value for cms_site.domain
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.description
     *
     * @return the value of cms_site.description
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.description
     *
     * @param description the value for cms_site.description
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.keywords
     *
     * @return the value of cms_site.keywords
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.keywords
     *
     * @param keywords the value for cms_site.keywords
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.theme
     *
     * @return the value of cms_site.theme
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getTheme() {
        return theme;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.theme
     *
     * @param theme the value for cms_site.theme
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.custom_index_view
     *
     * @return the value of cms_site.custom_index_view
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getCustomIndexView() {
        return customIndexView;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.custom_index_view
     *
     * @param customIndexView the value for cms_site.custom_index_view
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setCustomIndexView(String customIndexView) {
        this.customIndexView = customIndexView == null ? null : customIndexView.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.create_by
     *
     * @return the value of cms_site.create_by
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.create_by
     *
     * @param createBy the value for cms_site.create_by
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.create_date
     *
     * @return the value of cms_site.create_date
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.create_date
     *
     * @param createDate the value for cms_site.create_date
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.update_by
     *
     * @return the value of cms_site.update_by
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.update_by
     *
     * @param updateBy the value for cms_site.update_by
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.update_date
     *
     * @return the value of cms_site.update_date
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.update_date
     *
     * @param updateDate the value for cms_site.update_date
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.remarks
     *
     * @return the value of cms_site.remarks
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.remarks
     *
     * @param remarks the value for cms_site.remarks
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.del_flag
     *
     * @return the value of cms_site.del_flag
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.del_flag
     *
     * @param delFlag the value for cms_site.del_flag
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_site.copyright
     *
     * @return the value of cms_site.copyright
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_site.copyright
     *
     * @param copyright the value for cms_site.copyright
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright == null ? null : copyright.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_site
     *
     * @mbggenerated Thu Jul 21 16:46:03 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", logo=").append(logo);
        sb.append(", domain=").append(domain);
        sb.append(", description=").append(description);
        sb.append(", keywords=").append(keywords);
        sb.append(", theme=").append(theme);
        sb.append(", customIndexView=").append(customIndexView);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", copyright=").append(copyright);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}