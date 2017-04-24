package com.me.crm.service;

import com.me.crm.domain.SysPopedom;

import java.util.List;

/**
 * Created by DJ on 2017/4/24.
 */
public interface ISysPopedomService  {
    String SERVICE_NAME = "com.me.crm.service.impl.SysPopedomServiceImpl ";

    List<SysPopedom> findAllSysPopedom();
}
