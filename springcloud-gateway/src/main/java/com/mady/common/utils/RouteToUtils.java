package com.mady.common.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mady.common.annotation.GateWayRequest;
import com.mady.common.common.ApiRequestDTO;
import com.mady.common.common.ApiResponseDTO;
import com.mady.common.common.ApiRouteDTO;
import com.mady.common.common.BaseResultEnum;
import com.mady.common.exception.BaseRuntimeException;
import com.mady.common.service.impl.ProductServiceImpl;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/15 12:38
 * @description
 */
public class RouteToUtils {

    public static ApiResponseDTO routeTo(ApiRouteDTO apiRouteDTO,String bizContent) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object [] obj = null;
        Class<?>[] parameterTypes = apiRouteDTO.getMethod().getParameterTypes();
        if(ObjectUtils.isEmpty(bizContent)){
            return (ApiResponseDTO)apiRouteDTO.getMethod().invoke(apiRouteDTO.getClazz().newInstance(), null);
        }
//        Parameter[] parameters = apiRouteDTO.getParameters();
//        for (int i = 0; i < parameterTypes.length; i++) {
//            JsonParser parser = new JsonParser();
//            JsonObject asJsonObject = parser.parse(bizContent).getAsJsonObject();
//            String name = parameters[i].getName();
//            JsonElement jsonElement = asJsonObject.get(name);
//            if(jsonElement == null){
//                throw new BaseRuntimeException();
//            }
//            obj[i] = new Gson().fromJson(jsonElement, parameterTypes[i]);
//        }
        return (ApiResponseDTO)apiRouteDTO.getMethod().invoke(apiRouteDTO.getClazz().newInstance(), bizContent);
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<? extends ProductServiceImpl> clazz = new ProductServiceImpl().getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            //获取注解(包含私有)
            GateWayRequest annotation = method.getDeclaredAnnotation(GateWayRequest.class);
            //不存在跳过
            if (ObjectUtils.isEmpty(annotation)){
                continue;
            }
            Parameter[] parameters = method.getParameters();
            //初始化所有接口
            ApiRequestDTO apiRequestDTO = new ApiRequestDTO();
            Map<String, Object > map = new HashMap<>();
            map.put("bizContent", "123");
            Gson gson = new Gson();
            String bizContent = gson.toJson(map);
            apiRequestDTO.setBizContent(bizContent);
            ApiRouteDTO apiRouteDTO = new ApiRouteDTO();
            apiRouteDTO.setClazz(clazz);
            apiRouteDTO.setMethod(method);
            apiRouteDTO.setParameters(parameters);
            //转换:
            ApiResponseDTO apiResponseDTO = routeTo(apiRouteDTO, apiRequestDTO.getBizContent());
            System.out.println(apiResponseDTO);
        }
    }
}
