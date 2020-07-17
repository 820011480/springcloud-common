package com.mady.common.oauth.authorization.utils;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 14:12
 * @description 采用模板模式 + 策略模式进行不同方式的验证码发送
 */
@Slf4j
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    public static final String VALIDATE_CODE = "validate_code";


    @Autowired
    private Map<String, ValidateCodeGenerator> map;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String create(HttpServletRequest request) {
        //1. createCode();
        String code = createCode(request);
        //2.发送验证码
        sendCode(code, request);
        //3. 保存验证码
        saveCode(code, request);
        return code;
    }

    /**
     * 保存验证码
     *
     * @param code
     * @param request
     */
    private void saveCode(String code, HttpServletRequest request) {
        Boolean insertCode = redisTemplate.opsForValue().setIfAbsent(String.format("%s#" + VALIDATE_CODE, "oauth2"), code);
        if (insertCode == null || !insertCode) {
            log.error("validate code save failed, code:{}", code);
            throw new RuntimeException("validate code save failed");
        }
    }

    /**
     * 发送验证码
     *
     * @param code
     * @param request
     */
    public abstract void sendCode(String code, HttpServletRequest request);

    /**
     * 创建验证码
     *
     * @param request
     * @return
     */
    private String createCode(HttpServletRequest request) {
        if (ObjectUtils.isEmpty(map)) {
            String type = request.getParameter("type");
            ValidateCodeGenerator validateCodeGenerator = map.get(type);
            return validateCodeGenerator == null ? defaultCreateCode(request):
            validateCodeGenerator.generatorCode(request);
        }
        //生成有6位数字的验证码(兜底)
       return defaultCreateCode(request);
    }

    protected String defaultCreateCode(HttpServletRequest request){
        return RandomCode.random(6, true);
    }

    /**
     * 校验验证码
     *
     * @param request
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request) {
        String cacheCode = redisTemplate.opsForValue().get(String.format("%s#" + VALIDATE_CODE, "oauth2"));
        String code = request.getParameter("code");
        if (!StringUtils.hasText(code)) {
            throw new RuntimeException("验证码参数不能为空");
        }
        boolean result = ObjectUtils.nullSafeEquals(code, cacheCode);
        if(result){
            redisTemplate.delete(String.format("%s#" + VALIDATE_CODE, "oauth2"));
        }else{
            throw new RuntimeException("校验失败");
        }
        return true;
    }
}
