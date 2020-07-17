package com.mady.common.gateway.config;

import com.mady.common.gateway.domain.UserInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/15 13:42
 * @description 本地用户信息缓存
 */


public class GlobalUserMap {

    static Map<String, UserInfo> map = Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * 添加用户信息
     *
     * @param userInfo
     * @return
     */
    public static int putUser(UserInfo userInfo) {
        if (map.put(userInfo.getUsername(), userInfo) != null) {
            return 1;
        }
        return 0;
    }


    /**
     * 删除用户信息
     * @param userInfo
     * @return
     */
    public static int delUser(UserInfo userInfo) {
        return map.remove(userInfo.getUsername()) == null ? 0 : 1;
    }


    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    public static int updateUser(UserInfo userInfo) {
        return map.replace(userInfo.getUsername(), userInfo) == null ? 0 : 1;
    }

    /**
     * 查询用户信息
     * @param userName
     * @return
     */
    public static UserInfo queryUser(String userName) {
        return map.get(userName);
    }


    /**
     * 查询所有信息
     * @return
     */
    public static List<UserInfo> queryUserList() {
        return new ArrayList<>(map.values());
    }
}
