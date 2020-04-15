package com.mady.common.aspect;

import com.mady.common.common.ApiResponseDTO;
import com.mady.common.config.SecretProperties;
import com.mady.common.exception.BaseRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 15:55
 * @description 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private SecretProperties secretProperties;

    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = BaseRuntimeException.class)
    public ApiResponseDTO handler(BaseRuntimeException e){
        ApiResponseDTO<String> apiResponseDTO = ApiResponseDTO.newFailure(e.getErrorCode(), e.getErrorMsg(), "");
        String privateKey = secretProperties.getPrivateKey();
        apiResponseDTO.setSign(apiResponseDTO.doSign(apiResponseDTO, privateKey));
        return apiResponseDTO;
    }
}
