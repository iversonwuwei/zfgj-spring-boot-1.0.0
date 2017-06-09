package com.dlfc.zfgj.controller.web;

import com.dlfc.zfgj.convertor.web.*;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.web.*;
import com.dlfc.zfgj.entity.*;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.SelectCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wsz on 2017/3/16.
 */

@RestController
@RequestMapping("/w/select")
public class SelectPledgeController {

    @Autowired
    private SelectPledgeConvertor selectConvertor;
    @Autowired
    private SelectCodeService selectCodeService;
    @Autowired
    private SelectPayConvertor selectPayConvertor;
    @Autowired
    private SelectAreaConvertor selectAreaConvertor;
    @Autowired
    private SelectBusinessAreaConvertor selectBusinessAreaConvertor;
    @Autowired
    private SelectMetroConvertor selectMetroConvertor;
    @Autowired
    private SelectBuildingTimeConvertor selectBuildingTimeConvertor;
    @Autowired
    private SelectCompConvertor selectCompConvertorConvertor;
    @Autowired
    private SelectDirectionConvertor selectDirectionConvertor;
    @Autowired
    private SelectRoomConvertor selectRoomConvertor;


    /**
     * 出租信息押selece
     *
     * @return
     */
    @RequestMapping(value = "/pledge", method = RequestMethod.GET)
    public ListResultDTO<SelectPledgeCodeDTO> pledge() {
        try {
            List<SysCode> list = selectCodeService.getByPledge();
            return selectConvertor.toResultDTO(list);
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 出租信息付select
     *
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public ListResultDTO<SelectPayCodeDTO> pay() {
        try {
            List<SysCode> list = selectCodeService.getByPay();
            return selectPayConvertor.toResultDTO(list);
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 城市区域select
     *
     * @return
     */
    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public ListResultDTO<SelectAreaDTO> area() {
        try {
            List<SysAreaAreas> list = selectCodeService.getByArea();
            return selectAreaConvertor.toResultDTO(list);
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 城市区域对应的商圈select
     *
     * @return
     */
    @RequestMapping(value = "/businessarea", method = RequestMethod.GET)
    public ListResultDTO<SelectBusinessAreaDTO> businessarea(@RequestParam String id) {
        try {
            List<SysTradeAreas> list = selectCodeService.getByBusinessArea(id);
            return selectBusinessAreaConvertor.toResultDTO(list);
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商圈对应地铁select
     *
     * @return
     */
    @RequestMapping(value = "/metro", method = RequestMethod.GET)
    public ResultDTO<SelectMetroDTO> metro(@RequestParam String id) {

        SysTransportStation list = selectCodeService.getByMetro(id);
        return selectMetroConvertor.toResultDTO(list);

    }

    /**
     * 建筑时间select
     *
     * @return
     */
    @RequestMapping(value = "/btime", method = RequestMethod.GET)
    public ListResultDTO<SelectBuildingTimeDTO> buildingTime() {
        try {
            List<SysCode> list = selectCodeService.getByBulidingTime();
            return selectBuildingTimeConvertor.toResultDTO(list);
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 房屋朝向select
     *
     * @return
     */
    @RequestMapping(value = "/direction", method = RequestMethod.GET)
    public ListResultDTO<SelectDirectionDTO> direction() {
        try {
            List<SysCode> list = selectCodeService.getDirection();
            return selectDirectionConvertor.toResultDTO(list);
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

//    /**
//     * 公司列表
//     *
//     * @return
//     */
//    @RequestMapping(value = "/comlist", method = RequestMethod.GET)
//    public ListResultDTO<SelectCompCodeDTO> comlist() {
//        try {
//            List<AgtCompInfo> list = selectCodeService.getCompInfo();
//            return selectCompConvertorConvertor.toResultDTO(list);
//        } catch (CustomRuntimeException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 出租房间列表
     *
     * @return
     */
    @RequestMapping(value = "/roomlist", method = RequestMethod.GET)
    public ListResultDTO<SelectRoomCodeDTO> roomlist() {
        try {
            List<SysCode> list = selectCodeService.getRoomList();
            return selectRoomConvertor.toResultDTO(list);
        } catch (CustomRuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
