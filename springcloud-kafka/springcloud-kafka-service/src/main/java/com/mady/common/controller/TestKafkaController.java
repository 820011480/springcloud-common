package com.mady.common.controller;

import com.mady.common.domain.Message;
import com.mady.common.domain.OrderMessage;
import com.mady.common.producer.BaseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/5/22 11:06
 * @description kafka测试restTemplate
 */
@RequestMapping("/kafka")
@RestController
public class TestKafkaController {

    @Autowired
    @Qualifier("orderProducer")
    private BaseProducer orderProducer;



    @RequestMapping("/sendMessage")
    public String getKafkaInfo(){
        Message message = new OrderMessage();
        message.setTopic("mady");
        message.setKey(UUID.randomUUID().toString().replaceAll("-", ""));
        message.setBody("send message to test kafka");
        orderProducer.asyncSend(message);
        return "test rest ok";
    }
}
