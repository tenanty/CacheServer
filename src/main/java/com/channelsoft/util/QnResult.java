package com.channelsoft.util;

/**
 * 响应结果枚举
 *
 * Created by yuanshun on 2017/11/25.
 */
public enum QnResult {
    SUCCESS("000000","成功"),
    REQUEST_PARAM_EMPTY("110001","请求参数为空"),
    REQUEST_PARAM_TYPE_ERROR("110002","请求参数格式非法"),
    REQUEST_DATA_NOT_EXIST("110003","请求数据不存在"),
    ERROR_UNKNOW("110004","未知错误");

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

    QnResult() {
    }

    QnResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
