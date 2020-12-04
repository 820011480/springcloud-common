package com.mady.common.spring;

import com.mady.common.spring.service.ClassXmlApplicationContextImpl;
import com.mady.common.spring.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

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
