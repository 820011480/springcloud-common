package com.mady.common.common;

import lombok.Data;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 9:38
 * @description 网关入参对象
 */
@Data
public class ApiRequestDTO {
    /**
     * 应用Id 调用方身份标识
     */
    private String appId;
    /**
     *调用方法
     */
    private String method;
    /**
     * 字符集
     */
    private String charset;
    /**
     * 签名类型
     */
    private String signType;
    /**
     * 签名
     */
    private String sign;
    /**
     * 请求时间戳
     */
    private Long timestamp;
    /**
     * 随机数  签名唯一随机数。用于防止网络重放攻击，建议您每一次请求都使用不同的随机数。
     */
    private String nonce;
    /**
     * 版本号
     */
    private String version;
    /**
     * 请求业务参数
     */
    private String bizContent;
}
