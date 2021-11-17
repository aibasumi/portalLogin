package com.tssco.portalLogin.Utils;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private Integer expireTime = 1800;

    private static Logger logger = LogManager.getLogger(RedisUtils.class.getName());

    public void set (String key, Object data){
        try {
            redisTemplate.opsForValue().set(key,data);
            Object log = redisTemplate.opsForValue().get(key);
            logger.info("Set redis success : " + log);
        } catch (Exception e) {
            logger.error("Set redis fail : " + e);
        }

    }


    public Object get (String key){
        try {
            Object result = redisTemplate.opsForValue().get(key);
            System.err.println(result);
            return result;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
    
}
