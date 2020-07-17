package com.mady.common.spring.loader;


import org.springframework.core.io.Resource;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/7/8 10:50
 * @description
 */
public class DefaultResourceLoader implements ResourceLoader {

    private  Set<ProtocolResolver> protocolResolvers = new LinkedHashSet<>(4);

    @Override
    public String getResourceString(String location) {
        for (ProtocolResolver protocolResolver : protocolResolvers) {
            String resource = protocolResolver.resolve(location, this);

            if (resource != null) {
                System.out.println(resource);
                return resource;
            }
        }
        if (location != null){
          return location + ":test";
        }
        return "A";
    }

    @Override
    public Resource getResource(String location) {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }
}
