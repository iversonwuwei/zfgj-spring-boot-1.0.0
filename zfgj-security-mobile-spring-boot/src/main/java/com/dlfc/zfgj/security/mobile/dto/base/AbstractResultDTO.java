package com.dlfc.zfgj.security.mobile.dto.base;

import com.dlfc.zfgj.security.mobile.error.ResultError;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by walden on 2017/2/9.
 */
public abstract class AbstractResultDTO {
    /**
     * 请求的处理结果状态
     */
    public enum Status {
        /**
         * 成功 或 失败
         */
        success, failure;
    }

    /**
     * 处理结果状态
     */
    //@ApiModelProperty(value = "结果状态（成功 或 失败）", position = 0)
    protected Status status;

    /**
     * 错误消息
     */
    //@ApiModelProperty(value = "异常信息", position = 10)
    protected ResultError[] errors;

    /**
     * 时间戳
     */
    //@ApiModelProperty(value = "系统处理时间戳（增量拉数据时使用）", position = 11)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Date timestamp;

    ///////////////////////////////////////
    // Getter
    ///////////////////////////////////////
    @JsonProperty(value = "status")
    public Status getStatus() {
        return this.status;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "errors")
    public ResultError[] getErrors() {
        return this.errors;
    }

    ///////////////////////////////////////
    // Setter
    ///////////////////////////////////////
    protected void setErrors(final ResultError... errors) {
        this.errors = errors;
    }

    @JsonIgnore
    public boolean isFailure() {
        return Status.failure == this.status;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return Status.success == this.status;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    @JsonIgnore
    public String errorsToString() {
        if ((this.errors != null) && (this.errors.length > 0)) {
            final StringBuilder builder = new StringBuilder();
            builder.append("Errors : [");
            for (final ResultError error : this.errors) {
                builder.append(error.toString());
            }
            builder.append("]");
            return builder.toString();
        } else {
            return "errors : []";
        }
    }
}
