package com.mady.common.limit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/20 14:27
 * @description 漏桶算法
 *
 * 漏桶算法(Leaky Bucket)是网络世界中流量整形（Traffic Shaping）或速率限制（Rate Limiting）
 * 时经常使用的一种算法，它的主要目的是控制数据注入到网络的速率，平滑网络上的突发流量。
 * 漏桶算法提供了一种机制，通过它，突发流量可以被整形以便为网络提供一个稳定的流量。
 */
public class LimitBucketAlgorithm {
    /**
     * 资源池
     */
    private static final float CAPACITY = 100;
    /**
     * 每秒的出口速率 [每秒处理10个请求]
     */
    public static final float COUNT = 5; //控制出水量
    /**
     * 当前容量[初始0容量]
     */
    private static float CONCURRENT_CAPACITY = 0;
    /**
     * 上次执行时间
     */
    public static long PRE_TIME = System.currentTimeMillis();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for(int i = 0; i < 1000; i++){
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    if(tryAcquirer()){
                        System.out.println("true:" + finalI + "线程" +Thread.currentThread().getName());
                        System.out.println("当前水桶容量:" + CONCURRENT_CAPACITY);
                    }else{
                        System.out.println("false :" + Thread.currentThread().getName());
                        System.out.println("当前水桶容量:" + CONCURRENT_CAPACITY);
                    }
                    try {
                        Thread.sleep(100); // 控制进水量
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    static synchronized boolean tryAcquirer(){

        //获取当前时间
        long nowTime = System.currentTimeMillis();
        //出水量
        float outCount = (nowTime - PRE_TIME) / 1000f * COUNT;
        CONCURRENT_CAPACITY -= outCount;
        System.out.println("当前水桶出水容量："+ outCount);
        CONCURRENT_CAPACITY = Math.max(0, CONCURRENT_CAPACITY);
        if(CONCURRENT_CAPACITY == 0){
            PRE_TIME =  nowTime;
            CONCURRENT_CAPACITY++;
            return true;
        }
        if(CONCURRENT_CAPACITY >= CAPACITY){
            CONCURRENT_CAPACITY = CAPACITY;
            PRE_TIME = nowTime;
            return false;
        }
        PRE_TIME =  nowTime;
        CONCURRENT_CAPACITY++;
        return true;
    }
}
