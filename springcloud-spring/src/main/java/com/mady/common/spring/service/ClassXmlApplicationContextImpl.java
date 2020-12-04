package com.mady.common.spring.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.lang.Nullable;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/9 10:14
 * @description
 */
public class ClassXmlApplicationContextImpl extends ClassPathXmlApplicationContext {

    public ClassXmlApplicationContextImpl() {
    }


    /**
     * 自定义ClassPathXmlApplicationContext 实现类 ClassXmlApplicationContextImpl
     * 实现自定义BeanFactory
     * @param applicationContext
     * @throws BeansException
     */
    public ClassXmlApplicationContextImpl(ApplicationContext applicationContext) throws BeansException {
        super(applicationContext);
        this.setAllowCircularReferences(true);
        this.setAllowBeanDefinitionOverriding(true);
    }


    public ClassXmlApplicationContextImpl(boolean  refresh, @Nullable ApplicationContext parent, String... configLocations) throws BeansException {
        super(configLocations, true, null);
    }

    public void setRefresh(boolean  refresh, @Nullable ApplicationContext parent, String... configLocations){

    }

    /**
     * 初始化系统参数 AbstractApplicationContext
     * 系统初始化校验 【如果配置了系统参数 没有初始化的化 那么提示MissingRequiredPropertiesException 异常】
     * getEnvironment().validateRequiredProperties();
     */
    @Override
    protected void initPropertySources() {
        //设置初始化参数
        getEnvironment().setRequiredProperties("Var");
    }
}
