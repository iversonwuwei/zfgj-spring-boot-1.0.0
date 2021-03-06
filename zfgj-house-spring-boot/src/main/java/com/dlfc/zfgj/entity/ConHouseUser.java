package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import java.io.Serializable;

public class ConHouseUser extends MyDataEntity<ConHouseUser> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user.PINDEX
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user.PID
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    private String pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user.CID
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    private String cid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user.NAME
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user.ID_TYPE
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    private Integer idType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user.ID_NO
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    private String idNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user.MOBILE
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_user.SORT
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table con_house_user
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user.PINDEX
     *
     * @return the value of con_house_user.PINDEX
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user.PINDEX
     *
     * @param pindex the value for con_house_user.PINDEX
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user.PID
     *
     * @return the value of con_house_user.PID
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public String getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user.PID
     *
     * @param pid the value for con_house_user.PID
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user.CID
     *
     * @return the value of con_house_user.CID
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public String getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user.CID
     *
     * @param cid the value for con_house_user.CID
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user.NAME
     *
     * @return the value of con_house_user.NAME
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user.NAME
     *
     * @param name the value for con_house_user.NAME
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user.ID_TYPE
     *
     * @return the value of con_house_user.ID_TYPE
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public Integer getIdType() {
        return idType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user.ID_TYPE
     *
     * @param idType the value for con_house_user.ID_TYPE
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user.ID_NO
     *
     * @return the value of con_house_user.ID_NO
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public String getIdNo() {
        return idNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user.ID_NO
     *
     * @param idNo the value for con_house_user.ID_NO
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user.MOBILE
     *
     * @return the value of con_house_user.MOBILE
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user.MOBILE
     *
     * @param mobile the value for con_house_user.MOBILE
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_user.SORT
     *
     * @return the value of con_house_user.SORT
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_user.SORT
     *
     * @param sort the value for con_house_user.SORT
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table con_house_user
     *
     * @mbggenerated Fri Nov 13 16:14:10 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", pid=").append(pid);
        sb.append(", cid=").append(cid);
        sb.append(", name=").append(name);
        sb.append(", idType=").append(idType);
        sb.append(", idNo=").append(idNo);
        sb.append(", mobile=").append(mobile);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    private String perIdTypeName;
    
    public String getPerIdTypeName() {
		return perIdTypeName;
	}

	public void setPerIdTypeName(String perIdTypeName) {
		this.perIdTypeName = perIdTypeName;
	}
}