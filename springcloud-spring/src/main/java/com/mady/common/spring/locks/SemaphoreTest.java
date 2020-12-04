package com.mady.common.spring.locks;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/3 14:52
 * @description 信号量测试
 */
public class SemaphoreTest {

    private static final int SEMAPHORE_COUNT = 5;

    private static Semaphore semaphore = new Semaphore(SEMAPHORE_COUNT);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        semaphore.acquire();
                        int time = new Random().nextInt(10);
                        System.out.println(Thread.currentThread().getName() + "当前线程开始执行时常大约为:{" + time + "s}");
                        Thread.sleep(time * 1000);
                        System.out.println(Thread.currentThread().getName() + "当前线程执行完毕退出");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            }, "thread" + i);
            thread.start();
        }
    }

//
//    static class Parking{
//        //信号量
//        private Semaphore semaphore;
//
//        Parking(int count){
//            semaphore = new Semaphore(count);
//        }
//
//        public void park(){
//            try {
//                //获取信号量
//                semaphore.acquire();
//                long time = (long) (Math.random() * 10);
//                System.out.println(Thread.currentThread().getName() + "进入停车场，停车" + time + "秒..." );
//                Thread.sleep(time);
//                System.out.println(Thread.currentThread().getName() + "开出停车场...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                semaphore.release();
//            }
//        }
//    }
//
//    static class Car extends Thread {
//        Parking parking ;
//
//        Car(Parking parking){
//            this.parking = parking;
//        }
//
//        @Override
//        public void run() {
//            parking.park();     //进入停车场
//        }
//    }
//
//    public static void main(String[] args){
//        Parking parking = new Parking(3);
//
//        for(int i = 0 ; i < 5 ; i++){
//            new Car(parking).start();
//        }
//    }
}


