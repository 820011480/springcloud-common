package com.mady.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 14:38
 * @description 自定义路由网关 测试使用
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.mady.common"})
//服务注册发现
@EnableDiscoveryClient
//@EnableFeignClients
public class CustomizeGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomizeGateWayApplication.class, args);
        log.info("GateWayApplication service start success!");
    }
}
