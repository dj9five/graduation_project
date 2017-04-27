package com.me.crm.service;

import com.me.crm.domain.Province;

import java.util.List;

/**
 * Created by DJ on 2017/4/27.
 */
public interface IProvinceService {
    String SERVICE_NAME = "com.me.crm.service.impl.ProvinceServiceImpl";

    List<Province> findAllProvince();

    Province findProvinceByName(String name);
}
