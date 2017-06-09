package com.dlfc.zfgj.service.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.exception.CustomRuntimeException;

import java.util.List;

/**
 * Created by walden on 2017/2/23.
 */
public interface HouseService {
    //默认全部房源
    List<HouLeaseInfo> findAll(String releaseStatus, String collect, String CreateTime, UsrUser usrUser);

    List<HouLeaseInfo> findByNo(String bid) throws CustomRuntimeException;

    List<HouLeaseInfo> getAllDepartmentalHouses(String releaseStatus, String collect, String CreateTime, UsrUser usrUser);

    int delHouse(String hid) throws ApplicationException;

    List<String> findByHid(String uid);

    int saveCollection(String hid, UsrUser usrUser) throws ApplicationException;

    int calCollection(String hid);

    int putaway(String id, UsrUser usrUser);

    int soldOut(String id, UsrUser usrUser);

    String saveHouse(HouLeaseInfo houLeaseInfoV2, UsrUser usrUser);

    HouLeaseInfo findById(String id);

    String updateHouse(HouLeaseInfo houLeaseInfoV2, UsrUser usrUser);

    int updateHouseFreshTime(String id, UsrUser usrUser);

    boolean checkFresh(HouLeaseInfo houLeaseInfo);

    int actHouse(String eid);

    int countHouByStatus(String eid, int status);
}
