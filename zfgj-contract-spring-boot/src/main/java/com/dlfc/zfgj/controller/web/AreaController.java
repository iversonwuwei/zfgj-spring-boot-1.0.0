package com.dlfc.zfgj.controller.web;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.web.SystemCityConvertor;
import com.dlfc.zfgj.convertor.web.SystemProvinceConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.web.SystemCodeDTO;
import com.dlfc.zfgj.entity.SysAreaCities;
import com.dlfc.zfgj.entity.SysAreaProvinces;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.web.SysAreaAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by K on 2017/3/7.
 */

@RestController
@RequestMapping(value = "/w/area")
public class AreaController {
    // 直辖市：北京
    private static final String MUNICIPALITY_BEIJING_PROVINCE_ID = "110000";
    // 直辖市：上海
    private static final String MUNICIPALITY_TIANJIN_PROVINCE_ID = "120000";
    // 直辖市：天津
    private static final String MUNICIPALITY_SHANGHAI_PROVINCE_ID = "310000";
    // 直辖市：重庆
    private static final String MUNICIPALITY_CHONGQING_PROVINCE_ID = "500000";
    // 特别行政区：香港
    private static final String SPECIAL_HONGKONG_PROVINCE_ID = "810000";
    // 特别行政区：澳门
    private static final String SPECIAL_MACAO_PROVINCE_ID = "820000";
    // 台湾省
    private static final String MUNICIPALITY_TAIWAN_PROVINCE_ID = "710000";

    @Autowired
    private SysAreaAreasService sysAreaAreasServiceImpl;
    @Autowired
    private SystemProvinceConvertor systemProvinceConvertor;
    @Autowired
    private SystemCityConvertor systemCityConvertor;

    /**
     * 获取省列表
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/provinceList", method = RequestMethod.GET)
    public ListResultDTO<SystemCodeDTO> getProvinceList() throws CustomRuntimeException {
        // 户籍（省）列表
        List<SysAreaProvinces> provincesList = sysAreaAreasServiceImpl.getAllProvinces();
        return systemProvinceConvertor.toResultDTO(provincesList);
    }

    /**
     * 根据所选省份获取下属市
     *
     * @param provinceId
     * @return
     */
    @RequestMapping(value = "/cityList", method = RequestMethod.GET)
    public ListResultDTO<SystemCodeDTO> getCitiesByProvince(@RequestParam String provinceId)
            throws CustomRuntimeException {
        if (StringUtils.isEmpty(provinceId)
                || provinceId.equals(MUNICIPALITY_BEIJING_PROVINCE_ID)
                || provinceId.equals(MUNICIPALITY_TIANJIN_PROVINCE_ID)
                || provinceId.equals(MUNICIPALITY_SHANGHAI_PROVINCE_ID)
                || provinceId.equals(MUNICIPALITY_CHONGQING_PROVINCE_ID)
                || provinceId.equals(SPECIAL_HONGKONG_PROVINCE_ID)
                || provinceId.equals(SPECIAL_MACAO_PROVINCE_ID)
                || provinceId.equals(MUNICIPALITY_TAIWAN_PROVINCE_ID)) {
            // 所选省份为直辖市、自治区、港澳台时，返回取得失败，页面相关显示项目置空或者置灰
            return ListResultDTO.failure(null, null);
        }
        List<SysAreaCities> sysAreaCitiesList = sysAreaAreasServiceImpl.getAllCities(provinceId);
        if (null == sysAreaCitiesList || sysAreaCitiesList.size() == 0) {
            return ListResultDTO.failure(null, null);
        }
        return systemCityConvertor.toResultDTO(sysAreaCitiesList);
    }
}
