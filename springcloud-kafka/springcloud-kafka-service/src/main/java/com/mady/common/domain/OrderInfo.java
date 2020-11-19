package com.mady.common.domain;

import lombok.Data;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/10/23 9:14
 * @description 订单信息
 */
@Data
public class OrderInfo {
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 订单支付时间
     */
    private String payTime;
    /**
     * 用户信息
     */
    private String userId;
    /**
     * 产品信息
     */
    private String productId;
}
