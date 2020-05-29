package com.mady.common.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 9:40
 * @description 公共对象结果集
 */

@Data
public class ResultDTO<T> implements Serializable {

    private static final long serialVersionUID = -7362637181256337350L;
    /**
     * 返回是否正常
     */
    private boolean isSuccess;
    /**
     * 返回code
     */
    private String code;
    /**
     * 返回msg
     */
    private String msg;
    /**
     *返回参数对象
     */
    private T obj;

    public static <T> ResultDTO<T> newSuccess(){
        return newSuccess(null);
    }

    public static <T> ResultDTO<T> newSuccess(T data){
        ResultDTO resultDTO  = new ResultDTO();
        resultDTO.setSuccess(true);
        resultDTO.setCode(BaseResultEnum.SUCCESS.getCode());
        resultDTO.setMsg(BaseResultEnum.SUCCESS.getMsg());
        resultDTO.setObj(data);
        return resultDTO;
    }


    public static <T> ResultDTO<T> newFailure(String code, String msg){
        return newFailure(code, msg, null);
    }


    public static <T> ResultDTO<T> newFailure(String code, String msg, T data){
        ResultDTO resultDTO  = new ResultDTO();
        resultDTO.setSuccess(false);
        resultDTO.setCode(code);
        resultDTO.setMsg(msg);
        resultDTO.setObj(data);
        return resultDTO;
    }
}
