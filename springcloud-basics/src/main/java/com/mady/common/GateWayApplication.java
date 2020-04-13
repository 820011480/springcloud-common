package com.mady.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 14:38
 * @description springboot 启动器
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.mady.common"})
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
        log.info("GateWayApplication service start success!");
    }
}
