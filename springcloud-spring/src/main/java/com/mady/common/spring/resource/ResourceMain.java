package com.mady.common.spring.resource;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/8 10:31
 * @description
 */
public class ResourceMain {

    public static void main(String[] args) {
        Resource resource = new FileSystemResource("D:/opt/logs/open-api-2020-05-14-0.log");
        System.out.println(resource.exists());
    }
}
