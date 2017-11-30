package com.channelsoft.cache.controller;

import com.channelsoft.cache.to.RequestObj;
import com.channelsoft.cache.to.RequestParamKV;
import com.channelsoft.cache.to.ResponseObj;
import com.channelsoft.cache.util.JacksonUtil;
import com.channelsoft.cache.util.QNResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 缓存控制层
 * 负责对外提供http接口,进行数据的存储和获取操作
 *
 * Created by yuanshun on 2017/11/19.
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    private Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置key和value数据到缓存中
     * 设置key有效期为1天
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/setKeyAndValue", method = RequestMethod.POST)
    public ResponseObj setKeyAndValue(@RequestBody String param) {
        logger.debug("CacheController.setKeyAndValue() param:{} start.", param);

        if (StringUtils.isEmpty(param)) {
            logger.warn("[/setKeyAndValue]获取param参数异常:参数为空.");
            return new ResponseObj(QNResult.REQUEST_PARAM_EMPTY, new RequestObj(param, "/cache/setKeyAndValue"));
        }
        RequestParamKV requestParam = JacksonUtil.readValue(param, RequestParamKV.class);
        if (requestParam == null) {
            logger.warn("[/setKeyAndValue]获取param参数异常:格式非法.");
            return new ResponseObj(QNResult.REQUEST_PARAM_TYPE_ERROR, new RequestObj(param, "/cache/setKeyAndValue"));
        }
        if (StringUtils.isEmpty(requestParam.getKey()) || StringUtils.isEmpty(requestParam.getValue())) {
            logger.warn("[/setKeyAndValue]获取param参数异常:参数为空.");
            return new ResponseObj(QNResult.REQUEST_PARAM_EMPTY, new RequestObj(param, "/cache/setKeyAndValue"));
        }
        try {
            stringRedisTemplate.opsForValue().set(requestParam.getKey(), requestParam.getValue());
            stringRedisTemplate.expire(requestParam.getKey(), 1, TimeUnit.DAYS);//设置key有效期为1天
        } catch (Exception e) {
            logger.warn("执行redis异常:{}", e.getMessage());
            e.printStackTrace();
            return new ResponseObj(QNResult.ERROR_INSIDE_EXCEPTION, new RequestObj(param, "/cache/setKeyAndValue"));
        }
        logger.debug("CacheController.setKeyAndValue() key:{} value:{} end.", requestParam.getKey(), requestParam.getValue());
        return new ResponseObj(QNResult.SUCCESS, requestParam.getKey());
    }

    /**
     * 保存值到缓存中，并返回一个uuid供调用者需要时获取数据使用
     * 设置key有效期为1天
     *
     * @param param
     * @return 一个uuid的key, 供调用者保存
     */
    @RequestMapping(value = "/setValue", method = RequestMethod.POST)
    public ResponseObj setValue(@RequestBody String param) {
        logger.debug("CacheController.setValue() param:{} start.", param);
        if (StringUtils.isEmpty(param)) {
            logger.warn("[/setValue]获取param参数异常:参数为空.");
            return new ResponseObj(QNResult.REQUEST_PARAM_EMPTY, new RequestObj(param, "/cache/setValue"));
        }
        RequestParamKV requestParam = JacksonUtil.readValue(param, RequestParamKV.class);
        if (requestParam == null) {
            logger.warn("[/setKeyAndValue]获取param参数异常:格式非法.");
            return new ResponseObj(QNResult.REQUEST_PARAM_TYPE_ERROR, new RequestObj(param, "/cache/setValue"));
        }
        if (StringUtils.isEmpty(requestParam.getValue())) {
            logger.warn("[/setValue]获取value参数异常:参数为空.");
            return new ResponseObj(QNResult.REQUEST_PARAM_EMPTY, new RequestObj(param, "/cache/setValue"));
        }
        if (StringUtils.isEmpty(requestParam.getValue())) {
            logger.warn("[/setKeyAndValue]获取param参数异常:参数为空.");
            return new ResponseObj(QNResult.REQUEST_PARAM_EMPTY, new RequestObj(param, "/cache/setValue"));
        }
        String uuid = new String();
        uuid = UUID.randomUUID().toString();
        try {
            stringRedisTemplate.opsForValue().set(uuid, requestParam.getValue());
            stringRedisTemplate.expire(uuid, 1, TimeUnit.DAYS);//设置key有效期为1天
        } catch (Exception e) {
            logger.warn("执行redis异常:{}", e.getMessage());
            e.printStackTrace();
            return new ResponseObj(QNResult.ERROR_INSIDE_EXCEPTION, new RequestObj(param, "/cache/setValue"));
        }
        logger.debug("CacheController.setValue() key:{} value:{} end.", uuid, requestParam.getValue());
        return new ResponseObj(QNResult.SUCCESS, uuid);
    }

    /**
     * 获取特定key对应的值
     *
     * @param key
     *
     * @return
     */
    @RequestMapping(value = "/getValue", method = RequestMethod.GET)
    public ResponseObj getValue(@RequestParam(name = "key",required = false) String key) {
        logger.debug("CacheController.getValue() key:{} start.", key);

        if (StringUtils.isEmpty(key)) {
            logger.warn("[/getValue] key:{} 获取key参数异常:参数为空.", key);
            return new ResponseObj(QNResult.REQUEST_PARAM_EMPTY, new RequestObj(key, "/cache/getValue"));
        }
        String result = "";

        try {
            result = stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.warn("执行redis异常:{}", e.getMessage());
            e.printStackTrace();
            return new ResponseObj(QNResult.ERROR_INSIDE_EXCEPTION, new RequestObj(key, "/cache/getValue"));
        }

        if (StringUtils.isEmpty(result)) {
            logger.warn("[/getKey] key:{} 数据不存在.", key);
            return new ResponseObj(QNResult.REQUEST_DATA_NOT_EXIST, new RequestObj(key, "/cache/getValue"));
        }

        logger.debug("CacheController.getValue() key:{} result:{} end.", key, result);
        return new ResponseObj(QNResult.SUCCESS, result);
    }
}
