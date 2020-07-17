package com.mady.common.oauth.authorization.service;

import com.mady.common.oauth.authorization.utils.RandomCode;
import com.mady.common.oauth.authorization.utils.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 14:48
 * @description 邮件验证码生成器
 */
@Slf4j
@Service(value = "smsValidateCodeGenerator;1")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Override
    public String generatorCode(HttpServletRequest request) {
        return RandomCode.random(6, true);
    }
}
