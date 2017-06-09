package com.dlfc.zfgj.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/2/9.
 */

@Getter
@Setter
@Component
public class ResultError {
    /**
     * 异常关联的字段
     */
    private String field;
    /**
     * 异常消息
     */
    private String errmsg;
    /**
     * 异常编码
     */
    private String errcode;

    public ResultError() {
    }

    public ResultError(final String errmsg, final String field) {
        this.field = field;
        this.errmsg = errmsg;
    }

    public ResultError(final String errcode, final String errmsg, final String field) {
        this.field = field;
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return String.format("{errcode:%s, errmsg:%s, field:%s}", this.errcode, this.errmsg, this.field);
    }
}
