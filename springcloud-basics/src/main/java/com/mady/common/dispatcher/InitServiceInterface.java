package com.mady.common.dispatcher;

import com.mady.common.annotation.GateWayRequest;
import com.mady.common.service.ServiceInterface;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 14:19
 * @description 初始化服务接口 [服务启动监听注册所有spring服务]
 */
@Slf4j
@Data
@Component
public class InitServiceInterface implements CommandLineRunner {
    /**
     * 注spring容器
     */
    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, ServiceInterface> map = new HashMap<>();


    @Override
    public void run(String... args) throws Exception {
        Collection<ServiceInterface> values = applicationContext.getBeansOfType(ServiceInterface.class).values();
        log.info("InitServiceInterface#run 所有bean类型, map = {}", map);
        values.forEach(item -> {
            Method[] methods = item.getClass().getMethods();
            for (Method method : methods) {
                //获取注解(包含私有)
                GateWayRequest annotation = method.getDeclaredAnnotation(GateWayRequest.class);
                //不存在跳过
                if (ObjectUtils.isEmpty(annotation)){
                    continue;
                }
                String value = annotation.value();
                map.put(value, item);
            }
        });
    }
}
