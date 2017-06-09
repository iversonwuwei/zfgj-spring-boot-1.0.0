package com.dlfc.zfgj.common.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by walden on 2017/2/28.
 */
@Getter
@Setter
public class PageInfo<T> {

    private Integer totalNum;

    private Integer totalPageNum;

    private Integer pageSize;

    private Integer currentPageNum;

    private List<T> results;
}
