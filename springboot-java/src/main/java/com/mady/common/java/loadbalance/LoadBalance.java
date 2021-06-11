package com.mady.common.java.loadbalance;

import com.mady.common.java.domain.Server;

import java.util.List;

/**
 * @Author: mady
 * @Date: 2021/6/9 14:34
 * @Desc:
 */
public interface LoadBalance {

    /**
     * 服务列表负载均衡
     *
     * @param serverList
     * @param url
     * @return
     */
    Server selectServer(List<Server> serverList, String url);
}
