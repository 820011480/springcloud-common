//package com.mady.common.gateway.config;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @author mady
// * @version 1.0.0
// * @date 2020/7/15 15:08
// * @description
// */
//
//public class MyBCryptPasswordEncoder extends BCryptPasswordEncoder {
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        String presentedPassword =passwordEncoder.encode(encodedPassword);
//        return passwordEncoder.matches(rawPassword, presentedPassword);
//    }
//}
