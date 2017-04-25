package com.me.crm.dao.impl;

import com.me.crm.dao.ICompanyDao;
import com.me.crm.domain.Company;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/25.
 */
@Repository(ICompanyDao.SERVICE_NAME)
public class CompanyDapImpl extends CommonDaoImpl<Company> implements ICompanyDao {
}
