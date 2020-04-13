package com.mady.common.common;

import lombok.Getter;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 9:52
 * @description 总响应码枚举
 */
@Getter
public enum ResultCodeEnum {
    /**
     *成功
     */
    SUCCESS("S"),
    /**
     * 失败
     */
    FAILURE("F");

    /**
     * code
     */
    private String code;


    ResultCodeEnum(String code){
        this.code = code;
    }
}
