package com.me.crm.web.action;

import com.me.crm.container.ServiceProvider;
import com.me.crm.domain.SysOperateLog;
import com.me.crm.service.IOperateLogService;
import com.me.crm.web.form.OperateLogForm;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * Created by DJ on 2017/5/14.
 */
public class operateLogAction extends BaseAction implements ModelDriven<OperateLogForm> {
    private OperateLogForm operateLogForm;
   private IOperateLogService operateLogService=
           (IOperateLogService) ServiceProvider.getService(IOperateLogService.SERVICE_NAME);
    public String list() {
        List<SysOperateLog> sysOperateLogs=operateLogService.findOperateLog();
        request.setAttribute("operateLog",sysOperateLogs);
        return "list";
    }

    public OperateLogForm getModel() {
        return operateLogForm;
    }
}
