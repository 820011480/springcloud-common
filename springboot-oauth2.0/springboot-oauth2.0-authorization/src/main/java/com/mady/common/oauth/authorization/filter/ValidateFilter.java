package com.mady.common.oauth.authorization.filter;

import com.mady.common.oauth.authorization.utils.ValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/17 15:09
 * @description
 */
@Slf4j
@WebFilter()
@Component
public class ValidateFilter extends OncePerRequestFilter {

    private Map<String, String> urlMap = new HashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private Map<String, ValidateCodeProcessor> map;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        // 路径拦截
        urlMap.put("/oauth/sms", "sms");
        urlMap.put("/oauth/phone", "phone");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        if(!request.getRequestURI().equals("/oauth2/sms")){
            filterChain.doFilter(request, response);
            return;
        }
        String validateCodeType = request.getParameter("type");
        if (!StringUtils.isEmpty(validateCodeType)) {
            try {
                log.info("请求需要验证！验证请求：" + request.getRequestURI() + " 验证类型：" + validateCodeType);
                //验证
                ValidateCodeProcessor validateCodeProcessor = map.get(validateCodeType);
                if(validateCodeProcessor == null){
                    return;
                }
                validateCodeProcessor.validate(request);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
