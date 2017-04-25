package com.me.crm.dao;

import com.me.crm.domain.Company;

/**
 * Created by DJ on 2017/4/25.
 */
public interface ICompanyDao extends ICommonDao<Company> {
    String SERVICE_NAME = "com.me.crm.dao.impl.CompanyDapImpl";

}
