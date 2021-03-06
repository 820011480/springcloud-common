package com.mady.common.oauth.authorization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 15:41
 * @description SmsController
 */
@RestController
@RequestMapping("/oauth2")
public class SmsController {

    @RequestMapping("/sms")
    public String smsType(HttpServletRequest request, HttpServletResponse response){
        //获取类型
        return ResponseEntity.ok("sms").toString();
    }
}
