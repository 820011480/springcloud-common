//package com.mady.common.gateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author mady
// * @version 1.0.0
// * @date 2020/7/15 15:13
// * @description
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public AdminAuthenticationProvider adminAuthenticationProvider() {
//        return new AdminAuthenticationProvider();
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER。
////        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//        auth.authenticationProvider(this.adminAuthenticationProvider());
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
//
//
//    @Bean(name = "authenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //默认配置(所有认证请求都需要授权，要求用户在进入你的应用的任何URL之前都进行验证)
//        /*
//        http.authorizeRequests().anyRequest().authenticated().and()
//                .formLogin().and()
//                .httpBasic();
//         */
//        //配置进入以下URL之前不需要验证
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/admin/login.html", "/admin/login",
//                "/agreement.html", "/error.html").permitAll();
//        //通过 formLogin() 定义当需要用户登录时候，转到的登录页面
//        http.formLogin().loginPage("/login").permitAll().and().authorizeRequests().anyRequest().authenticated();
//    }
//}
