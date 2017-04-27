package com.me.crm.dao.impl;

import com.me.crm.dao.IProvinceDao;
import com.me.crm.domain.Province;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/27.
 */
@Repository(IProvinceDao.SERVICE_NAME)
public class ProvinceDaoImpl extends CommonDaoImpl<Province> implements IProvinceDao {
}
