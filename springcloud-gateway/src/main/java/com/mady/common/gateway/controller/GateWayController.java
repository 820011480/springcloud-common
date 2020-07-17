package com.mady.common.gateway.controller;

import com.mady.common.model.ApiRequestDTO;
import com.mady.common.model.ApiResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/14 17:28
 * @description
 */
@RestController
@RequestMapping("api")
public class GateWayController {


    @RequestMapping("/gateway.do")
    public ApiResponseDTO doHandler(@RequestBody ApiRequestDTO requestDTO){
        return null;
    }
}
