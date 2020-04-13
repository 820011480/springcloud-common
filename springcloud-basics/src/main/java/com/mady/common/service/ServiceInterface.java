package com.mady.common.service;

import com.mady.common.common.ApiResponseDTO;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/13 14:23
 * @description
 */
public interface ServiceInterface {
    /**
     * 路由接口
     * @param bizContent
     * @return
     */
    ApiResponseDTO routeTo(String bizContent);
}
