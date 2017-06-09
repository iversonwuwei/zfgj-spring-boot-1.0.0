package com.dlfc.zfgj.common.page.web;

import com.dlfc.zfgj.common.page.PageInfo;
import com.dlfc.zfgj.common.page.Pageable;
import com.dlfc.zfgj.dto.web.ContractDTO;
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
public class CutomizedPage implements Pageable<ContractDTO> {

    private Integer currentPageNo = 1;

    @Autowired
    private PageConvertor pageConvertor;

    private List<ContractDTO> source;

    private Paging page;

    @Override
    public PageInfo<ContractDTO> reSize(Integer pageSize) throws CustomRuntimeException {
        this.currentPageNo = 1;
        this.page = PageFactory.getInstance(this.source).pagedResults(pageSize);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractDTO> reNo(Integer pageNo) throws CustomRuntimeException {
        this.currentPageNo = pageNo;
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractDTO> next() throws CustomRuntimeException {
        Integer maxPageNo = this.page.getTotalPageNo();
        this.currentPageNo = this.currentPageNo + 1;
        if (this.currentPageNo >= maxPageNo) {
            this.currentPageNo = maxPageNo;
        }
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractDTO> previous() throws CustomRuntimeException {
        this.currentPageNo = this.currentPageNo - 1;
        if (this.currentPageNo <= 0) {
            this.currentPageNo = 1;
        }
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractDTO> first() throws CustomRuntimeException {
        this.currentPageNo = 1;
        this.page = PageFactory.getInstance(this.source).findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractDTO> last() throws CustomRuntimeException {
        this.currentPageNo = this.page.getTotalPageNo();
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }
}
