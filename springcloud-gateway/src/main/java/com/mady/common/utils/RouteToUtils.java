package com.mady.common.utils;

import com.google.gson.Gson;
import com.mady.common.annotation.GateWayRequest;
import com.mady.common.common.ApiRequestDTO;
import com.mady.common.common.ApiResponseDTO;
import com.mady.common.common.ApiRouteDTO;
import com.mady.common.exception.BaseRuntimeException;
import com.mady.common.service.impl.ProductServiceImpl;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Class> mapParameters = new HashMap<>();
        for(Parameter parameter : parameters){
            mapParameters.put(parameter.getName(), parameter.getType());
        }
        Object[] obj = new Object[parameters.length];
        List<Object> objectList = new ArrayList<>();
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(bizContent, Map.class);
        //遍历参数列表[参数名称非参数类型]
        map.forEach((k, v) -> {
            if (!mapParameters.containsKey(k)) {
                throw new BaseRuntimeException("500", String.format("%参数不存在", k));
            }
            Object o = gson.fromJson(v, mapParameters.get(k));
            List<String> validate = ValidationUtils.valid(o);
            if(ObjectUtils.isEmpty(validate)){
                objectList.add(o);
            }
        });
        return (ApiResponseDTO)apiRouteDTO.getMethod().invoke(apiRouteDTO.getClazz().newInstance(), objectList.toArray());
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
