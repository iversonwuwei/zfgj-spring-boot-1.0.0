package com.dlfc.zfgj.controller.mobile;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.convertor.mobile.HomeConvertor;
import com.dlfc.zfgj.dto.base.ResultDTO;
import com.dlfc.zfgj.dto.mobile.HomeDTO;
import com.dlfc.zfgj.entity.UsrUser;
import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.service.mobile.UserHouseMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by K on 2017/6/8.
 */

@RestController
@RequestMapping("/m/home")
public class HomeController {

    @Autowired
    @Qualifier("us111333M")
    private UserHouseMService userHouseMService;
    @Autowired
    private HomeConvertor homeConvertor;

    @RequestMapping(method = RequestMethod.GET)
    public ResultDTO<HomeDTO> home(@RequestHeader String token) {
        HomeDTO homeDTO = new HomeDTO();
        try {
            UsrUser user = userHouseMService.getUserName(token);
            homeDTO = homeConvertor.toDTO(user);
        } catch (ApplicationException e) {
            return ResultDTO.failure(homeDTO, new ResultError(e.getMessage(), null));
        }
        return ResultDTO.success(homeDTO);
    }
}
