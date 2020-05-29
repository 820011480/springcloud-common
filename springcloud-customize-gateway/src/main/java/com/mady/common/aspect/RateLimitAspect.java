package com.mady.common.aspect;

import com.mady.common.annotation.RateLimit;
import com.mady.common.exception.BaseRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/23 15:30
 * @description
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RateLimitAspect {
    private final static String SEPARATOR = ":";
    private final static String REDIS_LIMIT_KEY_PREFIX = "limit:";
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisScript<Long> limitRedisScript;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.mady.common.annotation.RateLimit)")
    public void limit(){}

    @Around("limit()")
    public Object pointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        String key = method.getDeclaringClass().getName()+method.getName();
        if(doLimit(key, rateLimit)){
            throw new BaseRuntimeException("50000", "你被限流了");
        }
        return joinPoint.proceed();
    }

    private boolean doLimit(String defaultName, RateLimit rateLimit) throws UnknownHostException {
        String key = rateLimit.key();
        if (StringUtils.isEmpty(key)) {
            //默认key
            key = defaultName;
        }
        key = key + SEPARATOR + InetAddress.getLocalHost().getHostName();
        long max = rateLimit.max();
        long timeout = rateLimit.timeout();
        TimeUnit timeUnit = rateLimit.timeUnit();
        key = REDIS_LIMIT_KEY_PREFIX + key;
        long ttl = timeUnit.toMillis(timeout);
        long now = Instant.now().toEpochMilli();
        long expired = now - ttl;
        Long executeTimes = stringRedisTemplate.execute(limitRedisScript, Collections.singletonList(key), now + "", ttl + "", expired + "", max + "");
        if (executeTimes != null) {
            if (executeTimes == 0) {
                log.error("【{}】在单位时间 {} 毫秒内已达到访问上限，当前接口上限 {}", key, expired, max);
                return true;
            } else {
                log.info("【{}】在单位时间 {} 毫秒内访问 {} 次", key, expired, executeTimes);
                return false;
            }
        }
        return false;
    }
}
