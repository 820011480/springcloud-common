//package com.mady.common.gateway.controller;
//
//import com.mady.common.gateway.config.AdminAuthenticationToken;
//import com.mady.common.gateway.config.GlobalUserMap;
//import com.mady.common.gateway.domain.UserInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author mady
// * @version 1.0.0
// * @date 2020/7/15 15:44
// * @description
// */
//@Slf4j
//public class AccountController {
//
//
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//
//    @GetMapping(value = "/loginIn")
//    public String loginByAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        long startTime = System.currentTimeMillis();
//        log.info("begin login ....");
//        UserInfo userInfo = GlobalUserMap.queryUser(username);
//        if(userInfo == null || password.equals(userInfo.getPassword())){
//            log.error("用户信息不匹配");
//            return "failed";
//        }
//        try {
//            AdminAuthenticationToken authRequest = new AdminAuthenticationToken(username,password);
//            Authentication authResult = this.authenticationManager.authenticate(authRequest);
//            long endTime = System.currentTimeMillis();
//            log.info("login successfully.");
//            log.info("AccountController.loginByAccount："+ (endTime - startTime) / 1000 + "秒");
//            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//            successHandler.onAuthenticationSuccess(request, response,authResult);
//            return "success";
//        } catch (AuthenticationException ex) {
//            log.error("login failed.", ex);
//            return "error";
//        }
//    }
//}
