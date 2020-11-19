package com.mady.common.java.validtor;

import com.mady.common.java.annotation.CustomDataTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/10/15 15:28
 * @description
 */
public class DataTimeValidator implements ConstraintValidator<CustomDataTime, String> {

    private CustomDataTime customDataTime;

    @Override
    public void initialize(CustomDataTime customDataTime) {
        this.customDataTime = customDataTime;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果 value 为空则不进行格式验证，为空验证可以使用 @NotBlank @NotNull @NotEmpty 等注解来进行控制，职责分离
        if (value == null) {
            return true;
        }
        String format = customDataTime.format();
        if (value.length() != format.length()) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            simpleDateFormat.parse(value);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
