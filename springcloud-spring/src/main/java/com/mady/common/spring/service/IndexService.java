package com.mady.common.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/2 10:36
 * @description IndexService 注入到Spring中
 */
@Slf4j
@Service
public class IndexService {

    public IndexService(){
       log.info("初始化 IndexService...");
    }

    @Autowired
    private UserService userService;

    public void getUserService(){
        log.info("IndexService 中获取 UserService:{}", userService);
    }
}
