package com.channelsoft.cache.to;

/**
 * Created by yuanshun on 2017/11/25.
 */

import com.channelsoft.cache.util.QNResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 接口返回对象
 *
 */
public class ResponseObj {

    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RequestObj requestObj;

    public RequestObj getRequestObj() {
        return requestObj;
    }

    public void setRequestObj(RequestObj requestObj) {
        this.requestObj = requestObj;
    }

    public ResponseObj() {
    }

    /**
     * 接收参数枚举类型
     *
     * @param qnResult
     * @param requestObj
     */
    public ResponseObj(QNResult qnResult,RequestObj requestObj){
        this.code = qnResult.getCode();
        this.message = qnResult.getMessage();
        this.requestObj = requestObj;
    }

    /**
     * 接收参数枚举类型
     *
     * @param qnResult
     * @param result
     */
    public ResponseObj(QNResult qnResult,String result){
        this.code = qnResult.getCode();
        this.message = qnResult.getMessage();
        this.result = result;
    }

    public ResponseObj(String code, String message, RequestObj requestObj) {
        this.code = code;
        this.message = message;
        this.requestObj = requestObj;
    }

    public ResponseObj(String code, String message, String result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ResponseObj(String code, String message, String result, RequestObj requestObj) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.requestObj = requestObj;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
