package com.me.crm.service;

import com.me.crm.domain.SysDictionaryType;

import java.util.List;

/**
 * Created by DJ on 2017/4/27.
 */
public interface ISysDictionaryTypeService {
    String SERVICE_NAME = "com.me.crm.service.impl.SysDictionaryTypeServiceImpl";


    List<SysDictionaryType> findSysDictionaryTypeByCode(String code);
}
