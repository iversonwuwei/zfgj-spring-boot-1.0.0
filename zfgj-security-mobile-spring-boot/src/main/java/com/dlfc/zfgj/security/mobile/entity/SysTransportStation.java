package com.dlfc.zfgj.security.mobile.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;

public class SysTransportStation extends MyDataEntity<SysTransportStation> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_transport_station.PINDEX
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_transport_station.SPELLING
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    private String spelling;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_transport_station.NAME
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_transport_station
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_transport_station.PINDEX
     *
     * @return the value of sys_transport_station.PINDEX
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_transport_station.PINDEX
     *
     * @param pindex the value for sys_transport_station.PINDEX
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_transport_station.SPELLING
     *
     * @return the value of sys_transport_station.SPELLING
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public String getSpelling() {
        return spelling;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_transport_station.SPELLING
     *
     * @param spelling the value for sys_transport_station.SPELLING
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public void setSpelling(String spelling) {
        this.spelling = spelling == null ? null : spelling.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_transport_station.NAME
     *
     * @return the value of sys_transport_station.NAME
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_transport_station.NAME
     *
     * @param name the value for sys_transport_station.NAME
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_transport_station
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", spelling=").append(spelling);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}