package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.mobile.AddHouseLeaseV2MConvertor;
import com.dlfc.zfgj.convertor.mobile.HouseFreshMConvertor;
import com.dlfc.zfgj.convertor.mobile.HouseLeaseMConvertor;
import com.dlfc.zfgj.convertor.mobile.HouseMConvertor;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.mobile.*;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.enums.HouseReleaseStatusEnum;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.service.mobile.HouseMService;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import com.dlfc.zfgj.validator.mobile.AddLeaseMValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangna on 2017/3/21.
 */
@Slf4j
@RestController
@RequestMapping("/m/houses")
public class HouseMController {

    @Autowired
    @Qualifier("hMServiceImpl")
    private HouseMService houseMServiceImpl;

    @Autowired
    @Qualifier("addHouseLeaseV2MConvertor")
    private AddHouseLeaseV2MConvertor addHouseLeaseV2MConvertor;

    @Autowired
    @Qualifier("houseFreshMConvertor")
    private HouseFreshMConvertor houseFreshMConvertor;

    @Autowired
    @Qualifier("houseMConvertor")
    private HouseMConvertor houseMConvertor;

    @Autowired
    private AddLeaseMValidator addLeaseMValidator;

    @Autowired
    private ResultError resultError;
    @Autowired
    @Qualifier("us111333M")
    private UserHouseMService userHouseMService;

    @Autowired
    @Qualifier("houseLeaseMConvertor")
    private HouseLeaseMConvertor houseLeaseMConvertor;

    /**
     * 发布房源
     *
     * @param addHouseLeaseMDTO 房源信息
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResultDTO<Void> addHouse(@RequestHeader String token,
                                    @RequestBody AddHouseLeaseMDTO addHouseLeaseMDTO) {

        try {
            UsrUser usrUser = userHouseMService.getUserName(token);
            addLeaseMValidator.checkHouse(addHouseLeaseMDTO);
            HouLeaseInfo houLeaseInfoV2 = addHouseLeaseV2MConvertor.toModel(addHouseLeaseMDTO);
            if (null == addHouseLeaseMDTO.getHouseId()){
                if (houseMServiceImpl.saveHouse(houLeaseInfoV2, addHouseLeaseMDTO, usrUser) != 0) {
                    return ResultDTO.success();
                }
            }else {
                if (houseMServiceImpl.updateHouse(houLeaseInfoV2, addHouseLeaseMDTO, usrUser) != 0) {
                    return ResultDTO.success();
                }
            }
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getCode(), e.getMessage(), null));
        }
        resultError.setErrmsg("发布或编辑出租信息失败");
        return ResultDTO.failure(this.resultError);
    }

    /**
     * 修改房源
     *
     * @param addHouseLeaseMDTO 房源信息
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultDTO<Void> updateHouse(@RequestHeader String token,
                                       @RequestBody AddHouseLeaseMDTO addHouseLeaseMDTO) {

        try {
            UsrUser usrUser = userHouseMService.getUserName(token);
            addLeaseMValidator.checkHouse(addHouseLeaseMDTO);
            HouLeaseInfo houLeaseInfoV2 = addHouseLeaseV2MConvertor.toModel(addHouseLeaseMDTO);
            if (houseMServiceImpl.updateHouse(houLeaseInfoV2, addHouseLeaseMDTO, usrUser) != 0) {
                return ResultDTO.success();
            }
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getCode(), e.getMessage(), null));
        }
        resultError.setErrmsg("编辑出租信息失败");
        return ResultDTO.failure(this.resultError);
    }

    /**
     * 编辑房源初始化
     *
     * @param id 房源出租信息id
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ResultDTO<HouseLeaseMDTO> initHouse(@RequestHeader String token,
                                               @RequestParam String id) {

        HouLeaseInfo houLeaseInfo = houseMServiceImpl.findById(id);
        HouseLeaseMDTO houseLeaseMDTO = houseLeaseMConvertor.toDTO(houLeaseInfo);
        return ResultDTO.success(houseLeaseMDTO);
    }

    /**
     * 我的房源
     *
     * @return
     */
    @RequestMapping(value = "/myhouse", method = RequestMethod.GET)
    ResultDTO<MyHouseMDTO> myHouse(@RequestHeader String token,
                                   @RequestParam int beginPos, int count) {

        List<HouLeaseInfo> releaseHouseList;
        List<HouLeaseInfo> unReLeaseHouseList;
        MyHouseMDTO myHouseMDTO = new MyHouseMDTO();
        try {
            UsrUser user = userHouseMService.getUserName(token);
            releaseHouseList = houseMServiceImpl.findAll(String.valueOf(HouseReleaseStatusEnum.YES_ENUM.getValue()), "", "", user, beginPos, count);
            unReLeaseHouseList = houseMServiceImpl.findAll(String.valueOf(HouseReleaseStatusEnum.NO_ENUM.getValue()), "", "", user, beginPos, count);
            List<HouseMDTO> releaseHouse = houseMConvertor.toListDTO(releaseHouseList);
            List<HouseMDTO> unReleaseHouse = houseMConvertor.toListDTO(unReLeaseHouseList);
            myHouseMDTO.setReleaseHouse(releaseHouse);
            myHouseMDTO.setUnReleaseHouse(unReleaseHouse);
            myHouseMDTO.setReleaseCount(houseMServiceImpl.findCount(String.valueOf(HouseReleaseStatusEnum.YES_ENUM.getValue()),"","",user));
            myHouseMDTO.setUnReleaseCount(houseMServiceImpl.findCount(String.valueOf(HouseReleaseStatusEnum.NO_ENUM.getValue()),"","",user));
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure(new MyHouseMDTO(), this.resultError);
        }
        return ResultDTO.success(myHouseMDTO);
    }

