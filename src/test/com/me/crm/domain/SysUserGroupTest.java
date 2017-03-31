package com.me.crm.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


/**
 * Created by DJ on 2017/3/30.
 */
public class SysUserGroupTest {
    @Test
    public void testHibernateConf() {
        Configuration configuration;
        configuration = new Configuration().configure();
        SessionFactory sf=configuration.buildSessionFactory();
        Session s=sf.openSession();
        Transaction tx=s.beginTransaction();
        SysUserGroup sysUserGroup=new SysUserGroup();
        sysUserGroup.setName("交付部");
        sysUserGroup.setPrincipal("段捷");
        sysUserGroup.setIncumbent("开发");
        sysUserGroup.setRemark("星杰项目");
        s.save(sysUserGroup);
        tx.commit();
        s.close();
    }


}