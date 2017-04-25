package com.me.crm.web.action;

import com.me.annotation.Limit;
import com.me.bean.SysRoleSearch;
import com.me.crm.container.ServiceProvider;
import com.me.crm.domain.SysPopedom;
import com.me.crm.domain.SysPopedomPrivilege;
import com.me.crm.domain.SysRole;
import com.me.crm.service.ISysPopedomPrivilegeService;
import com.me.crm.service.ISysPopedomService;
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
    private ISysRoleService sysRoleService =
            (ISysRoleService) ServiceProvider.getService(ISysRoleService.SERVICE_NAME);
    //获取操作功能的业务层
    private ISysPopedomService sysPopedomService =
            (ISysPopedomService) ServiceProvider.getService(ISysPopedomService.SERVICE_NAME);
    //获取操作权限表的业务层
    private ISysPopedomPrivilegeService sysPopedomPrivilegeService =
            (ISysPopedomPrivilegeService) ServiceProvider.getService(ISysPopedomPrivilegeService.SERVICE_NAME);

    @Limit(module = "role", privilege = "edit")
    public String updatePopedom() {
        //获取角色id
        String roleId = request.getParameter("roleId");
        SysRole sysRole = sysRoleService.findSysRoleById(roleId);
        request.setAttribute("sysRole", sysRole);
        //获取复选框的值
        String[] popedomModules = request.getParameterValues("popedomModule");
        sysPopedomPrivilegeService.updatePopedom(roleId, popedomModules);

        return "updatePopedom";
    }

    /*
    * 显示系统的所有操作功能*/
    @Limit(module="role",privilege="listPopedom")
    public String listPopedom() {
        //获取角色id
        String roleId = request.getParameter("roleId");
        SysRole sysRole = sysRoleService.findSysRoleById(roleId);
        request.setAttribute("sysRole", sysRole);
        //获取系统的所有功能
        List<SysPopedom> sysPopedoms = sysPopedomService.findAllSysPopedom();
        request.setAttribute("sysPopedoms", sysPopedoms);
        //查询权限组包含的的权限
        List<SysPopedomPrivilege> sysPopedomPrivileges = sysPopedomPrivilegeService.findSysPrivilegesByRoleId(roleId);
        request.setAttribute("sysPopedomPrivileges", sysPopedomPrivileges);
        return "listPopedom";
    }
    @Limit(module="role",privilege="save")
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
    @Limit(module="role",privilege="list")
    public String list() {
        SysRoleSearch sysRoleSearch = new SysRoleSearch();
        sysRoleSearch.setName(sysRoleFrom.getName());
        List<SysRole> sysRoles = sysRoleService.findSysRoles(sysRoleSearch);
        request.setAttribute("sysRoles", sysRoles);
        return "list";
    }
    @Limit(module="role",privilege="update")
    public String update() throws InvocationTargetException, IllegalAccessException {
        //实例化po对象
        SysRole sysRole = new SysRole();
        //vo给po赋值
        BeanUtils.copyProperties(sysRole, sysRoleFrom);
        //调用权限组的业务层
        sysRoleService.updateSysRole(sysRole);
        return "listAction";
    }
    @Limit(module="role",privilege="add")
    public String add() {
        return "add";
    }
    @Limit(module="role",privilege="edit")
    public String edit() throws InvocationTargetException, IllegalAccessException {
        //获取权限组id
        String id = request.getParameter("id");
        //根据权限组id查询权限组信息
        SysRole sysRole = sysRoleService.findSysRoleById(id);
        //放置权限组对象的值到模型驱动对象中
        BeanUtils.copyProperties(sysRoleFrom, sysRole);

        return "edit";
    }
    @Limit(module="role",privilege="delete")
    public String delete() throws InvocationTargetException, IllegalAccessException {
        //获取删除权限组的id
        String[] ids = request.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
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
