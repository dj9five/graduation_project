package com.me.crm.dao;

import com.me.crm.domain.SysUser;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/21.
 */
@Repository(ISysUserDao.SERVICE_NAME)
public interface ISysUserDao extends ICommonDao<SysUser> {
    public final static String SERVICE_NAME = "com.me.crm.dao.impl.SysUserImpl";
}
