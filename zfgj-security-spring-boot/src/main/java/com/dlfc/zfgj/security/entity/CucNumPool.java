package com.dlfc.zfgj.security.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.util.Date;

public class CucNumPool extends MyDataEntity<CucNumPool> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cuc_num_pool.PINDEX
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cuc_num_pool.NUM
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    private String num;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cuc_num_pool.STATUS
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cuc_num_pool.LOCK_UID
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    private String lockUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cuc_num_pool.LOCK_DATETIME
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    private Date lockDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cuc_num_pool.PINDEX
     *
     * @return the value of cuc_num_pool.PINDEX
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cuc_num_pool.PINDEX
     *
     * @param pindex the value for cuc_num_pool.PINDEX
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cuc_num_pool.NUM
     *
     * @return the value of cuc_num_pool.NUM
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public String getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cuc_num_pool.NUM
     *
     * @param num the value for cuc_num_pool.NUM
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cuc_num_pool.STATUS
     *
     * @return the value of cuc_num_pool.STATUS
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cuc_num_pool.STATUS
     *
     * @param status the value for cuc_num_pool.STATUS
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cuc_num_pool.LOCK_UID
     *
     * @return the value of cuc_num_pool.LOCK_UID
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public String getLockUid() {
        return lockUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cuc_num_pool.LOCK_UID
     *
     * @param lockUid the value for cuc_num_pool.LOCK_UID
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public void setLockUid(String lockUid) {
        this.lockUid = lockUid == null ? null : lockUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cuc_num_pool.LOCK_DATETIME
     *
     * @return the value of cuc_num_pool.LOCK_DATETIME
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public Date getLockDatetime() {
        return lockDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cuc_num_pool.LOCK_DATETIME
     *
     * @param lockDatetime the value for cuc_num_pool.LOCK_DATETIME
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    public void setLockDatetime(Date lockDatetime) {
        this.lockDatetime = lockDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cuc_num_pool
     *
     * @mbggenerated Thu Sep 29 11:05:49 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", num=").append(num);
        sb.append(", status=").append(status);
        sb.append(", lockUid=").append(lockUid);
        sb.append(", lockDatetime=").append(lockDatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    // 锁定时间，单位为分钟
    private String lockTime;

	public String getLockTime() {
		return lockTime;
	}

	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}
    
}