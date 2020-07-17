package com.mady.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/24 10:47
 * @description OAuth应用服务启动类
 */
@Slf4j
@EnableAuthorizationServer  //开启授权服务器
@SpringBootApplication
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
        log.info("OAuth应用服务启动成功");
    }
}
