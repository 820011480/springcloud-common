package com.mady.common.oauth.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/16 9:43
 * @description OAuth2.0启动类
 */
@Slf4j
//开启资源服务器
@EnableResourceServer
@SpringBootApplication(scanBasePackages = {"com.mady.common"})
public class SpringResourceApplication {

    /**
     * 指定初始化类
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringResourceApplication.class, args);
    }
}
