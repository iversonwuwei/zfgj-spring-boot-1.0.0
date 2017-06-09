package com.dlfc.zfgj.common.page.mobile;

import com.dlfc.zfgj.common.page.PageInfo;
import com.dlfc.zfgj.dto.moblie.ContractMDTO;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/28.
 */
@Component
public class PageMConvertor {

    public PageInfo<ContractMDTO> toPageInfo(PagingM page) {
        PageInfo<ContractMDTO> pageInfo = new PageInfo<>();
        pageInfo.setTotalNum(page.getConContracts().size());
        pageInfo.setTotalPageNum(page.getTotalPageNo());
        pageInfo.setPageSize(page.getPageSize());
        pageInfo.setResults(page.getResults());
        pageInfo.setCurrentPageNum(page.getCurrentPageNo());
        return pageInfo;
    }
}
