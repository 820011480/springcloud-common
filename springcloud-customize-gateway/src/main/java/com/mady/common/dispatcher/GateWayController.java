package com.mady.common.dispatcher;

import com.mady.common.annotation.RateLimit;
import com.mady.common.common.ApiRequestDTO;
import com.mady.common.common.ApiResponseDTO;
import com.mady.common.common.ApiRouteDTO;
import com.mady.common.common.BaseResultEnum;
import com.mady.common.exception.BaseRuntimeException;
import com.mady.common.service.ServiceInterface;
import com.mady.common.utils.RouteToUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 9:36
 * @description 分发器(支持不同类型请求分发)
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class GateWayController {
    /**
     * 方法匹配规则  系统.应用.服务.类.方法
     */
    private static final String METHOD_PATTERN = "([a-zA-Z]+\\.){4}[a-zA-Z]+";

    @Autowired
    private InitServiceInterface initServiceInterface;



    @RequestMapping(value = "/gateWay.do")
    @RateLimit(key = "gateway")
    public ApiResponseDTO routeTo(@RequestBody ApiRequestDTO apiRequestDTO){
        try{
            //匹配方法
            String method = apiRequestDTO.getMethod();
            if(!Pattern.matches(METHOD_PATTERN, method)){
                log.error("GateWayController#routeTo error method format, apiRequestDTO = {}", apiRequestDTO);
                throw new BaseRuntimeException(BaseResultEnum.ERROR_METHOD_FORMAT);
            }
            //路由
            ApiRouteDTO apiRouteDTO = initServiceInterface.getMap().get("method");
//            if(ObjectUtils.isEmpty(serviceInterface)){
//                log.error("GateWayController#routeTo interface not exits, method = {}", method);
//                throw new BaseRuntimeException(BaseResultEnum.METHOD_NOT_EXITS);
//            }
            return RouteToUtils.routeTo(apiRouteDTO, apiRequestDTO.getBizContent());
        }catch(BaseRuntimeException e){
            log.error("GateWayController#routeTo system service error, apiRequestDTO = {}", apiRequestDTO);
            throw new BaseRuntimeException(e.getErrorCode(), e.getErrorMsg());
        }catch(Exception e1){
            log.error("GateWayController#routeTo system error, apiRequestDTO = {}", apiRequestDTO);
            throw new BaseRuntimeException(BaseResultEnum.SYSTEM_ERROR);

        }
    }


}
