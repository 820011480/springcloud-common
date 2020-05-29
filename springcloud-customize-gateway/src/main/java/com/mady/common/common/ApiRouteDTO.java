package com.mady.common.common;

/**
 * @author mady
 * @version 1.0.0
 * @date 2020/4/15 12:35
 * @description api路由对象
 */

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Data
public class ApiRouteDTO implements Serializable {

    private static final long serialVersionUID = -8871650584186577717L;


    private Class clazz;

    private Method method;

    private Parameter[] parameters;
}
