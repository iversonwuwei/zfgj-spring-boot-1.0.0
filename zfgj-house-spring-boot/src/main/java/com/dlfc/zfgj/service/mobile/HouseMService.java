package com.dlfc.zfgj.service.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.dto.mobile.AddHouseLeaseMDTO;
import com.dlfc.zfgj.entity.HouHouseInfo;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.exception.CustomRuntimeException;

import java.util.List;

/**
 * Created by walden on 2017/2/23.
 */
public interface HouseMService {
    //默认全部房源
    List<HouLeaseInfo> findAll(String releaseStatus, String collect, String CreateTime, UsrUser usrUser,int beginPos,
                               int count);

    List<HouLeaseInfo> findByNo(String bid) throws CustomRuntimeException;

    List<HouLeaseInfo> getAllDepartmentalHouses(String releaseStatus, String collect, String CreateTime, UsrUser usrUser,int beginPos,int count);

    int delHouse(String hid) throws ApplicationException;

    List<String> findByHid(String uid);

    int saveCollection(String hid);

    int calCollection(String hid);

    int putaway(String id ,UsrUser user);

    int soldOut(String id,UsrUser user);

    int updateHouse(HouLeaseInfo houLeaseInfoV2,AddHouseLeaseMDTO addHouseLeaseMDTO, UsrUser usrUser);

    int saveHouse(HouLeaseInfo houLeaseInfo, AddHouseLeaseMDTO addHouseLeaseMDTO, UsrUser usrUser);

    HouLeaseInfo findById(String id);
    boolean checkFresh(HouLeaseInfo houLeaseInfo);

    int updateHouseFreshTime(String id, UsrUser usrUser);

    String findCount(String releaseStatus, String collect, String CreateTime, UsrUser usrUser);
}
