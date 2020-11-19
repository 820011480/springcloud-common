//package com.mady.common.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.listener.ContainerProperties;
//
///**
// * <p>
// * kafka配置类
// * </p>
// *
// * @package: com.xkcoding.mq.kafka.config
// * @description: kafka配置类
// * @author: yangkai.shen
// * @date: Created in 2019-01-07 14:49
// * @copyright: Copyright (c) 2019
// * @version: V1.0
// * @modified: yangkai.shen
// */
//@Configuration
//@EnableConfigurationProperties({KafkaProperties.class})
//@EnableKafka
//@AllArgsConstructor
//public class KafkaConsumerConfig {
//    private final KafkaProperties kafkaProperties;
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
//    }
//
//    @Bean("kafkaListenerContainerFactory")
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setConcurrency(100);
//        factory.setBatchListener(true);
//        factory.getContainerProperties().setPollTimeout(3000);
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
//    }
//
//    @Bean("ackContainerFactory")
//    public ConcurrentKafkaListenerContainerFactory<String, String> ackContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
//        factory.setConcurrency(2);
//        return factory;
//    }
//
//}
