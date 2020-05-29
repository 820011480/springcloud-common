package com.mady.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/10 16:49
 * @description
 */
@Configuration
@ConfigurationProperties("enable.ssl")
@Data
public class SecretProperties {
    /**
     * 是否支持加密
     */
    private Boolean supportType;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 公钥
     */
    private String publicKey;
}
