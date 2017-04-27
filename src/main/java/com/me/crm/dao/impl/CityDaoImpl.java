package com.me.crm.dao.impl;

import com.me.crm.dao.ICityDao;
import com.me.crm.domain.City;
import org.springframework.stereotype.Repository;

/**
 * Created by DJ on 2017/4/27.
 */
@Repository(ICityDao.SERVICE_NAME)
public class CityDaoImpl extends CommonDaoImpl<City> implements ICityDao {
}
