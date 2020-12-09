package com.mady.common.utils;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/4 15:24
 * @description 链路id测试
 */
public class ThreadMdcUtil {

    public static void setTraceIdIfAbsent() {
       TraceUtils.setTraceIdIfAbsent();
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            //设置原MDC的content域
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}

