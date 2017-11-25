package com.channelsoft.to;

/**
 * Created by yuanshun on 2017/11/25.
 */

/**
 * 接口返回对象
 *
 */
public class ResponseObj {

    private String code;
    private String message;
    private String result;
    private RequestObj requestObj;

    public RequestObj getRequestObj() {
        return requestObj;
    }

    public void setRequestObj(RequestObj requestObj) {
        this.requestObj = requestObj;
    }

    public ResponseObj() {
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
