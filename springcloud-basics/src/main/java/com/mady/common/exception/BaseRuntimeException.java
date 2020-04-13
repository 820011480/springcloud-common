package com.mady.common.exception;

import com.mady.common.common.BaseResultEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 14:09
 * @description 业务异常
 */
@Data
@Slf4j
public class BaseRuntimeException extends RuntimeException {
    /**
     * 错误信息code
     */
    private String errorCode;

    /**
     * 错误信描述
     */
    private String errorMsg;

    public BaseRuntimeException(){};

    public BaseRuntimeException(BaseResultEnum baseResultEnum){
        this.errorCode = baseResultEnum.getCode();
        this.errorMsg = baseResultEnum.getMsg();
    }


    public BaseRuntimeException(String errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
