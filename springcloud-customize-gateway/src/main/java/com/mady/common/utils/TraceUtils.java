package com.mady.common.utils;

import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/4 14:04
 * @description 使用日志自带的MDC工具完成链路追踪
 */
public class TraceUtils {
    /**
     * MDC trace_id
     */
    private static final String MDC_TRACE_ID = "MDC_TRADE_ID";
    /**
     * 系统trace_id 标识
     */
    private static final String TRACE_ID = "trace_id";

    /**
     * http使用head头存储
     */
    public static void getHttpTraceId(HttpServletRequest request, HttpServletResponse response){
        String traceId = request.getHeader(TRACE_ID);
        if (StringUtils.isEmpty(traceId)){
            traceId = newTraceId();
        }
        MDC.put(MDC_TRACE_ID, traceId);
    }


    public static void setRpcTraceId(){
        String traceId = RpcContext.getContext().getAttachment(TRACE_ID);
        if (StringUtils.isEmpty(traceId)){
            traceId = newTraceId();
        }
        RpcContext.getContext().setAttachment(TRACE_ID, traceId);
    }

    public static void getRpcTraceId(){
        //防止取到上一次的trace_id
        clearTraceId(TRACE_ID);
        String traceId = RpcContext.getContext().getAttachment(TRACE_ID);
        if (StringUtils.isEmpty(traceId)){
            traceId = newTraceId();
        }
        MDC.put(MDC_TRACE_ID, traceId);
    }

    /**
     * 创建新的trace_id
     * @return
     */
    public static String newTraceId(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 移除指定的trace_id
     */
    public static void clearTraceId(String traceId){
        MDC.remove(traceId);
    }
}
