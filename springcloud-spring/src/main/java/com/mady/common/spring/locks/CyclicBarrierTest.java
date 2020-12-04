package com.mady.common.spring.locks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/11/27 14:20
 * @description 同步辅助器 栅栏
 */
public class CyclicBarrierTest {

    private static final int THREAD_COUNT = 5;

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT, () -> {
        processExcelFinalResult();
    });

    /**
     * 处理excel最终结果
     */
    private static void processExcelFinalResult() {
        System.out.println(Thread.currentThread().getName() + "处理excel最终结果");
    }

    public static void main(String[] args)  {
        Thread.currentThread().interrupt();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                try {
                    handlerBeginExcelData();
                    cyclicBarrier.await();
                    handleringExcelData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            thread.setName("thread" + i);
            thread.start();
        }
    }

    /**
     * 开始处理excel数据
     */
    private static void handlerBeginExcelData() {
       System.out.println(Thread.currentThread().getName() +": 开始处理excel数据");
    }

    /**
     * 处理excel数据中
     */
    private static void handleringExcelData() {
        System.out.println(Thread.currentThread().getName() +": 处理excel数据中");
    }

    public static class SemaphoreTest {

        static class Parking{
            //信号量
            private Semaphore semaphore;

            Parking(int count){
                semaphore = new Semaphore(count);
            }

            public void park(){
                try {
                    //获取信号量
                    semaphore.acquire();
                    long time = (long) (Math.random() * 10);
                    System.out.println(Thread.currentThread().getName() + "进入停车场，停车" + time + "秒..." );
                    Thread.sleep(time *1000);
                    System.out.println(Thread.currentThread().getName() + "开出停车场...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    semaphore.release();
                }
            }
        }

        static class Car extends Thread {
            Parking parking ;

            Car(Parking parking){
                this.parking = parking;
            }

            @Override
            public void run() {
                parking.park();     //进入停车场
            }
        }

        public static void main(String[] args){
            Parking parking = new Parking(3);

            for(int i = 0 ; i < 5 ; i++){
                new Car(parking).start();
            }
        }
    }
}
