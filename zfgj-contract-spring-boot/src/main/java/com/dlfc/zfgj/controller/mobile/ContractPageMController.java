package com.dlfc.zfgj.controller.mobile;

import com.dlfc.zfgj.common.page.mobile.CutomizedMPage;
import com.dlfc.zfgj.common.page.PageInfo;
import com.dlfc.zfgj.dto.moblie.ContractMDTO;
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
@RequestMapping(value = "/m/contractPage")
public class ContractPageMController {

    @Autowired
    private CutomizedMPage cutomizedPage;

    /**
     * 通过页码分页
     *
     * @param pageNo
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/pageNo", method = RequestMethod.GET)
    public PageInfo<ContractMDTO> getPageByNo(@RequestParam Integer pageNo) throws CustomRuntimeException {
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
    public PageInfo<ContractMDTO> getPageBySize(@RequestParam Integer pageSize) throws CustomRuntimeException {
        return cutomizedPage.reSize(pageSize);
    }

    /**
     * 上一页
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/previous", method = RequestMethod.GET)
    public PageInfo<ContractMDTO> getPreviousPage() throws CustomRuntimeException {
        return cutomizedPage.previous();
    }

    /**
     * 下一页
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public PageInfo<ContractMDTO> getNextPage() throws CustomRuntimeException {
        return cutomizedPage.next();
    }

    /**
     * 最后一页
     *
     * @return
     * @throws CustomRuntimeException
     */
    @RequestMapping(value = "/last", method = RequestMethod.GET)
    public PageInfo<ContractMDTO> getLastPage() throws CustomRuntimeException {
        return cutomizedPage.last();
    }
}
