package com.mady.common.service.dubbo.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/4 16:04
 * @description
 */
@Slf4j
@Service(version = "1.0.0")
public class OrderServiceImpl implements OrderService {

    private Map<String, String> orderMap = new HashMap<>();

    @Override
    public int save(String orderId) {
        log.info("OrderServiceImpl#save save order info:{}", orderId);
        String put = orderMap.put(orderId, orderId);
        return StringUtils.isEmpty(put) ? 0 : 1;
    }
}
