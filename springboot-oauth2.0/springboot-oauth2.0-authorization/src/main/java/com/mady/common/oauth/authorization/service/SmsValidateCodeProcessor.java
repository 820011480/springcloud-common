package com.mady.common.oauth.authorization.service;

import com.mady.common.oauth.authorization.utils.AbstractValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 14:18
 * @description 短信发送
 */
@Slf4j
@Service
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor {


    @Override
    public void sendCode(String code, HttpServletRequest request) {
        log.info("邮件发送的验证码为：{}", code);
    }
}
