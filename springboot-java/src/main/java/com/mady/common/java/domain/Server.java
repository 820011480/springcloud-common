package com.mady.common.java.domain;

import lombok.Data;

/**
 * @Author: mady
 * @Date: 2021/6/9 14:38
 * @Desc: 服务器对象
 */
@Data
public class Server {
    /**
     * 主机ip
     */
    private String host;

    /**
     * 主机端口
     */
    private Integer port;

    /**
     * 元素权重
     */
    private Integer weight;

    /**
     * 当前权重
     */
    private Integer currentWeight;

    /**
     * 减去总权重之后的权重数值
     */
    private Integer minusWeight;

    /**
     * 临时权重和
     */
    private Integer tempWeight;

    /**
     * 是否为虚拟节点
     */
    private Boolean isVirtualNode;

    /**
     * 是否为虚拟节点
     */
    private String nodeName;


    public Server(){
        //当前权重初始化为元素权重
        currentWeight = 0;
        minusWeight = 0;
        tempWeight = 0;
    }

}
