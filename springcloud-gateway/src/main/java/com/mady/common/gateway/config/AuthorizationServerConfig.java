//package com.mady.common.gateway.config;
//
//import org.apache.commons.lang.ArrayUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.util.ObjectUtils;
//
//import java.util.List;
//
///**
// * @author mady
// * @version 1.0.0
// * @date 2020/7/15 11:13
// * @description 配置认证服务器
// */
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private SecurityProperty securityProperty;
//    @Autowired
//    private TokenStore tokenStore;
//
//    /**
//     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore) // 配置存储token的方式(默认InMemoryTokenStore)
//                .authenticationManager(authenticationManager) // 密码模式，必须配置AuthenticationManager，不然不生效
//                .userDetailsService(userDetailsService); // 密码模式，这里得配置UserDetailsService
//        /*
//         * pathMapping用来配置端点URL链接，有两个参数，都将以 "/" 字符为开始的字符串
//         *
//         * defaultPath：这个端点URL的默认链接
//         *
//         * customPath：你要进行替代的URL链接
//         */
//        endpoints.pathMapping("/oauth/token", "/oauth/xwj");
//    }
//
//
//
//    /**
//     * 用来配置客户端详情服务(给谁发送令牌)
//     */
//    @Override
//    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
//        //将客户端的信息存储在内存中
//        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
//        List<OAuth2Property> list = securityProperty.getList();
//        if (!ObjectUtils.isEmpty(list)) {
//            for (OAuth2Property config : list) {
//                builder // 使用in-memory存储
//                        .withClient(config.getClintId()).secret(config.getClientSecret())
//                        .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds()) // 发出去的令牌有效时间(秒)
//                        .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token") // 该client允许的授权类型
//                        .scopes("all", "read", "write") // 允许的授权范围(如果是all，则请求中可以不要scope参数，否则必须加上scopes中配置的)
//                        .autoApprove(true); // 自动审核
//            }
//        }
//    }
//
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()")
//         .checkTokenAccess("isAuthenticated()");
//    }
//}
