package com.me.crm.service;

import com.me.bean.CompanySearch;
import com.me.crm.domain.Company;
import com.me.crm.domain.SysUser;

import java.util.List;

/**
 * Created by DJ on 2017/4/26.
 */
public interface ICompanyService {
    String SERVICE_NAME = "com.me.crm.service.impl.CompanyServiceImpl";

    String getCompanyCodeByTabName(String tabName);

    void saveCompany(SysUser curSysUser, Company company);

    List<Company> findCompanysCondition(SysUser curSysuser, CompanySearch companySearch);

    List<Company> findCompanysConditionSource(SysUser curSysuser, CompanySearch companySearch);

    Company findCompanyById(Integer id);

    void updateCompany(SysUser curSysUser, Company company);

    void deleteCompanyById(Integer[] ids);

    void addUpdateShareSetOne(String s_module, Integer id, Integer[] uids);


    List<Company> findCompanysConditionqianzai(SysUser curSysuser, CompanySearch companySearch);

    List<Company> findCompanysConditionzhongyao(SysUser curSysuser, CompanySearch companySearch);

    List<Company> findCompanysConditionzhengshi(SysUser curSysuser, CompanySearch companySearch);

    List<Company> findCompanysConditionwuxiao(SysUser curSysuser, CompanySearch companySearch);
}
