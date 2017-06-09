package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import java.io.Serializable;

public class SysOfficeAreaLink extends MyDataEntity<SysOfficeAreaLink> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office_area_link.PINDEX
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office_area_link.OFFICE_ID
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    private String officeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_office_area_link.AREA_ID
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    private String areaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_office_area_link
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office_area_link.PINDEX
     *
     * @return the value of sys_office_area_link.PINDEX
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office_area_link.PINDEX
     *
     * @param pindex the value for sys_office_area_link.PINDEX
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office_area_link.OFFICE_ID
     *
     * @return the value of sys_office_area_link.OFFICE_ID
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    public String getOfficeId() {
        return officeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office_area_link.OFFICE_ID
     *
     * @param officeId the value for sys_office_area_link.OFFICE_ID
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    public void setOfficeId(String officeId) {
        this.officeId = officeId == null ? null : officeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_office_area_link.AREA_ID
     *
     * @return the value of sys_office_area_link.AREA_ID
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_office_area_link.AREA_ID
     *
     * @param areaId the value for sys_office_area_link.AREA_ID
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_office_area_link
     *
     * @mbggenerated Tue Dec 01 13:18:21 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", officeId=").append(officeId);
        sb.append(", areaId=").append(areaId);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    private  String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}