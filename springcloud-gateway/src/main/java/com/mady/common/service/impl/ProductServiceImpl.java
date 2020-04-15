package com.mady.common.service.impl;

import com.mady.common.annotation.GateWayRequest;
import com.mady.common.common.ApiResponseDTO;
import com.mady.common.service.ServiceInterface;
import org.springframework.stereotype.Service;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 14:27
 * @description 产品服务实现类
 */
@Service
public class ProductServiceImpl implements ServiceInterface {

    @Override
    @GateWayRequest("scpp.api.common.product.add")
    public ApiResponseDTO routeTo(String bizContent) {
        return ApiResponseDTO.newFailure("500", "hello", "123");
    }
}