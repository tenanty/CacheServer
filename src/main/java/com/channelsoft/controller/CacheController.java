package com.channelsoft.controller;

import com.channelsoft.to.RequestObj;
import com.channelsoft.to.ResponseObj;
import com.channelsoft.util.QnResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by yuanshun on 2017/11/19.
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    private Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/setKey",method = RequestMethod.POST)
    public ResponseObj setKey(@RequestParam(name = "param",required = false)String param){
        logger.debug("CacheController.setKey() param:{} start.",param);
        if(StringUtils.isEmpty(param)){
            logger.warn("[/setKey]获取param参数异常:参数为空.");
            return new ResponseObj(QnResult.REQUEST_PARAM_EMPTY.getCode(),QnResult.REQUEST_PARAM_EMPTY.getMessage(),new RequestObj(param,"/setKey"));
        }

        String uuid = new String();
        uuid = UUID.randomUUID().toString();
        try{
            stringRedisTemplate.opsForValue().set(uuid, param);
        }catch (Exception e){
            logger.warn("执行redis异常:{}",e);
            return new ResponseObj(QnResult.ERROR_INSIDE_EXCEPTION.getCode(),QnResult.ERROR_INSIDE_EXCEPTION.getMessage(),new RequestObj(param,"/setKey"));
        }
        logger.debug("CacheController.setKey() key:{} param:{} ok.",uuid,param);
        return new ResponseObj(QnResult.SUCCESS.getCode(),QnResult.SUCCESS.getMessage(),uuid,new RequestObj(param,"/setKey"));
    }

    @RequestMapping(value = "/getKey",method = RequestMethod.GET)
    public ResponseObj getKey(@RequestParam(name = "key" ,required = false )String key){
        logger.debug("CacheController.getKey() key:{} start.",key);
        if(StringUtils.isEmpty(key)){
            logger.warn("[/getKey]获取param参数异常:参数为空.");
            return new ResponseObj(QnResult.REQUEST_PARAM_EMPTY.getCode(),QnResult.REQUEST_PARAM_EMPTY.getMessage(),new RequestObj(key,"/getKey"));
        }
        String result = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(result)){
            logger.warn("[/getKey]获取param参数异常:参数为空.");
            return new ResponseObj(QnResult.REQUEST_DATA_NOT_EXIST.getCode(),QnResult.REQUEST_DATA_NOT_EXIST.getMessage(),new RequestObj(key,"/getKey"));

        }
        logger.debug("CacheController.getKey() key:{} param:{} start.",key,result);
        return new ResponseObj(QnResult.SUCCESS.getCode(),QnResult.SUCCESS.getMessage(),result,new RequestObj(key,"/getKey"));
    }
}
