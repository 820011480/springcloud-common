package com.mady.common.oauth.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/16 15:11
 * @description
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private ValidateFilter validateCodeFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("123456"))
                .authorities("ROLE_USER").build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .authorities("ROLE_ADMIN").build());
        manager.createUser(User.withUsername("13193302829")
                .password(passwordEncoder().encode("123456"))
                .authorities("ROLE_ADMIN").build());
        return manager;
    }


    /**
     * 认证管理
     * @return 认证管理对象
     * @throws Exception 认证异常信息
     */

    @Override
    @Bean  // 重点是这行，父类并没有将它注册为一个 Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //重写父类请求，进行表单认证
        ((HttpSecurity)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests()
//                .antMatchers("/favicon.ico").access("permitAll()")
                .anyRequest()).authenticated().and()).formLogin().and()).httpBasic();

//        http.authorizeRequests()
//                // 添加路径
//                .antMatchers("/favicon.ico").access("permitAll()")
////                .antMatchers("/oauth2/phone").access("permitAll()")
////                .antMatchers("/oauth2/code").access("permitAll()")
////                .antMatchers("/code/*").permitAll()
//                .anyRequest()
//                .authenticated()
//                // 务必关闭 csrf，否则除了 get 请求，都会报 403 错误
//                .and()
//                .csrf().disable();
//        http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
