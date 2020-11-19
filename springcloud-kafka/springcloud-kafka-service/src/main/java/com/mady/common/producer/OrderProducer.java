package com.mady.common.producer;

import com.mady.common.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/3/30 17:28
 * @description 订单生产者服务
 */
@Slf4j
@Component("orderProducer")
public class OrderProducer implements BaseProducer{

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void asyncSend(Message message) {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(message.getTopic(), message.getBody());
        send.addCallback(
                //成功回调
                success->{log.info("send message success,messageKey:{}, messageBody：{}", message.getKey(), message.getBody());},
                //失败回调
                ex->{ log.error("send message failed:{}", ex);});
    }
}
