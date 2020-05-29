package com.mady.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/5/22 11:06
 * @description kafka测试restTemplate
 */
@RequestMapping("/kafka")
@RestController
public class TestKafkaController {

    @RequestMapping("/test")
    public String getKafkaInfo(){
        return "test rest ok";
    }
}
