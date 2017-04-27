package com.me.crm.service;

import com.me.crm.domain.City;

import java.util.List;

/**
 * Created by DJ on 2017/4/27.
 */
public interface ICityService {
    String SERVICE_NAME = "com.me.crm.service.impl.CityServiceImpl";

    List<City> findCtiesByPid(Integer id);
}
