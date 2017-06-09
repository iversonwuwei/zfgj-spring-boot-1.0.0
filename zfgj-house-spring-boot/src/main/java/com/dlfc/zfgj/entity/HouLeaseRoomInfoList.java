/**
 * @name: HouLeaseRoomInfoList.java 
 *
 * @Copyright: (c) 2015 DLFC. All rights reserved. 
 *
 * @description: 
 *
 * @version: 1.0
 * @date : 2015年12月7日 
 * @author: fancy 
 *
 * @Modification  History:<br>
 *  Date          Author         Version        Discription
 *  2015年12月7日       fancy        1.0             <修改原因描述>
 */
package com.dlfc.zfgj.entity;

import java.util.List;

/**
 * @name: HouLeaseRoomInfoList
 * @description:
 * 
 * @version 1.0
 * @author fancy
 *
 */
public class HouLeaseRoomInfoList {
	private List<HouLeaseRoomInfo> roomInfos;

	public List<HouLeaseRoomInfo> getRoomInfos() {
		return roomInfos;
	}

	public void setRoomInfos(List<HouLeaseRoomInfo> roomInfos) {
		this.roomInfos = roomInfos;
	}

}
