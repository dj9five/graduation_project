package com.me.crm.service;

import com.me.crm.domain.SysOperateLog;

import java.util.List;

/**
 * Created by DJ on 2017/5/14.
 */
public interface IOperateLogService {
    String SERVICE_NAME = "com.me.crm.service.impl.OperateLogServiceImpl";

    List<SysOperateLog> findOperateLog();
}
