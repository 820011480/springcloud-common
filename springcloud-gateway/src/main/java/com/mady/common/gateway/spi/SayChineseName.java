package com.mady.common.gateway.spi;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/10/16 14:28
 * @description
 */
public class SayChineseName implements ISayName{
    @Override
    public String sayName(String name) {
        return "I say Chinese name:" + name;
    }
}
