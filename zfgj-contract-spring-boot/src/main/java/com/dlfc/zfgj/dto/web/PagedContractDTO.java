package com.dlfc.zfgj.dto.web;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by walden on 2017/2/28.
 */
@Getter
@Setter
public class PagedContractDTO {

    private Integer pageSize;

    private Integer pageNum;

    private Integer currentPage;

    private List<ContractDTO> conContracts;

}
