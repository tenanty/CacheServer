package com.channelsoft.cache.to;

import com.channelsoft.cache.util.QNResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author yuanshun
 * @create 2017-11-24
 */
public class ResponseTo {
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String result;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

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

    public ResponseTo(String code, String message, String result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ResponseTo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseTo() {
    }


}
