package com.mady.common.java.loadbalance;

import com.mady.common.java.domain.Server;
import com.mady.common.java.loadbalance.impl.RandomStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: mady
 * @Date: 2021/6/9 14:40
 * @Desc: 抽象的负载均衡实现
 */
@Slf4j
public abstract class AbstractLoadBalance implements LoadBalance {

    /**
     * 单机服务器数量
     */
    public static final int CLUSTER_NUMBER = 2;

    /**
     * 服务器权重参数配置集合
     */
    protected Map<String, Object> map = new ConcurrentHashMap<>(16);

    /**
     * 总权重标识
     */
    public static final String TOTAL_WEIGHT_KEY = "total_weight";

    /**
     * 是否相同权重标识
     */
    public static final String SAME_WEIGHT_KEY = "same_weight";

    /**
     * 初始动态权重标识
     */
    protected volatile boolean INIT_WEIGHT = true;

    /**
     * 动态权重计算
     */
    protected List<Server> dynamicWeight(List<Server> serverList){
//        //重新分配当前权重
//        int maxTempWeight = serverList.get(0).getTempWeight();
//        int index = 0;
//        for (int i = 0; i < serverList.size(); i++) {
//            Server server = serverList.get(i);
//            //上一次的当前权重
//            int currentWeight = server.getCurrentWeight();
//            int weight = server.getWeight();
//            int tempWeight = weight + currentWeight;
//            if(maxTempWeight < tempWeight){
//                maxTempWeight = tempWeight;
//                index = i;
//            }
//            //临时权重
//            server.setTempWeight(tempWeight);
//        }
//
//        for (int i = 0; i < serverList.size(); i++) {
//            Server server = serverList.get(i);
//            int tempWeight = server.getTempWeight();
//            if(i == index){
//                //最大的临时权重 - 总权重
//                server.setMinusWeight(tempWeight - totalWeight);
//            }else {
//                //则是临时权重
//                server.setMinusWeight(tempWeight);
//            }
//            server.setCurrentWeight(server.getWeight() + server.getMinusWeight());
//        }
        return serverList;
    }


    /**
     * 计算总权重
     * @param serverList  服务器列表
     * @return
     */
    protected  Map<String, Object> computeTotalWeight(List<Server> serverList) {
        int totalWeight = 0;
        boolean sameWeight = true;
        //计算总权重和权重一致标识
        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            if(server == null) {
                log.error("this server info is error, {}", serverList);
                throw new RuntimeException("this server info is error!");
            }
            int weight = server.getWeight();
            totalWeight += weight;
            //如果sameWeigh为true,则进行权重校验
            if(sameWeight && i > 0){
                //获取上一台服务器权重
                Server preServer = serverList.get(i - 1);
                int preWeight = preServer.getWeight();
                if(preWeight !=  weight){
                    sameWeight = false;
                }
            }
        }
        map.put(TOTAL_WEIGHT_KEY, totalWeight);
        map.put(SAME_WEIGHT_KEY, sameWeight);
        return map;
    }





    /**
     * 负载均衡方法
     *
     * @param serverList
     * @param url
     * @return
     */
    @Override
    public Server selectServer(List<Server> serverList, String url) {
        if (ObjectUtils.isEmpty(serverList)) {
            log.info("serverList is null, return null");
            return null;
        }
        //非集群，则只有一台服务器，返回服务器对象
        if (!isCluster(serverList)){
             return serverList.get(0);
        }
        return doSelect(serverList, url);
    }

    /**
     *  使用模板方法 实现具体负载均衡策略
     * @param serverList
     * @return
     */
    protected abstract Server doSelect(List<Server> serverList, String url);

    /**
     * 集群判断
     * @param serverList
     * @return
     */
    protected boolean isCluster(List<Server> serverList){
        return serverList.size() >= CLUSTER_NUMBER;
    }
}
