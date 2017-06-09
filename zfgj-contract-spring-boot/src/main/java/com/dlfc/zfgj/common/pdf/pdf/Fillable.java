package com.dlfc.zfgj.common.pdf.pdf;

/**
 * Created by walden on 2017/3/22.
 */
public interface Fillable<T, R> {

     R fill(T t);
}
