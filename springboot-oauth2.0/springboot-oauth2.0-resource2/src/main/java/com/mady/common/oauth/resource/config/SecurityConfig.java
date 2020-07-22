package com.mady.common.oauth.resource.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启对 Spring Security 注解的方法，进行权限验证。
@Order(101) // OAuth2SsoDefaultConfiguration 使用了 Order(100)，所以这里设置为 Order(101)，防止相同顺序导致报错
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/**").authorizeRequests()
//                .anyRequest().authenticated();
//    }
}