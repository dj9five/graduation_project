package com.me.crm.service;

import com.me.crm.domain.SysUserGroup;

import java.util.List;

/**
 * Created by DJ on 2017/4/10.
 */
public interface ISysUserGroupService {
    String SERVICE_NAME = "com.me.crm.service.impl.SysUserGroupServiceImpl";

    void saveSysUserGroup(SysUserGroup sysUserGroup);

    List<SysUserGroup> findSysUserGroups(String name, String principal);
}
