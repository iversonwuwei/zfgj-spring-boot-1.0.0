package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.utils.StringUtils;
import com.dlfc.zfgj.convertor.mobile.SystemCityMConvertor;
import com.dlfc.zfgj.convertor.mobile.SystemProvinceMConvertor;
import com.dlfc.zfgj.dto.base.ListResultDTO;
import com.dlfc.zfgj.dto.moblie.SystemCodeMDTO;
import com.dlfc.zfgj.entity.SysAreaProvinces;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.dlfc.zfgj.service.mobile.SysAreaAreasMService;
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
@RequestMapping(value = "/m/area")
public class AreaMController {
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
    private SysAreaAreasMService sysAreaAreasMServiceImpl;
    @Autowired
    private SystemProvinceMConvertor systemProvinceMConvertor;
    @Autowired
    private SystemCityMConvertor systemCityMConvertor;

    /**
     * 获取省列表
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/provinceList", method = RequestMethod.GET)
    public ListResultDTO<SystemCodeMDTO> getProvinceList() throws CustomRuntimeException {
        // 户籍（省）列表
        List<SysAreaProvinces> provincesList = sysAreaAreasMServiceImpl.getAllProvinces();
        return systemProvinceMConvertor.toResultDTO(provincesList);
    }

    /**
     * 根据所选省份获取下属市
     *
     * @param provinceId
     * @return
     */
    @RequestMapping(value = "/cityList", method = RequestMethod.GET)
    public List<SystemCodeMDTO> getCitiesByProvince(@RequestParam String provinceId) throws CustomRuntimeException {
        if (StringUtils.isEmpty(provinceId)
                || MUNICIPALITY_BEIJING_PROVINCE_ID.equals(provinceId)
                || MUNICIPALITY_TIANJIN_PROVINCE_ID.equals(provinceId)
                || MUNICIPALITY_SHANGHAI_PROVINCE_ID.equals(provinceId)
                || MUNICIPALITY_CHONGQING_PROVINCE_ID.equals(provinceId)
                || SPECIAL_HONGKONG_PROVINCE_ID.equals(provinceId)
                || SPECIAL_MACAO_PROVINCE_ID.equals(provinceId)
                || MUNICIPALITY_TAIWAN_PROVINCE_ID.equals(provinceId)) {
            return null;
        } else {
            return systemCityMConvertor.toListDTO(sysAreaAreasMServiceImpl.getAllCities(provinceId));
        }
    }
}
