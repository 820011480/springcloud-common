package com.mady.common.aspect;

import com.mady.common.config.SecretProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/10 16:44
 * @description 请求体
 */
@Slf4j
@ControllerAdvice
public class SecretRequestAdvice extends RequestBodyAdviceAdapter {
    @Autowired
    private SecretProperties secretProperties;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return false;
    }

    /**
     * 处理请求之前
     * 1. 解析当前请求 post 需要解密  get不需要
     * 2. 返回结果需要加密
     * @param inputMessage
     * @param parameter
     * @param targetType
     * @param converterType
     * @return
     * @throws IOException
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        boolean supportSafeMessage = supportSecretRequest(parameter);




        return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
    }


    private boolean supportSecretRequest(MethodParameter methodParameter) {
        //不支持直接放行
        if (!secretProperties.getSupportType()) {
            return true;
        }
        return true;
    }



}
