package com.mady.common.gateway.annotation;

import com.mady.common.gateway.config.DatabaseTypeCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/11/10 15:52
 * @description 数据源类型注解
 */
//放到类上和方法上
@Target({ ElementType.TYPE, ElementType.METHOD })
//运行时有用
@Retention(RetentionPolicy.RUNTIME)
//存在这个类是生效
@Conditional(DatabaseTypeCondition.class)
public @interface DatabaseType {

    String value();
}
