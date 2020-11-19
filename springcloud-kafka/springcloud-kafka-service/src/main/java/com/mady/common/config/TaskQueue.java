package com.mady.common.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/10/26 15:49
 * @description 任务队列
 */
@Component
public class TaskQueue {
    /**
     * 无界阻塞队列
     */
    private BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();








}
