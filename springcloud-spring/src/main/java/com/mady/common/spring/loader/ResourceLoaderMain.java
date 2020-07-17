package com.mady.common.spring.loader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/8 10:55
 * @description
 */
public class ResourceLoaderMain {
    private final static List<String> list = new ArrayList(16);

    public static void main(String[] args) {
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.add("d");

        for(String listTemp : list){
            System.out.println(listTemp);
        }
        System.out.println(list);
    }
}
