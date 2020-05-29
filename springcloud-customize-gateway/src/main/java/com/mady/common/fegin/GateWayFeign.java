package com.mady.common.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/5/22 17:18
 * @description
 */
//kafka 服务名称
@FeignClient(value = "kafka")
public interface GateWayFeign {

    @RequestMapping(value = "/kafka/test",method = RequestMethod.GET)
    String getKafkaInfo();
}
