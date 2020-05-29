package com.mady.common.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/24 11:01
 * @description
 */
@RestController
@RequestMapping("/oauth")
public class ResourceController {

    @RequestMapping(value = "/print/hello")
    public String printInfo(){
        return "hello ，test oauth2";
    }

    @RequestMapping(value = "/redirect/url")
    public String redirectUrl(){
        return "ok";
    }
}
