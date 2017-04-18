package com.me.crm.web.action;

import com.me.bean.SysRoleSearch;
import com.me.crm.container.ServiceProvider;
import com.me.crm.domain.SysRole;
import com.me.crm.service.ISysRoleService;
import com.me.crm.web.form.SysRoleFrom;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by DJ on 2017/4/18.
 */
public class SysRoleAction extends BaseAction implements ModelDriven<SysRoleFrom> {
    private SysRoleFrom sysRoleFrom = new SysRoleFrom();
    //获取权限组的业务层对象
    private ISysRoleService sysRoleService = (ISysRoleService) ServiceProvider.getService(ISysRoleService.SERVICE_NAME);

    public String save() throws InvocationTargetException, IllegalAccessException {
        //实例化po对象
        SysRole sysRole = new SysRole();
        //vo给po赋值
        BeanUtils.copyProperties(sysRole, sysRoleFrom);
        //调用权限组的业务层
        sysRoleService.saveSysRole(sysRole);
        return "listAction";
    }

    /*
        查询权限组信息
         */
    public String list() {
        SysRoleSearch sysRoleSearch = new SysRoleSearch();
        sysRoleSearch.setName(sysRoleFrom.getName());
        List<SysRole> sysRoles = sysRoleService.findSysRoles(sysRoleSearch);
        request.setAttribute("sysRoles", sysRoles);
        return "list";
    }

    public String add() {
        return "add";
    }

    public SysRoleFrom getModel() {
        return sysRoleFrom;
    }


}
