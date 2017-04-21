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

    public String update() throws InvocationTargetException, IllegalAccessException {
        //实例化po对象
        SysRole sysRole = new SysRole();
        //vo给po赋值
        BeanUtils.copyProperties(sysRole, sysRoleFrom);
        //调用权限组的业务层
        sysRoleService.updateSysRole(sysRole);
        return "listAction";
    }

    public String add() {
        return "add";
    }

    public String edit() throws InvocationTargetException, IllegalAccessException {
        //获取权限组id
        String id = request.getParameter("id");
        //根据权限组id查询权限组信息
        SysRole sysRole = sysRoleService.findSysRoleById(id);
        //放置权限组对象的值到模型驱动对象中
        BeanUtils.copyProperties(sysRoleFrom, sysRole);

        return "edit";
    }

    public String delete() throws InvocationTargetException, IllegalAccessException {
       //获取删除权限组的id
        String[] ids=request.getParameterValues("ids");
        if (ids!=null&&ids.length>0){
            //调用业务组的权限删除
            sysRoleService.deleteSysRoleById(ids);
            return "listAction";
        }
        return null;
    }

    public SysRoleFrom getModel() {
        return sysRoleFrom;
    }


}
