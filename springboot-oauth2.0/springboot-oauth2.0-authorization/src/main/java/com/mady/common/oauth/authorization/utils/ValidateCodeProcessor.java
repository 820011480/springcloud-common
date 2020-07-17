package com.mady.common.oauth.authorization.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 14:09
 * @description 验证码处理器
 */
public interface ValidateCodeProcessor {

    /**
     * 生成验证码
     */
    String create(HttpServletRequest request);

    /**
     * 校验验证码
     * @param request
     * @return
     */
    boolean validate(HttpServletRequest request);
}
