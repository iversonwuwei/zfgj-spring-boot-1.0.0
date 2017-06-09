package com.dlfc.zfgj.dto.moblie;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by walden on 2017/2/28.
 */
@Getter
@Setter
public class PagedContractMDTO {

    private Integer pageSize;

    private Integer pageNum;

    private Integer currentPage;

    private List<ContractMDTO> conContracts;

}
