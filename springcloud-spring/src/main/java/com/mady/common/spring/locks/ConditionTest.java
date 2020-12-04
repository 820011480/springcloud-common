package com.mady.common.spring.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/1 11:43
 * @description
 */
public class ConditionTest {
    private static Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        boolean flag = false;
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();

                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + i + "等待中...");
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "等待结束...");
                    if(flag){
                        break;
                    }
                }
//                for (; ; ) {
//                    System.out.println(Thread.currentThread().getName() + "等待中...");
//                    condition.await();
//                    System.out.println(Thread.currentThread().getName() + "等待结束...");
//                    if(flag){
//                        break;
//                    }
//                }
                System.out.println(Thread.currentThread().getName() + "跳出for循环...");
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放锁...");
                lock.unlock();
            }

        }, "thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                Thread.sleep(1000);
                condition.signalAll();
                System.out.println(Thread.currentThread().getName() + "唤醒所有线程...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放锁...");
                lock.unlock();
            }
        }, "thread2");
        thread2.start();
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + "主线程任务执行中1");
    }
}
