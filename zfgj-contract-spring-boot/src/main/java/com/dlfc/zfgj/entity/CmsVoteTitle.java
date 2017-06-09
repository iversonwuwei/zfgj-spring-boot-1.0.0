package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.util.List;

public class CmsVoteTitle extends MyDataEntity<CmsVoteTitle> implements Serializable {
	
	
	
	
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_vote_title.PINDEX
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_vote_title.TITLE
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_vote_title.STATUS
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cms_vote_title.REMARKS
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    private String remarks;
    
    
    private String createtime;
    
    
    public String getCreatetime() {
        return createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_vote_title.PINDEX
     *
     * @return the value of cms_vote_title.PINDEX
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_vote_title.PINDEX
     *
     * @param pindex the value for cms_vote_title.PINDEX
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_vote_title.TITLE
     *
     * @return the value of cms_vote_title.TITLE
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_vote_title.TITLE
     *
     * @param title the value for cms_vote_title.TITLE
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_vote_title.STATUS
     *
     * @return the value of cms_vote_title.STATUS
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_vote_title.STATUS
     *
     * @param status the value for cms_vote_title.STATUS
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cms_vote_title.REMARKS
     *
     * @return the value of cms_vote_title.REMARKS
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cms_vote_title.REMARKS
     *
     * @param remarks the value for cms_vote_title.REMARKS
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_vote_title
     *
     * @mbggenerated Tue Jun 07 16:51:32 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", title=").append(title);
        sb.append(", status=").append(status);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    private List<CmsVoteType> cmsVoteTypeList;
    
	public List<CmsVoteType> getCmsVoteTypeList() {
		return cmsVoteTypeList;
	}

	public void setCmsVoteTypeList(List<CmsVoteType> cmsVoteTypeList) {
		this.cmsVoteTypeList = cmsVoteTypeList;
	}
	
	private List<CmsVoteResult> cmsVoteResultList;


	public List<CmsVoteResult> getCmsVoteResultList() {
		return cmsVoteResultList;
	}

	public void setCmsVoteResultList(List<CmsVoteResult> cmsVoteResultList) {
		this.cmsVoteResultList = cmsVoteResultList;
	}
}