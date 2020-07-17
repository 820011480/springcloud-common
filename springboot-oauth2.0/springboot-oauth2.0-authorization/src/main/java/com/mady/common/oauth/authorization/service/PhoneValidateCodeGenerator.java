package com.mady.common.oauth.authorization.service;

import com.mady.common.oauth.authorization.utils.RandomCode;
import com.mady.common.oauth.authorization.utils.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 14:49
 * @description 短信验证码生成器
 */
@Slf4j
@Service(value = "phoneValidateCodeGenerator;0")
public class PhoneValidateCodeGenerator implements ValidateCodeGenerator {

    @Override
    public String generatorCode(HttpServletRequest request) {
        return RandomCode.random(4, true);
    }
}