    /**
     * 房源数量
     *
     * @return
     */
    @RequestMapping(value = "/housecount", method = RequestMethod.GET)
    ResultDTO<String> myHouse(@RequestHeader String token,
                                   @RequestParam String releaseStatus) {
        String count = null;
        try {
            UsrUser user = userHouseMService.getUserName(token);
            count = houseMServiceImpl.findCount(releaseStatus,"","",user);
        } catch (ApplicationException e) {
            log.error(e.getCodeAndExplanation());
            this.resultError.setErrmsg(e.getMessage());
            this.resultError.setErrcode(e.getCode());
            return ResultDTO.failure("查询房源数量失败");
        }
        return ResultDTO.success(count);
    }

    /**
     * 出租信息上架
     */
    @RequestMapping(value = "/putaway", method = RequestMethod.POST)
    public ResultDTO<Void> putaway(@RequestParam String id,
                                   @RequestHeader String token) throws ApplicationException {
        UsrUser usrUser = userHouseMService.getUserName(token);
        if (houseMServiceImpl.putaway(id, usrUser) != 0) {
            return ResultDTO.success();
        } else {
            return ResultDTO.failure(new ResultError("上架失败", null));
        }

    }

    /**
     * 出租信息下架
     */
    @RequestMapping(value = "/soldout", method = RequestMethod.POST)
    public ResultDTO<Void> soldOut(@RequestParam String id,
                                   @RequestHeader String token) throws ApplicationException {
        UsrUser usrUser = userHouseMService.getUserName(token);
        if (houseMServiceImpl.soldOut(id, usrUser) != 0) {
            return ResultDTO.success();
        } else {
            return ResultDTO.failure(new ResultError("下架失败", null));
        }
    }

    /**
     * 刷新房源
     */
    @RequestMapping(value = "/fresh", method = RequestMethod.POST)
    public ResultDTO<HouseFreshMDTO> freshTime(@RequestParam String id,
                                               @RequestHeader String token) throws ApplicationException {
        UsrUser usrUser = userHouseMService.getUserName(token);
        if (houseMServiceImpl.updateHouseFreshTime(id, usrUser) != 0) {
            HouLeaseInfo houLeaseInfo = houseMServiceImpl.findById(id);
            HouseFreshMDTO freshMDTO = houseFreshMConvertor.toDTO(houLeaseInfo);

            return ResultDTO.success(freshMDTO);
        } else {
            return ResultDTO.failure(new HouseFreshMDTO(),new ResultError("刷新失败！一天只能刷新一次", null));
        }

    }

    /**
     * 删除单条出租信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResultDTO<Void> delete(@RequestParam String id,
                                  @RequestHeader String token) {
        try {
            if (houseMServiceImpl.delHouse(id) != 0) {
                return ResultDTO.success();
            }
        } catch (ApplicationException e) {
            log.info("HouseController delete", e);
            resultError.setErrmsg(e.getMessage());
            return ResultDTO.failure(resultError);
        }
        return ResultDTO.failure(new ResultError("删除失败", null));
    }
}
