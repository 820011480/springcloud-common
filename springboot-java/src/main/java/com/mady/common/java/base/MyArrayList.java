package com.mady.common.java.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/28 9:42
 * @description
 */
public class MyArrayList  {

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            list.add(i);
//        }
//        Thread thread1 = new Thread(()->{
//            //线程1
//            list.forEach(item ->{
//                System.out.printf("当前线程为%s:输出语句为%d\n", Thread.currentThread().getName(),item);
//            });
//        });
//        thread1.start();
//
//        Thread thread2 = new Thread(()->{
//            //线程2
//            list.forEach(item ->{
//                if(5==item){
//                    list.remove(item);
//                }
//                System.out.printf("当前线程为%s:输出语句为%d\n", Thread.currentThread().getName(),item);
//            });
//        });
//        thread2.start();
//        Queue queue = new ArrayBlockingQueue<Integer>(10);
//        queue.add(1);
//        queue.add(2);
//        queue.add(3);
//        System.out.println(queue);
//        System.out.println(queue.peek());

//        LinkedList<Integer> linkedList  = new LinkedList();
//        linkedList.addFirst(null); //数据为空
//        linkedList.addFirst(null);
//        linkedList.add(1,null);
//        System.out.println(linkedList);
        //实时性集合测试
        Object[] objs = new Object[]{1,2,3};
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>(Arrays.asList(objs));
//        realTimeMethod(objs);
        noRealTimeMethod(list);
    }

    /**
     * 实时
     * @param objs
     */
    private static void realTimeMethod(Object[] objs) {
        Thread thread1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "当前线程读取到的objs:{" + Arrays.toString(objs) + "}");
            for (int i = 0; i < objs.length; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" +objs[i]);
            }
        });
        thread1.start();
        Thread thread2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "当前线程读取到的objs:{" + Arrays.toString(objs) + "}");
            objs[2] = 9;
        });
        thread2.start();
    }

    /**
     * 非实时
     * @param list
     */
    private static void noRealTimeMethod(CopyOnWriteArrayList<Object> list) {
        Thread thread1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "当前线程读取到的objs:{" + list + "}");
            for (int i = 0; i < list.size(); i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" +list.get(i));
            }
        });
        thread1.start();

        Thread thread2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "当前线程读取到的objs:{" + list + "}");
            list.set(2, 10);
        });
        thread2.start();
    }
}

