package com.mady.common.model;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/14 17:12
 * @description Token返回结果
 */
public class TokenModel {
    /**
     * token
     */
    private String token;
    /**
     * 刷新token
     */
    private String refreshToken;
    /**
     * 过期时间
     */
    private Integer expireTime;
}
