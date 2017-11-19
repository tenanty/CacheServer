package com.channelsoft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by yuanshun on 2017/11/19.
 */
@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/setKey")
    public String setKey(@RequestParam(name = "message",required = false)String message){
        logger.debug("进入 HelloController.setKey() message:{} .",message);
        String uuid = new String();
        uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(uuid, "Springboot redis:"+uuid);
        return stringRedisTemplate.opsForValue().get(uuid);
    }

    @RequestMapping("/getKey")
    public String getKey(@RequestParam(name = "key" ,required = false )String key){
        logger.debug("进入 HelloController.getKey() key:{}.",key);
        String reuslt = stringRedisTemplate.opsForValue().get(key);
        return reuslt;
    }
}
