package com.mady.common.java.loadbalance.impl;

import com.mady.common.java.domain.Server;
import com.mady.common.java.loadbalance.AbstractLoadBalance;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: mady
 * @Date: 2021/6/9 17:13
 * @Desc:
 */
@Slf4j
public class HashStrategy extends AbstractLoadBalance {

    /**
     * 节点列表
     */
    private LinkedList<Server> linkedList = new LinkedList<>();

    /**
     * 虚拟机点倍数
     */
    public static final int VIRTUAL_COUNT = 2;

    /**
     * 槽点总数 0-65535
     */
    public static final int SLOT_COUNT = 0xffff;

    /**
     * 槽点索引对应的节点列表
     */
    private Map<String, Server> map = new ConcurrentHashMap<>(16);

    public HashStrategy(List<Server> serverList) {
        initServerList(serverList);
    }



    private int hashCode(String key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    /**
     * 一致性算法
     *
     * @param serverList
     * @return
     */
    @Override
    protected Server doSelect(List<Server> serverList, String url) {
        int key = hashCode(url);
        key = key < 0 ? Math.abs(key) : key;
        int hash = key % SLOT_COUNT;
        int avgCount = SLOT_COUNT / linkedList.size();
        int index = 0;
        for (int i = 0; i < linkedList.size(); i++) {
            hash -= avgCount;
            if (hash <= 0) {
                index = i;
                break;
            }
        }
        Server server = linkedList.get(index);
        return server;
    }

    private void initServerList(List<Server> serverList) {
        //添加服务列表
        for (int i = 0; i < VIRTUAL_COUNT + 1; i++) {
            for (int j = 0; j < serverList.size(); j++) {
                Server server = serverList.get(j);
                //添加虚拟节点
                if (i > 0) {
                    server.setIsVirtualNode(true);
                    // 虚拟节点名称
                    server.setNodeName(server.getHost() + "_" + j);
                }
                linkedList.add(serverList.get(j));
            }
        }
//        //分配槽点
//        int firstNode = 0;
//        int lastNode;
//        int avgCount = SLOT_COUNT / linkedList.size();
//        List<Slot> list = new ArrayList<>();
//        for (int j = 0; j < linkedList.size(); j++) {
//            Server server = linkedList.get(j);
//            Slot slot = new Slot();
//            slot.setFirstNode(firstNode);
//            lastNode = firstNode + avgCount;
//            if (lastNode >= SLOT_COUNT) {
//                lastNode = SLOT_COUNT;
//            }
//            slot.setLastNode(lastNode);
//            slot.setHost(server.getHost());
//            //重置firstNode
//            firstNode = lastNode + 1;
//            list.add(slot);
//        }
        for (int i = 0; i < serverList.size(); i++) {
            Server server = serverList.get(i);
            String host = server.getHost();
            map.put(host, server);
        }
    }


    @Data
    public static class Slot {

        /**
         * 首节点
         */
        private Integer firstNode;

        /**
         * 首节点
         */
        private Integer lastNode;

        /**
         * 服务器信息
         *
         * @param key
         * @return
         */
        private String host;

    }

    public static void main(String[] args) {
        List<Server> serverList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Server server = new Server();
            server.setHost("127.0.0." + i);
            server.setPort(8080 + i);
            server.setWeight(1);
            if (i == 1) {
                server.setWeight(5);
            }
            server.setTempWeight(server.getWeight());
            serverList.add(server);
        }
//        System.out.println("serverList：" + serverList);
        HashStrategy hashStrategy = new HashStrategy(serverList);
        Map<String, Integer> countMap = new HashMap<>(16);
        //统计每个服务器负载次数
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                int count = 1;
                String url  = "ht" + i * new Random().nextInt(65535) + "tp://" + i;
                Server server = hashStrategy.doSelect(serverList, url);
                System.out.println("server：" + server.getHost() + "url：" + url);
                String host = server.getHost();
                if (countMap.containsKey(host)) {
                    //累计次数
                    count = countMap.get(host) + 1;
                }
                countMap.put(host, count);
            }
        }

        System.out.println(countMap);
    }
}
