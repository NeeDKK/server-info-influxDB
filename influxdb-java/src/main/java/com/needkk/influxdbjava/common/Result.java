package com.needkk.influxdbjava.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-08 23:30
 * @Description:
 */
@Data
public class Result<T> {

    public static final int SUCCESSFUL_CODE = 0;
    public static final int FAILED_CODE = 7;
    public static final String SUCCESSFUL_MSG = "处理成功";
    public static final String FAILED_MSG = "处理失败";

    private int code;
    private String msg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Instant timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    private Result() {
        this.timestamp = Instant.now();
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = ZonedDateTime.now().toInstant();
    }


    public static Result success(Object data) {
        return new Result(SUCCESSFUL_CODE, SUCCESSFUL_MSG, data);
    }


    public static Result failed(String msg) {
        return new Result(FAILED_CODE, FAILED_MSG, msg);
    }
}
