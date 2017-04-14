package com.me.crm.util;

import java.lang.reflect.ParameterizedType;

/**
 * Created by DJ on 2017/4/11.
 */
public class GenericClass {
    public static Class getGenericClass(Class clazz) {
        ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
        //System.out.println("type  " + type);
        Class entityClass = (Class) type.getActualTypeArguments()[0];
        //System.out.println("entityClass  " + entityClass);
        return entityClass;
    }
}
