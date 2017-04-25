package com.me.crm.service;

/**
 * Created by DJ on 2017/4/26.
 */
public interface ICompanyService {
    String SERVICE_NAME = "com.me.crm.service.impl.CompanyServiceImpl";

    String getCompanyCodeByTabName(String tabName);
}
