package com.mady.common.dispatcher;

import com.mady.common.fegin.GateWayFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/5/20 17:13
 * @description 测试consul 服务注册与发现
 */
@RequestMapping("/rest")
@RestController
public class RestTemplateController {
    public static final String KAFKA_SERVICE_ID = "kafka";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consul")
    public String getTestRestController(){
        return "test ok";
    }

    @GetMapping("/kafka")
    public String getKafkaServiceInfo(){
        String response = restTemplate.getForObject("http://" + KAFKA_SERVICE_ID + "/kafka/test", String.class);
        return response;
    }



    @Autowired
    private GateWayFeign gateWayFeign;


    @GetMapping("/testFeign")
    public String getFeignKafkaServiceInfo(){
       return gateWayFeign.getKafkaInfo();
    }
}
