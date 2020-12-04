package com.mady.common.spring.locks;

import java.util.concurrent.CountDownLatch;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/3 10:03
 * @description CountDownLatch 计数器
 */
public class CountDownLatchTest {
    private static final int THREAD_COUNT = 5;
    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);


    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            Thread thread = Thread.currentThread();
            try {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + "当前线程也加入等待组...");
                countDownLatch.await();
                System.out.println(thread.getName() + "等待结束...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "new Thread").start();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(()->{
                doSomething();
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "释放完成...");
            },"thread" + i);
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程准备结束...");
        countDownLatch.await();
        System.out.println("主线程等待结束...");
    }

    private static void doSomething() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName() + "当前线程开始工作...");
    }
}
