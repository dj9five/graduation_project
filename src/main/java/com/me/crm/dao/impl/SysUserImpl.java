package com.me.crm.dao.impl;

import com.me.crm.dao.ISysUserDao;
import com.me.crm.domain.SysUser;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/21.
 */
@Repository(ISysUserDao.SERVICE_NAME)
public class SysUserImpl extends CommonDaoImpl<SysUser> implements ISysUserDao {
}
