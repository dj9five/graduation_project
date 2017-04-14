package com.me.crm.dao.impl;

import com.me.crm.dao.ISysUserGroupDao;
import com.me.crm.domain.SysUserGroup;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/7.
 */
@Repository(ISysUserGroupDao.SERVICE_NAME)
public class SysUserGroupImpl extends CommonDaoImpl<SysUserGroup> implements ISysUserGroupDao {

}
