package com.channelsoft.cache.controller;

import com.channelsoft.cache.to.RequestObj;
import com.channelsoft.cache.to.ResponseObj;
import com.channelsoft.cache.util.QNResult;
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
            return new ResponseObj(QNResult.REQUEST_PARAM_EMPTY.getCode(),QNResult.REQUEST_PARAM_EMPTY.getMessage(),new RequestObj(param,"/setKey"));
        }

        String uuid = new String();
        uuid = UUID.randomUUID().toString();
        try{
            stringRedisTemplate.opsForValue().set(uuid, param);
        }catch (Exception e){
            logger.warn("执行redis异常:{}",e.getMessage());
            e.printStackTrace();
            return new ResponseObj(QNResult.ERROR_INSIDE_EXCEPTION.getCode(),QNResult.ERROR_INSIDE_EXCEPTION.getMessage(),new RequestObj(param,"/setKey"));
        }
        logger.debug("CacheController.setKey() key:{} param:{} ok.",uuid,param);
        return new ResponseObj(QNResult.SUCCESS.getCode(),QNResult.SUCCESS.getMessage(),uuid,new RequestObj(param,"/setKey"));
    }

    @RequestMapping(value = "/getKey",method = RequestMethod.GET)
    public ResponseObj getKey(@RequestParam(name = "param" ,required = false )String param){
        logger.debug("CacheController.getKey() param:{} start.",param);
        if(StringUtils.isEmpty(param)){
            logger.warn("[/getKey] param:{} 获取param参数异常:参数为空.",param);
            return new ResponseObj(QNResult.REQUEST_PARAM_EMPTY.getCode(),QNResult.REQUEST_PARAM_EMPTY.getMessage(),new RequestObj(param,"/getKey"));
        }
        String result = "";
        try {
            result = stringRedisTemplate.opsForValue().get(param);
        }catch (Exception e){
            logger.warn("执行redis异常:{}",e.getMessage());
            e.printStackTrace();
            return new ResponseObj(QNResult.ERROR_INSIDE_EXCEPTION.getCode(),QNResult.ERROR_INSIDE_EXCEPTION.getMessage(),new RequestObj(param,"/getKey"));
        }
        if(StringUtils.isEmpty(result)){
            logger.warn("[/getKey] param:{} 数据不存在.",param);
            return new ResponseObj(QNResult.REQUEST_DATA_NOT_EXIST.getCode(),QNResult.REQUEST_DATA_NOT_EXIST.getMessage(),new RequestObj(param,"/getKey"));

        }
        logger.debug("CacheController.getKey() param:{} param:{} start.",param,result);
        return new ResponseObj(QNResult.SUCCESS.getCode(),QNResult.SUCCESS.getMessage(),result,new RequestObj(param,"/getKey"));
    }
}
