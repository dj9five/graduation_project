package com.me.crm.service.impl;

import com.me.crm.dao.IProvinceDao;
import com.me.crm.domain.Province;
import com.me.crm.service.IProvinceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by DJ on 2017/4/27.
 */
@Transactional(readOnly = true)
@Service(IProvinceService.SERVICE_NAME)
public class ProvinceServiceImpl implements IProvinceService {
    @Resource(name = IProvinceDao.SERVICE_NAME)
    private IProvinceDao provinceDao;
    public List<Province> findAllProvince() {
        LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
        orderby.put("o.id","asc");
        return provinceDao.findObjectsByConditionWithNoPage(orderby);
    }

    public Province findProvinceByName(String name) {
        String whereHql="and o.name=?";
        Object[] params={name};
        List<Province>list=provinceDao.findObjectsByConditionWithNoPage(whereHql,params);
        if (list!=null&&list.size()==1){
            return list.get(0);
        }
        return null;
    }
}
