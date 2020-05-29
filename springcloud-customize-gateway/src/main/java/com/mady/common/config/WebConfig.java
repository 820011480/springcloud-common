package com.mady.common.config;

import com.mady.common.inteceptor.ParamInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/17 10:01
 * @description
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private ParamInterceptor paramInterceptor;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(paramInterceptor).addPathPatterns("/**").excludePathPatterns("/");
        super.addInterceptors(registry);
    }
}
