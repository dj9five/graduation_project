package com.me.crm.service.impl;

import com.me.crm.dao.ICityDao;
import com.me.crm.domain.City;
import com.me.crm.service.ICityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by DJ on 2017/4/27.
 */
@Transactional(readOnly = true)
@Service(ICityService.SERVICE_NAME)
public class CityServiceImpl implements ICityService {
    @Resource(name = ICityDao.SERVICE_NAME)
    private ICityDao cityDao;
    public List<City> findCtiesByPid(Integer id) {
        if (id!=null){
        String whereHql=" and o.pid=?";
        Object[] params={id};
        LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
        orderby.put("o.id","asc");
        return cityDao.findObjectsByConditionWithNoPage(whereHql,params,orderby);
        }
        return null;
    }
}
