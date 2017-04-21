package com.me.crm.domain;

import com.me.crm.container.ServiceProvider;
import com.me.crm.dao.ISysUserDao;
import com.me.crm.util.MD5keyBean;
import org.junit.Test;

/**
 * Created by DJ on 2017/4/21.
 */
public class TestSysUser {
    @Test
    public  void saveSysUser(){
                ISysUserDao sysUserDao=(ISysUserDao) ServiceProvider.getService(ISysUserDao.SERVICE_NAME);
            SysUser sysUser=new SysUser();
            sysUser.setName("admin");
            sysUser.setCnname("系统管理员");

            SysUserGroup  sysUserGroup=new SysUserGroup();
            sysUserGroup.setId(23);
            sysUser.setSysUserGroup(sysUserGroup);

            SysRole sysRole=new SysRole();
            sysRole.setId("4028a8ab5b7f6dff015b7f6e23860000");
            sysUser.setSysRole(sysRole);

            //密码
            MD5keyBean m = new MD5keyBean();
            String md5 = m.getkeyBeanofStr("123456");
            sysUser.setPassword(md5);

            sysUserDao.save(sysUser);

        }
    }

