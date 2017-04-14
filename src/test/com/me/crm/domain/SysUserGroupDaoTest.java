package com.me.crm.domain;

import com.me.crm.container.ServiceProvider;
import com.me.crm.dao.ISysUserGroupDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DJ on 2017/4/7.
 */
public class SysUserGroupDaoTest {

    @Test
    public void testSave() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ctx.getBean(ISysUserGroupDao.SERVICE_NAME);
        SysUserGroup sysUserGroup = new SysUserGroup();
        sysUserGroup.setName("交付部");
        sysUserGroup.setPrincipal("段捷123");
        sysUserGroup.setIncumbent("开发");
        sysUserGroup.setRemark("星杰项目");
        sysUserGroupDao.save(sysUserGroup);
    }

    @Test
    public void testUpdate() {
        ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ServiceProvider.getService(ISysUserGroupDao.SERVICE_NAME);
        SysUserGroup sysUserGroup = new SysUserGroup();
        sysUserGroup.setId(10);
        sysUserGroup.setName("企业");
        sysUserGroup.setPrincipal("段捷555");
        sysUserGroup.setIncumbent("应用");
        sysUserGroup.setRemark("赞同星杰项目");
        sysUserGroupDao.update(sysUserGroup);
    }

    @Test
    public void findSysUserGroupById() {
        ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ServiceProvider.getService(ISysUserGroupDao.SERVICE_NAME);
        Serializable id = 11;
        SysUserGroup sysUserGroup = sysUserGroupDao.findObjectById(id);
    }

    @Test
    public void deleteByIds() {
        ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ServiceProvider.getService(ISysUserGroupDao.SERVICE_NAME);
        Serializable[] ids = {17, 18, 19, 20, 21, 22};
        sysUserGroupDao.deleteById(ids);
    }

    @Test
    public void deleteCollections() {
        ISysUserGroupDao sysUserGroupDao = (ISysUserGroupDao) ServiceProvider.getService(ISysUserGroupDao.SERVICE_NAME);
        SysUserGroup s1 = new SysUserGroup();
        s1.setId(8);
        s1.setName("销售部");
        SysUserGroup s2 = new SysUserGroup();
        s2.setId(9);
        s2.setName("商务部");
        List<SysUserGroup> list = new ArrayList<SysUserGroup>();
        list.add(s1);
        list.add(s2);
        sysUserGroupDao.deleteALLObject(list);


    }

}

