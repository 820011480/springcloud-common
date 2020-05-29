package com.mady.common.sign;//

import com.mady.common.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * RSA签名统一入口
 */

@Slf4j
public abstract class AbstractSignerWithRsa implements Signer {

    public AbstractSignerWithRsa() {}


    /**
     * map/json 转化为 加签 String
     * @param inputMap
     * @return
     */
    public static String getStrToSign(Map<String, String> inputMap) {
        if(ObjectUtils.isEmpty(inputMap)){
            throw new BaseRuntimeException("500", "加签参数为空");
        }
        if(inputMap.containsKey("sign")){
            inputMap.remove("sign");
        }
        LinkedHashMap linkedHashMap = inputMap instanceof LinkedHashMap ? (LinkedHashMap)inputMap : new LinkedHashMap(inputMap);
        StringBuilder stringBuilder = new StringBuilder();
        linkedHashMap.forEach((k, v) -> {
            stringBuilder.append(k).append("=").append(v == null ? "" : v).append("&");
        });
        return stringBuilder.substring(0, stringBuilder.length() -1);
    }
}
