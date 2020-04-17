package com.mady.common.annotation;

import com.mady.common.config.DefaultGroup;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/17 10:24
 * @description
 */


@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredParam {
    /**
     * 是否必填
     * @return
     */
    boolean value() default  false;
    /**
     * 校验组
     */

    Class<DefaultGroup>[] group() default {};
}
