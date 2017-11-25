package com.channelsoft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by yuanshun on 2017/11/19.
 */
@RestController
public class CacheController {

    private Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/setKey")
    public String setKey(@RequestParam(name = "param",required = false)String param){
        logger.debug("CacheController.setKey() param:{} start.",param);
        String uuid = new String();
        uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(uuid, param);
        logger.debug("CacheController.setKey() key:{} param:{} ok.",uuid,param);
        return stringRedisTemplate.opsForValue().get(uuid);
    }

    @RequestMapping("/getKey")
    public String getKey(@RequestParam(name = "key" ,required = false )String key){
        logger.debug("CacheController.getKey() key:{} start.",key);
        String reuslt = stringRedisTemplate.opsForValue().get(key);
        logger.debug("CacheController.getKey() key:{} param:{} start.",key,reuslt);
        return reuslt;
    }
}
