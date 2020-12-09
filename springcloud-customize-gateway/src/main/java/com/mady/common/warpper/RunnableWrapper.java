package com.mady.common.warpper;

import com.mady.common.utils.TraceUtils;
import org.slf4j.MDC;

import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/9 11:44
 * @description
 */
public class RunnableWrapper implements Runnable {
    /**
     * 当前任务
     */
    private Runnable runnable;
    /**
     * 主线程的MDC域
     */
    private Map<String, String> context;

    public RunnableWrapper(Runnable runnable, Map<String, String> context) {
        this.runnable = runnable;
        this.context = context;
    }

    @Override
    public void run() {
        if (context == null) {
            MDC.clear();
        } else {
            MDC.setContextMap(context);
        }
        TraceUtils.setTraceIdIfAbsent();
        try {
             runnable.run();
        } finally {
            MDC.clear();
        }
    }
}
