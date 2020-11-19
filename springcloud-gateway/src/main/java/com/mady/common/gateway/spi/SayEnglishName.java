package com.mady.common.gateway.spi;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/10/16 14:27
 * @description
 */
public class SayEnglishName implements ISayName {
    @Override
    public String sayName(String name) {
        return "I say english name:" + name;
    }
}
