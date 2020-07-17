//package com.mady.common.gateway.config;
//
//import com.mady.common.gateway.domain.UserInfo;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author mady
// * @version 1.0.0
// * @date 2020/7/15 12:28
// * @description
// */
//@Service
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        UserInfo userInfo = GlobalUserMap.queryUser(userName);
//        if(userInfo == null){
//            throw new RuntimeException("用户信息不存在");
//        }
//        // 用户角色
//        List<? extends GrantedAuthority> authorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("ROLE_" + userInfo.getRoleId());
//
//        return new SocialUser(userName, userInfo.getPassword(), true, true, true, true, authorities);
//    }
//}
