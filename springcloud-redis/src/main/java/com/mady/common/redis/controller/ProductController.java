package com.mady.common.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/6/3 13:31
 * @description 产品接口控制器
 * 防止超卖
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "sale", method = RequestMethod.GET)
    public String productSale(){
        Integer currentCount =(Integer)redisTemplate.opsForValue().get("count");
        //库存>0
        if(currentCount > 0 ){
            currentCount--;
            redisTemplate.opsForValue().set("count", currentCount);
            return "扣减成功,剩余库存" + currentCount;
        }
        return "库存不足，扣减失败";
    }
}
