package com.mady.common.model;

import lombok.Data;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/14 16:25
 * @description 受理Token模型不需要登录
 */

@Data
public class ApiToken {

    /**
     * 表示请求属于哪个应用ID[决定应用]
     */
    private String appId;
    /**
     * 用户签名key[key + Secret 决定权限]
     */
    private String appKey;
    /**
     *用户签名密钥
     */
    private String  appSecret;
}
