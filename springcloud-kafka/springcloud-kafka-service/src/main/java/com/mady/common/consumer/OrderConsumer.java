package com.mady.common.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/10/21 20:20
 * @description 订单消费对象
 */
@Slf4j
@Component
public class OrderConsumer {
    /**
     * 消息监听
     */
    @KafkaListener(topics = "mady", groupId = "mady")
    public void handlerMessage(ConsumerRecord record, Acknowledgment acknowledgment){
        try{
            String value = (String)record.value();
            log.info("handler message：{}", value);
        }catch(Exception e){
            log.error("handler message failed, value:{}, error:{}", record.value(), e);
        }finally {
            acknowledgment.acknowledge();
        }
    }
}
