package com.channelsoft.cache.to;

/**
 * 请求参数信息 - 键值信息
 *
 * @author yuanshun
 * @create 2017-11-29
 */
public class RequestParamKV {
    //键
    private String key;
    //值
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RequestParamKV(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RequestParamKV() {

    }
}
