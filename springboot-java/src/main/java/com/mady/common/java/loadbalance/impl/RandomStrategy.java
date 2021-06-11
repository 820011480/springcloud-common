package com.mady.common.java.loadbalance.impl;

import com.mady.common.java.domain.Server;
import com.mady.common.java.loadbalance.AbstractLoadBalance;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Author: mady
 * @Date: 2021/6/9 14:58
 * @Desc: 随机负载均衡（加权重）
 * <p>
 * 设计思路：
 * 有一个集合S { A, B, C, D} 其中各项分配的权重为 N{1： 2 : 3 : 4}
 * 随机一个获取集合中的一个元素。
 * <p>
 * 将集合中元素按照权重比分别落到一个一维坐标上(从0开始)。例如
 * A [0,1] B [2, 3] C[4,6] D[7-10]
 */
@Slf4j
public class RandomStrategy extends AbstractLoadBalance {

    /**
     * 随机对象
     */
    private Random random = new Random();


    /**
     * 随机加权算法
     *
     * @param serverList
     * @return
     */
    @Override
    protected Server doSelect(List<Server> serverList, String url) {
        //获取总权重
        int totalWeight = 0;
        //权重一致标识，默认为true
        boolean sameWeight = true;
        //随机服务器索引
        int index = -1;
        Map<String, Object> map = computeTotalWeight(serverList);
        totalWeight = (int) map.get(TOTAL_WEIGHT_KEY);
        sameWeight = (boolean) map.get(SAME_WEIGHT_KEY);
        //权重一致，则不需要权重配比
        if (sameWeight) {
            index = random.nextInt(serverList.size());
        } else {
            //权重配比计算
            int offset = random.nextInt(totalWeight);
            for (int i = 0; i < serverList.size(); i++) {
                offset -= serverList.get(i).getWeight();
                if (offset <= 0) {
                    index = i;
                    break;
                }
            }
        }
        return serverList.get(index);
    }

    /**
     * 测试demo
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Server> serverList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            Server server = new Server();
            server.setHost("127.0.0." + i);
            server.setPort(8080 + i);
            server.setWeight(random.nextInt(5) + 1);
            serverList.add(server);
        }
        System.out.println("serverList：" + serverList);
        RandomStrategy randomStrategy = new RandomStrategy();
        Map<String, Integer> countMap = new HashMap<>(16);
        //统计每个服务器负载次数
        for (int i = 0; i < 100; i++) {
            int count = 1;
            Server server = randomStrategy.doSelect(serverList, "");
            String host = server.getHost();
            if (countMap.containsKey(host)) {
                //累计次数
                count = countMap.get(host) + 1;
            }
            countMap.put(host, count);
        }
        System.out.println(countMap);
    }
}
