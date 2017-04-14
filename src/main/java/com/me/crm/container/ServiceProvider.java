package com.me.crm.container;

import org.apache.commons.lang.StringUtils;

/**
 * Created by DJ on 2017/4/11.
 */
public class ServiceProvider {
    private static ServiceProviderCore sc;

    static {
        sc = new ServiceProviderCore();
        sc.load("beans.xml");
    }

    public static Object getService(String beanName) {
        if (StringUtils.isBlank(beanName)) {
            throw new RuntimeException("您要访问的服务名不能为空");
        }
        Object bean = null;
        //如果spring容器中包含beanname
        if (sc.ctx.containsBean(beanName)) {
            bean = sc.ctx.getBean(beanName);
        }
        //如果spring容器中不包含beanname
        if (bean == null) {
            throw new RuntimeException("您要访问的服务名称[" + beanName + "]不存在");
        }
        return bean;
    }
}
