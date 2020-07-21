package com.mady.common.oauth.authorization.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/16 16:08
 * @description 授权服务配置
 * 作用是:
 * 1. 授权 认证
 * 2. 发放token
*/
@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisConnectionFactory factory;

    @Autowired
    private TokenEnhancer tokenEnhancer;

    /**
     * 配置授权服务器的安全信息，比如 ssl 配置、checktoken 是否允许访问，是否允许客户端的表单身份验证等。
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    /**
     * 配置客户端的 service，也就是应用怎么获取到客户端的信息，
     * 一般来说是从内存或者数据库中获取，已经提供了他们的默认实现，你也可以自定义。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /**
         * 内存方式配置
         */
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        builder.withClient("oauth2")
                // 设置她的密钥，加密后的
                .secret(passwordEncoder.encode("oauth2"))
                // 设置允许访问的资源 id
                .resourceIds("oauth2")
                // 授权的类型
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                // 可以授权的角色
                .authorities("ROLE_ADMIN", "ROLE_USER")
                // 授权的范围
                .scopes("all")
                // token 有效期
                .accessTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
                // 刷新 token 的有效期
                .refreshTokenValiditySeconds(Math.toIntExact(Duration.ofHours(1).getSeconds()))
                // 授权码模式的重定向地址
                .redirectUris("http://127.0.0.1:8082/login");
        /**
         * 数据库方式配置
         */
//        clients.withClientDetails(getClientDetailsServices());
    }


//    @Bean
//    public DataSource getDataSource(){
//        DataSource dataSource  = DataSourceBuilder.create().build();
//        return dataSource;
//    }

    @Bean
    public ClientDetailsService getClientDetailsServices(){
        //客户端配置信息
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置授权服务器各个端点的非安全功能，如令牌存储，令牌自定义，用户批准和授权类型。
     * 如果需要密码授权模式，需要提供 AuthenticationManager 的 bean。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        //使用内存模式存储Token
//        endpoints.tokenStore(getRedisTokenStore());
        //使用jwt模式
        endpoints.tokenStore(getRedisTokenStore())
        .accessTokenConverter(jwtAccessTokenConverter());

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(tokenEnhancer, jwtAccessTokenConverter()));

        endpoints.tokenStore(getRedisTokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager);


        /**
         * jwt 机密方式:
         * 对称加密: (MAC @link MacSigner) 即信息的发送方和接收方用一个密钥去加密和解密数据
         * 它的最大优势是加/解密速度快，适合于对大数据量进行加密(相比对称加密安全性较低)
         * 非对称加密:
         * 又称公钥密钥加密。非对称加密为数据的加密与解密提供了一个非常安全的方法，它使用了一对密钥，
         * 公钥（public key）和私钥（private key）。私钥只能由一方安全保管，不能外泄，而公钥则可以发给任何请求它的人。
         * 非对称加密使用这对密钥中的一个进行加密，而解密则需要另一个密钥。
         * 在 Spring security 之中的相应实现是 org.springframework.security.jwt.crypto.sign.RsaSigner
         */
    }

    @Bean
    @Lazy //使用Lazy模式 在使用时初始化
    public TokenStore getRedisTokenStore(){
        return new RedisTokenStore(factory);
    }

//    @Bean
//    public TokenStore jwtTokenStore() throws NoSuchAlgorithmException {
//
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() throws NoSuchAlgorithmException {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        int keyLength = 2048;
        if (keyLength < 0 || keyLength % 1024 != 0) {
            throw new IllegalArgumentException("rsa key length must be mod by 1024!");
        }
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(keyLength, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        converter.setKeyPair(keyPair);
        return converter;
    }
}
