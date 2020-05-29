package com.mady.common.aspect;

import com.google.gson.Gson;
import com.mady.common.common.ApiResponseDTO;
import com.mady.common.config.ParamCheckProperties;
import com.mady.common.config.SecretProperties;
import com.mady.common.exception.BaseRuntimeException;
import com.mady.common.sign.Signer;
import com.mady.common.sign.SignerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLOutput;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 15:55
 * @description 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private SecretProperties secretProperties;

    @Autowired
    private ParamCheckProperties paramCheckProperties;


    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = BaseRuntimeException.class)
    public ApiResponseDTO handler(BaseRuntimeException e){
        ApiResponseDTO<String> apiResponseDTO = ApiResponseDTO.newFailure(e.getErrorCode(), e.getErrorMsg(), "");
        String privateKey = secretProperties.getPrivateKey();
        apiResponseDTO.setSign(apiResponseDTO.doSign(apiResponseDTO, privateKey));
        return apiResponseDTO;
    }

    /**
     * 验签
     * @param args
     */
    public static void main(String[] args) {
        String json ="{\n" +
                "    \"requestId\": \"0f1df59a6c6742d1a49c85dea7d9b6a9\",\n" +
                "    \"retry\": false,\n" +
                "    \"resultCode\": \"F\",\n" +
                "    \"subCode\": \"500\",\n" +
                "    \"subMsg\": \"系统异常\",\n" +
                "    \"data\": \"\",\n" +
                "    \"sign\": \"Pbmb6MtHEeBqAZcPjVQNOd5+pCOxcvfNslEJL7fOxTUsZLhAybvyn9+D4kwIL706D/8DF2+HQo9F8rDTaO8/gDBnw1Sg1u6rVaG5YoCzbNh9mx+/Rnx+2njncqavbVOvjUtLmmHFuvZvUx9PrgHvhvbgZvL9V1wc4bKmOqSEn97fGvN3pd/fqaxrkMZXsoRbh5Ah83VhuZKjskZQAv4VDIvlFNLC7oUFCq2KZNMydM4IiOhimq4VUDmUD2HC+VemFQq3ZgHlTEsioH9x1frpBkJ320wo8DWmsKJfkFp6vlNHXfMSCTI96n58zbvGJsJz4vCt2wMmkqCx+4YEVDj+vw==\"\n" +
                "}";
        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        Signer signer = SignerContainer.getSigner("RSA2");
        Boolean aBoolean = signer.doVerify(map, "Pbmb6MtHEeBqAZcPjVQNOd5+pCOxcvfNslEJL7fOxTUsZLhAybvyn9+D4kwIL706D/8DF2+HQo9F8rDTaO8/gDBnw1Sg1u6rVaG5YoCzbNh9mx+/Rnx+2njncqavbVOvjUtLmmHFuvZvUx9PrgHvhvbgZvL9V1wc4bKmOqSEn97fGvN3pd/fqaxrkMZXsoRbh5Ah83VhuZKjskZQAv4VDIvlFNLC7oUFCq2KZNMydM4IiOhimq4VUDmUD2HC+VemFQq3ZgHlTEsioH9x1frpBkJ320wo8DWmsKJfkFp6vlNHXfMSCTI96n58zbvGJsJz4vCt2wMmkqCx+4YEVDj+vw==", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkerMzqo1L2uhZdO+6Et/IMB8rAjztNI054rsnL0RMJJqXPz0DGih2h3uYNFUS1vgl5biuApG58i2MJgbbAmrxhhEqxRrXMDuQG3za654PeCYliWuda7b9LLhndKrxoF89BFYK8fZb15OCLa4yJcWHcTw9Zjo+XoKiEZCz+hKZDF6xyTxAiOQfeOu29eQwUjozXEl1D42gFpijs3U7i6kknhOWa0sOQwDX9j2BM64AUDKCA84X16TkeoJC8DserUnPI0bsNEw+spSwQV5UrPLaih1XhCNqkWFr3jQ4tLaXynR1kI5uZKdjbyxLufeHoaZqwPz6EL9OPntEFnSuMPZtwIDAQAB");
        System.out.println(aBoolean);
    }
}
