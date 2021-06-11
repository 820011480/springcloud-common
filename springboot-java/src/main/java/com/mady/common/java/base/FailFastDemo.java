package com.mady.common.java.base;

import net.minidev.json.JSONUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/24 11:23
 * @description 快速失败机制
 */
public class FailFastDemo {

    public List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        FailFastDemo failFastDemo = new FailFastDemo();
////        failFastDemo.addTest();
//        failFastDemo.addTest2();
//        System.out.println(failFastDemo.list);
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(4);
        list2.add(6);
        list2.add(5);
        list.retainAll(list2);
        System.out.println(list);
        Integer a = new Integer(128);
        OutputStream outputStream = new FileOutputStream("D://file.text");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectOutputStream.close();
        outputStream.close();
    }


    void addTest() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(0);
        list.add(0);
        this.list = list;
    }

    void addTest2() {
        List<Integer> list = this.list;
        list.add(0);
        list.add(0);
        list.add(0);
    }
}


class MyList extends ArrayList {

    @Override
    public String[] toArray() {
        return new String[]{};
    }
}