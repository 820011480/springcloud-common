package com.mady.common.spring;

import com.mady.common.spring.config.ClassXmlApplicationContextImpl;
import com.mady.common.spring.domain.User;
import com.mady.common.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionOverrideException;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import static org.springframework.context.support.AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/6/3 13:26
 * @description
 */
@Slf4j
//@SpringBootApplication(scanBasePackages = "com.mady.common")
@Configuration
@ComponentScan(basePackages = {"com.mady.common"})
public class SpringDemoApplication {

    public static void main(String[] args) throws InterruptedException {


//        String[] specifiedProfiles = StringUtils.tokenizeToStringArray(
//                "hello,|world", ",:|");
//        for (String item : specifiedProfiles){
//            System.out.println(item);
//        }
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//          ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
////        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
////        ac.register(SpringDemoApplication.class);
////        ac.refresh();
////        UserService userService = ac.getBean("userService", UserService.class);
////        log.info("userService ：{}", userService);
////        countDownLatch.await();
//
////        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/*.xml");
////        ApplicationContext context= new ClassPathXmlApplicationContext("classpath:\\*.xml");
////          ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//
//        //创建默认工厂 ————（）
//
        CountDownLatch countDownLatch = new CountDownLatch(1);


        ClassPathXmlApplicationContext context = new ClassXmlApplicationContextImpl(null);
        context.setConfigLocation("classpath:/*.xml");
        context.refresh();

//        DelegatingMessageSource messageSource = context.getBean(MESSAGE_SOURCE_BEAN_NAME, DelegatingMessageSource.class);
//        String message = messageSource.getMessage("hello", new Object[]{"world"}, Locale.CHINA);
//        System.out.println(message);
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/*.xml");
        User userBean = context.getBean("userBean", User.class);
        log.info("userBean ：{}", userBean);
        countDownLatch.await();
    }
}
