package com.mady.common.inteceptor;

import com.google.gson.Gson;
import com.mady.common.annotation.RequiredParam;
import com.mady.common.common.BaseResultEnum;
import com.mady.common.common.RequireValidParam;
import com.mady.common.config.DefaultGroup;
import com.mady.common.config.ParamCheckProperties;
import com.mady.common.config.SecretProperties;
import com.mady.common.exception.BaseRuntimeException;
import com.mady.common.sign.Signer;
import com.mady.common.sign.SignerContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/17 9:39
 * @description 参数校验拦截器
 */
@Slf4j
@Component
public class ParamInterceptor implements HandlerInterceptor{

    @Autowired
    private ParamCheckProperties paramCheckProperties;

    @Autowired
    private SecretProperties secretProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null){
            responseStrBuilder.append(inputStr);
        }
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(String.valueOf(responseStrBuilder), Map.class);
        /**
         * 参数校验
         */
        if(paramCheckProperties.isCommonParams()){
            checkParam(map);
        }
        /**
         * 签名校验
         */
        if(paramCheckProperties.isCheckSign()){
            checkSign(map);
        }
        return true;
    }

    private void checkSign(Map<String, String> map) {
        //获取签名方式：
        String signType = map.get("signType");
        String sign = map.get("sign");
        Signer signer = SignerContainer.getSigner(signType);
        //根据商户获取签名密钥 目前只有一个 todo  
        Boolean aBoolean = signer.doVerify(map, sign, secretProperties.getPrivateKey());
        if(!aBoolean){
            log.error("ParamInterceptor#checkSign sign check failed, map = {}", map);
            throw new BaseRuntimeException(BaseResultEnum.SIGNATURE_FAIL);
        }
    }

    private void checkParam(Map<String, String> map) throws IllegalAccessException, InstantiationException {
        Class<RequireValidParam> requireValidParamClass = RequireValidParam.class;
        Field[] fields = requireValidParamClass.getDeclaredFields();
        if(ObjectUtils.isEmpty(fields)){
            return;
        }
        for (Field field : fields) {
            RequiredParam annotation = field.getAnnotation(RequiredParam.class);
            if(ObjectUtils.isEmpty(annotation) || annotation.value() == false ){
                continue;
            }
            String parameterName = field.getName();
            if(!map.containsKey(parameterName)){
                log.error(String.format("%s参数不能为空", parameterName));
                throw new BaseRuntimeException("50000", String.format("%s参数不能为空", parameterName));
            }
            //额外校验
            Class<DefaultGroup> [] groups = annotation.group();
            if(!ObjectUtils.isEmpty(annotation.group())){
                for (Class<DefaultGroup> group : groups) {
                    DefaultGroup defaultGroup  = group.newInstance();
                    defaultGroup.validMode(map.get(parameterName));
                }
            }
        }
    }
    /**
     * 视图渲染前执行 可对视图处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 视图渲染之后执行， 可对返回结果处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
