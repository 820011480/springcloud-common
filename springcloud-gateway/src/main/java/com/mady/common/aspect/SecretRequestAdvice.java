package com.mady.common.aspect;

import com.google.gson.Gson;
import com.mady.common.annotation.GateWayRequest;
import com.mady.common.common.ApiRequestDTO;
import com.mady.common.common.BaseResultEnum;
import com.mady.common.config.SecretProperties;
import com.mady.common.exception.BaseRuntimeException;
import com.mady.common.sign.Signer;
import com.mady.common.sign.SignerContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/10 16:44
 * @description 请求体
 */
@Slf4j
@RestControllerAdvice
public class SecretRequestAdvice implements RequestBodyAdvice {
    @Autowired
    private SecretProperties secretProperties;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 处理请求之前
     * 验签, 参数校验
     *
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
        //判断是否支持加签
        if(secretProperties.getSupportType()){
            return inputMessage;
        }
        GateWayRequest annotation = parameter.getMethod().getAnnotation(GateWayRequest.class);
        if(ObjectUtils.isEmpty(annotation)){
            return inputMessage;
        }
        //验签处理
        return doVerify(inputMessage,  parameter);
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return null;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return null;
    }

    /**
     * 验签
     * @param inputMessage
     * @param parameter
     */
    private HttpInputMessage doVerify(HttpInputMessage inputMessage, MethodParameter parameter) {
        //获取请求体
        try{
            InputStream body = inputMessage.getBody();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = body.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();
            String messageBody = (new String(bytes, "UTF-8"));
            Gson gson = new Gson();
            //获取到请求参数
            ApiRequestDTO apiRequestDTO = gson.fromJson(messageBody, ApiRequestDTO.class);
            String sign = apiRequestDTO.getSign();
            Signer signer = SignerContainer.getSigner("RSA2");
            Boolean aBoolean = signer.doVerify(messageBody, sign, secretProperties.getPublicKey());
            if(!aBoolean){
                log.error("SecretRequestAdvice#doVerify sign verify failed, apiRequestDTO = {}", apiRequestDTO);
                throw new BaseRuntimeException(BaseResultEnum.SIGN_VERIFY_FAILED);
            }
            return inputMessage;
        }catch(IOException e){
            log.error("SecretRequestAdvice#doVerify data transform error, e ={}", e);
            throw new BaseRuntimeException(BaseResultEnum.SYSTEM_ERROR);
        }catch(Exception e1){
            log.error("SecretRequestAdvice#doVerify system error, e ={}", e1);
            throw new BaseRuntimeException(BaseResultEnum.SYSTEM_ERROR);
        }
    }
}
