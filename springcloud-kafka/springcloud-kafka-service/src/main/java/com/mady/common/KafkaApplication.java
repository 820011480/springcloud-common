package com.mady.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/5/20 16:58
 * @description kafka 应用服务 主要用来做消息服务
 */
@Slf4j
//服务注册发现
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.mady.common"})
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
        log.info("kafkaApplication start success...");
    }

}
