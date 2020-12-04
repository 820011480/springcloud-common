package com.mady.common.spring.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/3 15:42
 * @description 线程池测试
 */
public class ThreadPoolTest {
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) {
        System.out.println("核心线程数为:" + THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT * 2; i++) {
            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName() + "开始执行任务");
            });
        }
    }
}
