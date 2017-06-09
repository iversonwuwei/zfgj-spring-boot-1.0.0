package com.dlfc.zfgj.entity;

import com.dlfc.admin.common.persistence.MyDataEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class HouHouseBaseinfo extends MyDataEntity<HouHouseBaseinfo> implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.PINDEX
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private Integer pindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.NO
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String no;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.PROVINCE
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String province;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.CITY
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String city;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.DISTRICT
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String district;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.ROAD
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String road;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.NUMBER
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String number;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.UNIT
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String unit;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.FLOOR
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String floor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.DOOR
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String door;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.BUILDING_NO
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String buildingNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.HOUSE_ADDR
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String houseAddr;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.PROPERTY_ID_TYPE
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private Integer propertyIdType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.PROPERTY_ID_NO
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private String propertyIdNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.BUILDING_AREA
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private BigDecimal buildingArea;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.ACTIVE_STATUS
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private Integer activeStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.CERT_FLAG
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private Integer certFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column hou_house_baseinfo.CONTRACT_FLAG
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private Integer contractFlag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.PINDEX
	 * @return  the value of hou_house_baseinfo.PINDEX
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public Integer getPindex() {
		return pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.PINDEX
	 * @param pindex  the value for hou_house_baseinfo.PINDEX
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.NO
	 * @return  the value of hou_house_baseinfo.NO
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getNo() {
		return no;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.NO
	 * @param no  the value for hou_house_baseinfo.NO
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setNo(String no) {
		this.no = no == null ? null : no.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.PROVINCE
	 * @return  the value of hou_house_baseinfo.PROVINCE
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.PROVINCE
	 * @param province  the value for hou_house_baseinfo.PROVINCE
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.CITY
	 * @return  the value of hou_house_baseinfo.CITY
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getCity() {
		return city;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.CITY
	 * @param city  the value for hou_house_baseinfo.CITY
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.DISTRICT
	 * @return  the value of hou_house_baseinfo.DISTRICT
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.DISTRICT
	 * @param district  the value for hou_house_baseinfo.DISTRICT
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setDistrict(String district) {
		this.district = district == null ? null : district.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.ROAD
	 * @return  the value of hou_house_baseinfo.ROAD
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getRoad() {
		return road;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.ROAD
	 * @param road  the value for hou_house_baseinfo.ROAD
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setRoad(String road) {
		this.road = road == null ? null : road.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.NUMBER
	 * @return  the value of hou_house_baseinfo.NUMBER
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.NUMBER
	 * @param number  the value for hou_house_baseinfo.NUMBER
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setNumber(String number) {
		this.number = number == null ? null : number.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.UNIT
	 * @return  the value of hou_house_baseinfo.UNIT
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.UNIT
	 * @param unit  the value for hou_house_baseinfo.UNIT
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.FLOOR
	 * @return  the value of hou_house_baseinfo.FLOOR
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getFloor() {
		return floor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.FLOOR
	 * @param floor  the value for hou_house_baseinfo.FLOOR
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setFloor(String floor) {
		this.floor = floor == null ? null : floor.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.DOOR
	 * @return  the value of hou_house_baseinfo.DOOR
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getDoor() {
		return door;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.DOOR
	 * @param door  the value for hou_house_baseinfo.DOOR
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setDoor(String door) {
		this.door = door == null ? null : door.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.BUILDING_NO
	 * @return  the value of hou_house_baseinfo.BUILDING_NO
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getBuildingNo() {
		return buildingNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.BUILDING_NO
	 * @param buildingNo  the value for hou_house_baseinfo.BUILDING_NO
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo == null ? null : buildingNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.HOUSE_ADDR
	 * @return  the value of hou_house_baseinfo.HOUSE_ADDR
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getHouseAddr() {
		return houseAddr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.HOUSE_ADDR
	 * @param houseAddr  the value for hou_house_baseinfo.HOUSE_ADDR
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr == null ? null : houseAddr.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.PROPERTY_ID_TYPE
	 * @return  the value of hou_house_baseinfo.PROPERTY_ID_TYPE
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public Integer getPropertyIdType() {
		return propertyIdType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.PROPERTY_ID_TYPE
	 * @param propertyIdType  the value for hou_house_baseinfo.PROPERTY_ID_TYPE
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setPropertyIdType(Integer propertyIdType) {
		this.propertyIdType = propertyIdType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.PROPERTY_ID_NO
	 * @return  the value of hou_house_baseinfo.PROPERTY_ID_NO
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public String getPropertyIdNo() {
		return propertyIdNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.PROPERTY_ID_NO
	 * @param propertyIdNo  the value for hou_house_baseinfo.PROPERTY_ID_NO
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setPropertyIdNo(String propertyIdNo) {
		this.propertyIdNo = propertyIdNo == null ? null : propertyIdNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.BUILDING_AREA
	 * @return  the value of hou_house_baseinfo.BUILDING_AREA
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public BigDecimal getBuildingArea() {
		return buildingArea;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.BUILDING_AREA
	 * @param buildingArea  the value for hou_house_baseinfo.BUILDING_AREA
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setBuildingArea(BigDecimal buildingArea) {
		this.buildingArea = buildingArea;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.ACTIVE_STATUS
	 * @return  the value of hou_house_baseinfo.ACTIVE_STATUS
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public Integer getActiveStatus() {
		return activeStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.ACTIVE_STATUS
	 * @param activeStatus  the value for hou_house_baseinfo.ACTIVE_STATUS
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.CERT_FLAG
	 * @return  the value of hou_house_baseinfo.CERT_FLAG
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public Integer getCertFlag() {
		return certFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.CERT_FLAG
	 * @param certFlag  the value for hou_house_baseinfo.CERT_FLAG
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setCertFlag(Integer certFlag) {
		this.certFlag = certFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column hou_house_baseinfo.CONTRACT_FLAG
	 * @return  the value of hou_house_baseinfo.CONTRACT_FLAG
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public Integer getContractFlag() {
		return contractFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column hou_house_baseinfo.CONTRACT_FLAG
	 * @param contractFlag  the value for hou_house_baseinfo.CONTRACT_FLAG
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	public void setContractFlag(Integer contractFlag) {
		this.contractFlag = contractFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hou_house_baseinfo
	 * @mbggenerated  Tue Apr 05 17:09:59 CST 2016
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", pindex=").append(pindex);
		sb.append(", no=").append(no);
		sb.append(", province=").append(province);
		sb.append(", city=").append(city);
		sb.append(", district=").append(district);
		sb.append(", road=").append(road);
		sb.append(", number=").append(number);
		sb.append(", unit=").append(unit);
		sb.append(", floor=").append(floor);
		sb.append(", door=").append(door);
		sb.append(", buildingNo=").append(buildingNo);
		sb.append(", houseAddr=").append(houseAddr);
		sb.append(", propertyIdType=").append(propertyIdType);
		sb.append(", propertyIdNo=").append(propertyIdNo);
		sb.append(", buildingArea=").append(buildingArea);
		sb.append(", activeStatus=").append(activeStatus);
		sb.append(", certFlag=").append(certFlag);
		sb.append(", contractFlag=").append(contractFlag);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}