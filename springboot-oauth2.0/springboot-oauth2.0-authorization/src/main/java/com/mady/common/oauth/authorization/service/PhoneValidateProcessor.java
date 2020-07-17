package com.mady.common.oauth.authorization.service;

import com.mady.common.oauth.authorization.utils.AbstractValidateCodeProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 14:21
 * @description 手机发送
 */
@Slf4j
@Service
public class PhoneValidateProcessor extends AbstractValidateCodeProcessor {
    private int a = 0;
    @NotNull
    private String b;

    @Override
    public void sendCode(String code, HttpServletRequest request) {
        log.info("短信发送的验证码为：{}", code);
    }
}
