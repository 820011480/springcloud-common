package com.mady.common.java.utils;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/10/15 15:04
 * @description 校验工具类
 */
@Slf4j
public class ValidatorUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> List<String> valid(T obj, Class<?>... groups) {
        //入参为空返回
        if (obj == null) {
            return Collections.singletonList("请求参数为空");
        }
        //自定义校验条件
        if (groups == null) {
            groups = new Class<?>[]{Default.class};
        }
        //定义错误消息提示集合
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj, groups);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            List<String> errorList = new ArrayList<>();
            while (iterator.hasNext()) {
                errorList.add(iterator.next().getMessage());
            }
            return errorList;
        }
        return null;
    }
}
