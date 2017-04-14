package com.me.crm.dao;

import com.me.crm.domain.SysUserGroup;

/**
 * Created by DJ on 2017/4/7.
 */
public interface ISysUserGroupDao extends ICommonDao<SysUserGroup> {
    String SERVICE_NAME = "com.me.crm.dao.impl.SysUserGroupDaoImpl";
}
