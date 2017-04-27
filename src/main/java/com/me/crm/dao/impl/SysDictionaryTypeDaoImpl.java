package com.me.crm.dao.impl;

import com.me.crm.dao.ISysDictionaryTypeDao;
import com.me.crm.domain.SysDictionaryType;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/27.
 */
@Repository(ISysDictionaryTypeDao.service_name)
public class SysDictionaryTypeDaoImpl extends CommonDaoImpl<SysDictionaryType>implements ISysDictionaryTypeDao{
}
