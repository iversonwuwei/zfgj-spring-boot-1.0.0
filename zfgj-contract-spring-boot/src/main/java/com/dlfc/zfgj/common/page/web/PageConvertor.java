package com.dlfc.zfgj.common.page.web;

import com.dlfc.zfgj.common.page.PageInfo;
import com.dlfc.zfgj.dto.web.ContractDTO;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/28.
 */
@Component
public class PageConvertor {

    public PageInfo<ContractDTO> toPageInfo(Paging page) {
        PageInfo<ContractDTO> pageInfo = new PageInfo<>();
        pageInfo.setTotalNum(page.getConContracts().size());
        pageInfo.setTotalPageNum(page.getTotalPageNo());
        pageInfo.setPageSize(page.getPageSize());
        pageInfo.setResults(page.getResults());
        pageInfo.setCurrentPageNum(page.getCurrentPageNo());
        return pageInfo;
    }
}
