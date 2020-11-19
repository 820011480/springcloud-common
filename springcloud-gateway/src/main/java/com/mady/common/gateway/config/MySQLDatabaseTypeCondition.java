package com.mady.common.gateway.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MySQLDatabaseTypeCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {

        //获取环境变量
        String enabledDBType = System.getProperty("dbType");
        return (enabledDBType != null && enabledDBType.equalsIgnoreCase("MYSQL"));
    }
}