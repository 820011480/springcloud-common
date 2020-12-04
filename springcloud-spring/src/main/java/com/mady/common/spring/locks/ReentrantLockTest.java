package com.mady.common.spring.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/11/20 10:59
 * @description
 */
public class ReentrantLockTest {
    /**
     * 默认非公平锁
     */
    static ReentrantLock reentrantLock = new ReentrantLock();

    static Map<String, Thread> localMap = new HashMap<>();
    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        reentrantLockTest.test();
        System.out.println(reentrantLockTest);
    }

    private void test() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //doSomething
                try {
                    doSomething2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.setName("thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //doSomething
                try {
                    doSomething2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }


    /**
     * doSomething
     */
    private void doSomething2() throws InterruptedException {
        try{
            String name = Thread.currentThread().getName();
            localMap.put(name, Thread.currentThread());
            if("thread2".equals(name)){
                //park
                System.out.println("this concurrent thread begin park");
                System.out.println(this);
                LockSupport.park(this);
                System.out.println("this concurrent thread begin parked");
                Thread.interrupted();
            }
        }finally {
            System.out.println("this concurrent thread finally result" + Thread.currentThread().getName());
            Thread.sleep(100);
//            LockSupport.unpark(localMap.get("thread2"));
        }
    }






    /**
     * doSomething
     */
    private static void doSomething() {
        reentrantLock.lock();
        System.out.println("this concurrent thread do something:" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            System.out.println("this concurrent thread sleep end:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
}
