package com.mady.common.config;

import com.mady.common.aspect.SecretRequestAdvice;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/10 16:56
 * @description 加解密自动配置
 */
@EnableConfigurationProperties(SecretProperties.class)
@EnableAutoConfiguration
@Configuration
public class SecretRequestAutoConfig {
    /**
     * 自动配置全局切面
     * @return
     */
    @Bean
    public SecretRequestAdvice secretRequestAdvice(){
        return new SecretRequestAdvice();
    }
}
