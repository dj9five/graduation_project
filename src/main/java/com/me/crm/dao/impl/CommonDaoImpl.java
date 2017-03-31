package com.me.crm.dao.impl;

import com.me.crm.dao.ICommonDao;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * Created by DJ on 2017/3/30.
 */
public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T>{
    public void save(Object entity){
this.getHibernateTemplate().save(entity);
    }

}
