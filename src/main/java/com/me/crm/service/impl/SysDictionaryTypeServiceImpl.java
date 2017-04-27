package com.me.crm.service.impl;

import com.me.crm.dao.ISysDictionaryTypeDao;
import com.me.crm.domain.SysDictionaryType;
import com.me.crm.service.ISysDictionaryTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by DJ on 2017/4/27.
 */
@Transactional(readOnly = true)
@Service(ISysDictionaryTypeService.SERVICE_NAME)
public class SysDictionaryTypeServiceImpl implements ISysDictionaryTypeService {
    @Resource(name = ISysDictionaryTypeDao.service_name)
private ISysDictionaryTypeDao sysDictionaryTypeDao;
    public List<SysDictionaryType> findSysDictionaryTypeByCode(String code) {
        if (StringUtils.isNotBlank(code)){
            String whereHql=" and o.code=?";
            Object[] params={code};
            LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
            orderby.put("o.sort","asc");
            return sysDictionaryTypeDao.findObjectsByConditionWithNoPage(whereHql,params,orderby);
        }

        return null;
    }
}
