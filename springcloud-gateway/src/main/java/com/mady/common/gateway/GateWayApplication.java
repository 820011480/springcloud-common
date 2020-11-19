package com.mady.common.gateway;

import com.mady.common.gateway.config.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//@EnableDiscoveryClient

@SpringBootApplication
public class GateWayApplication implements ApplicationRunner {
    /**
     *  gateway服务启动方法
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
        log.info("gateway service started !!!");
    }

    @Autowired
    private UserDAO userDAO;

    /**
     * 服务启动运行该方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(userDAO.getAllUserNames());
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
