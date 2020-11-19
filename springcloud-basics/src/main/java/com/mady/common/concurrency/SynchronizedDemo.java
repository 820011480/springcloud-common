package com.mady.common.concurrency;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/23 17:12
 * @description Synchronized原理探索
 */
public class SynchronizedDemo {

    public static void main(String[] args) {

    }

    /**
     *同步方法
     */
    public synchronized void test1(){

    }

    /**
     * 同步代码快
     */
    public void test2(){
        synchronized (this){

        }
    }
}
