package com.me.crm.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 该类的主要作用是加载beans.xml文件
 * Created by DJ on 2017/4/11.
 */
public class ServiceProviderCore {
    protected ApplicationContext ctx;

    public void load(String filename) {
        ctx = new ClassPathXmlApplicationContext(filename);
    }
}
