package com.mady.common.aspect;

import com.mady.common.common.ApiResponseDTO;
import com.mady.common.exception.BaseRuntimeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 15:55
 * @description 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(value = BaseRuntimeException.class)
    public ApiResponseDTO handler(BaseRuntimeException e){
        ApiResponseDTO<String> apiResponseDTO = ApiResponseDTO.newFailure(e.getErrorCode(), e.getErrorMsg(), "");
        apiResponseDTO.setSign(apiResponseDTO.doSign("123456789"));
        return null;
    }
}
