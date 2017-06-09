package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.common.page.CutomizedPage;
import com.dlfc.zfgj.common.page.PageInfo;
import com.dlfc.zfgj.convertor.web.HouseConvertor;
import com.dlfc.zfgj.convertor.web.HouseLeaseInfoConvertor;
import com.dlfc.zfgj.convertor.web.HouseLeaseInfoInitConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.web.HouseDTO;
import com.dlfc.zfgj.dto.web.HouseLeaseDTO;
import com.dlfc.zfgj.entity.HouLeaseInfo;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.HouseService;
import com.dlfc.zfgj.service.web.UploadService;
import com.dlfc.zfgj.service.web.UserHouseService;
import com.dlfc.zfgj.validator.web.HouseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walden on 2017/2/23.
 */
@Slf4j
@RestController
@RequestMapping("/w/houses")
public class HouseController {

    private List<HouLeaseInfo> houLeaseInfoList;

    @Autowired
    @Qualifier("hServiceImpl")
    private HouseService houseService;
    @Autowired
    @Qualifier("houseConvertor")
    private HouseConvertor houseConvertor;
    @Autowired
    @Qualifier("us111333")
    private UserHouseService userHouseService;
    @Autowired
    @Qualifier("addHouseLeaseIfoConvertor")
    private HouseLeaseInfoConvertor houseLeaseInfoConvertor;
    @Autowired
    private CutomizedPage cutomizedPage;
    @Autowired
    private ResultError resultError;
    @Autowired
    private HouseLeaseInfoInitConvertor houseLeaseInfoInitConvertor;
    @Autowired
    private HouseValidator houseValidator;
    @Autowired
    private UploadService uploadService;


    /**
     * 房源默认初始页面
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageInfo<HouseDTO> houseInfos(@RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser usrUser = userHouseService.getUser(token);
        houLeaseInfoList = houseService.findAll(null, "", "", usrUser);
        cutomizedPage.setSource(houseConvertor.toListDTO(
                null == houLeaseInfoList ? new ArrayList<HouLeaseInfo>() : houLeaseInfoList));
        return cutomizedPage.index();
    }

    /**
     * 搜索条件页面
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public PageInfo<HouseDTO> houseInfos(@RequestParam String releaseStatus,
                                         String collect,
                                         String createTime,
                                         @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser usrUser = userHouseService.getUser(token);
        houLeaseInfoList = houseService.findAll(releaseStatus, collect, createTime, usrUser);
        cutomizedPage.setSource(houseConvertor.toListDTO(
                null == houLeaseInfoList ? new ArrayList<HouLeaseInfo>() : houLeaseInfoList));
        return cutomizedPage.index();
    }

    /**
     * 经理权限查看房源列表
     *
     * @return
     * @throws CustomRuntimeException
     */

    @RequestMapping(value = "/dep", method = RequestMethod.GET)
    public PageInfo<HouseDTO> departmental(@RequestParam String releaseStatus,
                                           String createTime,
                                           @RequestHeader String token)
            throws CustomRuntimeException, ApplicationException {
        UsrUser usrUser = userHouseService.getUser(token);
        houLeaseInfoList = houseService.getAllDepartmentalHouses(releaseStatus, "", createTime, usrUser);
        cutomizedPage.setSource(houseConvertor.toListDTO(
                null == houLeaseInfoList ? new ArrayList<HouLeaseInfo>() : houLeaseInfoList));
        return cutomizedPage.index();
    }


    /**
     * 搜索出租信息编号
     *
     * @param no
     * @return
     */

    @RequestMapping(value = "/{no}", method = RequestMethod.GET)
    public ListResultDTO<HouseDTO> houses(@PathVariable String no) {
        try {
            houLeaseInfoList = houseService.findByNo(no);
            if (houLeaseInfoList != null) {
                return houseConvertor.toResultDTO(houLeaseInfoList);
            }
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
        }
        return ListResultDTO.failure(null,new ResultError("无查询信息",null));
    }


