package com.me.crm.domain;

import com.me.crm.container.ServiceProvider;
import com.me.crm.dao.ICompanyDao;
import org.junit.Test;

/**
 * Created by DJ on 2017/4/25.
 */
public class TestCompany {
    @Test
    public void saveCompany(){
        ICompanyDao companyDao=(ICompanyDao) ServiceProvider.getService(ICompanyDao.SERVICE_NAME);
        Company c=new Company();
        c.setCode("xxxx");
        c.setName("xxxxx");
        SysUser sysUser=new SysUser();
        sysUser.setId(4);
        c.setSysUser(sysUser);
        c.setShareFlag('N');

        companyDao.save(c);

    }
}
