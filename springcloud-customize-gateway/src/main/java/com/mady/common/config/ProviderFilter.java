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
 * @date 2020/12/4 15:24
 * @description 链路id测试
 */

@Activate(group = "provider")
public class ProviderFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        TraceUtils.getRpcTraceId();
        return invoker.invoke(invocation);
    }
}