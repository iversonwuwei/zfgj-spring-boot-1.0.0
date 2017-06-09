package com.dlfc.zfgj.dto.base;

import com.dlfc.zfgj.error.ResultError;
import com.dlfc.zfgj.exception.CustomRuntimeException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by walden on 2017/2/9.
 */
public class ListResultDTO<T> extends AbstractResultDTO {
    /**
     * 列表数据
     */
    protected List<T> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "data")
    public List<T> getData() {
        return this.data;
    }

    public void setData(final List<T> data) {
        this.data = data;
    }

    public ListResultDTO() {
    }

    ListResultDTO(final Status status) {
        this.status = status;
    }

    public static <T> ListResultDTO<T> success(final List<T> listData) throws CustomRuntimeException {
        if (listData == null) {
            throw new CustomRuntimeException("NullPointerException", "The formal parameter 'listData' cannot be null");
        }

        final ListResultDTO<T> result = new ListResultDTO<>(Status.success);
        result.setData(listData);
        return result;
    }

    public static <T> ListResultDTO<T> failure(final List<T> listData, final ResultError... errors) {
        final ListResultDTO<T> result = new ListResultDTO<>(Status.failure);
        result.setData(listData);
        result.setErrors(errors);
        return result;
    }
}
