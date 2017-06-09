package com.dlfc.zfgj.security.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;

public class CmsTemplate extends MyDataEntity<CmsTemplate> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_template.PINDEX
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_template.TYPE
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_template.NAME
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_template.TEMPLATE_PATH
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    private String templatePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_template.STATIC_PATH
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    private String staticPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_template.MEMO
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cms_template
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_template.PINDEX
     *
     * @return the value of cms_template.PINDEX
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_template.PINDEX
     *
     * @param pindex the value for cms_template.PINDEX
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_template.TYPE
     *
     * @return the value of cms_template.TYPE
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_template.TYPE
     *
     * @param type the value for cms_template.TYPE
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_template.NAME
     *
     * @return the value of cms_template.NAME
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_template.NAME
     *
     * @param name the value for cms_template.NAME
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_template.TEMPLATE_PATH
     *
     * @return the value of cms_template.TEMPLATE_PATH
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_template.TEMPLATE_PATH
     *
     * @param templatePath the value for cms_template.TEMPLATE_PATH
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath == null ? null : templatePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_template.STATIC_PATH
     *
     * @return the value of cms_template.STATIC_PATH
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public String getStaticPath() {
        return staticPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_template.STATIC_PATH
     *
     * @param staticPath the value for cms_template.STATIC_PATH
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath == null ? null : staticPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_template.MEMO
     *
     * @return the value of cms_template.MEMO
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_template.MEMO
     *
     * @param memo the value for cms_template.MEMO
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_template
     *
     * @mbggenerated Fri Dec 25 10:57:39 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", templatePath=").append(templatePath);
        sb.append(", staticPath=").append(staticPath);
        sb.append(", memo=").append(memo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}