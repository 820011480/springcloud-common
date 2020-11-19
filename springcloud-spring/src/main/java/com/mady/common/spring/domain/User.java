package com.mady.common.spring.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/1 9:14
 * @description 用户对象
 */
@Data
@Slf4j
public class User {

    private String name;

    private Integer age;
}
