package com.mady.common.model;

import lombok.Data;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/14 17:13
 * @description
 */
@Data
public class ApiRequestDTO {
    /**
     * 访问token
     */
    private String token;
    /**
     * 时间戳
     */
    private Integer timeStamp;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 签名
     */
    private String sign;
    /**
     * 参数
     */
    private String params;
}
