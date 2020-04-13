package com.mady.common.common;

import com.mady.common.sign.Signer;
import com.mady.common.sign.SignerContainer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 9:38
 * @description 网关出参对象
 */
@Data
@Accessors(chain = true)
public class ApiResponseDTO<T> {
    /**
     * 请求 ID,无论调用接口成功与否，都会返回请求 ID。
     */
    private String requestId;
    /**
     * 重试标识
     */
    private Boolean retry;
    /**
     * 返回总响应码
     */
    private String resultCode;
    /**
     * 返回子响应码
     */
    private String subCode;
    /**
     * 返回子响应码
     */
    private String subMsg;
    /**
     * 返回对象
     */
    private T data;
    /**
     * 签名
     */
    private String sign;

    public static <T> ApiResponseDTO<T> newFailure(String code, String msg, T data){
      return newFailure(code, msg, data, false);
    }



    public static <T> ApiResponseDTO<T> newFailure(String code, String msg, T data, boolean retry){
        ApiResponseDTO apiResponseDTO  = new ApiResponseDTO();
        apiResponseDTO.setRequestId(UUID.randomUUID().toString().replace("-",""));
        apiResponseDTO.setRetry(retry);
        apiResponseDTO.setResultCode(ResultCodeEnum.FAILURE.getCode());
        apiResponseDTO.setSubCode(code);
        apiResponseDTO.setSubMsg(msg);
        apiResponseDTO.setData(data);
        return apiResponseDTO;
    }


    public String doSign(String privateKey){
        Map<String, String> param = new TreeMap();
        param.put("requestId", this.requestId);
        param.put("retry", this.retry.toString());
        param.put("resultCode", this.resultCode);
        param.put("subMsg", this.subMsg);
        param.put("data", String.valueOf(this.data));
        Signer signer = SignerContainer.getSigner("RSA2");
//        signer.doSign();
        return null;
    }
}
