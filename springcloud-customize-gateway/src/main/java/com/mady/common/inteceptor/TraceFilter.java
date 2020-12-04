package com.mady.common.inteceptor;

import com.mady.common.utils.TraceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/12/4 15:27
 * @description
 */
@WebFilter(
    filterName = "traceFilter",
    urlPatterns = "/*"
)
@Component
@Slf4j
public class TraceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        //设置trace_id
        TraceUtils.getHttpTraceId(httpServletRequest, httpServletResponse);
        log.info("http set traceId");
        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
