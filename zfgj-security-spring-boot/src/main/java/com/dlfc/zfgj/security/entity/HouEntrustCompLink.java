package com.dlfc.zfgj.security.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;

public class HouEntrustCompLink extends MyDataEntity<HouEntrustCompLink> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hou_entrust_comp_link.PINDEX
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hou_entrust_comp_link.MAIN_ID
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    private String mainId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hou_entrust_comp_link.COMP_ID
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    private String compId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hou_entrust_comp_link.PINDEX
     *
     * @return the value of hou_entrust_comp_link.PINDEX
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hou_entrust_comp_link.PINDEX
     *
     * @param pindex the value for hou_entrust_comp_link.PINDEX
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hou_entrust_comp_link.MAIN_ID
     *
     * @return the value of hou_entrust_comp_link.MAIN_ID
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    public String getMainId() {
        return mainId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hou_entrust_comp_link.MAIN_ID
     *
     * @param mainId the value for hou_entrust_comp_link.MAIN_ID
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    public void setMainId(String mainId) {
        this.mainId = mainId == null ? null : mainId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hou_entrust_comp_link.COMP_ID
     *
     * @return the value of hou_entrust_comp_link.COMP_ID
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    public String getCompId() {
        return compId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hou_entrust_comp_link.COMP_ID
     *
     * @param compId the value for hou_entrust_comp_link.COMP_ID
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    public void setCompId(String compId) {
        this.compId = compId == null ? null : compId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hou_entrust_comp_link
     *
     * @mbggenerated Fri Feb 19 11:01:13 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", mainId=").append(mainId);
        sb.append(", compId=").append(compId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}