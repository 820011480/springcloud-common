package com.mady.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/10/21 20:03
 * @description 抽象消息实体类
 */
@Data
public abstract class Message implements Serializable {
    private static final long serialVersionUID = 3534481906629325751L;
    /**
     * 主题
     */
    private String topic;
    /**
     * 消息头
     */
    private String key;
    /**
     * 消息体
     */
    private String body;
}
