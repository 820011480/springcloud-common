package com.mady.common.utils;

import com.mady.common.exception.BaseRuntimeException;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/16 14:16
 * @description 参数校验工具类
 */
public class ValidationUtils {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> List<String> valid(T args, Class<?> ... groups){
        Set<ConstraintViolation<T>> validate = validator.validate(args, groups);
        if(ObjectUtils.isEmpty(validate)){
            return new ArrayList<>();
        }
        throw new BaseRuntimeException("500001", validate.stream().findFirst().get().getMessage());
    }
}
