package com.dlfc.zfgj.security.mobile.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;

public class SysTransportLine extends MyDataEntity<SysTransportLine> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_transport_line.PINDEX
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_transport_line.SID
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    private String sid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_transport_line.LINE
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    private String line;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_transport_line.TYPE
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    private Byte type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_transport_line
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_transport_line.PINDEX
     *
     * @return the value of sys_transport_line.PINDEX
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_transport_line.PINDEX
     *
     * @param pindex the value for sys_transport_line.PINDEX
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_transport_line.SID
     *
     * @return the value of sys_transport_line.SID
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public String getSid() {
        return sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_transport_line.SID
     *
     * @param sid the value for sys_transport_line.SID
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_transport_line.LINE
     *
     * @return the value of sys_transport_line.LINE
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public String getLine() {
        return line;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_transport_line.LINE
     *
     * @param line the value for sys_transport_line.LINE
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public void setLine(String line) {
        this.line = line == null ? null : line.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_transport_line.TYPE
     *
     * @return the value of sys_transport_line.TYPE
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_transport_line.TYPE
     *
     * @param type the value for sys_transport_line.TYPE
     *
     * @mbggenerated Sat Apr 23 16:38:37 CST 2016
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_transport_line
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
        sb.append(", sid=").append(sid);
        sb.append(", line=").append(line);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    
    private String stationName;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
    
	
    
}