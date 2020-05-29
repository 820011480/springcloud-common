package com.mady.common.limit;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/20 10:02
 * @description 限流-计数器
 */
public class LimitCounter {
    /**
     * 信号量
     */
    private static Semaphore semaphore = new Semaphore(60);

    public static void main(String[] args) {
//        semaphoreHandler();
//        atoMicHandler();
    }

    /**
     * 信号量处理法
     */
    private static void semaphoreHandler() {
        Long beginTime = System.currentTimeMillis();
        int i;
        for (i = 0; i < 1000; i++) {
            Long currentTimeMillis = System.currentTimeMillis();
            long l = currentTimeMillis - beginTime;
            System.out.println("当前时第几次:"+ i +"当前时第几秒：" + l);
            //超出时间重新计数
            if (l >= 5) {
                System.out.println("当前已超过5s");
                semaphore.release(60);
                beginTime = currentTimeMillis;
            }
            if (semaphore.tryAcquire()) {
                //doSomething
                System.out.println("获取到执行信号，开始执行");
            } else {
                //获取失败
                System.out.println("获取执行信号失败, 我被限流了");
            }
        }
    }

    /**
     * 原子计数器
     */
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    /**
     * 限流次数
     */
    private static final int COUNT = 60;
    /**
     * 多长时间
     */
    private static final long SECONDS = 5l;
    /**
     * 原子类计数器
     */
    public static void atoMicHandler(){
        Long beginTime  = System.currentTimeMillis();
        int i;
        for (i = 0; i < 1000; i++) {
            long currentTimeMillis = System.currentTimeMillis();
            long differ = currentTimeMillis - beginTime;
            if(differ >= SECONDS){
                System.out.println("当前窗口已经超时, i = " + i + "当前时间为:" + differ);
                beginTime = currentTimeMillis;
                atomicInteger.set(0);
            }
            int i1 = atomicInteger.get();
            if(i1 < COUNT){
                //doSomething
                System.out.println("当前时间为" + differ +"获取到执行信号量，开始执行" + atomicInteger.incrementAndGet() + "当前请求数：" + i);
            }else {
                System.out.println("当前时间为" + differ + "我被限流了" + i);
            }

        }
    }
}
