package com.mady.common.oauth.authorization.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/16 14:57
 * @description
 */
@RestController
@Slf4j
@RequestMapping("/oauth")
public class ResourceController {


    @RequestMapping("/test")
    public String getResource(Principal principal){
        String result = ResponseEntity.ok(principal).toString();
        log.info("请求资源结果为:{}", result);
        return result;
    }
}
