package com.me.crm.web.action;

import com.me.crm.container.ServiceProvider;
import com.me.crm.domain.SysUserGroup;
import com.me.crm.service.ISysUserGroupService;
import com.me.crm.web.form.SysUserGroupForm;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by DJ on 2017/4/11.
 */
@SuppressWarnings("serial")
public class SysUserGroupAction extends ActionSupport implements ModelDriven<SysUserGroupForm> {
    private SysUserGroupForm sysUserGroupForm = new SysUserGroupForm();

    public String save() throws IllegalAccessException, InvocationTargetException, NullPointerException {
        System.out.print("123456789");
        System.out.print("姓名" + sysUserGroupForm.getName());
        //实例化Po对象
        SysUserGroup sysUserGroup = new SysUserGroup();
        //将vo对象的值赋给po对象
        BeanUtils.copyProperties(sysUserGroup, sysUserGroupForm);
        //获取业务层对象
        ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ServiceProvider.getService(ISysUserGroupService.SERVICE_NAME);
        //调用业务层保存po对象
        sysUserGroupService.saveSysUserGroup(sysUserGroup);

        return null;
    }

    public SysUserGroupForm getModel() {
        return sysUserGroupForm;
    }
}
