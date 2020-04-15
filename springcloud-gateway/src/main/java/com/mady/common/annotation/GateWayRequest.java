package com.mady.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 15:28
 * @description
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GateWayRequest {
    /**
     * 方法名称
     * @return
     */
    String value();
    /**
     * 是否验签
     */
    boolean isSign() default false;
    /**
     * 是否限流
     */
    boolean isLimit() default false;

}
