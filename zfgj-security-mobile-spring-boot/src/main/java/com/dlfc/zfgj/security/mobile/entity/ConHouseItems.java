package com.dlfc.zfgj.security.mobile.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConHouseItems extends MyDataEntity<ConHouseItems> implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_items.PINDEX
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private Integer pindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_items.CID
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private String cid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_items.ITEM
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private String item;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_items.BRAND
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private String brand;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_items.UNIT
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private String unit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_items.NUM
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private Integer num;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_items.PRICE
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_items.COMMENT
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private String comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column con_house_items.COMPEN_AMOUNT
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private BigDecimal compenAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table con_house_items
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_items.PINDEX
     *
     * @return the value of con_house_items.PINDEX
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public Integer getPindex() {
        return pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_items.PINDEX
     *
     * @param pindex the value for con_house_items.PINDEX
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public void setPindex(Integer pindex) {
        this.pindex = pindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_items.CID
     *
     * @return the value of con_house_items.CID
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public String getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_items.CID
     *
     * @param cid the value for con_house_items.CID
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_items.ITEM
     *
     * @return the value of con_house_items.ITEM
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public String getItem() {
        return item;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_items.ITEM
     *
     * @param item the value for con_house_items.ITEM
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_items.BRAND
     *
     * @return the value of con_house_items.BRAND
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_items.BRAND
     *
     * @param brand the value for con_house_items.BRAND
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_items.UNIT
     *
     * @return the value of con_house_items.UNIT
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_items.UNIT
     *
     * @param unit the value for con_house_items.UNIT
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_items.NUM
     *
     * @return the value of con_house_items.NUM
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_items.NUM
     *
     * @param num the value for con_house_items.NUM
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_items.PRICE
     *
     * @return the value of con_house_items.PRICE
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_items.PRICE
     *
     * @param price the value for con_house_items.PRICE
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_items.COMMENT
     *
     * @return the value of con_house_items.COMMENT
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_items.COMMENT
     *
     * @param comment the value for con_house_items.COMMENT
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column con_house_items.COMPEN_AMOUNT
     *
     * @return the value of con_house_items.COMPEN_AMOUNT
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public BigDecimal getCompenAmount() {
        return compenAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column con_house_items.COMPEN_AMOUNT
     *
     * @param compenAmount the value for con_house_items.COMPEN_AMOUNT
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    public void setCompenAmount(BigDecimal compenAmount) {
        this.compenAmount = compenAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table con_house_items
     *
     * @mbggenerated Mon Dec 14 11:05:12 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pindex=").append(pindex);
        sb.append(", cid=").append(cid);
        sb.append(", item=").append(item);
        sb.append(", brand=").append(brand);
        sb.append(", unit=").append(unit);
        sb.append(", num=").append(num);
        sb.append(", price=").append(price);
        sb.append(", comment=").append(comment);
        sb.append(", compenAmount=").append(compenAmount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}