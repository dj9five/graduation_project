package com.me.crm.dao;

import com.me.crm.domain.SysRole;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/18.
 */
@Repository(ISysRoleDao.SERVICE_NAME)
public interface ISysRoleDao extends ICommonDao<SysRole> {
    public final static String SERVICE_NAME = "com.me.crm.dao.impl.SysRoleDaoImpl";
}
