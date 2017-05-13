package com.me.crm.dao.impl;


import com.me.crm.dao.ISysOperateLogDao;
import com.me.crm.domain.SysOperateLog;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/5/13.
 */
@Repository(ISysOperateLogDao.SERVICE_NAME)
public class SysOperateLogDaoImpl extends CommonDaoImpl<SysOperateLog> implements ISysOperateLogDao {
}
