package com.mady.common.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/5/25 16:06
 * @description SpringCloud gateway 网关
 *
 * 为什么使用网关？
 * 1. 统一限流， 降级， 服务上下线
 * 2. 统一路由
 * 3. 协议转化
 * 4. 日志记录
 * 5. 鉴权
 */
@Slf4j
//开启注册中心客户端
@EnableDiscoveryClient
@SpringBootApplication
public class GateWayApplication {
    /**
     *  gateway服务启动方法
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
        log.info("gateway service started !!!");
    }


    /**
     * 基于代码路由
     * @param builder
     * @return
     */
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("path_route", r -> r.path("/csdn").uri("https://blog.csdn.net"))
//                .route("path_route", r -> r.path("/kafka").uri("http://127.0.0.1:18081/kafka/test"))
//                .build();
//    }
}
