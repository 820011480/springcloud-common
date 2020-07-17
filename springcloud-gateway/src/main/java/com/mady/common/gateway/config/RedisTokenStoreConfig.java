package com.mady.common.gateway.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.time.Duration;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/15 10:33
 * @description
 */
@Configuration
public class RedisTokenStoreConfig {


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore redisTokenStore(){
       return new RedisTokenStore(redisConnectionFactory);
    }

//    /**
//     * 获取缓存操作助手对象
//     *
//     * @return
//     */
//    @Bean("cacheRedisTemplate")
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//        //以下代码为将RedisTemplate的Value序列化方式由JdkSerializationRedisSerializer更换为Jackson2JsonRedisSerializer
//        //此种序列化方式结果清晰、容易阅读、存储字节少、速度快，所以推荐更换
//        Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }

//    /**
//     * 配置缓存管理器
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        ObjectMapper om = new ObjectMapper();
//        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        om.registerModule(new JavaTimeModule());
//        om.registerModule((new SimpleModule()));
//        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
//        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(om);
//        config = config.entryTtl(Duration.ofMinutes(5))
//                // 设置 key为string序列化
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
//                .disableCachingNullValues();
//        // 使用自定义的缓存配置初始化一个cacheManager
//        return RedisCacheManager
//                .builder(redisConnectionFactory)
//                .cacheDefaults(config)
//                .transactionAware()
//                .build();
//    }
//
//    /**
//     * json序列化
//     *
//     * @return
//     */
//    @Bean
//    public RedisSerializer<Object> jackson2JsonRedisSerializer() {
//        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        serializer.setObjectMapper(mapper);
//        return serializer;
//    }
//
//    /**
//     * 缓存的key是 包名+方法名+参数列表
//     */
//    @Bean
//    public KeyGenerator keyGenerator() {
//        return (target, method, objects) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName()).append("::" + method.getName() + ":");
//            for (Object obj : objects) {
//                sb.append(obj.toString());
//            }
//            return sb.toString();
//        };
//    }


}
