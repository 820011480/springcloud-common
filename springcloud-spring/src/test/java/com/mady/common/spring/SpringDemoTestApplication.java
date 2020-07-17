package com.mady.common.spring;

import com.mady.common.spring.domain.User;
import com.mady.common.spring.service.IndexService;
import com.mady.common.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionStoreException;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/6/3 13:26
 * @description
 */
@RunWith(SpringRunner.class)
//启动Spring
@SpringBootTest
@Slf4j
public class SpringDemoTestApplication {

    @Autowired
    private IndexService indexService;

    @Autowired
    private UserService userService;

    /**
     * 验证spring循环注入（spring在默认单例的情况下是支持循环引用的）
     * 1. IndexService中注入UserService，UserService中注入IndexService，启动成功
     * 2.
     *
     *
     *
     *
     *
     */


//    @Test
//    public void userService() throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//
////        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringDemoApplication.class);
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        userService.getIndexService();
//        indexService.getUserService();
//        countDownLatch.await();
//    }


    @Test
    public void beanFactory() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
        countDownLatch.await();
    }


}
