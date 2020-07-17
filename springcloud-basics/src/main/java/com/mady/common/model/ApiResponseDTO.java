package com.mady.common.model;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/14 17:18
 * @description
 */
public class ApiResponseDTO {
    /**
     * 链路追踪ID 也是唯一标识
     */
    private String requestId;
    /**
     * 参数
     */
    private String params;
    /**
     * 返回码
     */
    private String resultCode;
    /**
     * 返回信息
     */
    private String resultMsg;
    /**
     * 是否重试
     */
    private Boolean flag;
    /**
     * 签名
     */
    private String sign;
}
