package com.me.crm.web.action;

import com.me.annotation.Limit;
import com.me.bean.SysUserGroupSearch;
import com.me.crm.container.ServiceProvider;
import com.me.crm.domain.SysUserGroup;
import com.me.crm.service.ISysUserGroupService;
import com.me.crm.util.DataType;
import com.me.crm.web.form.SysUserGroupForm;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by DJ on 2017/4/11.
 */
@SuppressWarnings("serial")
public class SysUserGroupAction extends BaseAction implements ModelDriven<SysUserGroupForm> {
    private ISysUserGroupService sysUserGroupService = (ISysUserGroupService) ServiceProvider.getService(ISysUserGroupService.SERVICE_NAME);
    private SysUserGroupForm sysUserGroupForm = new SysUserGroupForm();

    @Limit(module = "group", privilege = "save")
    public String save() throws IllegalAccessException, InvocationTargetException, NullPointerException {

        //实例化Po对象
        SysUserGroup sysUserGroup = new SysUserGroup();
        //将vo对象的值赋给po对象
        BeanUtils.copyProperties(sysUserGroup, sysUserGroupForm);
        //获取业务层对象

        //调用业务层保存po对象
        sysUserGroupService.saveSysUserGroup(sysUserGroup);

        return "listAction";
    }

    @Limit(module = "group", privilege = "update")
    public String update() throws IllegalAccessException, InvocationTargetException, NullPointerException {
        //实例化Po对象
        SysUserGroup sysUserGroup = new SysUserGroup();
        //将vo对象的值赋给po对象
        BeanUtils.copyProperties(sysUserGroup, sysUserGroupForm);
        //调用业务层保存po对象
        sysUserGroupService.updateSysUserGroup(sysUserGroup);

        return "listAction";
    }

    @Limit(module = "group", privilege = "list")
    public String list() {
        SysUserGroupSearch sysUserGroupSearch = new SysUserGroupSearch();
        sysUserGroupSearch.setName(sysUserGroupForm.getName());
        //调用业务层的方法查询
        List<SysUserGroup> sysUserGroups = sysUserGroupService.findSysUserGroups(sysUserGroupSearch);
        request.setAttribute("sysUserGroups", sysUserGroups);
        return "list";
    }

    @Limit(module = "group", privilege = "edit")
    public String edit() throws InvocationTargetException, IllegalAccessException {
        //获取部门id
        String sid = sysUserGroupForm.getId();
        if (StringUtils.isNotBlank(sid)) {
            Integer id = Integer.parseInt(sid.trim());

            //调用业务层的方法，通过部门id查询部门信息
            SysUserGroup sysUserGroup = sysUserGroupService.findSysUserGroupById(id);
            //处理部门编辑页面，显示要编辑的信息
            BeanUtils.copyProperties(sysUserGroupForm, sysUserGroup);
            return "edit";
        }
        return null;
    }

    //删除
    @Limit(module = "group", privilege = "delete")
    public String delete() {
        String[] sids = request.getParameterValues("ids");
        Integer ids[] = DataType.converterStringArray2IntegerArray(sids);
        if (ids != null) {
            sysUserGroupService.deleteSysUserGroupByIds(ids);
            return "listAction";
        }
        return null;
    }

    //新建
    @Limit(module = "group", privilege = "add")
    public String add() {
        return "add";
    }

    public SysUserGroupForm getModel() {
        return sysUserGroupForm;
    }


}
