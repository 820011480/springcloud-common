package com.mady.common.service.dubbo.consumer;

import com.mady.common.service.dubbo.producer.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/4 16:10
 * @description
 */
@Slf4j
@Service
public class OrderConsumerServiceImpl {

    @Reference(
            version = "1.0.0",
            retries = 0,
            timeout = 3
    )
    private OrderService orderService;

    public int saveOrderInfo(String orderId){
        log.info("OrderConsumerServiceImpl#saveOrderInfo save order info:{}", orderId);
        return orderService.save(orderId);
    }
}
