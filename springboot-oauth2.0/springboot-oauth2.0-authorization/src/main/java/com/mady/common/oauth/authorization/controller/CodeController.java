package com.mady.common.oauth.authorization.controller;

import com.mady.common.oauth.authorization.utils.ValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 15:41
 * @description CodeController
 */
@RestController
@RequestMapping("/oauth2")
@Slf4j
public class CodeController {
    @Autowired
    private Map<String, ValidateCodeProcessor> map;

    @RequestMapping("/code")
    public String createCode(HttpServletRequest request, HttpServletResponse response){
        String type = request.getParameter("type");
        ValidateCodeProcessor validateCodeProcessor = map.get(type);
        if(validateCodeProcessor == null){
            throw new RuntimeException("生码类型为空");
        }
        String code = validateCodeProcessor.create(request);
        log.info("生码为：type：{}， code:{}", type, code);
        return code;
    }
}
