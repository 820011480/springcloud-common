package com.mady.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/10 9:40
 * @description
 */
public class TestMain {


    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(16, 0.75f, false);
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        map.get(3);
        System.out.println();
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
