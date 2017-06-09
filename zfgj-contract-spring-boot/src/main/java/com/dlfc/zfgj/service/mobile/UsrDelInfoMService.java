package com.dlfc.zfgj.service.mobile;

import java.util.List;

/**
 * Created by K on 2017/3/10.
 */
public interface UsrDelInfoMService {

    List<String> getDelIdListByUser();

    List<String> getDelIdListByEmpIdList(List<String> emlIdList);
}
