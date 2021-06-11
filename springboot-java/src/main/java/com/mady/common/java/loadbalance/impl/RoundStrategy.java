package com.mady.common.java.loadbalance.impl;

import com.mady.common.java.domain.Server;
import com.mady.common.java.loadbalance.AbstractLoadBalance;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: mady
 * @Date: 2021/6/10 9:09
 * @Desc: 平滑轮询策略
 *
 * 在一个集合S中包含元素S{A,B,C,D},各自配比权重分别为【1,2,3,4】
 * 则可以将列表展示位{A，B,B,C,C,C,D,D,D,D}，其中含义为各元素乘以各自权重配比即为元素个数。
 * 然后遍历元素集合，就是轮询策略。
 * 其中轮询的时候不够平滑。所有采用平滑轮询策略。
 * 平滑轮询，重要含义： 元素权重 weight, 当前权重：currentWeight
 * 当前权重初始值为 0，
 * 每次轮询计算当前权重： = （当前权重 + 元素权重） 找出最大的 即为本次轮询的集合。，然后最大的减去总权重则为当前权重。
 * 最大权重即为本次轮询的集合。
 * 集合：{A, B, C} 元素权重： {1, 5, 2}
 * 元素权重： {3, 5, 3}
 * 初始当前权重： {0, 0, 0}
 * 第一次轮询权重： B {1, 5, 2}
 *    当前权重： {1，5，2 }
 *    减去后的权重  {1, -3, 2}
 *第二次轮询权重： C {1, 5, 2}
 *    当前权重： {2，2，4}
 *    减去后的权重  {2, 2, -4}
 *第三次轮询权重： B {1, 5, 2}
 *    当前权重： {3，7，-2}
 *    减去后的权重  {3, -1, -2}
 *第四次轮询权重： A {1, 5, 2}
 *    当前权重： {4，4，0}
 *    减去后的权重  {-4, 4, 0}
 *第五次轮询权重： B {1, 5, 2}
 *    当前权重： {-3，9，2}
 *    减去后的权重  {-3, 1, 2}
 *第六次轮询权重： B {1, 5, 2}
 *    当前权重： {-2，6，4}
 *    减去后的权重  {-2, -2, 4}
 *第七次轮询权重： C {1, 5, 2}
 *    当前权重： {-1，3，6}
 *    减去后的权重  {-1, 3, -2}
 *第八次轮询权重： B {1, 5, 2}
 *    当前权重： {0，8，0}
 *    减去后的权重  {0, 0, 0}
 */
@Slf4j
public class RoundStrategy extends AbstractLoadBalance {

    /**
     * 平滑轮询策略
     * @param serverList
     * @param url
     * @return
     */
    @Override
    protected Server doSelect(List<Server> serverList, String url) {
        //获取总权重
        int totalWeight = 0;
        //获取总权重
        Map<String, Object> map = computeTotalWeight(serverList);
        totalWeight = (int) map.get(TOTAL_WEIGHT_KEY);
        //获取最大当前权重
        Server maxServer = serverList.stream().max(Comparator.comparingInt(Server::getCurrentWeight)).get();
        //计算相减后的权重
        int index = serverList.indexOf(maxServer);
        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            if(i == index) {
                //相减后的权重
                server.setMinusWeight(server.getTempWeight() - totalWeight);
            }else {
                server.setMinusWeight(server.getCurrentWeight());
            }
            //当前权重
            server.setCurrentWeight(server.getWeight() + server.getMinusWeight());
            server.setTempWeight(server.getCurrentWeight());
        }
//        System.out.println("serverList：" + serverList);
        return maxServer;
    }


    /**
     * 测试demo
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Server> serverList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Server server = new Server();
            server.setHost("127.0.0." + i);
            server.setPort(8080 + i);
            server.setWeight(1);
            if(i == 1){
                server.setWeight(5);
            }
            server.setTempWeight(server.getWeight());
            serverList.add(server);
        }
//        System.out.println("serverList：" + serverList);
        RoundStrategy roundStrategy = new RoundStrategy();
        Map<String, Integer> countMap = new HashMap<>(16);
        //统计每个服务器负载次数
        for (int i = 0; i < 14; i++) {
            int count = 1;
            Server server = roundStrategy.doSelect(serverList, "");
            System.out.println("server：" + server.getHost());
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
