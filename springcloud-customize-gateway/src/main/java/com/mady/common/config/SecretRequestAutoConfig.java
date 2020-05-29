package com.mady.common.config;

import com.mady.common.aspect.SecretRequestAdvice;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/10 16:56
 * @description 加解密自动配置
 */
@EnableAutoConfiguration
@EnableConfigurationProperties(SecretProperties.class)
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
