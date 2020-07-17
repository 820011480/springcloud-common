package com.mady.common.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/15 11:21
 * @description 资源认证类
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.csrf().disable();
        http.requestMatchers().antMatchers("/api/**").and().authorizeRequests()
                .anyRequest().authenticated();
    }
}
