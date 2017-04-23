package com.me.crm.service;

import com.me.bean.SysUserGroupSearch;
import com.me.crm.domain.SysUserGroup;

import java.util.List;

/**
 * Created by DJ on 2017/4/10.
 */
public interface ISysUserGroupService {
    String SERVICE_NAME = "com.me.crm.service.impl.SysUserGroupServiceImpl";

    void saveSysUserGroup(SysUserGroup sysUserGroup);
//按条件查询，封装查询条件
    List<SysUserGroup> findSysUserGroups(SysUserGroupSearch sysUserGroupSearch);
    public SysUserGroup findSysUserGroupById(Integer id);

    void updateSysUserGroup(SysUserGroup sysUserGroup);
//通过id批量删除部门
    void deleteSysUserGroupByIds(Integer[] ids);

    List<SysUserGroup> findAllSysUserGroup();

    //List<SysUserGroup> findSysUserGroups(String name, String principal);
}
