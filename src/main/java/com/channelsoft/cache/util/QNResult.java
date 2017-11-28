package com.channelsoft.cache.util;

/**
 * Created by yuanshun on 2017/11/24.
 */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 结果枚举类型
 *
 */
public enum QNResult {
    SUCCESS("000000","成功"),
    PARAM_EMPTY("111001","请求参数为空"),
    ;
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private QNResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CacheResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
