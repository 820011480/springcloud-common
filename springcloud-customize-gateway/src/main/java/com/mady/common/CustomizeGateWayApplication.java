package com.mady.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 14:38
 * @description 自定义路由网关 测试使用
 */
@Slf4j
//服务注册发现
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.mady.common"})
public class CustomizeGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomizeGateWayApplication.class, args);
        log.info("GateWayApplication service start success!");
    }


    @RequestMapping("/hello")
    public String hello() {
        return "helle consul";
    }
}
