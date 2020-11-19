package com.mady.common.java.base;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/8/3 11:30
 * @description
 */
public class MathDemo {

    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<>(8, 0.75F);
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>(8, 0.75F);
//        hashMap.put("test","test");
//        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
//        System.out.println(entries);
        int i;
        for (i = 0; i < 30; i++) {
            linkedHashMap.put(i+"", i+"");
            hashMap.put(i+"", i+"");
            System.out.println(i);
        }
        System.out.println(linkedHashMap);
        System.out.println(hashMap);
    }
}
