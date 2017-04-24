package com.me.crm.service.impl;

import com.me.crm.dao.ISysPopedomDao;
import com.me.crm.domain.SysPopedom;
import com.me.crm.service.ISysPopedomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by DJ on 2017/4/24.
 */
@Transactional(readOnly = true)
@Service(ISysPopedomService.SERVICE_NAME)
public class SysPopedomServiceImpl implements ISysPopedomService {
    @Resource(name = ISysPopedomDao.service_name)
    private ISysPopedomDao sysPopedomDao;
    public List<SysPopedom> findAllSysPopedom() {
        LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
        orderby.put("o.sort","asc");
        return sysPopedomDao.findObjectsByConditionWithNoPage(orderby);
    }
}
