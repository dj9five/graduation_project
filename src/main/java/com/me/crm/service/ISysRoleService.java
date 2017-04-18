package com.me.crm.service;

import com.me.crm.domain.SysRole;

/**
 * Created by DJ on 2017/4/18.
 */
public interface ISysRoleService {
    String SERVICE_NAME = "com.me.crm.service.impl.SysRoleServiceImpl";

    void saveSysRole(SysRole sysRole);
}
