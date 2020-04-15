package com.mady.common.common;

import lombok.Getter;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 9:52
 * @description 业务码返回枚举
 */
@Getter
public enum BaseResultEnum {
    /**
     *成功
     */
    SUCCESS("200", "成功"),
    /**
     * 错误的方法格式
     */
    ERROR_METHOD_FORMAT("10001", "错误的方法格式"),
    /**
     * 接口不存在
     */
    METHOD_NOT_EXITS("10002", "接口不存在"),
    /**
     * 签名校验失败
     */
    SIGN_VERIFY_FAILED("10003", "接口不存在"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR("500", "系统异常");

    /**
     * code
     */
    private String code;
    /**
     * msg
     */
    private String msg;

    BaseResultEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
