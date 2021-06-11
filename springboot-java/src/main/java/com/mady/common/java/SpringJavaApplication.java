package com.mady.common.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/16 9:43
 * @description SpringBoot初始化启动类
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.mady.common"})
public class SpringJavaApplication {

    /**
     * 指定初始化类
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringJavaApplication.class, args);
    }
}
