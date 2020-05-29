package com.mady.common.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/5/22 10:47
 * @description RestTemplateConfig 整合ribbon
 */

@Configuration
public class RestTemplateConfig {


    @Bean
//  使用负载均衡注解
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
