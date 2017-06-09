package com.dlfc.zfgj.convertor.web;

import com.dlfc.zfgj.common.impl.Paging;
import com.dlfc.zfgj.common.page.PageInfo;
import com.dlfc.zfgj.dto.web.HouseDTO;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/28.
 */
@Component
public class PagehConvertor {

    public PageInfo<HouseDTO> toPageInfo(Paging page){
        PageInfo<HouseDTO> pageInfo = new PageInfo<>();
        pageInfo.setTotalNum(page.getHouseIfo().size());
        pageInfo.setTotalPageNum(page.getTotalPageNo());
        pageInfo.setPageSize(page.getPageSize());
        pageInfo.setResults(page.getResults());
        pageInfo.setCurrentPageNum(page.getCurrentPageNo());
        return pageInfo;
    }
}
