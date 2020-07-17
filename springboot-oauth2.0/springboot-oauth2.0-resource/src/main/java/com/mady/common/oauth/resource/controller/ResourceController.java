package com.mady.common.oauth.resource.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 12:43
 * @description 受保护资源
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @RequestMapping("/user")
    public String getResource(Principal principal){
        return ResponseEntity.ok(principal).toString();
    }
}
