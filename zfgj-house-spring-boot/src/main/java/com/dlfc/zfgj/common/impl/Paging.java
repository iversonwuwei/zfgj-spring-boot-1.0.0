package com.dlfc.zfgj.common.impl;

import com.dlfc.admin.common.config.Global;
import com.dlfc.zfgj.dto.web.HouseDTO;
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

    private List houseIfo;

    private Integer pageSize = Integer.valueOf(Global.getConfig("page.pageSize"));

    private Integer currentPageNo = 1;

    private List results;

    public Paging() {
    }

    public Paging(List houseIfo) {
        this.houseIfo = houseIfo;
    }

    public Map<Integer, List> allPagedResults() {
        int startIndex = 0;
        int endIndex;
        if (this.houseIfo.size() < this.pageSize) {
            endIndex = this.houseIfo.size();
        } else {
            endIndex = this.pageSize;
        }
        Map<Integer, List> pagedMap = new HashMap<>();
        Integer totalPageNo = this.getTotalPageNo();
        for (int i = 0; i < totalPageNo; i++) {
            pagedMap.put(i + 1, subPage(startIndex, endIndex - 1));
            if (endIndex <= this.houseIfo.size() - this.pageSize) {
                startIndex = endIndex;
                endIndex += this.pageSize;
            } else if (endIndex < this.houseIfo.size()) {
                startIndex = endIndex;
                endIndex = this.houseIfo.size();
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

    private List<HouseDTO> subPage(int startIndex, int endIndex) {
        return this.houseIfo.subList(startIndex, endIndex + 1);
    }

    public int getTotalPageNo() {
        if (null == this.houseIfo
                || null == this.pageSize
                || 0 == this.pageSize) {
            return 0;
        } else if (this.houseIfo.size() % this.pageSize != 0) {
            return this.houseIfo.size() / this.pageSize + 1;
        } else {
            return this.houseIfo.size() / this.pageSize;
        }
    }

    public Integer getPageSize() {
        return this.pageSize;
    }
}
