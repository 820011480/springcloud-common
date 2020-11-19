package com.mady.common.producer;


import com.mady.common.domain.Message;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/3/30 17:29
 * @description 基础生产者
 */
public interface BaseProducer {

    /**
     * 同步生产消息
     * @param message
     */
    default void send(Message message){};
    /**
     * 异步生产消息
     * @param message
     */
    default void asyncSend(Message message){};
}
