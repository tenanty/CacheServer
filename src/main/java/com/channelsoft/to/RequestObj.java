package com.channelsoft.to;

/**
 * Created by yuanshun on 2017/11/25.
 *
 */
public class RequestObj {
    //传入的key值信息
    private String param;
    private String method;


    public RequestObj() {
    }

    public RequestObj(String param) {
        this.param = param;
    }

    public RequestObj(String param, String method) {
        this.param = param;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
