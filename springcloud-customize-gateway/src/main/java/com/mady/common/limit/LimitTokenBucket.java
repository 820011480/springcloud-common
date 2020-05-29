package com.mady.common.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/23 14:03
 * @description 令牌桶
 */
public class LimitTokenBucket {
    private static RateLimiter rateLimiter = RateLimiter.create(0.5);//每秒发放0.5个令牌。注意:本初始化声明不要放在方法内

    public static void main(String[] args) throws InterruptedException {
        while (true){
            if(rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)){
                System.out.println("获取到令牌");
            }else{
                System.out.println("未获取到令牌");
            }
            Thread.sleep(500);
        }
    }
}
