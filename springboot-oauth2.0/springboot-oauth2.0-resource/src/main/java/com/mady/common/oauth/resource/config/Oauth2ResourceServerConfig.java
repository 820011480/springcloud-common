package com.mady.common.oauth.resource.config;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 12:45
 * @description
 */
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 设置资源服务器的 id
        resources.resourceId("oauth2");
    }
}
