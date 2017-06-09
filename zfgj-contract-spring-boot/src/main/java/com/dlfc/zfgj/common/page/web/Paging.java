package com.dlfc.zfgj.common.page.web;

import com.dlfc.admin.common.config.Global;
import com.dlfc.zfgj.dto.web.ContractDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walden on 2017/2/24.
 */
@Getter
@Setter
@Component
public class Paging {

    private List conContracts;

    private Integer pageSize = Integer.valueOf(Global.getConfig("page.pageSize"));

    private Integer currentPageNo = 1;

    private List results;

    public Paging() {
    }

    public Paging(List conContracts) {
        this.conContracts = conContracts;
    }

    public Map<Integer, List> allPagedResults() {
        int startIndex = 0;
        int endIndex;
        if (this.conContracts.size() < this.pageSize) {
            endIndex = this.conContracts.size();
        } else {
            endIndex = this.pageSize;
        }
        Map<Integer, List> pagedMap = new HashMap<>();
        Integer totalPageNo = this.getTotalPageNo();
        for (int i = 0; i < totalPageNo; i++) {
            pagedMap.put(i + 1, subPage(startIndex, endIndex - 1));
            if (endIndex <= this.conContracts.size() - this.pageSize) {
                startIndex = endIndex;
                endIndex += this.pageSize;
            } else if (endIndex < this.conContracts.size()) {
                startIndex = endIndex;
                endIndex = this.conContracts.size();
            }
        }
        return pagedMap;
    }

    public Paging pagedResults(int pageSize) {
        this.pageSize = pageSize;
        this.currentPageNo = 1;
        Map<Integer, List> pagedMap = this.allPagedResults();
        this.results = pagedMap.get(currentPageNo);
        return this;
    }

    public Paging findPageByNum(Integer pageNo) {
        this.currentPageNo = pageNo;
        Map<Integer, List> pagedMap = this.allPagedResults();
        this.results = pagedMap.get(this.currentPageNo);
        return this;
    }

    private List<ContractDTO> subPage(int startIndex, int endIndex) {
        return this.conContracts.subList(startIndex, endIndex + 1);
    }

    public int getTotalPageNo() {
        if (null == this.conContracts
                || null == this.pageSize
                || 0 == this.pageSize) {
            return 0;
        } else if (this.conContracts.size() % this.pageSize != 0) {
            return this.conContracts.size() / this.pageSize + 1;
        } else {
            return this.conContracts.size() / this.pageSize;
        }
    }

    public Integer getPageSize() {
        return this.pageSize;
    }
}
