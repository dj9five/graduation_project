package com.me.crm.web.action;

import com.me.crm.container.ServiceProvider;
import com.me.crm.service.ICompanyService;

/**
 * Created by DJ on 2017/4/25.
 */
public class CompanyAction extends BaseAction {
    //获取客户的业务层
    private ICompanyService companyService=
            (ICompanyService) ServiceProvider.getService(ICompanyService.SERVICE_NAME);
    public String list(){
        return "list";
    }
    /*显示客户添加页面*/
    public String add(){
        //处理客户编码
        String code=companyService.getCompanyCodeByTabName("c_company");
        return "add";
    }
}
