//package com.mady.common.oauth.resource.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
///**
// * @author mady
// * @version 1.0.0
// * @date 2020/7/17 12:45
// * @description
// */
//@Configuration
//public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        // 设置资源服务器的 id
//        resources.resourceId("oauth2");
//    }
//
//
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                // 设置 /login 无需权限访问
//                .antMatchers("/login").permitAll()
//                // 设置其它请求，需要认证后访问
//                .anyRequest().authenticated()
//        ;
//    }
//}
