package com.mady.common.oauth.authorization.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 14:47
 * @description 验证码生成器
 */
public interface ValidateCodeGenerator {

    String generatorCode(HttpServletRequest request);
}
