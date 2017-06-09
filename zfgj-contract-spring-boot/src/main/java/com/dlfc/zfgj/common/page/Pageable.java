package com.dlfc.zfgj.common.page;

import com.dlfc.zfgj.exception.CustomRuntimeException;

/**
 * Created by walden on 2017/2/28.
 */
public interface Pageable<T> {

    PageInfo<T> reSize(Integer pageSize) throws CustomRuntimeException;

    PageInfo<T> reNo(Integer pageSize) throws CustomRuntimeException;

    PageInfo<T> next() throws CustomRuntimeException;

    PageInfo<T> previous() throws CustomRuntimeException;

    PageInfo<T> first() throws CustomRuntimeException;

    PageInfo<T> last() throws CustomRuntimeException;
}