    /**
     * 删除单条出租信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResultDTO<Void> delete(@PathVariable String id) {
        try {
            if (houseService.delHouse(id) != 0) {
                return ResultDTO.success();
            }
        } catch (ApplicationException e) {
            log.info("HouseController delete", e);
            resultError.setErrmsg(e.getMessage());
            return ResultDTO.failure(resultError);
        }
        return ResultDTO.failure(new ResultError("删除失败",null));
    }

    /**
     * 删除多条出租信息
     */
    @RequestMapping(value = "/deletes/{id}", method = RequestMethod.DELETE)
    public ResultDTO<Void> deletes(@PathVariable String id) {
        try {
            String[] arr = id.split(",");
            for (String str : arr) {
                if (StringUtils.isNotBlank(str)) {
                    int i = houseService.delHouse(str);
                    if (i != 0) {
                        return ResultDTO.success();
                    }
                }
            }
        } catch (ApplicationException e) {
            log.info("HouseController delete", e);
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.failure(new ResultError("删除失败",null));
    }

    /**
     * 收藏出租信息
     */
    @RequestMapping(value = "/collection/{id}", method = RequestMethod.POST)
    public ResultDTO<Void> collection(@PathVariable String id,
                                      @RequestHeader String token) throws ApplicationException {
        UsrUser usrUser = userHouseService.getUser(token);
        try {
            if (houseService.saveCollection(id, usrUser) != 0) {
                return ResultDTO.success();
            } else {
                return ResultDTO.failure(new ResultError("收藏失败",null));
            }
        } catch (ApplicationException e) {
            log.info("HouseController collection", e);
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
    }

    /**
     * 取消收藏
     */
    @RequestMapping(value = "/coldel/{id}", method = RequestMethod.POST)
    public ResultDTO<Void> coldel(@PathVariable String id) {
        if (houseService.calCollection(id) != 0) {
            return ResultDTO.success();
        } else {
            return ResultDTO.failure(new ResultError("收藏失败",null));
        }
    }

    /**
     * 出租信息上架
     */
    @RequestMapping(value = "/putaway/{id}", method = RequestMethod.POST)
    public ResultDTO<Void> putaway(@PathVariable String id,
                                   @RequestHeader String token) throws ApplicationException {
        UsrUser usrUser = userHouseService.getUser(token);
        if (houseService.putaway(id, usrUser) != 0) {
            return ResultDTO.success();
        } else {
            return ResultDTO.failure(new ResultError("上架失败",null));
        }

    }

    /**
     * 出租信息下架
     */
    @RequestMapping(value = "/soldout/{id}", method = RequestMethod.POST)
    public ResultDTO<Void> soldOut(@PathVariable String id,
                                   @RequestHeader String token) throws ApplicationException {
        UsrUser usrUser = userHouseService.getUser(token);
        if (houseService.soldOut(id, usrUser) != 0) {
            return ResultDTO.success();
        } else {
            return ResultDTO.failure(new ResultError("下架失败",null));
        }
    }

    /**
     * 发布出租信息前初始化
     *
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/addInit", method = RequestMethod.GET)
    public ResultDTO<HouseLeaseDTO> addInit() throws CustomRuntimeException {
        uploadService.initFileList(null);
        return houseLeaseInfoInitConvertor.toResultDTO(new HouLeaseInfo());
    }

    /**
     * 发布出租信息
     *
     * @param HouseLeaseDTO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultDTO<Void> add(@RequestBody HouseLeaseDTO HouseLeaseDTO,
                               @RequestHeader String token) throws CustomRuntimeException {
        try {
            UsrUser user = userHouseService.getUser(token);
            houseValidator.checkHouse(HouseLeaseDTO);
            HouLeaseInfo houLeaseInfoV2 = houseLeaseInfoConvertor.toModel(HouseLeaseDTO);
            String id = houseService.saveHouse(houLeaseInfoV2, user);
            uploadService.uploadFileList(id, user);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }

    /**
     * 编辑出租查询页面
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ResultDTO<HouseLeaseDTO> edit(@PathVariable String id) {
        HouLeaseInfo houLeaseInfoV2 = houseService.findById(id);
        return houseLeaseInfoInitConvertor.toResultDTO(houLeaseInfoV2);
    }

    /**
     * 编辑出租页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultDTO<Void> update(@RequestBody HouseLeaseDTO HouseLeaseDTO,
                                  @RequestHeader String token) throws CustomRuntimeException {
        try {
            UsrUser user = userHouseService.getUser(token);
            houseValidator.checkHouse(HouseLeaseDTO);
            HouLeaseInfo houLeaseInfoV2 = houseLeaseInfoConvertor.toModel(HouseLeaseDTO);
            String id = houseService.updateHouse(houLeaseInfoV2, user);
            uploadService.uploadFileList(id, user);
        } catch (ApplicationException e) {
            return ResultDTO.failure(new ResultError(e.getMessage(),null));
        }
        return ResultDTO.success();
    }

    /**
     * 刷新操作
     */
    @RequestMapping(value = "/fresh/{id}", method = RequestMethod.POST)
    public ResultDTO<Void> freshTime(@PathVariable String id,
                                     @RequestHeader String token) throws ApplicationException {
        UsrUser usrUser = userHouseService.getUser(token);
        if (houseService.updateHouseFreshTime(id, usrUser) != 0) {
            return ResultDTO.success();
        } else {
            return ResultDTO.failure(new ResultError("刷新失败！一天只能刷新一次",null));
        }

    }
}
