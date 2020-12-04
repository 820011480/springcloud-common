package com.mady.common.service.dubbo.producer;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/4 16:03
 * @description 订单接口
 */
public interface OrderService {
    /**
     * 保存订单信息
     * @param orderId
     * @return
     */
    int save(String orderId);
}
