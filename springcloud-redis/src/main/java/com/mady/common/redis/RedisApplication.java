package com.mady.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/6/3 13:26
 * @description
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.mady.common")
public class RedisApplication implements InitializingBean {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
        log.info("RedisApplication service started");
    }

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化库存
        redisTemplate.opsForValue().set("count", 50);
        log.info("初始化库存成功");
    }
}
