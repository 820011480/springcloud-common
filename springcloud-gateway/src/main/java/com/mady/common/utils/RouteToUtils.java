package com.mady.common.utils;

import com.google.gson.Gson;
import com.mady.common.annotation.GateWayRequest;
import com.mady.common.common.ApiRequestDTO;
import com.mady.common.common.ApiResponseDTO;
import com.mady.common.common.ApiRouteDTO;
import com.mady.common.exception.BaseRuntimeException;
import com.mady.common.service.impl.ProductServiceImpl;
import com.mady.common.sign.Signer;
import com.mady.common.sign.SignerContainer;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/15 12:38
 * @description 路由工具类
 */
public class RouteToUtils {

    /**
     * 路由方法
     * @param apiRouteDTO
     * @param bizContent
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static ApiResponseDTO routeTo(ApiRouteDTO apiRouteDTO,String bizContent) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //获取方法参数
        Parameter[] parameters = apiRouteDTO.getParameters();
        if(ObjectUtils.isEmpty(parameters)){
            return (ApiResponseDTO)apiRouteDTO.getMethod().invoke(apiRouteDTO.getClazz().newInstance(), null);
        }
        Map<String, Class> mapParameters = new TreeMap<>();
        for(Parameter parameter : parameters){
            mapParameters.put(parameter.getName(), parameter.getType());
        }
        List<Object> objectList = new ArrayList<>();
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(bizContent, Map.class);
        //遍历参数列表[参数名称非参数类型]
        for(Parameter parameter : parameters){
            map.forEach((k, v) -> {
                if (!mapParameters.containsKey(k)) {
                    throw new BaseRuntimeException("500", String.format("%s参数不存在", k));
                }
                if(!ObjectUtils.nullSafeEquals(parameter.getName(), k)){
                    return;
                }
                Object o = gson.fromJson(v, mapParameters.get(k));
                List<String> validate = ValidationUtils.valid(o);
                if(ObjectUtils.isEmpty(validate)){
                    objectList.add(o);
                }
            });
        }
        return (ApiResponseDTO)apiRouteDTO.getMethod().invoke(apiRouteDTO.getClazz().newInstance(), objectList.toArray());
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Gson gson = new Gson();
        //初始化所有接口
        ApiRequestDTO apiRequestDTO = new ApiRequestDTO();
        apiRequestDTO.setAppId("route");
        apiRequestDTO.setCharset("UTF-8");
        apiRequestDTO.setMethod("scpp.route.gateway.product.add");
        apiRequestDTO.setNonce(UUID.randomUUID().toString().replace("-", ""));
        apiRequestDTO.setSignType("RSA2");
        apiRequestDTO.setTimestamp(System.currentTimeMillis());
        apiRequestDTO.setVersion("1.0.0");
        Map<String, String> map = new HashMap<>();
        RSAKeyPair rsaKeyPair = new RSAKeyPair();
        rsaKeyPair.setPrivateKey("213");
        rsaKeyPair.setPublicKey("123");
        map.put("rsaKeyPair", gson.toJson(rsaKeyPair));
        map.put("count", "1");
        apiRequestDTO.setBizContent(gson.toJson(map));
        String s = gson.toJson(apiRequestDTO);
        Map<String, String> signMap = gson.fromJson(s, Map.class);
        Signer signer = SignerContainer.getSigner(apiRequestDTO.getSignType());
        apiRequestDTO.setSign(signer.doSign(signMap, "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCR6szOqjUva6Fl077oS38gwHysCPO00jTniuycvREwkmpc/PQMaKHaHe5g0VRLW+CXluK4CkbnyLYwmBtsCavGGESrFGtcwO5AbfNrrng94JiWJa51rtv0suGd0qvGgXz0EVgrx9lvXk4ItrjIlxYdxPD1mOj5egqIRkLP6EpkMXrHJPECI5B9467b15DBSOjNcSXUPjaAWmKOzdTuLqSSeE5ZrSw5DANf2PYEzrgBQMoIDzhfXpOR6gkLwOx6tSc8jRuw0TD6ylLBBXlSs8tqKHVeEI2qRYWveNDi0tpfKdHWQjm5kp2NvLEu594ehpmrA/PoQv04+e0QWdK4w9m3AgMBAAECggEAMP78onB4x2GGomCFfA7NszuvIT0oSBFFfnIhnUfHG94uJOczM2922XyvbIcdnOhO89fQbSfWQ0IcGxkYLCDbMvs3I5MJse1+ktfYotHEwlJGKjSg7hc2+GWQaKQy1cbpJDMmvhyvIDw0S6y/Q2uoG3rjxjQAFrUqEDQ3fwRq5lVM4NuvqP1VDwFctGv6czATTVtBFf1AmLY7jJ3XaP0gDqrwfAOVYpjx1vg31XwLzcvncIWpBkfFaqoEGIGJXPPe89n7KQx/R7xg5IZbSHJnYqsY9LcxcUPs8QirokHWStNzaO/APqKFia98rTbaKffGYCMPe8l7H0Crz7UlaCMPIQKBgQDO+OmiJp29Y8auWkuR0sQmAZUWO5gVqZMux7R55BxKtWR0MR+wQ+ab4HXmJGHKBT7v2T86sJyWL7cWt+ynXeObi9D25BdX2FXybiBkt4MGpshOsm7wHj9Ut7PAC0k0l+04g0sNzoLeEBvosAyvHA4oogcaPTgtasvqk+gfsJK1XwKBgQC0e2uIgPVc845V/T8LM+qFuor+5lrO5Sud7q6n2vN24RFZahfhMAGXnQ0Jb3ICjcBR3DQ6UMaNsywwxLLcaH1edriSfRzF4/0DLY+gFC3oppbmtpx+trO7md8F9zuPJ9d7AOQ4DwuymUelPSJW5uaIuKfK9DQrNeFhkke2vVyiqQKBgEaZa4BvMbFPno8Mp+hm/5eDRKqfy2HgRR9Y6bwGToxBUbG8q/WBQ+F58F1/nYe+wFgqP8IySkeEoSxzsprokOIXEEGXTksdtTuLyyrv2hbjv0Hw5ZyshKfOPddNQjxZcoaramecQNGkpxB5NOpqZH4BvvB5X3dGjaZHq49buL97AoGARvy4WfNnDo1ZcabvEgf0eW7a1tFh7DilRDQqDCmJUzvJKCMbi13ncmu6QfjMngaFG22COWGmBvvvKKtlMl4C2ZHWnP+Kvqci0AoukKb/O5+ygMJYoVpZLTOjTeRlT7Q7A81g9k+sqS2yyWJXTFj7WdFPZftRPaGuqULw5AZtFikCgYBGCXa0Vxfx+5hy+b0ghn+3ziJEkCw9/xz70HwBR98tLJFpZ34NOVbUWGf2273ylcmZtCiIPMhHl1rpIUwOCFNzdY10g6pCcU6xaEfYnhBEC2X9QambpHxhN142KyVbYmNUeWiroKBAHkrrWHNYDHWv0dIyDgoV4FU4S1hqsoVpaA=="));
        System.out.println(gson.toJson(apiRequestDTO));

        ApiRouteDTO apiRouteDTO = new ApiRouteDTO();
        Class<ProductServiceImpl> productServiceClass = ProductServiceImpl.class;
        apiRouteDTO.setClazz(productServiceClass);
        Method method = productServiceClass.getMethods()[0];
        apiRouteDTO.setMethod(method);
        apiRouteDTO.setParameters(method.getParameters());
        ApiResponseDTO apiResponseDTO = routeTo(apiRouteDTO, apiRequestDTO.getBizContent());
        System.out.println(apiResponseDTO);
    }
}
