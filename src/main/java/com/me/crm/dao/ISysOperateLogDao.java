package com.me.crm.dao;

import com.me.crm.domain.SysOperateLog;

/**
 * Created by DJ on 2017/5/13.
 */
public interface ISysOperateLogDao extends ICommonDao<SysOperateLog> {
    String SERVICE_NAME = "com.me.crm.dao.impl.SysOperateLogDaoImpl";

}
