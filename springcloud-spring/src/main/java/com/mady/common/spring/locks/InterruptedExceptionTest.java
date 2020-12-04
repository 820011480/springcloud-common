package com.mady.common.spring.locks;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/2 13:57
 * @description
 */
public class InterruptedExceptionTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            Thread thread = Thread.currentThread();
            try {
                boolean interrupted = thread.isInterrupted();
                System.out.println(thread.getName() + "当前线程的中断状态:" + interrupted);
                doSomething();
                boolean interrupted2 = thread.isInterrupted();
                System.out.println(thread.getName() + "当前线程的中断状态:" + interrupted2);
            } catch (InterruptedException e) {
                thread.interrupt();
                boolean interrupted3 = thread.isInterrupted();
                System.out.println(thread.getName() + "当前线程的中断状态:" + interrupted3);
                e.printStackTrace();
                boolean interrupted4 = thread.isInterrupted();
                System.out.println(thread.getName() + "当前线程的中断状态:" + interrupted4);
            }
        }, "thread1");
        thread1.start();
        //main休眠1s
        Thread.sleep(1000);
        thread1.interrupt();
        System.out.println("thread1线程的中断状态:" + thread1.isInterrupted());
    }

    private static void doSomething() throws InterruptedException {
        //休眠5s
        Thread.sleep(5000);
    }
}
