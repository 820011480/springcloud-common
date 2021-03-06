package com.mady.common.controller;

import com.mady.common.service.dubbo.consumer.OrderConsumerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/4 15:24
 * @description 链路id测试
 */
@Slf4j
@RestController
@RequestMapping("/trace")
public class TraceController {

    @GetMapping("/test")
    public String testTraceId(){
        log.info("测试日志...");
        return "test trace id";
    }

    @Autowired
    private OrderConsumerServiceImpl orderConsumerService;

    @GetMapping("/order")
    public String saveOrderInfo(String orderId){
        log.info("TraceController#saveOrderInfo save order info:{}", orderId);
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        System.out.println("copyOfContextMap: " + copyOfContextMap);
        return orderConsumerService.saveOrderInfo(orderId) + "";
    }


    /**
     * 异步保存
     * @param orderId
     * @return
     */
    @GetMapping("/async")
    public String asyncSaveOrderInfo(String orderId){
        log.info("TraceController#asyncSaveOrderInfo save order info:{}", orderId);
        return orderConsumerService.saveOrderInfo(orderId) + "";
    }
}
