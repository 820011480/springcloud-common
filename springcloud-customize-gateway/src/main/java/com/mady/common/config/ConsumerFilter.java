package com.mady.common.config;

import com.mady.common.utils.TraceUtils;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/4 16:21
 * @description
 */

@Activate(group = "consumer")
public class ConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        TraceUtils.setRpcTraceId();
        return invoker.invoke(invocation);
    }
}
