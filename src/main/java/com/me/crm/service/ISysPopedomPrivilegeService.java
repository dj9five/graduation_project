package com.me.crm.service;

import com.me.crm.domain.SysPopedomPrivilege;

import java.util.List;

/**
 * Created by DJ on 2017/4/25.
 */
public interface ISysPopedomPrivilegeService {
    String SERVICE_NAME = "com.me.crm.service.impl.SysPopedomPrivilegeServiceImpl";

    void updatePopedom(String roleId, String[] popedomModules);

    List<SysPopedomPrivilege> findSysPrivilegesByRoleId(String roleId);

    List<SysPopedomPrivilege> findAllSysPopePrivileges();
}
