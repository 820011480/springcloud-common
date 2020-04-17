package com.mady.common.config;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/17 11:31
 * @description 时间格式校验
 *
 * 1. 校验时间格式 【yyyy-MM-dd HH:MM:SS】
 * 2. 校验请求时间必须不能超过服务器5分钟
 */
public class DateTimeGroup implements DefaultGroup {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:MM:SS";

    /**
     * todo
     * @param dateTime
     */
    @Override
    public void validMode(String dateTime) {
    }
}
