package com.dlfc.zfgj.common.page.mobile;

import com.dlfc.zfgj.common.page.PageInfo;
import com.dlfc.zfgj.common.page.Pageable;
import com.dlfc.zfgj.dto.moblie.ContractMDTO;
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
public class CutomizedMPage implements Pageable<ContractMDTO> {

    private Integer currentPageNo = 1;

    @Autowired
    private PageMConvertor pageConvertor;

    private List<ContractMDTO> source;

    private PagingM page;

    @Override
    public PageInfo<ContractMDTO> reSize(Integer pageSize) throws CustomRuntimeException {
        this.currentPageNo = 1;
        this.page = PageMFactory.getInstance(this.source).pagedResults(pageSize);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractMDTO> reNo(Integer pageNo) throws CustomRuntimeException {
        this.currentPageNo = pageNo;
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractMDTO> next() throws CustomRuntimeException {
        Integer maxPageNo = this.page.getTotalPageNo();
        this.currentPageNo = this.currentPageNo + 1;
        if (this.currentPageNo >= maxPageNo) {
            this.currentPageNo = maxPageNo;
        }
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractMDTO> previous() throws CustomRuntimeException {
        this.currentPageNo = this.currentPageNo - 1;
        if (this.currentPageNo <= 0) {
            this.currentPageNo = 1;
        }
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractMDTO> first() throws CustomRuntimeException {
        this.currentPageNo = 1;
        this.page = PageMFactory.getInstance(this.source).findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }

    @Override
    public PageInfo<ContractMDTO> last() throws CustomRuntimeException {
        this.currentPageNo = this.page.getTotalPageNo();
        this.page = this.page.findPageByNum(this.currentPageNo);
        return this.pageConvertor.toPageInfo(this.page);
    }
}
