package com.dlfc.zfgj.controller.web;

import com.dlfc.zfgj.common.page.CutomizedPage;
import com.dlfc.zfgj.common.page.PageInfo;
import com.dlfc.zfgj.dto.web.HouseDTO;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by K on 2017/3/7.
 */

@Slf4j
@RestController
@RequestMapping(value = "/w/housePage")
public class HouseInfoPageController {

    @Autowired
    private CutomizedPage cutomizedPage;

    /**
     * 通过页码分页
     *
     * @param pageNo
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/pageNo", method = RequestMethod.GET)
    public PageInfo<HouseDTO> getPageByNo(@RequestParam Integer pageNo) throws CustomRuntimeException {
        return cutomizedPage.reNo(pageNo);
    }

    /**
     * 通过单页显示条数分页
     *
     * @param pageSize
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/pageSize", method = RequestMethod.GET)
    public PageInfo<HouseDTO> getPageBySize(@RequestParam Integer pageSize) throws CustomRuntimeException {
        return cutomizedPage.reSize(pageSize);
    }

    /**
     * 上一页
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/previous", method = RequestMethod.GET)
    public PageInfo<HouseDTO> getPreviousPage() throws CustomRuntimeException {
        return cutomizedPage.previous();
    }

    /**
     * 下一页
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public PageInfo<HouseDTO> getNextPage() throws CustomRuntimeException {
        return cutomizedPage.next();
    }

    /**
     * 最后一页
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/last", method = RequestMethod.GET)
    public PageInfo<HouseDTO> getLastPage() throws CustomRuntimeException {
        return cutomizedPage.last();
    }
}
