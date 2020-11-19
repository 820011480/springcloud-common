package com.mady.common.gateway.config;

import com.mady.common.gateway.annotation.DatabaseType;
import com.mady.common.gateway.domain.JdbcUserDAO;
import com.mady.common.gateway.domain.MongoUserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/11/10 11:17
 * @description
 */
@Configuration
public class AppConfig {


    @Bean
    @Profile("DEV")
    void publicDataSourceDevDataSource(){
        System.out.println("dev environment active");
    }

    @Bean
    @Profile("PROD")
    void publicDataSourceProdDataSource(){
        System.out.println("prod environment active");
    }


//    @Bean
//    @Conditional(MySQLDatabaseTypeCondition.class)
//    public UserDAO jdbcUserDAO(){
//        return new JdbcUserDAO();
//    }
//
//    @Bean
//    @Conditional(MongoDbTypePropertyCondition.class)
//    public UserDAO mongoUserDAO(){
//        return new MongoUserDAO();
//    }
//

    @Bean
    @DatabaseType("MYSQL")
    public UserDAO jdbcUserDAO(){
        return new JdbcUserDAO();
    }

    @Bean
    @DatabaseType("MONGO")
    public UserDAO mongoUserDAO(){
        return new MongoUserDAO();
    }
}

