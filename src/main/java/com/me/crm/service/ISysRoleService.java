package com.me.crm.service;

import com.me.bean.SysRoleSearch;
import com.me.crm.domain.SysRole;

import java.util.List;

/**
 * Created by DJ on 2017/4/18.
 */
public interface ISysRoleService {
    String SERVICE_NAME = "com.me.crm.service.impl.SysRoleServiceImpl";

    void saveSysRole(SysRole sysRole);

    List<SysRole> findSysRoles(SysRoleSearch sysRoleSearch);

    SysRole findSysRoleById(String id);

    void updateSysRole(SysRole sysRole);

    void deleteSysRoleById(String[] ids);
}
