package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;
import java.io.Serializable;

public class AgtEmpDeptLink extends MyDataEntity<AgtEmpDeptLink> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agt_emp_dept_link.PINDEX
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agt_emp_dept_link.EID
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    private String eid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agt_emp_dept_link.DID
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    private String did;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agt_emp_dept_link.CMID
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    private String cmid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agt_emp_dept_link.STATUS
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agt_emp_dept_link.REJECT_REASON
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    private String rejectReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table agt_emp_dept_link
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agt_emp_dept_link.PINDEX
     *
     * @return the value of agt_emp_dept_link.PINDEX
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agt_emp_dept_link.PINDEX
     *
     * @param pindex the value for agt_emp_dept_link.PINDEX
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agt_emp_dept_link.EID
     *
     * @return the value of agt_emp_dept_link.EID
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public String getEid() {
        return eid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agt_emp_dept_link.EID
     *
     * @param eid the value for agt_emp_dept_link.EID
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agt_emp_dept_link.DID
     *
     * @return the value of agt_emp_dept_link.DID
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public String getDid() {
        return did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agt_emp_dept_link.DID
     *
     * @param did the value for agt_emp_dept_link.DID
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agt_emp_dept_link.CMID
     *
     * @return the value of agt_emp_dept_link.CMID
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public String getCmid() {
        return cmid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agt_emp_dept_link.CMID
     *
     * @param cmid the value for agt_emp_dept_link.CMID
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public void setCmid(String cmid) {
        this.cmid = cmid == null ? null : cmid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agt_emp_dept_link.STATUS
     *
     * @return the value of agt_emp_dept_link.STATUS
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agt_emp_dept_link.STATUS
     *
     * @param status the value for agt_emp_dept_link.STATUS
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agt_emp_dept_link.REJECT_REASON
     *
     * @return the value of agt_emp_dept_link.REJECT_REASON
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public String getRejectReason() {
        return rejectReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agt_emp_dept_link.REJECT_REASON
     *
     * @param rejectReason the value for agt_emp_dept_link.REJECT_REASON
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agt_emp_dept_link
     *
     * @mbggenerated Mon Nov 16 14:21:39 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", eid=").append(eid);
        sb.append(", did=").append(did);
        sb.append(", cmid=").append(cmid);
        sb.append(", status=").append(status);
        sb.append(", rejectReason=").append(rejectReason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}