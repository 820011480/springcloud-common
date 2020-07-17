package com.mady.common.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/2 10:35
 * @description UserService 注入到spring中
 */
@Slf4j
@Service
public class UserService {

    public UserService(){
        log.info("初始化 UserService...");
    }

    @Autowired
    private IndexService indexService;

    public void getIndexService(){
        log.info("UserService 中获取 IndexService:{}", indexService);
    }

}
