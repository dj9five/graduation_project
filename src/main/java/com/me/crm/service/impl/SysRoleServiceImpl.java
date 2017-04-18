package com.me.crm.service.impl;

import com.me.bean.SysRoleSearch;
import com.me.crm.dao.ISysRoleDao;
import com.me.crm.domain.SysRole;
import com.me.crm.service.ISysRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by DJ on 2017/4/18.
 */
@Transactional(readOnly = true)
@Service(ISysRoleService.SERVICE_NAME)
public class SysRoleServiceImpl implements ISysRoleService {
    @Resource(name = ISysRoleDao.SERVICE_NAME)
    private ISysRoleDao sysRoleDao;
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveSysRole(SysRole sysRole) {
        sysRoleDao.save(sysRole);

    }

    public List<SysRole> findSysRoles(SysRoleSearch sysRoleSearch) {
        if (sysRoleSearch==null){
            throw new  RuntimeException("传递的参数为空");
        }
        String whereHql="";
        List paramList=new ArrayList();
        if (StringUtils.isNotBlank(sysRoleSearch.getName())){
            whereHql=" and o.name like ?";
            paramList.add("%"+sysRoleSearch.getName().trim()+"%");
        }
        Object[] params=paramList.toArray();
        LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
        orderby.put("o.id","asc");
        return sysRoleDao.findObjectsByConditionWithNoPage(whereHql,params,orderby);
    }
}
