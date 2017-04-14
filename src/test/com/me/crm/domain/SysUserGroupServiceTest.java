package com.me.crm.domain;

import com.me.crm.container.ServiceProvider;
import com.me.crm.service.ISysUserGroupService;
import org.junit.Test;

import java.util.List;

/**
 * Created by DJ on 2017/4/11.
 */
public class SysUserGroupServiceTest {
    @Test
    public void testSave() {

        ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ServiceProvider.getService(ISysUserGroupService.SERVICE_NAME);
        SysUserGroup sysUserGroup = new SysUserGroup();
        sysUserGroup.setName("交付部");
        sysUserGroup.setPrincipal("段捷444");
        sysUserGroup.setIncumbent("开发");
        sysUserGroup.setRemark("星杰项目");
        if (sysUserGroupService != null) {
            sysUserGroupService.saveSysUserGroup(sysUserGroup);
        }

    }

    @Test
    public void findObjectsByConditionWithNoPage() {
        ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ServiceProvider.getService(ISysUserGroupService.SERVICE_NAME);
        //获取部门名称
        String name = "企业应用部";
        //获取部门负责人
        String principal = "段捷";
        List<SysUserGroup> list = sysUserGroupService.findSysUserGroups(name, principal);
    }
}
/*
public class SysUserGroupServiceTest {
    @Test
    public void testSave(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("beans. xml");
        ISysUserGroupService sysUserGroupService =(ISysUserGroupService)ctx.getBean(ISysUserGroupService.SERVICE_NAME);
        SysUserGroup sysUserGroup=new SysUserGroup();
        sysUserGroup.setName("交付部");
        sysUserGroup.setPrincipal("段捷222");
        sysUserGroup.setIncumbent("开发");
        sysUserGroup.setRemark("星杰项目");
        sysUserGroupService.saveSysUserGroup(sysUserGroup);
    }
}
 */