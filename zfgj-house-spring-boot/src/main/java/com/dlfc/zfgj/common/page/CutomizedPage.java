package com.dlfc.zfgj.common.page;



import com.dlfc.zfgj.common.impl.Paging;
import com.dlfc.zfgj.convertor.web.PagehConvertor;
import com.dlfc.zfgj.dto.web.HouseDTO;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by walden on 2017/2/27.
 */
@Getter
@Setter
@Component
public class CutomizedPage implements Pageable<HouseDTO> {

    private Integer currentPageNo = 1;

    @Autowired
    private PagehConvertor pageConvertor;

    private List<HouseDTO> source;
    @Autowired
    private Paging page;

    @Override
    public PageInfo<HouseDTO> index() throws CustomRuntimeException {
        if (null == this.source || this.source.size() == 0) {
            PageInfo pageInfo =  new PageInfo();
            pageInfo.setTotalNum(0);
            return pageInfo;
        }
        this.currentPageNo = 1;
        this.page = HouseFactory.getInstance(this.source).findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<HouseDTO> reSize(Integer pageSize) throws CustomRuntimeException {
        this.currentPageNo = 1;
        this.page = HouseFactory.getInstance(this.source).pagedResults(pageSize);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<HouseDTO> reNo(Integer pageNo) throws CustomRuntimeException {
        this.currentPageNo = pageNo;
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<HouseDTO> next() throws CustomRuntimeException {
        Integer maxPageNo = this.page.getTotalPageNo();
        this.currentPageNo = this.currentPageNo + 1;
        if (this.currentPageNo >= maxPageNo) {
            this.currentPageNo = maxPageNo;
        }
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<HouseDTO> previous() throws CustomRuntimeException {
        this.currentPageNo = this.currentPageNo - 1;
        if (this.currentPageNo <= 0) {
            this.currentPageNo = 1;
        }
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<HouseDTO> first() throws CustomRuntimeException {
        this.currentPageNo = 1;
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<HouseDTO> last() throws CustomRuntimeException {
        this.currentPageNo = this.page.getTotalPageNo();
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }
}
