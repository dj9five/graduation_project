package com.me.crm.service.impl;

import com.me.crm.dao.ISysOperateLogDao;
import com.me.crm.domain.SysOperateLog;
import com.me.crm.service.IOperateLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by DJ on 2017/5/14.
 */
@Service(IOperateLogService.SERVICE_NAME)
public class OperateLogServiceImpl implements IOperateLogService {
    @Resource(name = ISysOperateLogDao.SERVICE_NAME)
    private ISysOperateLogDao sysOperateLogDao;
    public List<SysOperateLog> findOperateLog() {
        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("o.id","asc");
        return sysOperateLogDao.findObjectsByConditionWithNoPage(orderby);
    }
}
